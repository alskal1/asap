<template>
  <q-page padding>
    <div class="row flex justify-center">
      <!-- <q-icon name="notice" /> -->

      <div class="q-pa-md" style="width: 1500px">
        <q-table
          title="공지사항"
          class="text-green text-weight-bold"
          :rows="rows"
          :columns="columns"
          row-key="name"
        />
      </div>
      <!-- <q-img
          class="col-12"
          src="http://drive.google.com/uc?export=view&id=1QIil2QrpP6yPFSbL984Xno090hshlL2X"
          no-native-menu
          :ratio="1"
          style="width: 600px; height: 900px"
        >
          <div class="absolute-top text-center"></div>
        </q-img> -->
    </div>
  </q-page>
</template>

<script>
const columns = [
  {
    name: "num",

    label: "작성번호",
    align: "left",
    field: (row) => row.num,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "title",

    label: "제목",
    align: "left",
    field: (row) => row.title,
    format: (val) => `${val}`,
    sortable: true,
  },

  {
    name: "publisher",
    label: "작성자",
    field: "publisher",
    sortable: true,
    sort: (a, b) => parseInt(a, 10) - parseInt(b, 10),
  },
  {
    name: "date",
    label: "날짜",
    field: "date",
    sortable: true,
    sort: (a, b) => parseInt(a, 10) - parseInt(b, 10),
  },
];

const rows = [
  {
    num: "2",
    title: "ASAP 포인트 충전 안내",

    publisher: "ASAP",
    date: "2022-02-16 00:03",
  },
  {
    num: "1",
    title: "ASAP을 소개 합니다.",

    publisher: "ASAP",
    date: "2022-02-11 10:00",
  },
];
export default {
  name: "Notice",
  setup() {
    return {
      columns,
      rows,
    };
  },
};
</script>
