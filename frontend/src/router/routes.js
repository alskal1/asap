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
        component: () => import("pages/Auction.vue"),
      },
      {
        path: "/point",
        component: () => import("pages/ChargePoint.vue"),
      },
      {
        path: "/member",
        component: () => import("pages/Mypage.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/Error404.vue"),
  },
];

export default routes;
