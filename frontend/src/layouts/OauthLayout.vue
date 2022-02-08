<template>
  <q-layout>
    <!-- Be sure to play with the Layout demo on docs -->
    <div class="row flex-start justify-center">
      <div class="column items-center">
        <q-responsive :ratio="6 / 4" style="width: 500px" class="col">
          <div>
            <h1 class="text-bold text-center text-green">ASAP</h1>

            <q-page-container>
              <!-- This is where pages get injected -->
              <router-view />
            </q-page-container>
          </div>

          <!-- <q-item>
      <q-tabs>
        <q-route-tab icon="assignment" to="/signup" replace label="Other Tab" />
      </q-tabs>
    </q-item> -->
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
