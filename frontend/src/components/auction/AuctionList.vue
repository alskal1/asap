<template>
  <q-page>
    <q-list class="row">
      <auction-list-item class="col-md" v-for="i in 5" :key="i">
      </auction-list-item>
    </q-list>
  </q-page>
</template>

<script>
import AuctionListItem from "./AuctionListItem.vue";
import { useStore } from "vuex";

export default {
  name: "AuctionList",
  components: {
    AuctionListItem,
  },
  created() {
    this.updateList();
  },
  setup() {
    const updateList = () => {
      useStore().dispatch("moduleExample/updateList");
    };

    return { updateList };
  },
};
</script>
