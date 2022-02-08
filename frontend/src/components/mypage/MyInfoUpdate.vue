<template>
  <q-page>
    <q-card class="q-pa-md">
      <q-form>
        <p>회원 정보 변경</p>
        <div>
          <label>아이디</label>
          <q-input
            square
            outlined
            type="text"
            v-model="userid"
            label="이메일을 입력하세요"
            required
          />
        </div>
        <div>
          <label>비밀번호</label>
          <q-input
            square
            outlined
            type="password"
            v-model="password"
            required
          />
        </div>
        <div>
          <label>비밀번호 확인</label>
          <q-input
            square
            outlined
            type="password"
            v-model="repassword"
            required
          />
        </div>
        <div>
          <label>이름</label>
          <q-input square outlined type="text" v-model="name" required />
        </div>
        <div>
          <label>주소</label>
          <q-input square outlined type="text" v-model="address" />
        </div>
        <div>
          <label>전화번호</label>
          <q-input
            type="text"
            square
            outlined
            v-model="phone"
            label="010-1234-5678"
            required
          />
        </div>
        <div>
          <q-btn @click="updateBtn()" label="변경" />
        </div>
      </q-form>
    </q-card>
  </q-page>
</template>

<script>
export default {
  name: "MyInfoUpdate",
  data() {
    return {
      userid: "",
      password: "",
      repassword: "",
      name: "",
      address: "",
      phone: "",
    };
  },

  methods: {
    async updateBtn() {
      if (this.password != this.repassword) {
        alert("비밀번호를 다시 입력해주세요");
      }
    },
  },
};
</script>
