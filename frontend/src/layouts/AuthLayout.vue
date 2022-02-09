<template>
  <q-layout>
    <div class="row flex-start justify-center">
      <div class="column items-center">
        <q-responsive :ratio="6 / 4" style="width: 500px" class="col">
          <div>
            <h1 class="text-bold text-center text-green">ASAP</h1>

            <q-page-container>
              <router-view />
            </q-page-container>
          </div>
        </q-responsive>
      </div>
    </div>
  </q-layout>
</template>

<script>
import { ref } from "vue";

export default {
  // name: 'LayoutName',

  setup() {
    const leftDrawerOpen = ref(false);

    return {
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
};
</script>
