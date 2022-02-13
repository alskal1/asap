<template>
  <div class="q-pa-md">
    <q-card>
      <q-card-section>
        <div class="text-subtitle2">{{ auction.bidTerm }}</div>
        <div class="text-subtitle2">{{ auction.priceTerm }}</div>
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
