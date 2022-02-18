<template>
  <q-page>
    <payment-form></payment-form>
  </q-page>
</template>

<script>
import paymentForm from "components/payment/paymentForm";
export default {
  name: "Payment",
  components: {
    paymentForm,
  },
  setup() {
    return {};
  },
};
</script>
