<template>
  <q-page padding>
    <div class="row flex justify-center">
      <div class="col-12 q-pa-xs" style="height: 200px; text-align: center">
        <q-avatar
          icon-right="colorize"
          size="250px"
          text-color="green-4"
          icon="account_circle"
        >
        </q-avatar>
      </div>
      <div style="text-align: center" class="q-md-lg col-12 text-h5">
        {{ userInfo.name }}
      </div>
      <div style="text-align: center" class="text-grey q-md-lg col-12 text-h7">
        {{ userInfo.email }}
      </div>
    </div>

    <div class="q-pt-lg">
      <q-tabs
        v-model="mypageTab"
        dense
        class="text-green"
        align="justify"
        narrow-indicator
      >
        <q-tab name="mySell" label="판매내역" />
        <q-tab name="myWin" label="낙찰내역" />
        <q-tab name="myPoint" label="내 포인트" />
        <q-tab name="myInfo" label="내 정보" />
      </q-tabs>
    </div>

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
