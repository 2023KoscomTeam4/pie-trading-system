import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
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
  path: "/createroom",
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
