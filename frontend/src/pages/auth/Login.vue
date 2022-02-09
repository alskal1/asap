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
export default {
  name: "Login",
};
</script>
