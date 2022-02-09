import { api } from "boot/axios";

export function getUserInfo({ commit }, token) {
  let decode_token = jwt_decode(token);
  console.dir(decode_token);
  api
    .get("/api/member/user", decode_token)
    .then((response) => {
      commit("setUserInfo", response.data);
    })
    .catch(function (error) {
      console.log("회원 정보 없음");
      console.log(error);
    });
}

export function updateConfirm({ commit }, user) {
  api
    .patch("/api/member/", user)
    .then((response) => {
      commit("setIsUpdate", true);
    })
    .catch(function (error) {
      commit("setIsUpdate", false);
      console.log(error);
    });
}
