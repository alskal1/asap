<template>
  <q-page>
    <div>낙찰 내역</div>
    <q-card class="q-pa-md">
      <div class="q-pa-md">
        <div class="row">
          <div class="col">
            경매일자
            <q-input v-model="date" filled type="date" /> ~
            <q-input v-model="date" filled type="date" />
          </div>
          <div class="col">.col</div>
          <div class="col">
            상태<q-input
              outlined
              v-model="ph"
              placeholder="검색어를 입력하세요"
              :dense="dense"
            />
          </div>
        </div>
        <div class="row">
          <div class="col">
            품목명
            <q-input
              outlined
              v-model="ph"
              placeholder="검색어를 입력하세요"
              :dense="dense"
            />
          </div>
          <div class="col">
            판매자명<q-input
              outlined
              v-model="ph"
              placeholder="검색어를 입력하세요"
              :dense="dense"
            />
          </div>
          <div class="col">
            낙찰번호<q-input
              outlined
              v-model="ph"
              placeholder="검색어를 입력하세요"
              :dense="dense"
            />
          </div>
        </div>
        <div class="row">
          <div class="col"><q-btn>검색</q-btn></div>
        </div>
      </div>
    </q-card>
  </q-page>
</template>

<script>
export default {
  name: "MyWinHistory",
};
</script>
