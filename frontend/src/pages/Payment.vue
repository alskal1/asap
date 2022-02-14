<template>
  <q-page>
    <q-stepper
      v-model="step"
      ref="stepper"
      alternative-labels
      color="primary"
      animated
    >
      <q-step
        :name="1"
        title="포인트 충전"
        icon="shopping_cart"
        :done="step > 1"
      >
        <div class="flex justify-center items-center">
          <q-card style="width: 800px; padding: 20px">
            <q-card-section>
              <div class="text-weight-bold text-h6">현재 잔액 포인트</div>
              <!-- <q-input class="text-left" v-model="currentPointInfo" disable /> -->
              <div class="text-right text-weight-bold text-h6 text-green">
                {{ currentPointInfo }} 포인트
              </div>
            </q-card-section>
            <q-card-section>
              <q-form @submit.prevent="PaymentBtn" class="q-gutter-md">
                <div class="text-weight-bold text-h6">충전 금액 입력</div>
                <q-input
                  filled
                  color="green"
                  v-model="price"
                  :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
                />
                <div style="text-align: center">
                  <q-checkbox
                    v-model="right"
                    label="상기 결제 내용을 동의합니다."
                  />
                </div>
                <!-- <div style="text-align: center">
                  <q-btn
                    v-if="right"
                    label="충전하기"
                    type="submit"
                    color="green"
                  />
                </div> -->
              </q-form>
            </q-card-section>
          </q-card>
        </div>
      </q-step>

      <q-step :name="2" title="결제" icon="paid" :done="step > 2">
        <div class="row flex justify-center items-center">
          <h2>결제 중 입니다.</h2>
          <div>
            <q-circular-progress
              indeterminate
              size="90px"
              :thickness="0.2"
              color="green"
              center-color="white"
              track-color="transparent"
              class="q-ma-md"
            />
          </div>
        </div>
        <div style="text-align: center">
          <p>결제가 안되신다면 다시 결제를 눌러주세요</p>
        </div>
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation style="text-align: center">
          <q-btn
            v-if="right"
            @click="
              () => {
                step = 2;
                price == 0 ? alert() : PaymentBtn();
              }
            "
            color="green"
            :label="step === 2 ? '다시 결제' : '결제'"
          />
          <q-btn
            v-if="step > 1"
            flat
            color="primary"
            @click="$refs.stepper.previous()"
            label="결제취소"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </q-page>
</template>

<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
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
    const currentPointInfo = ref({});
    const router = useRouter();
    const $q = useQuasar();

    function alert() {
      $q.dialog({
        title: "결제오류",
        message: "결제 금액이 너무 작습니다.",
      })
        .onOk(() => {
          // console.log('OK')
        })
        .onCancel(() => {
          // console.log('Cancel')
        })
        .onDismiss(() => {
          // console.log('I am triggered on both OK and Cancel')
        });
    }
    $store.dispatch("user/getUserInfo").then(() => {
      const stateUserInfo = {
        email: $store.state.user.userInfo.email,
        name: $store.state.user.userInfo.name,
        phone: $store.state.user.userInfo.phone,
      };

      const stateAddressInfo = $store.state.user.userInfo.address;
      const currentPoint = $store.state.user.userInfo.point;
      const stateAddressInfos = {
        zipCode: stateAddressInfo.zipCode,
        roadAddress: stateAddressInfo.roadAddress,
        detailAddress: stateAddressInfo.detailAddress,
      };

      userInfo.value = stateUserInfo;
      addressInfo.value = stateAddressInfos;
      currentPointInfo.value = currentPoint;
    });

    function chargePoint() {
      const point = {
        price: this.price,
        status: "CHARGE",
      };

      $store.dispatch("user/chargePoint", point).then(() => {
        location.reload();
        router.push("/payment");
      });
    }
    return {
      alert,
      userInfo,
      addressInfo,
      chargePoint,
      currentPointInfo,
      right: ref(false),
      step: ref(1),
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
/* input[type="number"]::-webkit-outer-spin-button,
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
} */
/* .test {
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
} */
</style>
