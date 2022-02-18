<template>
  <q-card>
    <div v-if="auctionList.length == 0">
      <q-card-section class="row items-center no-wrap">
        <div>
          <div class="text-weight-bold">
            진행 예정 경매가 존재하지 않습니다.
          </div>
        </div>
      </q-card-section>
    </div>
    <auction-list-item
      v-else
      v-for="(auction, index) in auctionList"
      :key="auction.id"
      :auction="auction"
      @delete-auction="deleteAuction(index)"
    >
    </auction-list-item>
  </q-card>
</template>

<script>
import { useStore } from "vuex";
import { Notify } from "quasar";
import AuctionListItem from "./AuctionListItem";

export default {
  name: "AuctionList",
  components: {
    AuctionListItem,
  },
  created() {
    this.$store.dispatch(
      "moduleExample/selectAllAuctions",
      this.$store.state.user.userInfo.email.replace("@", "-").replace(".", "-")
    );
  },
  computed: {
    auctionList: function () {
      const $store = useStore();
      return $store.state.moduleExample.auctionList;
    },
  },
  methods: {
    deleteAuction(index) {
      const obj = this.auctionList[index];

      if (this.$store.state.moduleExample.curAuction.id == obj.id) {
        this.notifyError();
      } else {
        this.$store.dispatch("moduleExample/deleteAuction", obj.id).then(() => {
          this.$store.dispatch(
            "moduleExample/selectAllAuctions",
            this.$store.state.user.userInfo.email
              .replace("@", "-")
              .replace(".", "-")
          );
        });
      }
    },

    notifyError() {
      Notify.create({
        message: "현재 진행중인 경매는 삭제할 수 없습니다",
        color: "red",
        icon: "announcement",
        position: "top",
      });
    },
  },
};
</script>
