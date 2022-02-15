<template>
  <div class="q-mt-xl">
    <q-list v-if="winHistoryList.length">
      <win-list-item
        v-for="(winItem, key) in winHistoryList"
        :key="key"
        :winItem="winItem"
      >
      </win-list-item>
    </q-list>
    <q-list class="text-center" v-else>
      <h6 class="text-grey">낙찰내역이 없습니다.</h6>
    </q-list>
  </div>
</template>

<script>
import WinListItem from "./child/WinListItem";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
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

    const winHistoryList = computed({
      get: () => $store.state.user.winHistory,
    });

    return {
      winHistoryList,
    };
  },
};
</script>

<style lang="scss" scoped>
.date {
  width: 300px;
}
</style>
