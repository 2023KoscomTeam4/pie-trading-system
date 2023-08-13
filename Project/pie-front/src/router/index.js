import Vue from "vue";
import VueRouter from "vue-router";
// import DashBoard from "../views/DashBoard.vue";


Vue.use(VueRouter);

const routes = [
  { path: '/', 
    redirect: '/dash-board' 
  },
  {
    path: "/dash-board",
    name: "DashBoard",
    component: () => import(/* webpackChunkName: "DashBoard" */ "../views/DashBoard.vue")
    // component: DashBoard
  },
  {
    path: "/my-page",
    name: "MyPage",
    component: () =>
      import(/* webpackChunkName: "MyPage" */ "../views/MyPage.vue")
  },
  {
    path: "/room/:userId/:roomMemberId",
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
   path: "/chat-room",
   name: "ChatRoom",
   component: () =>
     import(/* webpackChunkName: "room" */ "../views/ChatRoom.vue")
 },
 {
  path: "/room-create/:userId",
  name: "Createroom",
  component: () =>
    import(/* webpackChunkName: "room" */ "../views/Createroom.vue")
},
{
  path: "/room-list/:userId",
  name: "RoomList",
  component: () =>
    import(/* webpackChunkName: "room" */ "../views/RoomList.vue")
}
];

export {router}

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
