export function setIsLogin(state, isLogin) {
  state.isLogin = isLogin;
}

export function setUserInfo(state, userInfo) {
  state.isLogin = true;
  state.userInfo = userInfo;
}

export function setIsManage(state, isManage) {
  state.isManage = isManage;
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
