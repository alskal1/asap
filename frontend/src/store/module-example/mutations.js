export const updateList = (state, list) => {
  state.roomList = list;
};

export const selectAllAuctions = (state, list) => {
  state.auctionList = list;
};

export const selectCurrentAuction = (state, auction) => {
  state.curAuction = auction;
};
