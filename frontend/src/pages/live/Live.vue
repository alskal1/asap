<template>
  <q-page padding>
    <div class="row">
      <div class="col-1" style="width: 50px"></div>
      <div class="col-8">
        <div class="col-6">
          <user-video :stream-manager="mainStreamManager"></user-video>
        </div>
        <div class="row">
          <div class="col-md-4">
            <q-avatar size="md" icon="account_circle"> </q-avatar>판매자
            <span> {{ title }} </span>
          </div>
          <!-- <span>시청자수</span> -->
          <div class="col-md-4 offset-md-3">
            <q-btn
              side="right"
              v-if="manage"
              color="red"
              @click="leaveSession()"
              >방송 종료</q-btn
            >
          </div>
        </div>
      </div>
      <div class="q-pa-sm q-ml-md col-3">
        <div class="col-2">
          <div>
            <q-card
              class="my-card"
              bordered
              style="max-height: 400px; width: 400px"
            >
              <q-card-actions>
                <q-btn flat color="dark" label="경매 정보" />
                <q-space />
                <q-btn
                  color="grey"
                  round
                  flat
                  dense
                  :icon="expanded ? 'keyboard_arrow_down' : 'keyboard_arrow_up'"
                  @click="expanded = !expanded"
                />
              </q-card-actions>
              <q-slide-transition>
                <div v-show="expanded">
                  <q-separator />
                  <q-card-section class="text-subitle2">
                    <!-- <auction-list></auction-list> -->
                    <div v-if="currentAuction">
                      상품명 : {{ currentAuction.productName }}
                    </div>
                    <div v-if="currentAuction">
                      시작 가격 : {{ currentAuction.startPrice }}
                    </div>
                    <div v-if="currentAuction">
                      원산지 : {{ currentAuction.origin }}
                    </div>
                  </q-card-section>
                </div>
              </q-slide-transition>
            </q-card>
          </div>
        </div>
        <q-card class="q-mt-sm q-mb-sm" style="width: 400px; padding: 10px">
          <span>현재 가격 : </span><span>500000원 </span>
          <span v-if="manage">
            <input style="width: 50px" />
            <q-btn outline style="color: green" label="가격내리기" />
          </span>
          <q-btn v-else outline style="color: green" label="낙찰하기" />
        </q-card>

        <q-dialog v-model="dialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold">방송이 종료되었습니다.</div>
                <q-btn @click="leaveSession()">OK</q-btn>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="cameraDialog" no-esc-dismiss no-backdrop-dismiss>
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold">
                  카메라 리소스가 사용중이거나 존재하지 않습니다.
                </div>
                <q-btn @click="leaveSession()">OK</q-btn>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <div>
          <q-card style="width: 400px; height: 400px; padding: 10px">
            <div name="chat">
              <div>채팅</div>
              <q-scroll-area style="height: 300px">
                <div id="chattings"></div>
              </q-scroll-area>
              <div>
                <q-input
                  rounded
                  outlined
                  v-model="message"
                  placeholder="메시지를 입력하세요."
                >
                  <q-btn
                    flat
                    round
                    color="primary"
                    icon="send"
                    @click="sendMessage()"
                  />
                </q-input>
              </div>
            </div>
          </q-card>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import { ref } from "vue";
import { api } from "boot/axios";
import { ovapi } from "boot/axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "components/live/UserVideo";
const OPENVIDU_SERVER_URL = `https://${global.location.hostname}:4443`;
const OPENVIDU_SERVER_SECRET = "ASAP";

export default {
  name: "Live",

  components: {
    UserVideo,
  },
  setup() {
    return {
      tab: ref("live"),
      visible: ref(true),
      expanded: ref(true),
    };
  },
  data() {
    return {
      title: "",
      messageList: "",
      position: "center",
      OV: undefined,
      dialog: false,
      auctionDialog: false,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      message: "",
      manage: false,
      sessionId: "",
      myUserName: "",
      description: "",
      videoNotFound: false,
      cameraDialog: false,
      currentAuction: {},
    };
  },
  created() {
    this.title = this.$route.query.title;
    this.description = this.$route.query.description;
    this.sessionId = this.$route.query.sessionId
      .replace("@", "-")
      .replace(".", "-");
    this.myUserName = this.$route.query.myUserName;
    console.log(this.sessionId);
    if (this.$route.query.status == "PUBLISHER") {
      this.$store.commit("user/setIsManage", true);
    }
  },
  mounted() {
    if (this.$route.query.status == "PUBLISHER") {
      this.manage = true;
      this.joinSession();
    } else {
      this.justJoinSession();
    }
  },
  computed: {
    curAuction() {
      return this.$store.state.moduleExample.auction;
    },
  },
  watch: {
    "$store.state.moduleExample.curAuction": function () {
      this.currentAuction = this.$store.state.moduleExample.curAuction;
    },
  },
  beforeRouteLeave: function (to, from, next) {
    if (!this.manage) {
      this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      next();
    } else {
      if (this.videoNotFound) {
        api
          .delete("/api/room/" + this.sessionId)
          .then(() => {
            this.session.disconnect();
            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;
            next();
            return;
          })
          .catch(function (error) {
            console.log(error);
          });
      } else {
        if (window.confirm("want to quit?")) {
          api
            .delete("/api/room/" + this.sessionId)
            .then(() => {
              this.session.disconnect();
              this.session = undefined;
              this.mainStreamManager = undefined;
              this.publisher = undefined;
              this.subscribers = [];
              this.OV = undefined;
            })
            .then(() => {
              next();
            })
            .catch(function (error) {
              console.log(error);
            });
        } else {
          next(false);
        }
      }
    }
  },

  methods: {
    addAuction() {
      this.auctionDialog = true;
    },

    fetchAuctionList: function () {
      this.auctionDialog = false;
      this.sendUpdateMessage();
    },

    joinSession() {
      this.OV = new OpenVidu();

      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:user-chat", (event) => {
        const data = JSON.parse(event.data);

        let newMessage = document.createElement("div");
        let sender = document.createElement("div");

        sender.innerText = data.sender;
        newMessage.innerText = data.message;

        if (data.sender == this.$store.state.user.userInfo.name) {
          newMessage.classList.add("message-orange");
          sender.classList.add("message-sender");
        } else {
          newMessage.classList.add("message-blue");
          sender.classList.add("message-receiver");
        }

        document.getElementById("chattings").appendChild(sender);
        document.getElementById("chattings").appendChild(newMessage);
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getToken(this.sessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            console.log("세션 아이디 : ", this.sessionId);
            const newData = {
              description: this.description,
              title: this.title,
              sessionId: this.sessionId.replace("@", "").replace(".", ""),
            };

            api
              .post("/api/room/", newData)
              .then((response) => {
                console.log(response);
              })
              .then(() => {
                this.$store.dispatch(
                  "moduleExample/selectAllAuctions",
                  this.sessionId
                );
              })
              .catch(function (error) {
                if (error.response.status == 409) {
                  console.log("방송이 리로드 되었습니다.");
                } else {
                  console.log(error);
                }
              });
          })
          .then(() => {
            const publisher = this.OV.initPublisher(
              undefined,
              {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,
                publishVideo: true,
                resolution: "640x360", // The resolution of your video
                frameRate: 30, // The frame rate of your video
                insertMode: "APPEND",
                mirror: false, // Whether to mirror your local video or not
              },
              (error) => {
                if (error) {
                  this.videoNotFound = true;
                  this.cameraDialog = true;
                } else {
                  console.log("Publisher successfully initialized");
                }
              }
            );

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
    },

    justJoinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:user-chat", (event) => {
        const data = JSON.parse(event.data);

        let newMessage = document.createElement("div");
        let sender = document.createElement("div");

        sender.innerText = data.sender;
        newMessage.innerText = data.message;

        if (data.sender == this.$store.state.user.userInfo.name) {
          newMessage.classList.add("message-orange");
          sender.classList.add("message-sender");
        } else {
          newMessage.classList.add("message-blue");
          sender.classList.add("message-receiver");
        }

        document.getElementById("chattings").appendChild(sender);
        document.getElementById("chattings").appendChild(newMessage);
      });

      this.session.on("signal:update-auction", (event) => {
        console.log("update list message received!!!");
        this.$store
          .dispatch("moduleExample/selectAllAuctions", this.sessionId)
          .catch((error) => {
            console.log(error);
          });
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }

        this.dialog = true;
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getSubToken(this.sessionId).then((token) => {
        this.session
          .connect(token)
          .then(() => {
            this.$store.dispatch(
              "moduleExample/selectAllAuctions",
              this.sessionId
            );
          })
          .then(() => {
            this.mainStreamManager = this.subscribers[0];
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
    },

    sendMessage() {
      if (this.session) {
        const data = {
          message: this.message,
          sender: this.$store.state.user.userInfo.name,
        };
        this.session
          .signal({
            data: JSON.stringify(data),
            to: [],
            type: "user-chat",
          })
          .then(() => {
            this.message = "";
            console.log("Message successfully sent!!!");
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
    sendUpdateMessage() {
      if (this.session) {
        this.session
          .signal({
            to: [],
            type: "update-auction",
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },

    sendPriceChangeMessage(newPrice) {
      if (this.session) {
        this.session
          .signal({
            data: newPrice,
            to: [],
            type: "update-auction",
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },

    leaveSession() {
      this.$router.push("/");
    },

    getSubToken(mySessionId) {
      return this.getSession(mySessionId).then((sessionId) =>
        this.createSubToken(sessionId)
      );
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    getSession(sessionId) {
      return new Promise((resolve, reject) => {
        ovapi
          .get("/openvidu/api/sessions/" + sessionId, {
            auth: {
              username: "OPENVIDUAPP",
              password: OPENVIDU_SERVER_SECRET,
            },
          })
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                global.location.assign(
                  `${OPENVIDU_SERVER_URL}/accept-certificate`
                );
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        ovapi
          .post(
            "/openvidu/api/sessions",
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                global.location.assign(
                  `${OPENVIDU_SERVER_URL}/accept-certificate`
                );
              }
              reject(error.response);
            }
          });
      });
    },

    createSubToken(sessionId) {
      return new Promise((resolve, reject) => {
        ovapi
          .post(
            `/openvidu/api/sessions/${sessionId}/connection`,
            {
              role: "SUBSCRIBER",
            },
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        ovapi
          .post(
            `/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400);

.container {
  width: 400px;
  padding: 10px;
}

user-video {
  width: 100%;
  height: 100%;
}

.message-blue {
  position: relative;
  margin-left: 20px;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #a8ddfd;
  width: 200px;
  height: 50px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
  border: 1px solid #97c6e3;
  border-radius: 10px;
}

.message-orange {
  position: relative;
  margin-bottom: 10px;
  margin-left: calc(100% - 240px);
  padding: 10px;
  background-color: #f8e896;
  width: 200px;
  height: 50px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
  border: 1px solid #dfd087;
  border-radius: 10px;
}
.message-sender {
  position: relative;
  margin-bottom: 5px;
  padding: 5px;
  text-align: right;
  font: 400 0.9em "Open Sans", sans-serif;
}
.message-receiver {
  position: relative;
  margin-bottom: 5px;
  padding: 5px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
}

.message-blue:after {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  border-top: 15px solid #a8ddfd;
  border-left: 15px solid transparent;
  border-right: 15px solid transparent;
  top: 0;
  left: -15px;
}

.message-blue:before {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  border-top: 17px solid #97c6e3;
  border-left: 16px solid transparent;
  border-right: 16px solid transparent;
  top: -1px;
  left: -17px;
}

.message-orange:after {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  border-bottom: 15px solid #f8e896;
  border-left: 15px solid transparent;
  border-right: 15px solid transparent;
  bottom: 0;
  right: -15px;
}

.message-orange:before {
  content: "";
  position: absolute;
  width: 0;
  height: 0;
  border-bottom: 17px solid #dfd087;
  border-left: 16px solid transparent;
  border-right: 16px solid transparent;
  bottom: -1px;
  right: -17px;
}
</style>
