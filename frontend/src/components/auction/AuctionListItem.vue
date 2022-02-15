<template>
  <div class="q-pa-md">
    <q-card>
      <q-card-section>
        <div>
          <div class="text-subtitle2">상품명 : {{ auction.productName }}</div>
          <div class="text-subtitle2">시작가 : {{ auction.startPrice }}원</div>
        </div>
        <div class="row">
          <q-space />
          <q-btn flat label="수정" color="primary" @click="deleteAuction()" />
          <q-btn flat label="삭제" color="red" @click="deleteAuction()" />
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
    auction: {
      type: Object,
    },
  },
  setup(props) {
    const $store = useStore();

    function deleteAuction() {
      const deleteId = props.auction.id;
      $store
        .dispatch("moduleExample/deleteAuction", deleteId)
        .then(() => {
          // $store.dispatch("moduleExample/selectAllAuctions", email.value);
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
