<template>
  <v-app id="inspire">
    <v-main class="orange lighten-4">
      <v-container fluid :grid-list-md="!$vuetify.breakpoint.xs">
        <v-layout wrap row>
          <v-flex v-if="roomList.length <= 0" class="centered-content">
            <p>매매요청된 PIE STOCK이 없습니다. <br>매매요청하시겠습니까?</p>
            <v-btn :to="'/room-create/' + userId " class="orange lighten-1 white--text">요청하기</v-btn>
          </v-flex>
          <v-flex v-else
                  md12 class="pb-2 clickable"
                  v-for="item in roomList"
                  :key="item.stockId"
                  >
            <router-link :to="'/room/' + userId +'/'+ item.myRoomMemberId" tag="div">
              <v-card shaped elevation="24">
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
                          <h4>\{{item.price}}</h4>
                          <span class="caption grey--text">&#177;{{item.pricePercent}}%</span>
                        </div>
                      </div>
                    </v-card>
                    <v-card outlined class="pa-3">
                      <div class="d-flex align-center">
                        <div>
                          <span class="body-1 font-weight-bold">{{item.myTradingCnt}}주
                            <span :class="item.totalPercent >= 90 ? (item.totalPercent >= 100 ? 'red--text blinking' : 'caption grey--text blinking') : 'caption grey--text'">
                              {{item.totalPercent}}({{item.myPersonPercent}})/100%
                              {{item.totalPercent >= 90 ? (item.totalPercent >= 100 ? '구매완료' : '구매임박') : '구매대기중'}}
                            </span>
                          </span>
                        </div>
                      </div>
                    </v-card>
                    <v-card outlined class="pa-3">
                      <div class="d-flex align-center">
                        <v-avatar>
                          <v-icon medium color="orange darken-1">person</v-icon>
                          <div class="body-1 font-weight-bold">{{item.personCnt}}</div>
                        </v-avatar>
                      </div>
                    </v-card>
                  </v-flex>
                  <v-flex xs4>
                    <v-card outlined class="d-flex flex-column align-center justify-center">
                      <div style="position: relative; width: 200px; height: 200px;">
                        <v-progress-circular
                            :rotate="360"
                            :size="200"
                            :width="60"
                            :value="progressValues[item.roomId]"
                            color="red darken-1"
                            style="position: absolute;">
                        </v-progress-circular>
                        <v-progress-circular
                            :rotate="360"
                            :size="200"
                            :width="60"
                            :value="item.myPersonPercent"
                            color="orange darken-1"
                            style="position: absolute;">
                        </v-progress-circular>
                        <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
                          <h3>{{ progressValues[item.roomId] }}%</h3>
                        </div>
                      </div>
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
    </v-main>
  </v-app>
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
      userId : this.$route.params.userId,
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
      axios.get('http://localhost:8081/chat/rooms/'+this.userId)
          .then(
              response => {
                this.roomList = response.data;
                this.roomList.forEach((item) => {
                  // console.log(item)
                  this.$set(this.progressValues, item.roomId, 0); // progressValues 초기화
                  // console.log(this.progressValues)
                });
                // console.log(this.roomList)
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
@keyframes blink {
  0%, 100% { opacity: 1; }
  50% { opacity: 0; }
}

.blinking {
  animation: blink 1s linear infinite;
}

.clickable {
  cursor: pointer;
}
.centered-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  justify-content: center;
  height: 100vh; /* 브라우저 뷰포트의 높이를 채우도록 설정 */
}
</style>