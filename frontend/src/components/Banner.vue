<template>
  <div>
    <q-carousel
      animated
      v-model="slide"
      navigation
      infinite
      arrows
      :autoplay="autoplay"
      transition-prev="slide-right"
      transition-next="slide-left"
      @mouseenter="autoplay = false"
      @mouseleave="autoplay = true"
      height="23vmin"
      control-color="grey"
    >
      <q-carousel-slide :name="1" img-src="~assets/images/banner1.png" />
      <q-carousel-slide :name="2" img-src="~assets/images/banner2.png" />
      <q-carousel-slide :name="3" img-src="~assets/images/banner3.png" />
    </q-carousel>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "Banner",
  setup() {
    return {
      slide: ref(1),
      autoplay: ref(2000),
    };
  },
};
</script>
