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
    .get(`/api/auction/list/${roomId}`)
    .then((response) => {
      console.log(response.data);
      commit("selectAllAuctions", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function deleteAuctions({ commit }, roomId) {
  return api
    .delete(`/api/auction/list/${roomId}`)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });
}
