<template>
  <div class="q-pa-lg row flex-start justify-center">
    <q-card square bordered style="width: 500px" class="q-pa-md shadow-1">
      <q-card-section class="q-px-md">
        <q-form class="q-gutter-lg">
          <q-input
            square
            filled
            v-model="email"
            type="email"
            label="아이디"
            @keyup.enter="login(email, password)"
          />
          <q-input
            square
            filled
            v-model="password"
            type="password"
            label="비밀번호"
            @keyup.enter="login(email, password)"
          />
        </q-form>
      </q-card-section>
      <q-card-actions class="q-px-md">
        <q-btn
          unelevated
          @click="login(email, password)"
          color="light-green-7"
          size="lg"
          class="full-width"
          label="로그인"
        />
      </q-card-actions>
      <q-card-actions class="q-px-md">
        <q-btn
          to="/auth/signup"
          unelevated
          color="grey"
          size="lg"
          class="full-width"
          label="회원가입"
        />
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

export default {
  name: "Login",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  setup() {
    const $store = useStore();
    const router = useRouter();
    const $q = useQuasar();

    const login = (email, password) => {
      const notifyerror = () => {
        $q.notify({
          message: "로그인이 실패하였습니다.",
          color: "red",
          icon: "announcement",
        });
      };
      const loginData = {
        email: email,
        password: password,
      };
      $store
        .dispatch("user/login", loginData)
        .then((response) => {
          const token = response.data.token;
          sessionStorage.setItem("jwt", token);
        })
        .then(() => {
          $store.dispatch("user/getUserInfo").then(() => {
            const name = $store.state.user.userInfo.name;
            $q.notify({
              message: `안녕하세요 ${name}님!`,
              color: "green",
              position: "center",
            });
          });
        })
        .then(() => {
          router.push("/");
        })
        .catch((error) => {
          notifyerror();
          console.log(error);
        });
    };

    return {
      router,
      login,
    };
  },
};
</script>
