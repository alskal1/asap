<template>
  <q-page outline class="text-center q-mt-xl">
    <q-item>
      <q-item-section>경매일자</q-item-section>
      <q-item-section>품목명</q-item-section>
      <q-item-section>판매자명</q-item-section>
      <q-item-section>수량</q-item-section>
      <q-item-section>낙찰금액</q-item-section>
      <q-item-section>배송상태</q-item-section>
    </q-item>
    <q-separator />
    <q-item>
      <q-item-section>{{ winItem.date }}</q-item-section>
      <q-item-section>{{ winItem.productName }}</q-item-section>
      <q-item-section>{{ winItem.sellerId }}</q-item-section>
      <q-item-section>{{ winItem.count }}</q-item-section>
      <q-item-section>{{ winItem.finalPrice }}</q-item-section>
      <q-item-section>{{ winItem.deliveryState }}</q-item-section>
    </q-item>
  </q-page>
</template>

<script>
export default {
  name: "WinListItem",
  props: {
    winItem: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>
