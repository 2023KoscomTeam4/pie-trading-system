<template>
    <v-card>
        <v-card-title>
          <span class="text-h5">소수점 매매</span>
        </v-card-title>
        <v-container>
            <v-autocomplete
                v-model = "selected"
                :items="stocklist"
                item-text="stockName"
                :item-value="item => item"
                label="종목"
                multiple
            ></v-autocomplete>
        </v-container>
        <v-card-text v-for="item in selected">
          <v-container>
            <v-row>
              <v-col cols="12">
                {{ item.stockName }}
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-card height="70%" hint="시장가">가격 {{ item.price }}</v-card>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  label="최소 가격(%)"
                  hint="시장가 대비 -(입력)%"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="최대 가격(%)"
                  hint="시장가 대비 +(입력)%"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="매수 수량"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="orange darken-1" @click="createRoom">주문</v-btn>
        </v-card-actions>
      </v-card>
</template>

<script>
import axios from 'axios'; // axios 라이브러리를 import

export default{
  data() {
    return {
      stocklist: [],
      selected: [],
    };
  },
  created() {
    this.findAllStock();
  },
  methods: {
    findAllStock() {
      axios.get('http://localhost:8081/kospi/findall')
          .then(
              response => {
                this.stocklist = response.data;
              });
    },
    createRoom() {
      if ("" === this.room_name) {
        alert("종목을 선택하세요");
        return;
      } else {
        var params = new URLSearchParams();
        params.append("name", this.room_name);
        axios.post('http://localhost:8081/chat/room', params)
            .then(
                response => {
                  alert(response.data.roomName + "방 개설에 성공하였습니다.");
                  this.room_name = '';
                  this.findAllRoom();
                }
            )
            .catch(response => { alert("채팅방 개설에 실패하였습니다."); });
      }
    }
  }
}
</script>