<template>
  <q-page padding>
    <div class="row">
      <div class="text-h5 text-bold q-px-md q-pt-sm">경매 라이브 개설</div>
    </div>
    <q-separator class="q-my-md" />

    <room-form></room-form>
  </q-page>
</template>

<script>
import RoomForm from "components/room/RoomForm";

export default {
  name: "Room",
  components: {
    RoomForm,
  },
};
</script>
