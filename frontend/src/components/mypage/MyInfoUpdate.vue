<template>
  <q-page>
    <q-card class="q-pa-lg">
      <h6>회원정보변경</h6>
      <q-form class="q-pa-md">
        <q-input label="닉네임" color="green" v-model="userInfo.name"
          ><template v-slot:before>
            <q-avatar size="60px" text-color="green" icon="account_circle">
            </q-avatar>
            <q-input
              label="아이디"
              color="green"
              v-model="userInfo.email"
              disable
            />
          </template>
        </q-input>

        <div>
          <q-input
            type="text"
            class="q-mt-sm"
            color="green"
            v-model="addressInfo.zipCode"
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
            v-model="addressInfo.roadAddress"
            label="도로명주소"
            disable
          />
          <span id="guide" style="color: #000; display: none"></span>
          <q-input
            type="text"
            color="green"
            v-model="addressInfo.detailAddress"
            label="상세주소"
          />
        </div>
        <q-input
          type="text"
          color="green"
          label="전화번호"
          v-model="userInfo.phone"
        />
        <div class="text-center q-mt-lg">
          <q-btn
            class="q-mr-md"
            outline
            color="green"
            label="변경"
            @click="changeInfo()"
          />
        </div>
      </q-form>
    </q-card>
  </q-page>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default {
  name: "MyInfoUpdate",
  data() {
    return {};
  },
  setup() {
    const $store = useStore();
    const userInfo = ref({});
    const addressInfo = ref({});
    const router = useRouter();

    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      const stateAddressInfo = $store.state.user.userInfo.address;

      const stateAddressInfos = {
        zipCode: stateAddressInfo.zipCode,
        roadAddress: stateAddressInfo.roadAddress,
        detailAddress: stateAddressInfo.detailAddress,
      };

      userInfo.value = stateUserInfo;
      addressInfo.value = stateAddressInfos;
    });

    function search() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          let roadAddr = data.roadAddress;
          addressInfo.value.zipCode = data.zonecode;
          addressInfo.value.roadAddress = roadAddr;

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
    }

    function changeInfo() {
      const userDto = {
        name: userInfo.value.name,
        email: userInfo.value.email,
        phone: userInfo.value.phone,
        address: {
          zipCode: addressInfo.value.zipCode,
          roadAddress: addressInfo.value.roadAddress,
          detailAddress: addressInfo.value.detailAddress,
        },
      };

      console.log("업데이트 정보 : ", userDto);

      $store.dispatch("user/updateConfirm", userDto).then(() => {
        router.push("/");
      });
    }

    return {
      userInfo,
      addressInfo,
      search,
      changeInfo,
    };
  },
};
</script>
