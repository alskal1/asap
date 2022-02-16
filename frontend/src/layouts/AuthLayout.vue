<template>
  <q-layout>
    <div class="q-pt-xl row flex-start justify-center">
      <div class="col-xs-12 col-sm-12 col-md-12">
        <h1
          @click="Home"
          class="cursor-pointer text-weight-medium text-center text-green-5"
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
