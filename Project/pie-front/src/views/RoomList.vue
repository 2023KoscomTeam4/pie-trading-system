<template>
  <v-container fluid :grid-list-md="!$vuetify.breakpoint.xs">
    <v-layout wrap row>
      <v-flex md12 class="pb-2 clickable"
              v-for="item in roomList"
              :key="item.stockId">
        <router-link :to="'/room/' + this.userId +'/'+ item.roomId" tag="div">
          <v-card>
          <v-container pa-1>
            <v-layout row wrap>
              <v-flex xs12>
                <v-card outlined>
                  <v-card-title>
                    <div>
                      <h3 class="headline">{{ item.stockName }} ({{ item.no }})</h3>
                    </div>
                  </v-card-title>
                </v-card>
              </v-flex>
              <v-flex xs8>
                <v-card outlined class="pa-3">
                  <div class="d-flex align-center">
                    <div>
                      <h4>{{item.price}}\</h4>
                      <span class="caption grey--text">&#177;{{item.pricePercent}}%</span>
                    </div>
                  </div>
                </v-card>
                <v-card outlined class="pa-3">
                  <div class="d-flex align-center">
                    <div>
                      <span class="body-1">{{item.myTradingCnt}}주 구매대기중</span>
                    </div>
                  </div>
                </v-card>
                <v-card outlined class="pa-3">
                  <div class="d-flex align-center">
                    <v-avatar>
                      <v-icon medium color="orange darken-1">person</v-icon>
                      <div>{{item.personCnt}}</div>
                    </v-avatar>
                  </div>
                </v-card>
              </v-flex>
              <v-flex xs4>
                <v-card outlined class="d-flex flex-column align-center justify-center">
                  <v-progress-circular
                      :rotate="360"
                      :size="200"
                      :width="60"
                      :value="progressValues[item.roomId]"
                      color="orange darken-1">
                    <h3>{{ progressValues[item.roomId] }}%</h3>
                  </v-progress-circular>
                </v-card>
              </v-flex>
            </v-layout>

            <v-divider light></v-divider>
            <v-card-actions class="pa-2">
              <v-icon small color="orange darken-1">update</v-icon>
              <span class="grey--text caption font-italic">&nbsp;5분전</span>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-container>
        </v-card>

        </router-link>
      </v-flex>

    </v-layout>

  </v-container>
</template>

<script>
import axios from 'axios'; // axios 라이브러리를 import
import VueApexCharts from 'vue-apexcharts';
export default{
  components: {
    'v-chart': VueApexCharts
  },

  data() {
    return {
      userId : "ko1",
      roomList: [],
      progressValues: {}
    };
  },
  beforeDestroy () {
    clearInterval(this.interval)
  },
  created() {
    this.findAllRoom();
  },
  methods: {
    findAllRoom() {
      axios.get('http://localhost:8081/chat/rooms?userId='+this.userId)
          .then(
              response => {
                this.roomList = response.data;
                this.roomList.forEach((item) => {
                  // console.log(item)
                  this.$set(this.progressValues, item.roomId, 0); // progressValues 초기화
                  // console.log(this.progressValues)
                });
                this.updateSeriesAndLabels(); // series와 labels 업데이트
              });
    },
    updateSeriesAndLabels() {

      this.interval = setInterval(() => {
        for (const item of this.roomList) {
          // console.log(this.progressValues[item.roomId])
          if (this.progressValues[item.roomId] < item.totalPercent) {
            this.progressValues[item.roomId]++;
          }
        }
      }, 200);
    },
  }
}
</script>
<style>
.clickable {
  cursor: pointer;
}
</style>