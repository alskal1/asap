<template>
  <q-page>
    <div>
      <table>
        <tr>
          <th>경매일자</th>
          <th>품목명</th>
          <th>판매자명</th>
          <th>수량</th>
          <th>금액</th>
          <th>상태</th>
        </tr>
        <tr
          v-for="(winItem, key) in winHistoryList"
          :key="key"
          v-bind="winItem"
        >
          <td>{{ winItem.date }}</td>
          <td>{{ winItem.productName }}</td>
          <td>{{ winItem.sellerId }}</td>
          <td>{{ winItem.count }}</td>
          <td>{{ winItem.finalPrice }}</td>
          <td>{{ winItem.deliveryState }}</td>
        </tr>
      </table>
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

    const winHistoryList = computed({
      get: () => $store.state.user.winHistory,
    });

    const getWinHistory = () => {
      $store.dispatch("user/getWinHistory");
    };

    return {
      $store,
      winHistoryList,
    };
  },
};
</script>
