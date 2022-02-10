<template>
  <q-form
    @submit.prevent="
      sumbitAuction(
        bidterm,
        currentPricce,
        finalPrice,
        priceTerm,
        productName,
        startPrice
      )
    "
  >
    <div class="row">
      <q-input filled type="number" v-model="bidterm" label="bidterm">
      </q-input>
      <q-input
        filled
        type="number"
        v-model="currentPricce"
        label="currentPricce"
      >
      </q-input>
      <q-input filled type="number" v-model="finalPrice" label="finalPrice">
      </q-input>
      <q-input filled type="number" v-model="priceTerm" label="priceTerm">
      </q-input>
      <q-input filled type="text" v-model="productName" label="productName">
      </q-input>
      <q-input filled type="number" v-model="startPrice" label="startPrice">
      </q-input>
    </div>
    <div>
      <q-btn label="Submit" type="submit" color="primary" />
    </div>
  </q-form>
</template>

<script>
import { api } from "boot/axios";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

export default {
  name: AuctionForm,
  data() {
    return {
      bidterm: 0,
      currentPricce: 0,
      finalPrice: 0,
      priceTerm: 0,
      productName: "",
      startPrice: 0,
    };
  },
  setup() {
    const router = useRouter();
    const $q = useQuasar();
    function sumbitAuction(
      bidterm,
      currentPricce,
      finalPrice,
      priceTerm,
      productName,
      startPrice
    ) {
      const auctionData = {
        bidterm: bidterm,
        currentPricce: currentPricce,
        finalPrice: finalPrice,
        priceTerm: priceTerm,
        productName: productName,
        startPrice: startPrice,
      };
      api.post("api/auction", auctionData);
    }

    return {
      router,
      sumbitAuction,
    };
  },
};
</script>
