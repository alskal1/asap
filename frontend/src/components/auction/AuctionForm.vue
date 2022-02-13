<template>
  <q-page padding>
    <div class="q-pa-md">
      <q-form @submit.prevent="onSubmit" class="q-gutter-md">
        <div>
          <div class="text-green text-weight-bolder">bidTerm</div>
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
          <div class="text-green text-weight-bolder">currentPrice</div>
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
          <div class="text-green text-weight-bolder">finalPrice</div>
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
          <div class="text-green text-weight-bolder">priceTerm</div>
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
          <div class="text-green text-weight-bolder">productName</div>
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
          <div class="text-green text-weight-bolder">startPrice</div>
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
      console.log(newData);
      api
        .post("api/auction/", newData)
        .then((response) => {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
