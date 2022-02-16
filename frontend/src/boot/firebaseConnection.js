import { boot } from "quasar/wrappers";

import { initializeApp } from "firebase/app";
import { getStorage, ref } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyBcDiXRFv3VLhqsyd2-bQMex7cCa1l4s7M",
  authDomain: "asap-ab84e.firebaseapp.com",
  projectId: "asap-ab84e",
  storageBucket: "asap-ab84e.appspot.com",
  messagingSenderId: "949094836346",
  appId: "1:949094836346:web:b9c898344a06d5a79273c3",
  measurementId: "G-NZ0HEB6NBE",
};
const firebaseApp = initializeApp(firebaseConfig);
const storage = getStorage(firebaseApp);
const fireStorageRef = ref(storage, "");
const thumbnailsRef = (imgName) => {
  return ref(storage, `thumbnails/${imgName}`);
};
// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async ({ app }) => {
  app.config.globalProperties.$fireStorageRef = fireStorageRef;
});

export { fireStorageRef, thumbnailsRef };
