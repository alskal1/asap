import { api } from "boot/axios";

export function updateList({ commit }) {
  return api.get("/api/room/").then((response) => {
    commit("updateList", response.data);
  });
}

export function selectAllAuctions({ commit }, sessionId) {
  return api
    .get("/api/auction/list/" + sessionId)
    .then((response) => {
      commit("selectAllAuctions", response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function deleteAuction(id) {
  return api
    .delete("/api/auction/" + id)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function deleteAuctions(sessionId) {
  return api.delete("/api/auction/list/" + sessionId).catch((error) => {
    console.log(error);
  });
}
