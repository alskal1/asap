<template>
  <q-page>
    <div>
      <table>
        <tr>
          <th>경매일자</th>
          <th>품목명</th>
          <th>수량</th>
          <th>금액</th>
          <th>상태</th>
        </tr>
        <tr
          v-for="(sellItem, index) in sellHistoryList"
          :key="index"
          v-bind="sellItem"
        >
          <td>{{ sellItem.date }}</td>
          <td>{{ sellItem.productName }}</td>
          <td>{{ sellItem.count }}</td>
          <td>{{ sellItem.finalPrice }}</td>
          <td>{{ sellItem.deliveryState }}</td>
        </tr>
      </table>
    </div>
  </q-page>
</template>

<script>
import { computed, onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";

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
