<template>
  <q-page padding>
    <!-- <p>마이페이지</p> -->
    <q-tabs
      v-model="mypageTab"
      dense
      class="text-gray"
      align="justify"
      narrow-indicator
    >
      <q-tab name="myInfo" label="내 정보" />
      <q-tab name="mySell" label="판매내역" />
      <q-tab name="myWin" label="낙찰내역" />
      <q-tab name="myPoint" label="내 포인트" />
    </q-tabs>
    <q-separator />

    <q-tab-panels v-model="mypageTab" animated>
      <q-tab-panel name="myInfo">
        <my-info-update />
      </q-tab-panel>
      <q-tab-panel name="mySell">
        <my-sell-history />
      </q-tab-panel>
      <q-tab-panel name="myWin">
        <my-win-history />
      </q-tab-panel>
      <q-tab-panel name="myPoint">
        <my-point-history />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
// import MyProfileInfo from "src/components/mypage/MyProfileInfo";
import MyInfoUpdate from "src/components/mypage/MyInfoUpdate";
import MySellHistory from "src/components/mypage/MySellHistory";
import MyWinHistory from "src/components/mypage/MyWinHistory";
import MyPointHistory from "src/components/mypage/MyPointHistory";
import { ref } from "vue";
export default {
  name: "Mypage",
  setup() {
    return {
      mypageTab: ref("myInfo"),
    };
  },
  components: {
    // MyProfileInfo,
    MyInfoUpdate,
    MySellHistory,
    MyWinHistory,
    MyPointHistory,
  },
};
</script>
