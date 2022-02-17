<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated class="bg-white q-py-xs" height-hint="58">
      <q-toolbar class="bg-white text-green">
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />
        <q-btn @click="reLoad" flat no-caps no-wrap class="q-ml-xs">
          <q-toolbar-title class="text-h4"> ASAP </q-toolbar-title>
        </q-btn>
        <q-space />

        <q-input
          dense
          outlined
          color="green"
          v-model="search"
          placeholder="Search"
          style="width: 30%"
          v-if="$q.screen.gt.xs"
        >
          <template v-slot:prepend>
            <q-icon color="green" v-if="search === ''" name="search" />
            <q-icon
              color="green"
              v-else
              name="clear"
              class="cursor-pointer"
              @click="search = ''"
            />
          </template>
        </q-input>

        <q-space />
        <div v-if="isLogin" class="q-gutter-sm row items-center no-wrap">
          <q-btn
            to="/payment"
            round
            dense
            flat
            color="grey-8"
            icon="attach_money"
          >
            <q-tooltip>현재 포인트: {{ currentPointInfo }}</q-tooltip>
          </q-btn>
          <q-btn to="/room" round dense flat color="grey-8" icon="video_call">
            <q-tooltip>라이브 경매 생성</q-tooltip>
          </q-btn>
          <q-btn @click="logout" round dense flat color="grey-8" icon="logout">
            <q-tooltip>로그아웃</q-tooltip>
          </q-btn>
          <q-btn to="/member" round flat>
            <q-avatar size="md" icon="account_circle"> </q-avatar>
            <q-tooltip>{{ userInfo.name }}</q-tooltip>
          </q-btn>
        </div>
        <div v-else class="q-gutter-sm row items-center no-wrap">
          <q-btn
            to="/auth/login"
            round
            dense
            flat
            color="grey-8"
            icon="video_call"
          >
            <q-tooltip>라이브 경매 생성</q-tooltip>
          </q-btn>
          <q-btn to="/auth/login" round dense flat color="grey-8" icon="login">
            <q-tooltip>로그인</q-tooltip>
          </q-btn>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered :width="240">
      <q-list padding>
        <div v-if="manageMode">
          <q-item
            v-ripple
            active-class="my-menu-link"
            @click="addAuction()"
            exact
            clickable
          >
            <q-item-section avatar>
              <q-icon name="playlist_add" />
            </q-item-section>
            <q-item-section>
              <q-item-label>경매추가</q-item-label>
            </q-item-section>
          </q-item>

          <q-item
            v-ripple
            active-class="my-menu-link"
            @click="addAuctionList()"
            exact
            clickable
          >
            <q-item-section avatar>
              <q-icon name="list" />
            </q-item-section>
            <q-item-section>
              <q-item-label>경매리스트</q-item-label>
            </q-item-section>
          </q-item>
        </div>

        <div v-else>
          <q-item
            v-ripple
            active-class="my-menu-link"
            @click="PointChargingDialog()"
            exact
            clickable
          >
            <q-item-section avatar>
              <q-icon name="attach_money" />
            </q-item-section>
            <q-item-section>
              <q-item-label>경매 충전</q-item-label>
            </q-item-section>
          </q-item>
        </div>
      </q-list>
    </q-drawer>

    <q-page-container>
      <q-dialog v-if="auctionDialog" v-model="auctionDialog">
        <q-card style="background-color: white">
          <div class="q-pa-md q-ml-sm text-h6">경매 추가</div>

          <auction-form @new-auction-added="fetchAuctionList"></auction-form>
        </q-card>
      </q-dialog>

      <q-dialog v-if="auctionListDialog" v-model="auctionListDialog">
        <q-card style="background-color: white">
          <auction-list></auction-list>
        </q-card>
      </q-dialog>

      <q-dialog v-if="PointChargingDialog" v-model="PointChargingDialog">
        <q-card style="background-color: white">
          <auction-list></auction-list>
        </q-card>
      </q-dialog>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import AuctionForm from "components/auction/AuctionForm";
import AuctionList from "components/auction/AuctionList";

import { computed, defineComponent, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { route } from "quasar/wrappers";

export default defineComponent({
  name: "MainLayout",

  components: { AuctionForm, AuctionList },
  data() {
    return {
      auctionDialog: false,
      auctionListDialog: false,
    };
  },
  setup() {
    const $store = useStore();
    const router = useRouter();

    const isLogin = ref(sessionStorage.getItem("jwt"));
    const userInfo = ref({});
    const currentPointInfo = ref({});

    const manageMode = computed({
      get: () => $store.state.user.isManage,
    });

    $store.dispatch("user/getUserInfo").then(() => {
      const _userInfo = $store.state.user.userInfo;
      const currentPoint = $store.state.user.userInfo.point;
      userInfo.value = _userInfo;
      currentPointInfo.value = currentPoint;
    });

    const logout = () => {
      sessionStorage.removeItem("jwt");
      $store.commit("user/setUserInfo", { userInfo: {} });
      isLogin.value = sessionStorage.getItem("jwt");
      location.reload();
      router.push("/");
    };
    const reLoad = () => {
      location.replace("/");
    };

    const leftDrawerOpen = ref(false);
    const search = ref("");
    return {
      isLogin,
      userInfo,
      manageMode,
      logout,
      leftDrawerOpen,
      currentPointInfo,
      search,
      reLoad,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
  methods: {
    addAuction() {
      this.auctionDialog = true;
    },
    addAuctionList() {
      this.auctionListDialog = true;
    },
    fetchAuctionList: function () {
      this.auctionDialog = false;
    },
  },
});
</script>
<style lang="sass">
.my-menu-link
  color: white
  background: #9fd1a1
</style>
