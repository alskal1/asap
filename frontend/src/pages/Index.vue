<template>
  <q-page padding>
    <banner></banner>
    <div class="row">
      <div class="text-h5 text-bold q-px-md q-pt-sm">현재 진행중인 경매</div>
    </div>
    <q-separator class="q-my-md" />
    <room-list></room-list>
  </q-page>
</template>

<script>
import RoomList from "components/room/RoomList";
import Banner from "components/Banner";
export default {
  name: "PageIndex",
  components: {
    Banner,
    RoomList,
  },
};
</script>
