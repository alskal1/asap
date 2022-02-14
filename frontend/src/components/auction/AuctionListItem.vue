<template>
  <div class="q-pa-md">
    <q-card>
      <q-card-section>
        <div>
          <div class="text-subtitle2">상품명 : {{ auction.productName }}</div>
          <div class="text-subtitle2">
            상품가격 : {{ auction.finalPrice }}원
          </div>
        </div>
        <div class="row">
          <q-space />
          <q-btn flat label="Cancel" color="primary" @click="deleteAuction()" />
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import { useStore } from "vuex";
export default {
  name: "AuctionListItem",
  props: {
    sessionId: {
      type: String,
    },
    auction: {
      type: Object,
    },
  },
  setup(props) {
    const $store = useStore();
    function deleteAuction() {
      $store
        .dispatch("moduleExample/deleteAuction", props.auction.id)
        .then(() => {
          // this.$store.dispatch("moduleExample/selectAllAuctions", props.sessionId);
        })
        .catch(function (error) {
          console.log(error);
        });
    }

    return {
      deleteAuction,
    };
  },
};
</script>
