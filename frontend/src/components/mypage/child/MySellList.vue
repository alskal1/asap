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
          v-for="(sellItem, index) in sellHistoryList"
          :key="index"
          v-bind="sellItem"
        >
          <q-td>{{ sellItem.createdDate }}</q-td>
          <q-td>{{ sellItem.productName }}</q-td>
          <q-td>{{ sellItem.sellerId }}</q-td>
          <q-td>{{ sellItem.count }}</q-td>
          <q-td>{{ sellItem.finalPrice }}</q-td>
          <q-td>{{ sellItem.deliveryState }}</q-td>
        </q-tr>
      </q-table>
    </div>
  </q-page>
</template>

<script>
export default {
  name: "MySellList",
  setup() {
    const $store = useStore();

    onMounted(() => {
      getSellHistory();
    });

    const sellHistoryList = computed({
      get: () => $store.state.user.sellHistory,
    });

    const getSellHistory = () => {
      $store.dispatch("user/getSellHistory");
    };

    console.log(sellHistoryList);

    return {
      $store,
      sellHistoryList,
    };
  },
};
</script>
