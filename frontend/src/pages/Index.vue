<template>
  <q-page>
    <p>메인</p>
    <auction-list></auction-list>
  </q-page>
</template>

<script>
import AuctionList from "components/auction/AuctionList";

export default {
  name: "PageIndex",
  components: {
    AuctionList,
  },
};
</script>
