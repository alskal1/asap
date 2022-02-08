<template>
  <q-page>
    <q-card square bordered class="q-pa-lg shadow-1">
      <q-card-section>
        <q-form class="q-gutter-md">
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
            v-model="password"
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
            v-model="phonnumber"
            type="phone"
            label="전화 번호"
          />

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
export default {
  name: "Signup",
};
</script>
