<template>
  <div class="q-mt-xl">
    <q-list v-if="winHistoryList.length">
      <win-list-item
        @intoWinItem="intoWinItem"
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
    const $q = useQuasar();
    $store.dispatch("user/getWinHistory");

    const winHistoryList = computed({
      get: () => $store.state.user.winHistory,
    });
    function intoWinItem(data) {
      const dialog = $q
        .dialog({
          title: "운송장 번호 등록",
          html: true,
          message: "dd",
          prompt: {
            model: "",
            isValid: (val) => val.length > 2, // << here is the magic
            type: "text", // optional
          },
          cancel: true,
          persistent: true,
        })
        .onOk((data) => {
          // console.log('>>>> OK, received', data)
        });
      console.log(data);
      return dialog;
    }

    return {
      winHistoryList,
      intoWinItem,
    };
  },
};
</script>

<style lang="scss" scoped>
.date {
  width: 300px;
}
</style>
