<template>
  <v-app id="inspire">
    <v-main class="orange lighten-4">
    <v-container class="my-5">
      <v-card shaped elevation="24">
        <v-sheet color="orange lighten-2" class="py-3">
          <v-row class="align-center justify-center">
            <v-card-title class="justify-center black--text font-weight-bold">
              파이 생성
            </v-card-title>
          </v-row>
        </v-sheet>
        <v-container>
          <v-select label="계좌 선택" :items="accountlist"></v-select>
          <v-autocomplete
              v-model="selectedList"
              :items="stocklist"
              item-text="stockName"
              :item-value="item => item"
              label="종목"
              multiple
              @input="userInput=null"
              :search-input.sync="userInput"
          >
            <template #item="{ item }">
              <v-list-item-content>
                <v-list-item-title class="font-weight-bold">
                  {{ item.stockName }}
                </v-list-item-title>
                <v-list-item-subtitle class="caption grey--text">
                  {{ item.no }} - \{{ item.price }}
                </v-list-item-subtitle>
              </v-list-item-content>
            </template>
          </v-autocomplete>
        </v-container>
        <v-card-text v-for="(item, index) in selectedList" :key="index">
          <v-container>
            <v-row>
              <v-col cols="12" class="font-weight-bold mb-3">
                {{ item.stockName }}
              </v-col>
              <v-col cols="12" sm="4" md="4">
                <v-card outlined class="d-flex flex-column justify-center align-center" hint="시가">
                  <span class="font-weight-light caption grey--text">금일 시작가</span>
                  <span class="font-weight-bold headline">\{{ item.price }}</span>
                </v-card>
              </v-col>
              <v-col cols="12" sm="4" md="4">
                <v-text-field
                    v-model="item.pricePercent"
                    label="체결가(시가대비) +/-(%)"
                    :rules="[value => !value || value <= 100 || '값은 100 이하이어야 합니다.']"
                    filled
                    type="number"
                    step="1"
                    required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="4" md="4">
                <v-text-field
                    v-model="item.orderCnt"
                    label="소수점 매수수량"
                    :rules="[value => (value > 0 && value < 1) || '값은 0 이상 1 미만이어야 합니다.']"
                    filled
                    type="number"
                    step="0.01"
                    required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn class="orange darken-1 white--text" @click="createRoom">주문</v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios'; // axios 라이브러리를 import

export default{
  data() {
    return {
      userId : this.$route.params.userId,
      stocklist: [],
      selectedList: [],
      userInput: null,
      selectedAccount: null,
      accountlist: ['[키움증권] 5001-4892', '[NH투자증권] 4892-1892']
    };
  },
  created() {
    this.findAllStock();
  },
  methods: {
    findAllStock() {
      axios.get('http://54.180.115.36:8081/kospi/findall')
          .then(
              response => {
                this.stocklist = response.data;
              });
    },
    createRoom() {
      if (this.selectedList == null) {
        alert("종목을 선택하세요");
        return;
      } else {
        this.selectedList.forEach((item, index) => {

          var params = new URLSearchParams();
          params.append("userId", this.userId);
          params.append("no", item.no);
          params.append("stockName", item.stockName);
          params.append("price", item.price);
          params.append("pricePercent", item.pricePercent); // 이렇게 사용하면 v-model로 연결된 속성을 가져올 수 있습니다.
          params.append("orderCnt", item.orderCnt);

          axios.post('http://54.180.115.36:8081/chat/create-room', params)
              .then(response => {
                alert(response.data.stockName + "방 개설에 성공하였습니다.");
                // 마지막 항목일 경우에만 리디렉션을 수행
                if (index === this.selectedList.length - 1) {
                  if (this.selectedList.length >= 2) {
                    window.location.href = "/room-list/"+this.userId; // 2개 이상일 때
                  } else {
                    window.location.href = "/room/"+this.userId+"/"+response.data.myRoomMemberId; // 1개일 때
                  }
                }
              })
              .catch(response => {
                alert(item.stockName + "방 개설에 실패하였습니다.");
              });
        });

      }
    }
  }
}
</script>
