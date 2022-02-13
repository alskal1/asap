export function someMutation(/* state */) {}

export const updateList = (state, list) => {
  state.roomList = list;
};
export const getAuctionList = (state, list) => {
  state.auctionList = list;
};
