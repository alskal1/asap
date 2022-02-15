import { api } from "boot/axios";
import { Notify } from "quasar";

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
      if (error.response.status === 401) {
        Notify.create({
          message: "로그인 후 진행해 주세요",
          color: "red",
        });
      }
    });
}

export function updateConfirm(user) {
  return api.put("/api/member/", user).catch(function (error) {
    console.log(error);
  });
}

export function chargePoint(point) {
  return api.post("/api/point/", point).catch(function (error) {
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

export function getPointHistory({ commit }) {
  return api
    .get("/api/point/")
    .then((response) => {
      commit("getPointHistory", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}
