const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue"),
        meta: { authRequired: false },
      },
      {
        path: "/notice",
        component: () => import("pages/Notice.vue"),
        meta: { authRequired: false },
      },
      {
        path: "/auction",
        component: () => import("pages/live/LiveTest.vue"),
        meta: { authRequired: true },
      },
      {
        path: "/point",
        component: () => import("pages/ChargePoint.vue"),
        meta: { authRequired: true },
      },
      {
        path: "/member",
        component: () => import("pages/Mypage.vue"),
        meta: { authRequired: true },
      },
      {
        path: "/live",
        component: () => import("pages/live/Live.vue"),
        meta: { authRequired: true },
      },
      {
        path: "/room",
        component: () => import("pages/CreateRoom.vue"),
        meta: { authRequired: true },
      },
    ],
  },
  {
    path: "/auth",
    component: () => import("layouts/AuthLayout.vue"),
    children: [
      {
        path: "/auth/signup",
        component: () => import("pages/auth/Signup.vue"),
      },
      {
        path: "/auth/login",
        component: () => import("pages/auth/Login.vue"),
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
