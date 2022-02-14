<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="goLive()" class="q-gutter-md">
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
            <div class="text-green text-weight-bolder">판매자 명</div>
            <p>
              <q-input
                outlined
                v-model="userInfo.name"
                class="form-control"
                type="text"
                required
                disable
              />
            </p>
            <div class="text-green text-weight-bolder">Session</div>
            <p>
              <q-input
                outlined
                v-model="userInfo.email"
                class="form-control"
                type="text"
                required
                disable
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
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default {
  name: "RoomForm",

  setup() {
    const $store = useStore();
    const router = useRouter();
    const userInfo = ref({});
    const title = ref("");
    const description = ref("");

    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      userInfo.value = stateUserInfo;
    });

    function goLive() {
      router.push(
        "/live?sessionId=" +
          userInfo.value.email +
          "&myUserName=" +
          userInfo.value.name +
          "&title=" +
          title.value +
          "&status=PUBLISHER"
      );
    }

    return {
      userInfo,
      title,
      description,
      goLive,
    };
  },
};
</script>
