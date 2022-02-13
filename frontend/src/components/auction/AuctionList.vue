<template>
  <q-page>
    <auction-list-item
      v-for="(auction, key) in auctionList"
      :key="key"
      :auction="auction"
    >
    </auction-list-item>
  </q-page>
</template>

<script>
// import { computed } from "vue";
import AuctionListItem from "./AuctionListItem";
import { useStore } from "vuex";
export default {
  name: "AuctionList",
  components: {
    AuctionListItem,
  },
  props: ["roomId"],

  mounted() {
    const $store = useStore();
    // console.log(roomId);

    $store.dispatch("moduleExample/selectAllAuctions", this.roomId);
    const auctionList = $store.state.moduleExample.auctionList;

    return {
      auctionList,
    };
  },
};
</script>
