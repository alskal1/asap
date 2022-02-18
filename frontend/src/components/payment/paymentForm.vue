<template>
  <q-stepper
    v-model="step"
    ref="stepper"
    alternative-labels
    active-color="green"
    done-color="grey"
    animated
  >
    <q-step :name="1" title="포인트 충전" icon="shopping_cart" :done="done1">
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
              <div class="text-weight-bold text-h7">충전 금액 입력</div>
              <q-input
                outlined
                class="text-h6"
                color="green"
                v-model="price"
                input-class="text-right"
                suffix="₩"
                :rules="[(val) => !isNaN(val) || '숫자를 입력해주세요']"
              />
              <div style="text-align: center">
                <q-checkbox
                  v-model="right"
                  label="상기 결제 내용을 동의합니다."
                />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
      <q-stepper-navigation>
        <div style="text-align: center">
          <q-btn
            v-if="right"
            @click="
              () => {
                done1 = true;
                step = 2;
                price == 0 ? alert() : PaymentBtn();
              }
            "
            color="green"
            label="충전"
          />
        </div>
      </q-stepper-navigation>
    </q-step>

    <q-step :name="2" title="결제 진행" icon="paid" :done="done2">
      <div class="row flex justify-center items-center">
        <h3>결제 진행 중 입니다.</h3>
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
      <q-stepper-navigation>
        <div style="text-align: center">
          <q-btn
            flat
            @click="step = 1"
            color="red"
            label="다시 결제"
            class="q-ml-sm"
          />
        </div>
      </q-stepper-navigation>
    </q-step>

    <q-step :name="3" title="결제 완료" icon="receipt" :done="done3">
      <div class="row flex justify-center items-center">
        <h2>결제가 완료 되었습니다.</h2>
      </div>
      <q-stepper-navigation>
        <div style="text-align: center">
          <q-btn
            flat
            @click="step = 1"
            color="primary"
            label="완료"
            class="q-ml-sm"
          />
        </div>
      </q-stepper-navigation>
    </q-step>
  </q-stepper>
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
const { IMP } = window;

export default {
  name: "PaymentForm",
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
    const $q = useQuasar();
    function alert() {
      $q.dialog({
        title: "결제오류",
        message: "결제 금액이 너무 작습니다.",
        ok: "확인",
        style: "font-family: pocheon_b",
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
    function completePay() {
      $q.dialog({
        style: "font-family: pocheon_b",
        title: "결제 완료",
        message: "결제가 완료 되었습니다. ",
        ok: "확인",
      })
        .onOk(() => {})
        .onCancel(() => {})
        .onDismiss(() => {});
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
      };

      $store.dispatch("user/chargePoint", point).then(() => {
        $store.dispatch("user/getUserInfo").then(() => {
          completePay();
        });
      });
    }
    return {
      alert,
      completePay,
      userInfo,
      addressInfo,
      chargePoint,
      currentPointInfo,
      right: ref(false),
      done1: ref(false),
      done2: ref(false),
      done3: ref(false),
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
            this.step = 3;
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
