<template>
  <div class="q-pa-md">
    <div>
      <q-list v-if="pointHistoryList.length">
        <point-list-item
          v-for="(pointItem, key) in pointHistoryList"
          :key="key"
          :pointItem="pointItem"
        >
        </point-list-item>
      </q-list>
      <q-list class="text-center" v-else>
        <h6 class="text-grey">포인트 내역이 없습니다.</h6>
      </q-list>
    </div>
  </div>
</template>

<script>
import PointListItem from "./child/PointListItem";
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "MyPointHistory",
  components: {
    PointListItem,
  },
  setup() {
    const $store = useStore();

    $store.dispatch("user/getPointHistory");

    const pointHistoryList = computed({
      get: () => $store.state.user.pointHistory,
    });

    return {
      pointHistoryList,
    };
  },
};
</script>

<style lang="scss" scoped></style>
