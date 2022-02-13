<template>
  <q-card class="q-pa-md q-mb-lg">
    <q-item>
      <q-item-section>
        <q-item-label>{{ pointItem.price }} 포인트</q-item-label>
        <q-item-label caption lines="2">{{ pointItem.date }}</q-item-label>
      </q-item-section>

      <q-item-section side class="text-red">{{
        pointItem.status
      }}</q-item-section>
    </q-item>
  </q-card>
</template>

<script>
export default {
  name: "PointListItem",
  props: {
    pointItem: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>
