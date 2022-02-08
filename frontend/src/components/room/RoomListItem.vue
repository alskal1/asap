<template>
  <div class="q-pa-md">
    <q-card>
      <img src="https://cdn.quasar.dev/img/mountains.jpg" />
      <q-card-section>
        <div class="text-h6">{{ room.title }}</div>
        <div class="text-subtitle2">{{ room.description }}</div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
export default {
  name: "RoomListItem",
  props: {
    room: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>
