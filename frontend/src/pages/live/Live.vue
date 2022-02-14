<template>
  <q-page class="flex flex-center">
    <div v-if="session">
      <div>
        <h2>{{ title }}</h2>
        <q-btn v-if="manage" color="red" @click="leaveSession"
          >방송 종료하기</q-btn
        >
      </div>
      <div class="row">
        <user-video :stream-manager="mainStreamManager"></user-video>
      </div>

      <div>
        <q-input v-model="message" />
        <button type="button" @click="sendMessage()">전송</button>
      </div>

      <q-div id="chattings">
        <h2>메시지 리스트</h2>
      </q-div>

      <q-dialog v-model="dialog">
        <q-card>
          <q-card-section class="row items-center no-wrap">
            <div>
              <div class="text-weight-bold">방송이 종료되었습니다.</div>
              <q-btn @click="goMain()">OK</q-btn>
            </div>
          </q-card-section>
        </q-card>
      </q-dialog>

      <auction-form v-if="manage"></auction-form>
      <auction-list v-if="manage"></auction-list>
    </div>
  </q-page>
</template>

<script>
import { api } from "boot/axios";
import { ovapi } from "boot/axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "components/live/UserVideo";
import AuctionForm from "components/auction/AuctionForm";
import AuctionList from "components/auction/AuctionList";
const OPENVIDU_SERVER_URL = `https://${global.location.hostname}:4443`;
const OPENVIDU_SERVER_SECRET = "ASAP";

export default {
  name: "Live",

  components: {
    UserVideo,
    AuctionForm,
    AuctionList,
  },

  data() {
    return {
      title: "",
      messageList: "",
      position: "center",
      OV: undefined,
      dialog: false,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      message: "",
      manage: false,
      sessionId: "",
      myUserName: "",
    };
  },
  created() {
    this.title = this.$route.query.title;
    this.sessionId = this.$route.query.sessionId
      .replace("@", "")
      .replace(".", "");
    this.myUserName = this.$route.query.myUserName;
  },
  mounted() {
    if (this.$route.query.status == "PUBLISHER") {
      this.manage = true;
      this.joinSession();
    } else {
      this.justJoinSession();
    }
  },
  methods: {
    goMain() {
      this.$router.push("/");
    },

    joinSession() {
      this.OV = new OpenVidu();

      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:user-chat", (event) => {
        if (this.session.connection.connectionId != event.from.connectionId) {
          // let newMessage = document.createElement("q-chat-message");
          // newMessage.setAttribute("v-bind:text", "[" + event.data + "]");
          // newMessage.setAttribute("name", "viewer");

          let newMessage = document.createElement("div");
          newMessage.innerText = event.data;
          newMessage.classList.add("message-blue");

          document.getElementById("chattings").appendChild(newMessage);
          // this.messageList += event.data;
        }
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
                console.log(error);
              });
          })
          .then(() => {
            const publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true,
              publishVideo: true,
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND",
              mirror: false, // Whether to mirror your local video or not
            });

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

      window.addEventListener("beforeunload", this.leaveSession);
    },

    justJoinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("signal:user-chat", (event) => {
        if (this.session.connection.connectionId != event.from.connectionId) {
          // let newMessage = document.createElement("q-chat-message");
          // newMessage.setAttribute("v-bind:text", "[" + event.data + "]");
          // newMessage.setAttribute("name", "viewer");

          let newMessage = document.createElement("div");
          newMessage.innerText = event.data;
          newMessage.classList.add("message-blue");

          document.getElementById("chattings").appendChild(newMessage);
        }
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

      window.addEventListener("beforeunload", this.leaveSession);
    },

    sendMessage() {
      if (this.session) {
        this.session
          .signal({
            data: this.message,
            to: [],
            type: "user-chat",
          })
          .then(() => {
            // let newMessage = document.createElement("q-chat-message");
            // newMessage.setAttribute("v-bind:text", "[" + this.message + "]");
            // newMessage.setAttribute("name", "me");
            // newMessage.setAttribute("sent");

            let newMessage = document.createElement("div");
            newMessage.innerText = this.message;
            newMessage.classList.add("message-orange");

            document.getElementById("chattings").appendChild(newMessage);
            // this.messageList += this.message;
            this.message = "";
            console.log("Message successfully sent!!!");
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      if (this.manage) {
        api
          .delete("/api/room/" + this.sessionId)
          .then(() => {
            this.$router.push("/");
          })
          .catch(function (error) {
            console.log(error);
          });
      }
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
