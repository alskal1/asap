<template>
  <div class="q-pa-md">
    <q-card>
      <q-card-section>
        <div class="text-subtitle2">{{ auction.id }}</div>
        <div class="text-subtitle2">{{ auction.productName }}</div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
export default {
  name: "AuctionListItem",
  props: {
    auction: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>
