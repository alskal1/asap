<template>
  <q-page padding>
    <div class="row flex justify-center">
      <!-- <q-icon name="notice" /> -->
      <div class="col-12 q-pa-md">
        <q-table
          title="공지사항"
          class="text-green text-weight-bold"
          :rows="rows"
          :columns="columns"
          row-key="name"
          v-model:pagination="pagination"
          hide-pagination
        />
        <div class="row justify-center q-mt-md">
          <q-pagination
            v-model="pagination.page"
            color="green-4"
            :max="pagesNumber"
            size="sm"
          />
        </div>
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
    <div class="q-gutter-md">
      <div class="row flex justify-center">
        <q-btn class="text-white col-2" color="green" size="xl" @click="glad"
          >환영합니다!</q-btn
        >
      </div>
      <div class="row flex justify-center">
        <q-btn class="text-white col-2" color="pink" size="xl" @click="love"
          >핑크핑크</q-btn
        >
      </div>
      <div class="row flex justify-center">
        <q-btn class="text-white col-2" color="grey" size="xl" @click="stop"
          >멈추기</q-btn
        >
      </div>
    </div>
  </q-page>
</template>

<script>
import { ref, computed } from "vue";

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
    const pagination = ref({
      sortBy: "desc",
      descending: false,
      page: 1,
      rowsPerPage: 5,
      // rowsNumber: xx if getting data from a server
    });
    return {
      pagination,
      columns,
      rows,

      pagesNumber: computed(() =>
        Math.ceil(rows.length / pagination.value.rowsPerPage)
      ),
    };
  },
  methods: {
    glad() {
      this.$confetti.start();
    },
    stop() {
      this.$confetti.stop();
    },
    love() {
      this.$confetti.update({
        particles: [
          {
            type: "heart",
          },
          {
            type: "circle",
          },
        ],
        defaultColors: ["red", "pink", "#ba0000"],
      });
    },
  },
};
</script>
