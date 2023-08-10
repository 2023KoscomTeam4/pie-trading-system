<template>
  <div class="container" id="app" v-cloak>
    <div>
      <h2>{{room.name}}</h2>
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">내용</label>
      </div>
      <input type="text" class="form-control" v-model="message" @keypress.enter="sendMessage">
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-for="message in messages">
        {{message.sender}} - {{message.message}}
      </li>
    </ul>
    <div></div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import axios from "axios";

export default {
  data() {
    return {
      roomId: '',
      room: {},
      sender: '',
      message: '',
      messages: [],
      client: null,
      reconnect: 0
    };
  },
  created() {
    this.roomId = localStorage.getItem('wschat.roomId');
    this.sender = localStorage.getItem('wschat.sender');
    this.findRoom();
    this.client = new Client({
      webSocketFactory: () => new SockJS("/ws/chat"),
      onConnect: this.onConnect,
      onStompError: this.onStompError
    });
    this.client.activate();
  },
  methods: {
    findRoom: function() {
      axios.get('/chat/room/' + this.roomId).then(response => { this.room = response.data; });
    },
    sendMessage: function() {
      this.client.publish({ destination: "/app/chat/message", body: JSON.stringify({ type: 'TALK', roomId: this.roomId, sender: this.sender, message: this.message }) });
      this.message = '';
    },
    recvMessage: function(recv) {
      this.messages.unshift({ "type": recv.type, "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender, "message": recv.message });
    },
    onConnect: function(frame) {
      this.client.subscribe("/topic/chat/room/" + this.roomId, (message) => {
        var recv = JSON.parse(message.body);
        this.recvMessage(recv);
      });
      this.client.publish({ destination: "/app/chat/message", body: JSON.stringify({ type: 'ENTER', roomId: this.roomId, sender: this.sender }) });
    },
    onStompError: function(error) {
      if (this.reconnect++ <= 5) {
        setTimeout(() => {
          console.log("connection reconnect");
          this.client.activate();
        }, 10 * 1000);
      }
    }
  }
};
</script>

<style>
[v-cloak] {
  display: none;
}
</style>
