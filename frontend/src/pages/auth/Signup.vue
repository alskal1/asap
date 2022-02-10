<template>
  <q-page>
    <q-card square bordered class="q-pa-lg shadow-1">
      <q-card-section>
        <q-form
          @submit.prevent="submitForm(email, name, password, phone, address)"
          class="q-gutter-md"
        >
          <div class="text-bold text-green">회원가입</div>
          <q-input
            color="green"
            filled
            clearable
            v-model="email"
            type="email"
            label="이메일"
            :rules="[(val) => !!val || '이메일을 입력해주세요.']"
          />
          <q-input
            color="green"
            filled
            v-model="name"
            type="text"
            label="이름"
            :rules="[(val) => !!val || '이름을 입력해주세요.']"
          />

          <q-input
            color="green"
            filled
            v-model="password"
            type="password"
            label="비밀번호"
            :rules="[(val) => !!val || '비밀번호를 입력해주세요.']"
          />

          <q-input
            color="green"
            filled
            v-model="confirmation"
            type="password"
            label="비밀번호 확인"
            :rules="[
              ((val) => !!val || '비밀번호 확인를 입력해주세요.') ||
                ((val) => val !== password || '제목이 너무 깁니다.'),
            ]"
          />
          <q-input
            color="green"
            filled
            v-model="phone"
            type="phone"
            label="전화 번호"
            :rules="[(val) => !!val || '전화번호를 입력해주세요.']"
          />
          <div>
            <q-input
              type="text"
              color="green"
              filled
              v-model="address.zipCode"
              label="우편번호"
              @click="search()"
              :rules="[(val) => !!val || '우편번호를 입력해주세요.']"
            >
              <template v-slot:prepend>
                <q-icon color="green" name="search" />
              </template>
            </q-input>
            <br />

            <q-input
              type="text"
              v-model="address.roadAddress"
              label="도로명주소"
              color="green"
              filled
              :rules="[(val) => !!val || '도로명주소를 입력해주세요.']"
            />
            <span id="guide" style="color: #000; display: none"></span>
            <q-input
              type="text"
              color="green"
              filled
              v-model="address.detailAddress"
              label="상세주소"
            />
          </div>
          <div>
            <q-btn label="Submit" type="submit" color="green" />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script>
import { api } from "boot/axios";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
export default {
  name: "Signup",
  data() {
    return {
      email: "",
      name: "",
      confirmation: "",
      password: "",
      phone: "",
      address: {
        zipCode: "",
        roadAddress: "",
        detailAddress: "",
      },
    };
  },
  setup() {
    const router = useRouter();
    const $q = useQuasar();

    const signupSuccess = () => {
      router.push("/auth/login");
    };

    function submitForm(email, name, password, phone, address) {
      const userData = {
        email: email,
        name: name,
        password: password,
        phone: phone,
        address: address,
      };
      const notifyerror = $q.notify({
        message: "회원가입 성공 하였습니다.",
        color: "green",
        icon: "announcement",
      });
      api
        .post("/api/member/signup", userData)
        .then((response) => {
          if (response.status === 200) {
            signupSuccess();
          }
        })
        .catch(function (error) {
          notifyerror();
          console.log(error);
        });
    }

    return {
      router,
      signupSuccess,
      submitForm,
    };
  },
  methods: {
    search() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          let roadAddr = data.roadAddress;
          this.address.zipCode = data.zonecode;
          this.address.roadAddress = roadAddr;

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
