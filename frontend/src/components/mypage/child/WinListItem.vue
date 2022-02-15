<template>
  <q-card class="q-pa-md q-mb-lg">
    <q-item>
      <q-item-section>
        <div class="text-h6 text-primary">낙찰확정</div>

        <div>{{ winItem.date }}</div>
        <q-item-label>{{ winItem.productName }}</q-item-label>
        <q-item-label>{{ winItem.sellerId }}</q-item-label>
        <q-item-label>{{ winItem.count }}</q-item-label>
        <q-item-label>{{ winItem.finalPrice }}</q-item-label>
        <q-item-label>{{ winItem.deliveryState }}</q-item-label>
      </q-item-section>

      <q-item-section
        @click="$emit('intoWinItem', winItem)"
        side
        class="text-primary"
        >배송조회
      </q-item-section>
      <!-- <q-item-section v-else side class="text-primary"
        >{{ winItem.deliveryState }}
      </q-item-section> -->
    </q-item>
  </q-card>
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
