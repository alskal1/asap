<template>
  <q-card class="q-pa-md q-mb-lg">
    <q-item>
      <q-item-section>
        <q-item-label>{{ sellItem.productName }}</q-item-label>
        <q-item-label caption lines="2">판매날짜 : {{ sellDate }}</q-item-label>

        <!-- <q-item-label caption lines="2"
          >상품 수량 : {{ sellItem.count }}</q-item-label
        >
        <q-item-label caption lines="2"
          >최종 낙찰가 :{{ sellItem.finalPrice }}</q-item-label
        > -->
      </q-item-section>

      <q-item-section
        @click="$emit('intoSellItem', sellItem)"
        side
        class="text-primary"
        >판매상세 조회
      </q-item-section>
    </q-item>
  </q-card>
</template>

<script>
import { date } from "quasar";
export default {
  name: "SellListItem",
  props: {
    sellItem: {
      type: Object,
    },
  },
  setup(props) {
    const sellDate = date.formatDate(props.sellItem.date, "YYYY-MM-DD HH:mm");
    // const SellDate = date.inferDateFormat(props.sellItem.date);
    return { sellDate };
  },
};
</script>
