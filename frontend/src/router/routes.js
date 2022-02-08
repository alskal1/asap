const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue"),
      },
      {
        path: "/notice",
        component: () => import("pages/Notice.vue"),
      },
      {
        path: "/auction",
        component: () => import("pages/live/LiveTest.vue"),
      },
      {
        path: "/point",
        component: () => import("pages/ChargePoint.vue"),
      },
      {
        path: "/member",
        component: () => import("pages/Mypage.vue"),
      },
      {
        path: "/live",
        component: () => import("pages/live/Live.vue"),
      },
      {
        path: "/room",
        component: () => import("pages/CreateRoom.vue"),
      },
    ],
  },
  {
    path: "/login",
    component: () => import("layouts/OauthLayout.vue"),
    children: [
      {
        path: "/signup",
        component: () => import("pages/oauth/Signup.vue"),
      },
      {
        path: "/login",
        component: () => import("pages/oauth/Login.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/error/Error404.vue"),
  },
];

export default routes;
