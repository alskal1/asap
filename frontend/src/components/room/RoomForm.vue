<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="onSubmit" class="q-gutter-md">
        <div>
          <div class="text-green text-weight-bolder">방송 제목</div>
          <q-input
            type="text"
            v-model="title"
            dense
            outlined
            color="green"
            :rules="[
              ((val) => !!val || '방송 제목을 입력해주세요.') ||
                ((val) => val.length <= 100 || '제목이 너무 깁니다.'),
            ]"
            clearable
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">방송 설명</div>
          <q-input
            type="textarea"
            v-model="description"
            dense
            outlined
            color="green"
            :rules="[
              ((val) => !!val || '방송 설명을 입력해주세요') ||
                ((val) => val.length <= 500 || '설명이 너무 깁니다.'),
            ]"
            clearable
          />
        </div>

        <div>
          <q-form class="q-gutter-md">
            <div class="text-green text-weight-bolder">Participant</div>
            <p>
              <q-input
                outlined
                v-model="myUserName"
                class="form-control"
                type="text"
                required
              />
            </p>
            <div class="text-green text-weight-bolder">Session</div>
            <p>
              <q-input
                outlined
                v-model="sessionId"
                class="form-control"
                type="text"
                required
              />
            </p>
          </q-form>
        </div>

        <div>
          <q-btn label="Submit" type="submit" color="green" />
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script>
export default {
  name: "RoomForm",
  data() {
    return {
      description: "",
      title: "",
      status: "PUBLISHER",
      sessionId: "SessionA",
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
    };
  },
  methods: {
    onSubmit() {
      this.goLive();
    },

    goLive() {
      console.log(this.sessionId);
      this.$router.push(
        "/live?sessionId=" +
          this.sessionId +
          "&myUserName=" +
          this.myUserName +
          "&title=" +
          this.title +
          "&status=" +
          this.status
      );
    },
  },
};
</script>
