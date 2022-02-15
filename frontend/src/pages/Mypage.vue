<template>
  <q-page>
    <q-toolbar class="bg-green-3 text-white">
      <q-btn size="20px" flat>{{ userInfo.name }} 의 페이지</q-btn>
      <q-space />
      <q-tabs v-model="mypageTab">
        <q-tab name="mySell" label="판매내역" />
        <q-tab name="myWin" label="낙찰내역" />
        <q-tab name="myPoint" label="내 포인트" />
        <q-tab name="myInfo" label="내 정보" />
      </q-tabs>
    </q-toolbar>
    <q-separator />

    <q-tab-panels v-model="mypageTab" animated>
      <q-tab-panel name="mySell">
        <my-sell-history />
      </q-tab-panel>
      <q-tab-panel name="myWin">
        <my-win-history />
      </q-tab-panel>
      <q-tab-panel name="myPoint">
        <my-point-history />
      </q-tab-panel>
      <q-tab-panel name="myInfo">
        <my-info-update />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import MyInfoUpdate from "src/components/mypage/MyInfoUpdate";
import MySellHistory from "src/components/mypage/MySellHistory";
import MyWinHistory from "src/components/mypage/MyWinHistory";
import MyPointHistory from "src/components/mypage/MyPointHistory";
import { ref } from "vue";
import { useStore } from "vuex";
export default {
  name: "Mypage",
  components: {
    MyInfoUpdate,
    MySellHistory,
    MyWinHistory,
    MyPointHistory,
  },
  setup() {
    const $store = useStore();
    const userInfo = ref({});

    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      userInfo.value = stateUserInfo;
    });

    return {
      mypageTab: ref("mySell"),
      userInfo,
    };
  },
};
</script>
