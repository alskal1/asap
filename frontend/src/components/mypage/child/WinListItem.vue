<template>
  <q-item>
    <q-item-section class="col-2">{{ winItem.date }}</q-item-section>
    <q-item-section class="col-3">{{ winItem.productName }}</q-item-section>
    <q-item-section class="col-2">{{ winItem.sellerId }}</q-item-section>
    <q-item-section class="col-1">{{ winItem.count }}</q-item-section>
    <q-item-section class="col-2">{{ winItem.finalPrice }}</q-item-section>
    <q-item-section class="col-1">{{ winItem.deliveryState }}</q-item-section>
  </q-item>
  <q-separator />
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
