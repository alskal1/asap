<template>
  <q-page>
    <q-list v-for="(auction, key) in auctionList" :key="key">
      <q-item>{{ auction }}</q-item>
    </q-list>
  </q-page>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
export default {
  name: "AuctionList",
  props: ["roomId"],
  setup() {
    const $store = useStore();

    $store.dispatch("moduleExample/getAuctionList");

    const auctionList = computed({
      get: () => $store.state.moduleExample.auctionList,
    });
    return {
      auctionList,
    };
  },
};
</script>
