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
        <q-btn to="/" flat no-caps no-wrap class="q-ml-xs">
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
          <q-btn to="/room" round dense flat color="grey-8" icon="video_call">
            <q-tooltip>라이브 경매 생성</q-tooltip>
          </q-btn>
          <q-btn @click="logout" round dense flat color="grey-8" icon="logout">
            <q-tooltip>로그아웃</q-tooltip>
          </q-btn>
          <q-btn round flat>
            <q-avatar size="26px">
              <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
            </q-avatar>
            <q-tooltip>Account</q-tooltip>
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
        <q-item v-ripple active-class="my-menu-link" to="/" exact clickable>
          <q-item-section avatar>
            <q-icon name="home" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Home</q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          v-ripple
          active-class="my-menu-link"
          to="/notice"
          exact
          clickable
        >
          <q-item-section avatar>
            <q-icon name="newspaper" />
          </q-item-section>
          <q-item-section>
            <q-item-label>공지사항</q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          v-ripple
          active-class="my-menu-link"
          to="/point"
          exact
          clickable
        >
          <q-item-section avatar>
            <q-icon name="attach_money" />
          </q-item-section>
          <q-item-section>
            <q-item-label>충전하기</q-item-label>
          </q-item-section>
        </q-item>
        <q-item v-ripple active-class="my-menu-link" to="/room" exact clickable>
          <q-item-section avatar>
            <q-icon name="live_tv" />
          </q-item-section>
          <q-item-section>
            <q-item-label>경매등록</q-item-label>
          </q-item-section>
        </q-item>
        <q-item
          v-ripple
          active-class="my-menu-link"
          to="/member"
          exact
          clickable
        >
          <q-item-section avatar>
            <q-icon name="perm_identity" />
          </q-item-section>
          <q-item-section>
            <q-item-label>마이페이지</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent, ref } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  name: "MainLayout",

  components: {},

  setup() {
    const $store = useStore();

    const isLogin = sessionStorage.getItem("jwt");

    const logout = () => {
      sessionStorage.removeItem("jwt");
      $store.commit("user/setUserInfo", { userInfo: null });
    };

    const leftDrawerOpen = ref(false);
    const search = ref("");
    return {
      isLogin,
      logout,
      leftDrawerOpen,
      search,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
});
</script>
<style lang="sass">
.my-menu-link
  color: white
  background: #9fd1a1
</style>
