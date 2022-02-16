<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="goLive()" class="row justify-center q-gutter-xl">
        <div class="col-4">
          <div>
            <div class="text-green text-weight-bolder">방송 썸네일 입력</div>
            <q-file
              v-model="thumbnail"
              label="방송 썸네일 이미지 등록하기"
              outlined
              accept=".jpg, image/*"
            >
              <template v-slot:prepend>
                <q-icon name="image"></q-icon>
              </template>
            </q-file>
            <p></p>
          </div>
          <div>
            <div>
              <div>
                <div class="text-green text-weight-bolder">미리보기</div>
                <q-card>
                  <q-img :src="preview" />
                  <q-card-section>
                    <div class="text-h6">{{ title }}</div>
                    <div class="text-subtitle2">{{ description }}</div>
                  </q-card-section>
                </q-card>
              </div>
            </div>
          </div>
        </div>
        <div class="col-4">
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
            <q-btn label="경매방 생성하기" type="submit" color="green" />
          </div>
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script>
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

import { uploadBytes } from "firebase/storage";
import { thumbnailsRef } from "boot/firebaseConnection";

export default {
  name: "RoomForm",

  setup() {
    const $store = useStore();
    const router = useRouter();
    const userInfo = ref({});
    const title = ref("");
    const description = ref("");
    const thumbnail = ref(null);
    const preview = ref(
      "https://img.freepik.com/free-vector/antique-auction-isometric-composition_1284-22062.jpg"
    );

    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      userInfo.value = stateUserInfo;
    });

    function goLive() {
      $store.commit("user/setIsManage", true);
      router.push(
        "/live/publisher?sessionId=" +
          userInfo.value.email +
          "&myUserName=" +
          userInfo.value.name +
          "&title=" +
          title.value +
          "&description=" +
          description.value +
          "&status=PUBLISHER"
      );

      if (thumbnail.value == null) return;

      const imgName = userInfo.value.email.replace("@", "-").replace(".", "-");

      uploadBytes(thumbnailsRef(`${imgName}.jpg`), thumbnail.value).then(
        (snapshot) => {
          console.log("Upload thumbnail!");
          console.log(snapshot);
        }
      );
    }

    function putThumnail() {
      preview.value = URL.createObjectURL(thumbnail.value);
    }

    watch(thumbnail, putThumnail);

    return {
      userInfo,
      title,
      description,
      thumbnail,
      preview,
      putThumnail,
      goLive,
    };
  },
};
</script>
