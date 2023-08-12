import Vue from "vue";
import VueRouter from "vue-router";
import DashBoard from "../views/DashBoard.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/dash-board",
    name: "DashBoard",
    component: DashBoard
  },
  {
    path: "/my-page",
    name: "myPage",
    component: () =>
      import(/* webpackChunkName: "MyPage" */ "../views/MyPage.vue")
  },
  {
    path: "/room",
    name: "Room",
    component: () =>
      import(/* webpackChunkName: "room" */ "../views/Room.vue")
  },
  {
    path: "/room2",
    name: "Room2",
    component: () =>
      import(/* webpackChunkName: "room" */ "../views/Room2.vue")
  },
 {
   path: "/ChatRoom",
   name: "ChatRoom",
   component: () =>
     import(/* webpackChunkName: "room" */ "../views/ChatRoom.vue")
 },
 {
  path: "/room-create",
  name: "Createroom",
  component: () =>
    import(/* webpackChunkName: "room" */ "../views/Createroom.vue")
}
];

export {router}

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
