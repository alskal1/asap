import { api } from "boot/axios";

export function login({ commit }, loginInfo) {
  return api.post("/api/member/login", loginInfo);
}

export function getUserInfo({ commit }) {
  const jwt = sessionStorage.getItem("jwt");
  api.defaults.headers.common["Authorization"] = jwt ? `Bearer ${jwt}` : ``;

  return api
    .get("/api/member/user")
    .then((response) => {
      commit("setUserInfo", response.data);
    })
    .catch(function (error) {
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

export function getSellHistory({ commit }) {
  return api
    .get("/api/history/sell-list")
    .then((response) => {
      commit("getSellHistory", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function getWinHistory({ commit }) {
  return api
    .get("/api/history/win-list")
    .then((response) => {
      commit("getWinHistory", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}
