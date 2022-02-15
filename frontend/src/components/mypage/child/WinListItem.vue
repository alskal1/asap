<template>
  <q-card class="q-pa-md q-mb-lg">
    <q-item>
      <q-item-section>
        <div class="text-h6 text-primary">낙찰확정</div>

        <q-item-label>낙찰 날짜 : {{ winItem.date }}</q-item-label>
        <q-item-label>상품 : {{ winItem.productName }}</q-item-label>
        <q-item-label>판매자 : {{ winItem.sellerId }}</q-item-label>
        <q-item-label>수량 : {{ winItem.count }}</q-item-label>
        <q-item-label>최종 낙찰가 : {{ winItem.finalPrice }}</q-item-label>
        <q-item-label>배송 상태 : {{ winItem.deliveryState }}</q-item-label>
      </q-item-section>

      <q-item-section @click="findProduct" side class="text-primary"
        >배송조회
      </q-item-section>
      <!-- <q-item-section v-else side class="text-primary"
        >{{ winItem.deliveryState }}
      </q-item-section> -->
    </q-item>
  </q-card>
</template>

<script>
import { api } from "boot/axios";
export default {
  name: "WinListItem",
  props: {
    winItem: {
      type: Object,
    },
  },
  setup(props) {
    const testKey = "9E7QuaGXyIVWiccS3fNTJQ";

    function findProduct() {}
    return { findProduct };
  },
};
</script>
