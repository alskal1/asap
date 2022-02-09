<template>
  <q-page>
    <q-card>
      <!-- <div class="row q-pa-md">
        <div class="q-pa-md q-mr-lg">
          <q-avatar rounded size="200px" color="teal" icon="none" />
        </div>
        <div class="text-h6 q-pa-md">
          <p>무안 황토 고구마 10KG (낙찰 시간 : 2022년 02월 08일 08:13:07)</p>
          <p>낙찰 수량 : 3</p>
          <p>낙찰 포인트 : 8200 포인트</p>

          <p>구매자 정보 : 김아삽</p>
        </div>
      </div> -->
      <div>
        <my-sell-list></my-sell-list>
      </div>
    </q-card>
  </q-page>
</template>

<script>
import MySellList from "./child/MySellList";
import { useStore } from "vuex";

export default {
  name: "MySellHistory",
  components: {
    MySellList,
  },
  setup() {
    const $store = useStore();

    $store.dispatch("user/getSellHistory");

    return {};
  },
};
</script>
