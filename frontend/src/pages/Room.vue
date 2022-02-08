<template>
  <q-page padding>
    <div class="row">
      <div class="text-h5 text-bold q-px-md q-pt-sm">경매 라이브 개설</div>
    </div>
    <q-separator class="q-my-md" />

    <auction-form></auction-form>
  </q-page>
</template>

<script>
import AuctionForm from "components/auction/AuctionForm";

export default {
  name: "Room",
  components: {
    AuctionForm,
  },
};
</script>
