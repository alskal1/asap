<template>
  <q-layout>
    <div class="row flex-start justify-center">
      <div class="col-xs-12 col-sm-12 col-md-12">
        <h1
          @click="Home"
          class="cursor-pointer text-bold text-center text-green"
        >
          ASAP
        </h1>

        <q-page-container>
          <router-view />
        </q-page-container>
      </div>
    </div>
  </q-layout>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
export default {
  // name: 'LayoutName',

  setup() {
    const leftDrawerOpen = ref(false);
    const router = useRouter();

    const Home = () => {
      router.push("/");
    };

    return {
      leftDrawerOpen,
      Home,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
};
</script>
