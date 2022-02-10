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
              label="판매자명"
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
    <div>
      <q-list class="row" v-if="winHistoryList.length">
        <win-list-item
          class="col-md"
          v-for="(winItem, key) in winHistoryList"
          :key="key"
          :winItem="winItem"
        >
        </win-list-item>
      </q-list>
      <q-list class="text-center" v-else>
        <h6>낙찰내역이 없습니다.</h6>
      </q-list>
    </div>
  </q-page>
</template>

<script>
import WinListItem from "./child/WinListItem";
import { ref, computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "MyWinHistory",
  components: {
    WinListItem,
  },
  data() {
    return {
      startDate: "",
      endDate: "",
    };
  },
  setup() {
    const $store = useStore();

    $store.dispatch("user/getWinHistory");

    $store.dispatch("user/getWinHistory");

    const winHistoryList = computed({
      get: () => $store.state.user.winHistory,
    });

    return {
      winHistoryList,

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
