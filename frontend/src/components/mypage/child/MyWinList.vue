<template>
  <q-table class="q-mt-md" :rows="rows" :columns="columns" row-key="name" />
</template>

<script>
const columns = [
  {
    name: "auctionDate",
    align: "center",
    label: "경매일자",
    field: "auctionDate",
  },
  { name: "productName", align: "center", label: "품목명", field: "name" },
  { name: "seller", align: "center", label: "판매자명", field: "seller" },
  { name: "quantity", align: "center", label: "수량", field: "sodium" },
  { name: "state", align: "center", label: "상태", field: "sodium" },
];

const rows = [
  {
    name: "Frozen Yogurt",
    auctionDate: 20220208,
    name: 7.0,
    carbs: 24,
    protein: 4.0,
  },
  {
    name: "Ice cream sandwich",
    auctionDate: 20220208,
    name: 9.0,
    carbs: 37,
    protein: 4.3,
  },
  {
    name: "Eclair",
    auctionDate: 20220208,
    name: 16.0,
    carbs: 23,
    protein: 6.0,
  },
  {
    name: "Cupcake",
    auctionDate: 20220208,
    name: 3.7,
    carbs: 67,
    protein: 4.3,
  },
];
export default {
  name: "MyWinList",
  setup() {
    return {
      columns,
      rows,
    };
  },
};
</script>
