<template>
  <q-page>
    <q-list class="row">
      <room-list-item class="col-md" v-for="i in 5" :key="i"> </room-list-item>
    </q-list>
  </q-page>
</template>

<script>
import RoomListItem from "./RoomListItem";
import { useStore } from "vuex";

export default {
  name: "AuctionList",
  components: {
    RoomListItem,
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
