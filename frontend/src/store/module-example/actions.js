import { api } from "boot/axios";

export function someAction(/* context */) {}

export function updateList({ commit }) {
  return api.get("/api/room/").then((response) => {
    console.log("response : ", response);
    commit("updateList", response.data);
  });
}

export function selectAllAuctions({ commit }, roomId) {
  return api
    .get(`/api/auction/list/${roomId}/`)
    .then((response) => {
      commit("selectAllAuctions", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}
