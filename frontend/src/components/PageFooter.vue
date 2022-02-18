<template>
  <div class="footer">
    <footer>
      <div class="first">
        <span>서비스 소개</span>
        <span>고객센터</span>
        <span>자주 묻는 질문</span>
        <span>카카오톡 문의</span>
      </div>

      <div class="second">
        <p>
          ASAP &emsp;|&emsp; 대표 : 어진 없는 어진팀<br />서울특별시 강남구
          역삼동 테헤란로 212 &emsp;|&emsp; TEL : 010-1234-5678
        </p>
      </div>
      <div>이용약관 &emsp;|&emsp; 개인정보처리방침</div>
      <p>© 2022. SSAFY ALL right reserved.</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: "PageFooter",
  setup() {
    return {};
  },
};
</script>

<style scoped>
.footer {
  width: 100%;
  border: 1px solid #e8eaed;
  padding: 24px;
  background-color: #fcfcfc;
  color: #747474;
}
footer {
  width: 1200px;
  padding: 20px;
  flex-direction: column;
}

.second {
  width: 100%;
  margin-top: 24px;
}

span {
  margin-right: 20px;
}
</style>
