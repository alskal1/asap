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

export async function updateCurrentAuction({ commit }, auctionId) {
  try {
    const response = await api.put("/api/room/auction/" + auctionId);
    console.log("DB정보가 변경되었습니다.!!!");
  } catch (error) {
    console.log(error);
  }
}

export async function selectCurrentAuction({ commit }, sessionId) {
  try {
    const response = await api.get("/api/auction/current/" + sessionId);
    console.log("현재 진행중인 경매 정보 갖고왔음!!!");
    commit("selectCurrentAuction", response.data);
  } catch (error) {
    console.log(error);
  }
}
