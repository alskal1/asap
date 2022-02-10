<template>
  <q-page>
    <q-card class="q-pa-md">
      <div class="q-pa-md">
        <div class="row">
          <div class="col">
            <q-input
              class="date"
              v-model="startDate"
              label="경매일자"
              stack-label
              type="date"
            />
          </div>

          <div class="col">
            <q-input
              class="date"
              v-model="endDate"
              label="경매일자"
              stack-label
              type="date"
            />
          </div>
          <div class="col">
            <div class="q-pa-lg">
              <q-option-group
                v-model="group"
                :options="options"
                color="green"
                inline
              />
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <q-input
              class="q-mr-md"
              outlined
              color="green"
              v-model="text"
              label="품목명"
              stack-label
            />
          </div>
          <div class="col">
            <q-input
              class="q-mr-md"
              outlined
              color="green"
              v-model="text"
              label="구매자명"
              stack-label
            />
          </div>
          <div class="col">
            <q-input
              class="q-mr-md"
              outlined
              color="green"
              v-model="text"
              label="상태"
              stack-label
            />
          </div>
        </div>
        <div class="text-center">
          <q-btn class="q-mt-lg" outline color="green">검색</q-btn>
        </div>
      </div>
    </q-card>
    <div class="q-mt-xl">
      <q-list class="text-center" v-if="sellHistoryList.length">
        <q-item class="text-white bg-green-3">
          <q-item-section class="col-2">경매 일자</q-item-section>
          <q-item-section class="col-3">상품명</q-item-section>
          <q-item-section class="col-2">구매자</q-item-section>
          <q-item-section class="col-1">수량</q-item-section>
          <q-item-section class="col-2">낙찰 금액</q-item-section>
          <q-item-section class="col-1">주문 상태</q-item-section>
        </q-item>
        <sell-list-item
          v-for="(sellItem, key) in sellHistoryList"
          :key="key"
          :sellItem="sellItem"
        >
        </sell-list-item>
      </q-list>
      <q-list class="text-center" v-else>
        <h6 class="text-grey">판매내역이 없습니다.</h6>
      </q-list>
    </div>
  </q-page>
</template>

<script>
import SellListItem from "./child/SellListItem";
import { ref, computed } from "vue";
import { useStore } from "vuex";

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

    $store.dispatch("user/getSellHistory");

    const sellHistoryList = computed({
      get: () => $store.state.user.sellHistory,
    });

    return {
      sellHistoryList,
      group: ref("op1"),

      options: [
        {
          label: "당일",
          value: "day1",
        },
        {
          label: "7일",
          value: "day7",
        },
        {
          label: "1달",
          value: "day30",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.date {
  width: 300px;
}
</style>
