export function setIsLogin(state, isLogin) {
  state.isLogin = isLogin;
}

export function setUserInfo(state, userInfo) {
  state.isLogin = true;
  state.userInfo = userInfo;
}

export function setIsJoin(state, isJoin) {
  state.isJoin = isJoin;
}

export function setIsDelete(state, isDelete) {
  state.isDelete = isDelete;
}

export function setIsUpdate(state, isUpdate) {
  state.isUpdate = isUpdate;
}

export function setPoint(state, isCharge) {
  state.isCharge = isCharge;
}

export function getSellHistory(state, sellHistory) {
  state.sellHistory = sellHistory;
}

export function getWinHistory(state, winHistory) {
  state.winHistory = winHistory;
}

export function getPointHistory(state, pointHistory) {
  state.pointHistory = pointHistory;
}
