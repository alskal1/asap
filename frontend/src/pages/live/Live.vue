<template>
  <q-page padding>
    <div class="row">
      <div class="q-pr-sm col-sm-8">
        <q-card class="col-6">
          <user-video :stream-manager="mainStreamManager"></user-video>
        </q-card>
        <q-card>
          <q-item>
            <q-item-section>
              <div class="text-h5 q-ml-lg">{{ title }}</div>
            </q-item-section>
            <space />
            <div class="text-h7 text-grey q-mr-md">
              시청자 수 :
              {{ session ? session.remoteConnections.size : 0 }}
            </div>
            <q-btn
              size="sm"
              side="right"
              v-if="manage"
              color="red-5"
              @click="leaveSession()"
              >방송 종료</q-btn
            >
          </q-item>

          <q-card
            class="fit bg-grey-2 my-card"
            bordered
            style="max-height: 400px; width: 500px"
          >
            <q-card-actions>
              <q-item>
                <q-item-section avatar>
                  <q-avatar
                    color="green"
                    text-color="white"
                    icon-right="colorize"
                    icon="account_circle"
                  >
                  </q-avatar>
                </q-item-section>

                <q-item-section>{{ sellerId }}</q-item-section>
              </q-item>

              <q-btn flat color="dark" />
              <q-space />
              <q-btn
                color="grey"
                round
                flat
                dense
                :icon="
                  expandedDetail ? 'keyboard_arrow_down' : 'keyboard_arrow_up'
                "
                @click="expandedDetail = !expandedDetail"
              />
            </q-card-actions>
            <q-slide-transition>
              <div v-show="expandedDetail">
                <q-separator class="q-ml-md q-mr-md" />
                <q-card-section class="text-subitle2 q-ml-md">
                  {{ description }}
                </q-card-section>
              </div>
            </q-slide-transition>
          </q-card>
        </q-card>
        <div class="q-pa-sm"></div>
      </div>
      <div class="col-sm-8 col-md-4">
        <div style="width: 500">
          <q-item-section class="q-gutter-sm">
            <q-card class="q-pa-sm">
              <q-card-actions>
                <q-btn disable flat color="dark" label="경매 정보" />
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
                  <q-card-section class="q-gutter-sm text-subitle2">
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

            <q-card class="q-pa-sm">
              <q-item>
                <q-item-section>
                  <div class="text-h7">
                    현재가격 : {{ currentAuction.currentPrice }}
                  </div>
                </q-item-section>
                <q-item-section v-if="manage">
                  <span
                    ><q-input
                      outlined
                      size="sm"
                      v-model="term"
                      dense
                      placeholder="하향단위"
                    />
                  </span>

                  <q-btn
                    class="q-qt-lg"
                    size="xs"
                    outline
                    style="color: green"
                    label="가격내리기"
                    @click="sendPriceChangeMessage(this.term)"
                  />
                </q-item-section>

                <q-btn
                  id="winBtn"
                  v-else
                  outline
                  size="xs"
                  style="color: green"
                  label="낙찰하기"
                  @click="winProduct()"
                />
              </q-item>
            </q-card>

            <q-card class="q-pa-sm" style="height: 400px">
              <div name="chat">
                <div class="q-pl-sm q-pt-sm">채팅</div>
                <q-scroll-area ref="chatScroll" style="height: 300px">
                  <div id="chattings"></div>
                </q-scroll-area>
                <div>
                  <q-input
                    rounded
                    outlined
                    v-model="message"
                    placeholder="메시지를 입력하세요."
                    @keyup.enter="sendMessage()"
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
          </q-item-section>
        </div>

        <q-dialog v-model="streamEndDialog" no-esc-dismiss no-backdrop-dismiss>
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  방송이 종료되었습니다.
                </div>
                <div class="text-right">
                  <q-btn @click="leaveSession()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="cameraDialog" no-esc-dismiss no-backdrop-dismiss>
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  카메라 리소스가 사용중이거나 존재하지 않습니다.
                </div>
                <div class="text-right">
                  <q-btn @click="leaveSession()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="selectAuctionDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  경매 정보를 선택해주세요.
                </div>
                <div class="text-right">
                  <q-btn @click="closeDialog()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="pointLessDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  포인트 충전 후 이용해주세요.
                </div>
                <div class="text-right">
                  <q-btn @click="closeDialog()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="noAuctionDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  진행중인 경매 정보가 없습니다.
                </div>
                <div class="text-right">
                  <q-btn @click="closeDialog()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="priceLessDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  현재가보다 낮은 가격을 설정할 수 없습니다.
                </div>
                <div class="text-right">
                  <q-btn @click="closeDialog()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="isSell">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">
                  해당 경매 물품이 판매되었습니다.
                </div>
                <div class="text-right">
                  <q-btn @click="refreshAuction()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="winDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold q-mb-md">낙찰 축하합니다!!!</div>
                <div class="text-right">
                  <q-btn @click="closeWinDialog()">OK</q-btn>
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <q-dialog v-model="auctionEndDialog">
          <q-card>
            <q-card-section class="row items-center no-wrap">
              <div>
                <div class="text-weight-bold">
                  해당 경매가 종료되었습니다. 다음 경매 진행을 기다려주세요.
                </div>
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>
      </div>
    </div>
  </q-page>
</template>

<script>
import { ref } from "vue";
import { Dialog } from "quasar";
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
      expanded: ref(true),
      expandedDetail: ref(false),
    };
  },
  data() {
    return {
      manage: false,
      OV: undefined,
      session: undefined,
      sessionId: "",
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      message: "",
      title: "",
      myUserName: "",
      description: "",
      currentAuction: {},
      currentPrice: "",
      term: "",
      isSell: false,
      winDialog: false,
      cameraDialog: false,
      videoNotFound: false,
      streamEndDialog: false,
      pointLessDialog: false,
      priceLessDialog: false,
      noAuctionDialog: false,
      auctionEndDialog: false,
      selectAuctionDialog: false,
      sellerId: "",
    };
  },

  created() {
    this.sellerId = this.$route.query.sessionId;
    this.title = this.$route.query.title;
    this.description = this.$route.query.description;
    this.sessionId = this.$route.query.sessionId
      .replace("@", "-")
      .replace(".", "-");
    this.myUserName = this.$route.query.myUserName;

    this.$store.dispatch("moduleExample/selectCurrentAuction", this.sessionId);

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
  watch: {
    "$store.state.moduleExample.curAuction.id": function () {
      if (
        this.currentAuction.id !=
          this.$store.state.moduleExample.curAuction.id &&
        this.session != undefined
      ) {
        this.currentAuction = this.$store.state.moduleExample.curAuction;
        if (this.manage) {
          if (this.currentAuction.id != undefined) {
            this.sendAuctionSelected(this.currentAuction);
          } else {
            this.sendNoAuction(this.currentAuction);
          }
        }
      }
    },
    "$store.state.moduleExample.curAuction.currentPrice": function () {
      if (
        this.currentAuction.id ==
          this.$store.state.moduleExample.curAuction.id &&
        this.session != undefined
      ) {
        this.currentAuction.currentPrice =
          this.$store.state.moduleExample.curAuction.currentPrice;
      }
    },
  },
  beforeRouteLeave: function (to, from, next) {
    if (!this.manage) {
      this.removeAllMessages();
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
        Dialog.create({
          title: "<span class='text-h6'>알림</span>",
          style: "shadow-box",
          html: true,
          message:
            "<span class='text-h7 text-weight-medium'>방송을 종료하시겠습니까?</span>",
          color: "green",
          cancel: "종료하기",
          ok: "취소",
          persistent: true,
        })
          .onOk(() => {
            next(false);
          })
          .onCancel(() => {
            api
              .delete("/api/room/" + this.sessionId)
              .then(() => {
                this.session.disconnect();
                this.session = undefined;
                this.mainStreamManager = undefined;
                this.publisher = undefined;
                this.subscribers = [];
                this.OV = undefined;
                this.removeAllMessages();
                this.$store.commit("moduleExample/selectCurrentAuction", {});
              })
              .then(() => {
                next();
              })
              .catch(function (error) {
                console.log(error);
              });
          });
      }
    }
  },

  methods: {
    joinSession() {
      this.OV = new OpenVidu();

      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:win-auction", (event) => {
        this.isSell = true;
        this.term = "";
        const nullAuction = {
          id: undefined,
          currentPrice: "",
          startPrice: "",
          productName: "",
          origin: "",
        };
        this.$store.dispatch("user/getUserInfo");
        this.setNoAuction(nullAuction);
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

        this.animateScroll();
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }

        // this.$store.commit("moduleExample/selectCurrentAuction", {});
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
              .then(() => {
                this.$store.dispatch(
                  "moduleExample/selectAllAuctions",
                  this.sessionId
                );
              })
              .catch(function (error) {
                console.error(error);
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

      window.addEventListener("beforeunload", (event) => {
        event.preventDefault();

        api
          .delete("/api/room/" + this.sessionId)
          .then(() => {
            this.session.disconnect();
            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;
            this.removeAllMessages();
            this.$store.commit("moduleExample/selectCurrentAuction", {});
          })
          .then(() => {
            next();
          })
          .catch(function (error) {
            console.log(error);
          });
        event.returnValue = "";
      });
    },

    justJoinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:win-auction", (event) => {
        if (this.$store.state.user.userInfo.email == event.data) {
          this.$store.dispatch("user/getUserInfo");
          this.winDialog = true;
          this.glad();
        } else {
          this.$store.dispatch("user/getUserInfo");
          this.auctionEndDialog = true;
          setTimeout(() => {
            this.auctionEndDialog = false;
          }, 3000);
        }
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

        this.animateScroll();
      });

      this.session.on("signal:update-auction", (event) => {
        this.$store.dispatch("moduleExample/selectCurrentAuction", event.data);
      });

      this.session.on("signal:auction-selected", (event) => {
        this.$store.dispatch("moduleExample/selectCurrentAuction", event.data);
      });

      this.session.on("signal:no-auction", (event) => {
        const newData = JSON.parse(event.data);
        this.$store.commit("moduleExample/selectCurrentAuction", newData);
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }

        this.session.disconnect();
        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;
        this.streamEndDialog = true;
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
      if (
        this.message == "" ||
        this.message == undefined ||
        this.message == null
      ) {
        return;
      } else {
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
            })
            .catch((error) => {
              console.error(error);
            });
        }
      }
    },

    sendPriceChangeMessage(newTerm) {
      if (this.term == "" || this.term == undefined || this.term == 0) {
        return;
      }

      if (this.term > this.currentAuction.currentPrice) {
        this.priceLessDialog = true;
      } else {
        if (this.currentAuction.id == undefined) {
          this.selectAuctionDialog = true;
          return;
        }
        const newData = {
          id: this.currentAuction.id,
          currentPrice: this.currentAuction.currentPrice - newTerm,
        };
        this.$store
          .dispatch("moduleExample/updateAuction", newData)
          .then(() => {
            if (this.session) {
              this.session
                .signal({
                  data: this.sessionId,
                  to: [],
                  type: "update-auction",
                })
                .catch((error) => {
                  console.error(error);
                });
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    sendAuctionSelected(auction) {
      if (this.session) {
        this.$store
          .dispatch("moduleExample/updateCurrentAuction", auction.id)
          .then(() => {
            this.session
              .signal({
                data: this.sessionId,
                to: [],
                type: "auction-selected",
              })
              .catch((error) => {
                console.error(error);
              });
          });
      }
    },

    sendNoAuction(auction) {
      const data = JSON.stringify(auction);

      if (this.session) {
        this.session
          .signal({
            data: data,
            to: [],
            type: "no-auction",
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },

    setNoAuction(auction) {
      this.$store.commit("moduleExample/selectCurrentAuction", auction);
    },

    winProduct() {
      const newData = {
        productName: this.currentAuction.productName,
        finalPrice: this.currentAuction.currentPrice,
        sellerId: this.sellerId,
      };
      const point = {
        price: this.currentAuction.currentPrice,
        sellerId: this.sellerId,
      };
      if (
        this.$store.state.user.userInfo.point >=
        this.currentAuction.currentPrice
      ) {
        this.$store
          .dispatch("moduleExample/deleteAuction", this.currentAuction.id)
          .then(() => {
            this.$store
              .dispatch("user/spendPoint", point)
              .then(() => {
                this.$store
                  .dispatch("moduleExample/createHistory", newData)
                  .then(() => {
                    if (this.session) {
                      this.session
                        .signal({
                          data: this.$store.state.user.userInfo.email,
                          to: [],
                          type: "win-auction",
                        })
                        .catch((error) => {
                          console.error(error);
                        });
                    }
                  })
                  .catch((error) => {
                    console.error(error);
                  });
              })
              .catch((error) => {
                conosole.error(error);
              });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        if (
          this.currentAuction.currentPrice == "" ||
          this.currentAuction.currentPrice == undefined ||
          this.currentAuction.currentPrice == null
        ) {
          this.noAuctionDialog = true;
        } else {
          this.pointLessDialog = true;
        }
      }
    },

    leaveSession() {
      this.$router.push("/");
    },

    closeDialog() {
      this.noAuctionDialog = false;
      this.pointLessDialog = false;
      this.priceLessDialog = false;
      this.selectAuctionDialog = false;
    },

    closeWinDialog() {
      this.winDialog = false;
      this.stop();
    },

    refreshAuction() {
      this.isSell = false;
    },

    animateScroll() {
      const scrollArea = this.$refs.chatScroll;
      if (scrollArea != null) {
        const scrollTarget = scrollArea.getScrollTarget();
        const duration = 300; // ms - use 0 to instant scroll
        scrollArea.setScrollPosition(
          "vertical",
          scrollTarget.scrollHeight,
          duration
        );
      }
    },

    removeAllMessages() {
      document.getElementById("chattings").innerHTML = "";
    },

    glad() {
      this.$confetti.start();
    },
    stop() {
      this.$confetti.stop();
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
  padding: 15px;
  background-color: #8fd89c;
  width: 220px;
  height: 50px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
  border-radius: 15px;
}

.message-orange {
  position: relative;
  margin-bottom: 10px;
  margin-left: calc(100% - 240px);
  padding: 15px;
  background-color: #e2e2e2;
  width: 220px;
  height: 50px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
  border-radius: 15px;
}
.message-sender {
  position: relative;
  margin-bottom: 5px;
  padding: 5px 20px 5px 0px;
  text-align: right;
  font: 400 0.9em "Open Sans", sans-serif;
}
.message-receiver {
  position: relative;
  margin-bottom: 5px;
  padding: 5px 0px 5px 20px;
  text-align: left;
  font: 400 0.9em "Open Sans", sans-serif;
}
</style>
