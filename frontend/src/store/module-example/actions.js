import { api } from "boot/axios";

export function someAction(/* context */) {}

export function updateList({ commit }) {
  return api.get("/api/room/").then((response) => {
    console.log("response : ", response);
    commit("updateList", response.data);
  });
}
export function getAuctionList({ commit }) {
  return api.get("/api/auction/list/").then((response) => {
    commit("getAuctionList", response.data);
  });
}
