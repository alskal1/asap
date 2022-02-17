<template>
  <q-card>
    <div class="gutter-sm" style="margin: 30px; width: 300px">
      <q-form @submit.prevent="onSubmit">
        <div class="text-green text-weight-bolder">상품명</div>
        <q-input
          type="text"
          v-model="productName"
          dense
          outlined
          color="green"
          clearable
        />

        <div>
          <div class="text-green text-weight-bolder">원산지</div>
          <q-input
            type="text"
            v-model="origin"
            dense
            outlined
            color="green"
            clearable
          />
        </div>

        <div class="text-green text-weight-bolder">시작 가격</div>
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
        <div class="q-pt-md column q-gutter-sm row justify-center">
          <q-btn label="경매등록" type="submit" color="green" />
        </div>
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
        $store.state.user.userInfo.email.replace("@", "-").replace(".", "-"),
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
