# ASAP(Agriculture & Seafood Auction Platform)
![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/dc1515d6-8967-4a91-bd96-2c5ba11e42e6/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220329%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220329T013958Z&X-Amz-Expires=86400&X-Amz-Signature=09ba645dcad0c699c314455dbf09e0b0437982fc8522e5d5f248f2f6efbd8e7f&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)
> **농수산물 라이브 경매 서비스**  
> **개발기간: 2022.01.04 ~ 2022.02.18**

## 💻 프로젝트 소개  
  
### 기획

코로나 19 로 인한 농수산물 경매 차질에 대한 우려에 대한 기사와 \
기존 경매 시장의 품질 규격화와 신뢰에 대한 문제점을 다룬 기사를 토대로 \
생산자와 소비자가 직접 온라인으로 경매를 진행할 수 있는 농수산물 라이브 경매 서비스를 기획 및 구현했습니다.

### 경매 방식 소개

신선도가 중요한 농수산물 특성상 빠른 경매 진행을 위해, \
높은 가격에서 부터 시작하는 하향식 경매 방식을 참고했습니다. \
실제 네덜란드 화훼 시장에서 적용 되며 네덜란드식 경매라고도 불립니다.


![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b0e4d4c9-fac3-4e29-a6e8-6e246fcaa860/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220329%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220329T014933Z&X-Amz-Expires=86400&X-Amz-Signature=d23ed9e9679cbcfbbc9b3a19ee8a7d80525698094c213c5f3637ead349fa28a0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

<br/>

## 🙆‍♂️ 팀원 소개

|김어진|윤숙|오용록|이상윤|이아현|
|:-:|:-:|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/62640679?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/42508120?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/18694745?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/38884368?v=4" width=200>|<img src="https://avatars.githubusercontent.com/u/48577694?v=4" width=200>|
|[@EoJin-Kim](https://github.com/EoJin-Kim)|[@djs02027](https://github.com/djs02027)|[@ohyr](https://github.com/ohyr)|[@dltkddbs](https://github.com/dltkddbs)|[@alskal1](https://github.com/alskal1)|

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

## System Architecture
![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6392f1ef-85e6-4734-8659-604f9cfc8d75/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220329%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220329T014522Z&X-Amz-Expires=86400&X-Amz-Signature=f7c246548938b1af8aa5be06c888f2481bc8be51908d7d243c1d88ae31a93ad3&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

## 🕹 프로젝트 주요 기능 및 동작 플로우
![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6623e442-579f-45ad-b985-555d83ea3872/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220329%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220329T014604Z&X-Amz-Expires=86400&X-Amz-Signature=08b3b4a93e2e264d6f5f0bf39dfd50cb88b30cd9719261026f15a44d11fc8b12&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)



