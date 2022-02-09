<template>
  <q-page>
    <div>
      <q-table>
        <q-tr>
          <q-th>경매일자</q-th>
          <q-th>품목명</q-th>
          <q-th>판매자명</q-th>
          <q-th>수량</q-th>
          <q-th>금액</q-th>
          <q-th>상태</q-th>
        </q-tr>
        <q-tr
          v-for="(winItem, index) in winHistoryList"
          :key="index"
          v-bind="winItem"
        >
          <q-td>{{ winItem.createdDate }}</q-td>
          <q-td>{{ winItem.productName }}</q-td>
          <q-td>{{ winItem.sellerId }}</q-td>
          <q-td>{{ winItem.count }}</q-td>
          <q-td>{{ winItem.finalPrice }}</q-td>
          <q-td>{{ winItem.deliveryState }}</q-td>
        </q-tr>
      </q-table>
    </div>
  </q-page>
</template>

<script>
import { computed, onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";

export default {
  name: "MyWinList",
  setup() {
    const $store = useStore();

    onMounted(() => {
      getWinHistory();
    });

    const winHistoryList = computed({
      get: () => $store.state.user.winHistory,
    });

    const getWinHistory = () => {
      $store.dispatch("user/getWinHistory");
    };

    console.log(winHistoryList);

    return {
      $store,
      winHistoryList,
    };
  },
};
</script>
