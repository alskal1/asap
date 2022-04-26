<br/>

# ASAP(Agriculture & Seafood Auction Platform)
![logo](https://user-images.githubusercontent.com/48577694/165339544-7be16caf-00b5-49de-857e-0097796073e3.png)

> **농수산물 라이브 경매 서비스**  
> **개발기간: 2022.01.04 ~ 2022.02.18**

<br/>

## 🙆‍♂️ 팀원 소개

|김어진|윤숙|오용록|이상윤|이아현|
|:-:|:-:|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/62640679?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/42508120?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/18694745?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/38884368?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/48577694?v=4" width=200>|
|[@EoJin-Kim](https://github.com/EoJin-Kim)|[@djs02027](https://github.com/djs02027)|[@ohyr](https://github.com/ohyr)|[@dltkddbs](https://github.com/dltkddbs)|[@alskal1](https://github.com/alskal1)|

<br/>

## 💻 프로젝트 소개  
  
### 기획

코로나 19 로 인한 농수산물 경매 차질에 대한 우려에 대한 기사와 \
기존 경매 시장의 품질 규격화와 신뢰에 대한 문제점을 다룬 기사를 토대로 \
생산자와 소비자가 직접 온라인으로 경매를 진행할 수 있는 농수산물 라이브 경매 서비스를 기획 및 구현했습니다.

### 경매 방식 소개

신선도가 중요한 농수산물 특성상 빠른 경매 진행을 위해, \
높은 가격에서 부터 시작하는 하향식 경매 방식을 참고했습니다. \
실제 네덜란드 화훼 시장에서 적용 되며 네덜란드식 경매라고도 불립니다.

![경매방식](https://user-images.githubusercontent.com/48577694/165340341-7e510495-01f5-467d-b862-dc2de29d39dc.png)

<br/>



## 🎨 개발환경 및 라이브러리

### Backend
    
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJIDEA&logoColor=white"/> <img src="https://img.shields.io/badge/Gradle-7.3.2-02303A?style=flat-square&logo=Gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring Boot-2.5.8-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring Boot JPA-2.5.8-59666C?style=flat-square&logo=SpringBootJPA&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Java-11-007396?style=flat&logo=Java&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
    
### Frontend
    
<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat-square&logo=Visual Studio Code&logoColor=white"/> <img src="https://img.shields.io/badge/Node-16.13.0-339933?style=flat-square&logo=Node.js&logoColor=white"/>
<img src="https://img.shields.io/badge/Npm-8.3.2-CB3837?style=flat-square&logo=Npm&logoColor=white"/>
<img src="https://img.shields.io/badge/Vue-3.0-4FC08D?style=flat-square&logo=Vue.js&logoColor=white"/>
<img src="https://img.shields.io/badge/Quasar-2.0-1976D2?style=flat-square&logo=Quasar&logoColor=white"/>
<img src="https://img.shields.io/badge/VueRouter-4.0-4FC08D?style=flat-square&logo=Vue.js&logoColor=white"/>
<img src="https://img.shields.io/badge/Vuex-4.0-4FC08D?style=flat-square&logo=Vue.js&logoColor=white"/>
    
### Web RTC

<img src="https://img.shields.io/badge/OpenVidu 2.20.0-333333?style=flat-square&logo=WebRTC&logoColor=white"/>
 
### Deploy

<img src="https://img.shields.io/badge/AWS-232F3E?style=flat-square&logo=amazon aws&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/>
<img src="https://img.shields.io/badge/NGINX-009639?style=flat-square&logo=NGINX&logoColor=white"/>
    
<br/>

## 🕹 프로젝트 주요 기능

> **메인 페이지**

![메인](https://user-images.githubusercontent.com/48577694/165340332-81fdf9bd-1199-4b7e-bf8d-ba781d43cd19.png)

> **회원가입 및 로그인**

![회원가입](https://user-images.githubusercontent.com/48577694/165340334-a0ac773f-9d60-4c69-af26-7652599ea7f6.png)
![로그인](https://user-images.githubusercontent.com/48577694/165340327-07d370c4-2ba3-49e7-94bf-5685f52d5185.png)

> **마이페이지**

![마이페이지](https://user-images.githubusercontent.com/48577694/165340323-6c0b8483-9db1-4c68-a7f3-13c4b74c1ea2.png)

> **포인트 충전 - 아임포트 결제 연동**

![포인트충전1](https://user-images.githubusercontent.com/48577694/165340319-e089956f-c836-41c3-a71c-f90171b4b2bc.png)
![아임포트](https://user-images.githubusercontent.com/48577694/165340275-a18c5019-1e5b-4480-849b-1c3b3791e694.png)
![충전완료](https://user-images.githubusercontent.com/48577694/165340343-63acd313-8134-4d06-8149-700cb0e043a2.png)
![충전내역](https://user-images.githubusercontent.com/48577694/165340317-986666b2-b839-42cf-8255-557db02675ed.png)

> **경매 참여**

![경매참여1)](https://user-images.githubusercontent.com/48577694/165340313-265739ed-2dc6-4651-bf09-a0c72051b83d.png)
![낙찰](https://user-images.githubusercontent.com/48577694/165340306-ebb074b3-0057-4b81-88c2-215bf2788460.png)
![낙찰내역](https://user-images.githubusercontent.com/48577694/165340302-8fa8128a-5692-4e20-944e-ce1822925400.png)

> **경매 라이브 개설**

![경매방개설](https://user-images.githubusercontent.com/48577694/165340299-81d29453-b857-488b-b49d-b863bfa4afc3.png)

> **경매 상품 등록**

![상품추가](https://user-images.githubusercontent.com/48577694/165340296-dc573e4f-40f7-482d-8655-f4a0458580aa.png)
![상품선택](https://user-images.githubusercontent.com/48577694/165340293-9e2e4490-0345-4450-ae04-f0a6a9428040.png)

> **경매 진행**

![경매진행](https://user-images.githubusercontent.com/48577694/165340288-a9bd3682-994a-45a4-9a71-bfb72a7521b3.png)
![상품판매](https://user-images.githubusercontent.com/48577694/165340283-1f624812-09c9-46b9-8bff-4012aff7428e.png)

> **판매내역**

![판매내역](https://user-images.githubusercontent.com/48577694/165340281-caf90d8e-047a-4982-b781-6f1bd948f742.png)

## 🏗️프로젝트 구성

### 서비스 아키텍쳐
![아키텍쳐](https://user-images.githubusercontent.com/48577694/165340267-601e81c1-16a0-4664-a21c-c4cd526a285b.png)

### 동작 플로우
![동작플로우](https://user-images.githubusercontent.com/48577694/165340279-dd3f1e1e-2e3a-4673-96ff-203ab4c7b49e.png)
