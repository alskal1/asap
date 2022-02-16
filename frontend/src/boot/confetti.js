import { boot } from "quasar/wrappers";
import VueConfetti from "vue-confetti";

export default boot(async ({ app }) => {
  app.use(VueConfetti);
});
