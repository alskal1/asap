<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="onSubmit" class="q-gutter-md">
        <div>
          <div class="text-green text-weight-bolder">방송 제목</div>
          <q-input
            type="text"
            v-model="title"
            dense
            outlined
            color="green"
            :rules="[
              ((val) => !!val || '방송 제목을 입력해주세요.') ||
                ((val) => val.length <= 100 || '제목이 너무 깁니다.'),
            ]"
            clearable
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">방송 설명</div>
          <q-input
            type="textarea"
            v-model="description"
            dense
            outlined
            color="green"
            :rules="[
              ((val) => !!val || '방송 설명을 입력해주세요') ||
                ((val) => val.length <= 500 || '설명이 너무 깁니다.'),
            ]"
            clearable
          />
        </div>
        <div>
          <q-btn label="Submit" type="submit" color="green" />
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script>
import { computed, ref } from "vue";
import { useStore } from "vuex";
import { api } from "boot/axios";

// import { createAuction } from "src/store/module-auction/mutations";
export default {
  name: "AuctionForm",
  data() {
    return {
      description: "",
      title: "",
    };
  },
  methods: {
    async onTest() {
      const test = await testCall();
      console.log(test);
    },
    onSubmit() {
      const newData = {
        description: this.description,
        title: this.title,
      };

      api
        .post("/api/room/", newData)
        .then((response) => {
          console.log(response);
        })
        .catch(function (error) {
          // 에러인 경우 실행
          console.log(error);
        });
    },
  },
};
</script>
