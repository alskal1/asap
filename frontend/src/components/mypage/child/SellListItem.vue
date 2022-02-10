<template>
  <q-item>
    <q-item-section class="col-2">{{ sellItem.date }}</q-item-section>
    <q-item-section class="col-3">{{ sellItem.productName }}</q-item-section>
    <q-item-section class="col-2">{{ sellItem.sellerId }}</q-item-section>
    <q-item-section class="col-1">{{ sellItem.count }}</q-item-section>
    <q-item-section class="col-2">{{ sellItem.finalPrice }}</q-item-section>
    <q-item-section class="col-1">{{ sellItem.deliveryState }}</q-item-section>
  </q-item>
  <q-separator />
</template>

<script>
export default {
  name: "SellListItem",
  props: {
    sellItem: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>
