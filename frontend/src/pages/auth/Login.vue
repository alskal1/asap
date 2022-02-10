<template>
  <q-page>
    <q-card square bordered class="q-pa-lg shadow-1">
      <q-card-section>
        <q-form class="q-gutter-md">
          <q-input
            square
            filled
            clearable
            v-model="email"
            type="email"
            label="아이디"
          />
          <q-input
            square
            filled
            clearable
            v-model="password"
            type="password"
            label="비밀번호"
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
          label="Login"
        />
      </q-card-actions>
      <q-card-actions class="q-px-md">
        <q-btn
          to="/auth/signup"
          unelevated
          color="grey"
          size="lg"
          class="full-width"
          label="SIGN UP"
        />
      </q-card-actions>
    </q-card>
  </q-page>
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

    const $q = useQuasar();

    const login = (email, password) => {
      const notifyerror = $q.notify({
        message: "로그인이 실패하였습니다.",
        color: "green",
        icon: "announcement",
      });
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
              timeout: 1000,
            });
          });
        })
        .then(() => {
          router.push("/");
        })
        .then(() => {
          if (response.status === 401) {
            notifyerror();
          }
        })
        .catch((error) => {
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
