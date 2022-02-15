<template>
  <div class="q-mt-xl">
    <q-list v-if="sellHistoryList.length">
      <sell-list-item
        @intoSellItem="intoSellItem"
        v-for="(sellItem, key) in sellHistoryList"
        :key="key"
        :sellItem="sellItem"
      >
      </sell-list-item>
    </q-list>
    <q-list class="text-center" v-else>
      <div class="text-h6 text-grey">판매내역이 없습니다.</div>
    </q-list>
  </div>
</template>

<script>
import SellListItem from "./child/SellListItem";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";

export default {
  name: "MySellHistory",
  components: {
    SellListItem,
  },
  data() {
    return {
      startDate: "",
      endDate: "",
    };
  },
  setup() {
    const $store = useStore();
    const $q = useQuasar();
    $store.dispatch("user/getSellHistory");

    const sellHistoryList = computed({
      get: () => $store.state.user.sellHistory,
    });

    function intoSellItem(data) {
      const dialog = $q
        .dialog({
          title: `${data.productName}`,
          html: true,
          message: `<div>상품 수 : ${data.productName}</div> <div>배송 상태 : ${data.deliveryState}</div> <div>최종 낙찰가 : ${data.finalPrice}</div> <div>판매 날짜 : ${data.date}</div>`,

          persistent: true,
        })
        .onOk((data) => {
          // console.log('>>>> OK, received', data)
        });
      console.log(data);
      return dialog;
    }
    return {
      sellHistoryList,
      intoSellItem,
    };
  },
};
</script>

<style lang="scss" scoped>
.date {
  width: 300px;
}
</style>
