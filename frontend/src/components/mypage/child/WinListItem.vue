<template>
  <q-card class="q-pa-md q-mb-lg">
    <q-dialog v-model="findProduct">
      <q-card style="width: 500px">
        <q-card-section>
          <div padding class="text-h6">배송 조회</div>
        </q-card-section>
        <q-form
          action="http://info.sweettracker.co.kr/tracking/5?t_key=9E7QuaGXyIVWiccS3fNTJQ"
          method="post"
          target="_blank"
        >
          <div class="flex justify-center">
            <div class="form-group" style="width: 300px">
              <q-select
                v-model="preferred"
                :options="options"
                color="primary"
                type="text"
                class="form-control"
                name="t_code"
                id="t_code"
              />
              <!-- <q-input
                type="text"
                class="form-control"
                name="t_code"
                id="t_code"
                placeholder="택배사 코드"
              /> -->
            </div>
            <div class="form-group" style="width: 300px">
              <q-input
                type="text"
                class="form-control"
                name="t_invoice"
                id="t_invoice"
                placeholder="운송장 번호"
                v-model="t_invoice"
              />
            </div>
          </div>

          <br />
          <div style="text-align: center">
            <q-btn type="submit" class="btn btn-default text-primary"
              >조회하기</q-btn
            >
          </div>
          <br />
        </q-form>
      </q-card>
    </q-dialog>

    <q-item>
      <q-item-section>
        <div class="text-h6 text-primary">낙찰확정</div>

        <q-item-label>낙찰 날짜 : {{ winItem.date }}</q-item-label>
        <q-item-label>상품 : {{ winItem.productName }}</q-item-label>
        <q-item-label>판매자 : {{ winItem.sellerId }}</q-item-label>
        <q-item-label>최종 낙찰가 : {{ winItem.finalPrice }}</q-item-label>
        <q-item-label>배송 상태 : {{ winItem.deliveryState }}</q-item-label>
      </q-item-section>

      <q-item-section @click="findProduct = true" side class="text-green"
        >배송조회
      </q-item-section>
      <!-- <q-item-section v-else side class="text-primary"
        >{{ winItem.deliveryState }}
      </q-item-section> -->
    </q-item>
  </q-card>
</template>

<script>
import { api } from "boot/axios";
import { ref } from "vue";

export default {
  name: "WinListItem",
  props: {
    winItem: {
      type: Object,
    },
  },
  setup(props) {
    return {
      findProduct: ref(false),
      t_code: ref(""),
      t_invoice: ref(""),
      preferred: ref({ label: "CJ대한통운", value: "04" }),
      options: [
        {
          label: "CJ대한통운",
          value: "04",
        },
        {
          label: "우체국택배",
          value: "01",
        },
        {
          label: "한진택배",
          value: "05",
        },
        {
          label: "롯데택배",
          value: "08",
        },
        {
          label: "로젠택배",
          value: "06",
        },
        {
          label: "일양로지스",
          value: "11",
        },
      ],
    };
  },
};
</script>
