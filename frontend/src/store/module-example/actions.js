import { api } from "boot/axios";

export async function updateList({ commit }) {
  const response = await api.get("/api/room/");
  commit("updateList", response.data);
}

export async function selectAllAuctions({ commit }, sessionId) {
  try {
    const response = await api.get("/api/auction/list/" + sessionId);
    commit("selectAllAuctions", response.data);
  } catch (error) {
    console.log(error);
  }
}

export async function deleteAuction({ commit }, id) {
  try {
    const response = await api.delete("/api/auction/" + id);
  } catch (error) {
    console.log(error);
  }
}

export async function selectAuction({ commit }, id) {
  try {
    const response = await api.get("/api/auction/" + id);
    commit("selectCurrentAuction", response.data);
  } catch (error) {
    console.log(error);
  }
}

export async function deleteAuctions({ commit }, sessionId) {
  try {
    const response = await api.delete("/api/auction/list/" + sessionId);
    console.log(response);
  } catch (error) {
    console.log(error);
  }
}
