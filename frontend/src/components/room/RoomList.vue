<template>
  <q-page padding>
    <q-list class="row">
      <room-list-item
        class="col-xs-12 col-sm-6 col-md-3"
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
      $store.commit("user/setIsManage", false);
      // $store.dispatch(
      //   "moduleExample/selectCurrentAuction",
      //   sessionId.replace("@", "-").replace(".", "-")
      // );
      router.push("/live/publisher?sessionId=" + sessionId);
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
