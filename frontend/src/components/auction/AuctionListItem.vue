<template>
  <div class="q-pa-md">
    <q-card style="width: 400px">
      <q-card-section>
        <div>
          <div class="text-subtitle2">
            상품명 : {{ curAuction.productName }}
          </div>
          <div class="text-subtitle2">
            시작가 : {{ curAuction.startPrice }}원
          </div>
          <div class="text-subtitle2">원산지 : {{ curAuction.origin }}</div>
        </div>
        <div class="row">
          <q-space />
          <q-btn flat label="선택" color="primary" @click="selectAuction" />
          <q-btn flat label="삭제" color="red" @click="deleteAuction" />
        </div>
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
  data() {
    return {
      curAuction: this.auction,
    };
  },
  methods: {
    deleteAuction() {
      this.$emit("deleteAuction");
    },
    selectAuction() {
      this.$store.dispatch("moduleExample/selectAuction", this.curAuction.id);
    },
  },
};
</script>
