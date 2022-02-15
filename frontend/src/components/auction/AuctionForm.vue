<template>
  <q-card>
    <div style="padding: 30px; width: 350px">
      <q-form @submit.prevent="onSubmit">
        <p class="text-green text-weight-bolder">상품 이름</p>
        <q-input
          type="text"
          v-model="productName"
          dense
          outlined
          color="green"
          clearable
        />

        <div>
          <div class="text-green text-weight-bolder">상품 원산지</div>
          <q-input
            type="text"
            v-model="origin"
            dense
            outlined
            color="green"
            clearable
          />
        </div>

        <div class="text-green text-weight-bolder">시작가</div>
        <q-input
          type="text"
          v-model="startPrice"
          dense
          outlined
          color="green"
          clearable
          :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          input-class="text-right"
          suffix="₩"
        />
        <q-btn label="경매등록" type="submit" color="green" />
      </q-form>
    </div>
  </q-card>
</template>

<script>
import { api } from "boot/axios";
import { useStore } from "vuex";
import { computed, ref } from "@vue/runtime-core";

export default {
  name: "AuctionForm",
  setup(props, { emit }) {
    const $store = useStore();
    const productName = ref("");
    const startPrice = ref("");
    const origin = ref("");

    const email = computed({
      get: () =>
        $store.state.user.userInfo.email.replace("@", "").replace(".", ""),
    });

    function onSubmit() {
      const newData = {
        productName: productName.value,
        startPrice: startPrice.value,
        origin: origin.value,
      };

      api
        .post("api/auction/", newData)
        .then((response) => {
          console.log(response);
        })
        .then(() => {
          $store.dispatch("moduleExample/selectAllAuctions", email.value);
        })
        .then(() => {
          productName.value = "";
          startPrice.value = "";
          origin.value = "";
          emit("newAuctionAdded");
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    return {
      productName,
      startPrice,
      origin,
      onSubmit,
    };
  },
};
</script>
