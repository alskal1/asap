<template>
  <q-page>
    <div style="padding: 10px">
      <q-form @submit.prevent="onSubmit" style="width: 300px">
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
          <div class="text-green text-weight-bolder">상품 이름</div>
          <q-input
            type="text"
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
        <div>
          <q-btn label="경매등록" type="submit" color="green" />
        </div>
        <q-input
          type="text"
          v-model="startPrice"
          dense
          outlined
          color="green"
          :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          input-class="text-right"
          suffix="₩"
        />

        <p class="text-green text-weight-bolder">하향 주기</p>
        <q-input
          type="text"
          v-model="bidTerm"
          dense
          outlined
          color="green"
          :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          input-class="text-right"
          suffix="분"
        />
        <p class="text-green text-weight-bolder">하향 금액</p>
        <q-input
          type="text"
          v-model="priceTerm"
          dense
          outlined
          color="green"
          :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
          input-class="text-right"
          suffix="₩"
        />

        <q-btn label="경매등록" type="submit" color="green" />
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
