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

        <q-space />
        <div v-if="isLogin" class="q-gutter-sm row items-center no-wrap">
          <q-btn to="/payment" round dense flat color="grey-9" icon="paid">
            <span>{{ user.point }} 포인트</span>
          </q-btn>
          <q-btn to="/room" round dense flat color="grey-9" icon="video_call">
            <q-tooltip>라이브 경매 생성</q-tooltip>
          </q-btn>
          <q-separator vertical />
          <q-btn to="/member" round dense flat color="green-5">
            <q-avatar size="md" icon="account_circle"> </q-avatar>
            <q-tooltip>{{ user.name }} </q-tooltip>
          </q-btn>
          <q-btn
            @click="logout"
            dense
            flat
            color="grey-8"
            class="bg-white-3 rounded-borders"
          >
            로그아웃
          </q-btn>
        </div>
        <div v-else class="q-gutter-sm row items-center no-wrap">
          <q-btn
            to="/auth/login"
            round
            dense
            flat
            color="grey-9"
            icon="video_call"
          >
            <q-tooltip>라이브 경매 생성</q-tooltip>
          </q-btn>
          <q-btn
            to="/auth/login"
            flat
            color="grey-8"
            class="bg-white-3 rounded-borders"
          >
            <span>로그인</span>
          </q-btn>
          <q-btn
            to="/auth/signup"
            color="white"
            class="bg-green-5 rounded-borders"
          >
            <span>회원가입</span>
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
          to="/payment"
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
  <page-footer></page-footer>
</template>

<script>
import { computed, defineComponent, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import PageFooter from "components/PageFooter";

export default defineComponent({
  name: "MainLayout",

  components: {
    PageFooter,
  },

  setup() {
    const $store = useStore();
    const router = useRouter();

    const isLogin = ref(sessionStorage.getItem("jwt"));

    $store.dispatch("user/getUserInfo");

    const user = computed({
      get: () => $store.state.user.userInfo,
    });

    const logout = () => {
      sessionStorage.removeItem("jwt");
      $store.commit("user/setUserInfo", { userInfo: {} });
      isLogin.value = sessionStorage.getItem("jwt");
      router.push("/");
    };

    const reLoad = () => {
      location.replace("/");
    };

    const leftDrawerOpen = ref(false);

    return {
      user,
      isLogin,
      logout,
      leftDrawerOpen,

      reLoad,
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
