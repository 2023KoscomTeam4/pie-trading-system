<template>
    <v-card>
        <v-card-title>
          <span class="text-h5">소수점 매매</span>
        </v-card-title>
        <v-container>
            <v-autocomplete
                v-model = "selectedList"
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
                  <v-list-item-subtitle>
                    {{ item.no }}
                  </v-list-item-subtitle>
                  <v-list-item-title>
                    {{ item.stockName }}
                  </v-list-item-title>
                  <v-list-item-subtitle>
                    {{ item.price }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </template>
            </v-autocomplete>
        </v-container>
        <v-card-text v-for="(item, index) in selectedList">
          <v-container>
            <v-row>
              <v-col cols="12">
                {{ item.stockName }}
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-card height="70%" class="d-flex flex-column justify-center align-center" hint="시가">
                  <span class="font-weight-light small">금일 시작가</span>
                  <span class="font-weight-bold ">{{ item.price }}</span>
                </v-card>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                    v-model="item.pricePercent"
                    label="체결가(시가대비) +/-(%)"
                    hint="체결가(시가대비) +/-(%)"
                    :rules="[value => !value || value <= 100 || '값은 100 이하이어야 합니다.']"
                    required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                    v-model="item.orderCnt"
                    label="소수점 매수수량"
                    hint="매수수량"
                    :rules="[value => (value > 0 && value < 1) || '값은 0 이상 1 미만이어야 합니다.']"
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
          <v-btn class="orange darken-1" @click="createRoom">주문</v-btn>
        </v-card-actions>
      </v-card>
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

          axios.post('http://localhost:8081/chat/create-room', params)
              .then(response => {
                alert(response.data.stockName + "파이 개설에 성공하였습니다.");
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
                alert(item.stockName + "파이 개설에 실패하였습니다.");
              });
        });

      }
    }
  }
}
</script>
