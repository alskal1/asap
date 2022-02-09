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
import { useRouter } from "vue-router";

export default {
  name: "RoomList",

  components: {
    RoomListItem,
  },
  setup() {
    const $store = useStore();
    const router = useRouter();

    $store.dispatch("moduleExample/updateList");

    function goLive(sessionId) {
      router.push("/live?sessionId=" + sessionId);
    }

    const roomList = computed({
      get: () => $store.state.moduleExample.roomList,
    });

    const info = (room) => {
      // console.log("room : ", room);
      goLive(room.sessionId);
    };

    return {
      roomList,
      info,
      goLive,
    };
  },
};
</script>
