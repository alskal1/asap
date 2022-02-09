<template>
  <q-page>
    <q-card class="q-pa-lg">
      <h6>회원정보변경</h6>
      <q-form class="q-pa-md">
        <q-input label="아이디" v-model="email"
          ><template v-slot:before>
            <q-avatar>
              <img src="https://cdn.quasar.dev/img/avatar5.jpg" />
            </q-avatar>
            <q-input label="이름" v-model="name" />
          </template>
        </q-input>
        <q-input
          type="password"
          color="green"
          label="비밀번호"
          v-model="password"
        />

        <q-input
          type="password"
          color="green"
          label="비밀번호 재입력"
          v-model="repassword"
        />

        <div>
          <q-input
            type="text"
            class="q-mt-sm"
            color="green"
            v-model="postcode"
            label="우편번호"
            @click="search()"
          >
            <template v-slot:prepend>
              <q-icon color="green" name="search" />
            </template>
          </q-input>
          <br />

          <q-input
            type="text"
            v-model="roadAddress"
            label="도로명주소"
            disable
          />
          <span id="guide" style="color: #000; display: none"></span>
          <q-input
            type="text"
            color="green"
            v-model="detailAddress"
            label="상세주소"
          />
        </div>
        <q-input type="text" color="green" label="전화번호" v-model="phone" />
        <div class="text-center q-mt-lg">
          <q-btn
            class="q-mr-md"
            outline
            color="green"
            label="변경"
            @click="updateInfo()"
          />
        </div>
      </q-form>
    </q-card>
  </q-page>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "MyInfoUpdate",
  data() {
    return {
      email: "",
      password: "",
      repassword: "",
      name: "",
      postcode: "",
      roadAddress: "",
      detailAddress: "",
      phone: "",
    };
  },
  setup() {
    const $store = useStore();

    const getUserInfo = computed({
      get: () => $store.state.user.getUserInfo,
    });

    return {
      getUserInfo,
    };
  },
  methods: {
    search() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          let roadAddr = data.roadAddress;
          this.postcode = data.zonecode;
          this.roadAddress = roadAddr;

          let guideTextBox = document.getElementById("guide");
          if (data.autoRoadAddress) {
            let expRoadAddr = data.autoRoadAddress;
            guideTextBox.innerHTML = "도로명 주소 : " + expRoadAddr;
            guideTextBox.style.display = "block";
          } else {
            guideTextBox.innerHTML = "";
            guideTextBox.style.display = "none";
          }
        },
      }).open();
    },
  },
};
</script>
