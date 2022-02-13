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

export default {
  name: "AuctionForm",
  props: ["roomId"],
  data() {
    return {
      bidTerm: "",
      currentPrice: "",
      finalPrice: "",
      priceTerm: "",
      productName: "",

      startPrice: "",
    };
  },
  methods: {
    onSubmit() {
      const newData = {
        bidTerm: this.bidTerm,
        currentPrice: this.currentPrice,
        finalPrice: this.finalPrice,
        priceTerm: this.priceTerm,
        productName: this.productName,
        roomId: this.roomId,
        startPrice: this.startPrice,
      };

      api
        .post("api/auction/", newData)
        .then((response) => {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
      this.bidTerm = "";
      this.currentPrice = "";
      this.finalPrice = "";
      this.priceTerm = "";
      this.productName = "";

      this.startPrice = "";
    },
  },
};
</script>
