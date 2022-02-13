<template>
  <section class="test">
    <input type="number" placeholder="금액 입력" v-model="price" />
    <div @click="PaymentBtn">충전</div>
  </section>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
const { IMP } = window;

export default {
  name: "Payment",
  data() {
    return {
      price: 0,
      merchant_uid: " ORD20180131-0000011",
    };
  },
  setup() {
    const $store = useStore();
    const userInfo = ref({});
    const addressInfo = ref({});
    const router = useRouter();

    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      const stateAddressInfo = $store.state.user.userInfo.address;

      const stateAddressInfos = {
        zipCode: stateAddressInfo.zipCode,
        roadAddress: stateAddressInfo.roadAddress,
        detailAddress: stateAddressInfo.detailAddress,
      };

      userInfo.value = stateUserInfo;
      addressInfo.value = stateAddressInfos;
    });

    function chargePoint() {
      const point = {
        price: this.price,
        status: "CHARGE",
      };

      $store.dispatch("user/chargePoint", point).then(() => {
        router.push("/");
      });
    }
    return {
      userInfo,
      addressInfo,
      chargePoint,
    };
  },
  created() {
    document.cookie = "safeCookie1=foo; SameSite=Lax";
    document.cookie = "safeCookie2=foo";
    document.cookie = "crossCookie=bar; SameSite=None; Secure";
  },
  methods: {
    PaymentBtn: function () {
      IMP.init("imp13544545");

      IMP.request_pay(
        {
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: this.merchant_uid++,
          name: "ASAP",
          amount: this.price,
          buyer_email: this.userInfo.email,
          buyer_name: this.userInfo.name,
          buyer_tel: this.userInfo.phone,
          buyer_addr: this.userInfo.roadAddress,
          buyer_postcode: this.userInfo.zipCode,
        },
        (rsp) => {
          console.log(rsp);
          if (rsp.success) {
            console.log("결제 성공");
            this.chargePoint();
          } else {
            console.log("결제 실패", rsp);
          }
        }
      );
    },
  },
};
</script>

<style scoped>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input {
  width: 300px;
  border: 0;
  border-bottom: 1px #a8a8a8 solid;
  margin: 0 10px 0;
  padding: 0;
  height: 40px;
  line-height: 40px;
  outline: none;
}
.test {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
div {
  width: 200px;
  height: 40px;
  background-color: #ffffff;
  border: 1px #a8a8a8 solid;
  color: black;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
</style>
