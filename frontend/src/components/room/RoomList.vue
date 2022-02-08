<template>
  <q-page>
    <q-list class="row">
      <room-list-item
        class="col-md"
        v-for="(room, key) in roomList"
        :key="key"
        :room="room"
        @click="info(room)"
      >
      </room-list-item>
    </q-list>
  </q-page>
</template>

<script>
import RoomListItem from "./RoomListItem";
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "RoomList",

  components: {
    RoomListItem,
  },
  setup() {
    const $store = useStore();

    $store.dispatch("moduleExample/updateList");

    const roomList = computed({
      get: () => $store.state.moduleExample.roomList,
    });

    const info = (room) => {
      console.log("Room : ", room);
    };

    return {
      roomList,
      info,
    };
  },
};
</script>
