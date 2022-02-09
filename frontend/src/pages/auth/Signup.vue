<template>
  <q-page>
    <q-card square bordered class="q-pa-lg shadow-1">
      <q-card-section>
        <q-form @submit.prevent="submitForm" class="q-gutter-md">
          <div class="text-bold text-green">회원가입</div>
          <q-input
            color="green"
            filled
            clearable
            v-model="email"
            type="email"
            label="이메일"
          />
          <q-input
            color="green"
            filled
            v-model="name"
            type="text"
            label="이름"
          />

          <q-input
            color="green"
            filled
            v-model="password"
            type="password"
            label="비밀번호"
          />

          <q-input
            color="green"
            filled
            v-model="confirmation"
            type="password"
            label="비밀번호 확인"
          />
          <q-input
            color="green"
            filled
            v-model="phone"
            type="phone"
            label="전화 번호"
          />
          <q-select v-model="authorityName" :options="roleOptions"></q-select>

          <div>
            <q-btn label="Submit" type="submit" color="green" />
            <q-btn
              label="Reset"
              type="reset"
              color="green"
              flat
              class="q-ml-sm"
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script>
import { api } from "boot/axios";
import { useRouter } from "vue-router";
export default {
  name: "Signup",
  data() {
    return {
      email: "",
      name: "",
      confirmation: "",
      password: "",
      phone: "",
      point: 0,
      authorityName: "",
      roleOptions: [
        {
          label: "경매자",
          value: "sales",
        },
        {
          label: "구매자",
          value: "buyer",
        },
      ],
    };
  },
  setup() {
    const router = useRouter();
    return {
      router,
    };
  },
  methods: {
    submitForm() {
      const authorityDtoSet = [];
      const userData = {
        email: this.email,
        name: this.name,
        password: this.password,
        phone: this.phone,
        point: this.point,
      };
      console.log(authorityDtoSet);
      api
        .post("/api/member/signup", userData, authorityDto)
        .then((response) => {
          if (response.status === 200) {
            router.push({ name: "login" });
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
