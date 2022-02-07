import { api } from "boot/axios";

export function someAction(/* context */) {}

export function updateList({ commit }) {
  return api.get("/api/room/").then((response) => {
    console.log("response : ", response);
    commit("updateList", response.data);
  });
}
