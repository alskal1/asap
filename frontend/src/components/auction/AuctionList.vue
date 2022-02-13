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
  // data() {
  //   return { auctionList: [] };
  // },
  props: ["roomId"],
  computed: {
    auctionList: function () {
      const $store = useStore();
      return $store.state.moduleExample.auctionList;
    },
  },

  created() {
    const $store = useStore();
    // console.log(roomId);

    $store.dispatch("moduleExample/selectAllAuctions", this.roomId);
  },
};
</script>
