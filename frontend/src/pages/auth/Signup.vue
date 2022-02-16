<template>
  <div class="q-pa-lg row flex-start justify-center">
    <q-card flat style="width: 500px" class="q-pa-lg shadow-1">
      <q-card-section>
        <q-form
          @submit.prevent="submitForm(email, name, password, phone, address)"
        >
          <q-input
            color="green"
            outlined
            clearable
            v-model="email"
            type="email"
            label="이메일"
            lazy-rules
            :rules="[
              (val) => {
                var emailCheck =
                  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
                if (emailCheck.test(val) == false) {
                  const emailError = `이메일 형식이 올바르지않습니다.`;
                  return emailError;
                }
              },
            ]"
            dense
          />
          <q-input
            color="green"
            outlined
            v-model="name"
            type="text"
            label="닉네임"
            lazy-rules
            :rules="[(val) => !!val || '이름을 입력해주세요.']"
            dense
          />

          <q-input
            color="green"
            outlined
            v-model="password"
            type="password"
            label="비밀번호"
            lazy-rules
            :rules="[
              (val) => {
                var passCheck =
                  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
                if (passCheck.test(val) == false) {
                  const passwordError1 = `최소 8자, 하나 이상의 문자, 하나의 숫자 및 하나의 특수 문자이어야 합니다`;
                  return passwordError1;
                }
              },
            ]"
            dense
          />

          <q-input
            color="green"
            outlined
            v-model="confirmation"
            type="password"
            label="비밀번호 확인"
            lazy-rules
            :rules="[
              (val) => {
                if (!val) {
                  const passwordError1 = `비밀번호 확인을 입력해주세요.`;
                  return passwordError1;
                }
                if (
                  password != confirmation ||
                  password.length != confirmation.length
                ) {
                  const passwordError2 = `비밀번호가 일치하지 않습니다.`;
                  return passwordError2;
                }
              },
            ]"
            dense
          />

          <q-input
            color="green"
            outlined
            v-model="phone"
            mask="###-####-####"
            label="전화 번호"
            :rules="[(val) => !!val || '전화번호를 입력해주세요.']"
            dense
          />
          <div>
            <q-input
              type="text"
              color="green"
              outlined
              v-model="address.zipCode"
              label="우편번호"
              @click="search()"
              lazy-rules
              :rules="[(val) => !!val || '우편번호를 입력해주세요.']"
              dense
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
              outlined
              lazy-rules
              :rules="[(val) => !!val || '도로명주소를 입력해주세요.']"
              dense
            />
            <span id="guide" style="color: #000; display: none"></span>
            <q-input
              type="text"
              color="green"
              outlined
              v-model="address.detailAddress"
              label="상세주소"
              dense
            />
          </div>
          <div class="q-pt-md column q-gutter-sm row justify-center">
            <q-btn style="" label="SIGN UP" type="submit" color="green" />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </div>
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
      successSignup();
      router.push("/auth/login");
    };
    function sameID() {
      const dialog = $q
        .dialog({
          title: "가입 실패",
          html: true,
          message: "아이디가 중복 됩니다.",
          persistent: true,
          ok: "확인",
          color: "negative",
        })
        .onOk(() => {
          // console.log('>>>> OK, received', data)
        });

      return dialog;
    }
    function successSignup() {
      const notify = $q.notify({
        message: "회원가입 성공 하였습니다.",
        color: "green",
        icon: "announcement",
      });
      return notify;
    }

    function submitForm(email, name, password, phone, address) {
      const userData = {
        email: email,
        name: name,
        password: password,
        phone: phone,
        address: address,
      };

      api
        .post("/api/member/signup", userData)
        .then((response) => {
          if (response.status === 200) {
            signupSuccess();
          }
        })
        .catch(function (error) {
          // notifyerror();
          if (error.response.status === 500) {
            sameID();
          }
          console.log(error);
        });
    }

    return {
      router,
      signupSuccess,
      submitForm,
      sameID,
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
