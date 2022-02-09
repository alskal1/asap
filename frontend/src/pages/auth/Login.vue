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

    const login = (email, password) => {
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
          $store.dispatch("user/getUserInfo");
        })
        .then(() => {
          router.push("/");
        })
        .catch((error) => {
          console.log(error);
        });
    };
    return {
      login,
    };
  },
};
</script>
