<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="onSubmit" class="q-gutter-md">
        <div>
          <div class="text-green text-weight-bolder">하향 주기</div>
          <q-input
            type="text"
            v-model="bidTerm"
            dense
            outlined
            color="green"
            clearable
            :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">현재 가격</div>
          <q-input
            type="text"
            v-model="currentPrice"
            dense
            outlined
            color="green"
            clearable
            :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">최종 낙찰가</div>
          <q-input
            type="text"
            v-model="finalPrice"
            dense
            outlined
            color="green"
            clearable
            :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">하향 금액</div>
          <q-input
            type="text"
            v-model="priceTerm"
            dense
            outlined
            color="green"
            clearable
            :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">상품 이름</div>
          <q-input
            type="textarea"
            v-model="productName"
            dense
            outlined
            color="green"
            clearable
          />
        </div>
        <div>
          <div class="text-green text-weight-bolder">시작가</div>
          <q-input
            type="text"
            v-model="startPrice"
            dense
            outlined
            color="green"
            clearable
            :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          />
        </div>
        <div>
          <q-btn label="경매등록" type="submit" color="green" />
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script>
import { api } from "boot/axios";
import { useStore } from "vuex";
import { computed, ref } from "@vue/runtime-core";

export default {
  name: "AuctionForm",
  setup() {
    const $store = useStore();
    const bidTerm = ref("");
    const currentPrice = ref("");
    const finalPrice = ref("");
    const priceTerm = ref("");
    const productName = ref("");
    const roomId = ref("");
    const startPrice = ref("");

    const email = computed({
      get: () =>
        $store.state.user.userInfo.email.replace("@", "").replace(".", ""),
    });

    function onSubmit() {
      const newData = {
        bidTerm: bidTerm.value,
        currentPrice: currentPrice.value,
        finalPrice: finalPrice.value,
        priceTerm: priceTerm.value,
        productName: productName.value,
        roomId: roomId.value,
        startPrice: startPrice.value,
      };

      api
        .post("api/auction/", newData)
        .then((response) => {
          console.log(response);
        })
        .then(() => {
          $store.dispatch("moduleExample/selectAllAuctions", email.value);
        })
        .catch(function (error) {
          console.log(error);
        });
      bidTerm.value = "";
      currentPrice.value = "";
      finalPrice.value = "";
      priceTerm.value = "";
      productName.value = "";
      startPrice.value = "";
    }

    return {
      bidTerm,
      currentPrice,
      finalPrice,
      priceTerm,
      productName,
      roomId,
      startPrice,
      onSubmit,
    };
  },
};
</script>
