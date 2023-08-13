<template>
  <v-container>
    <!-- 종목명-------------------------------------------->
    <v-row>
      <v-col cols="12">
        <v-card class="pa-3 text-center">{{ roomData.stockName }} (\{{ roomData.price }} <span class="caption grey--text">&#177;{{roomData.pricePercent}}%</span>)</v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2">

        <!-- NOTE: 실시간 금액 API-------------------------------------------->
        <!----------------
        <v-row>
          <v-col cols="12">
            <v-card>
              <h3>{{ stockInfo.marketSum }} 억 {{ stockInfo.marketSum % 100 }} 백만 - 시가 총액</h3>
              <h3>{{ stockInfo.risefall }} - 1 : 상한 , 2 : 상승, 3: 보합? , 4 : 하한, 5, 하락</h3>
              <h3>{{ stockInfo.diff }} - 전일대비 가격 차이</h3>
              <h3>{{ stockInfo.rate }}% - 상승율</h3>
              <h3>{{ stockInfo.high }} - 고가</h3>
              <h3>{{ stockInfo.low }} - 저가</h3>
              <h3>{{ stockInfo.quant }} - 거래량</h3>
              <h3>{{ stockInfo.amount }} - 거래대금</h3>
              <h3>{{ stockInfo.per }} - PER</h3>
              <h3>{{ stockInfo.eps }} - EPS</h3>
              <h3>{{ stockInfo.pbr }} - PBR</h3>
              <h3>{{ stockInfo.now }} - 현재가</h3>
            </v-card>
          </v-col>
        </v-row>
        ---------------->

        <!-- 호가창-------------------------------------------->
        <v-simple-table class="text-center">
          <tbody>
          <tr
            v-for="i in array"
            :key="i"
          >
            <td :class="{
              'blue lighten-4': i > 0,
              'red lighten-1': i <= 0
            }">{{ 
              stockInfo.now+i*get_unit(stockInfo.now)
            }}</td>
          </tr>
          </tbody>
        </v-simple-table>
      </v-col>
      <v-col cols="4">
        <!-- 소수점 매매 거래 range 최소-------------------------------------------->
        <v-card class="pa-3 text-center">최소가격:{{ roomData.minPrice }}</v-card>
        <v-row>
          <v-col cols="10">
            <br></br>
              <!-- 소수점 거래를 위한 참여자 퍼센테이지-------------------------------------------->
              <apexcharts width="900" height="350" type="bar" :options="chartOptions" :series="series"/>
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="4">
        <!-- 소수점 매매 거래 range 최대-------------------------------------------->
        <v-card class="pa-3 text-center">최대가격:{{ roomData.maxPrice }}</v-card>
      </v-col>
      <v-col cols="2">
          <v-card class="pa-3 text-center">{{ userId }}님</v-card>
      </v-col>
    </v-row>
    <v-overlay
      v-model="exit"
      contained
    >
      <v-layout justify-center align-center>
        <v-text class>현재가가 설정한 범위를 벗어났습니다. 나가시겠습니까?</v-text>
      </v-layout>
      <br></br>
      <v-layout justify-center align-center>
        <v-col cols="auto">
          <v-btn
            color="red darken-1"
            @click="exit = false"
          >
            나가기
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn
            color="orange darken-1"
            @click="exit = false"
          >
            새로운 방
          </v-btn>
        </v-col>
        <v-col cols="auto">
          <v-btn
            color="green darken-1"
            @click="exit = false"
          >
            머무르기
          </v-btn>
        </v-col>
      </v-layout>
    </v-overlay>
    <v-overlay
      v-model="buy"
      contained
    >
      <v-layout justify-center align-center>
        <v-text class>체결되었습니다!</v-text>
      </v-layout>
      <br></br>
      <v-layout justify-center align-center>
        <v-col cols="auto">
          <v-btn
            color="red darken-1"
            @click="buy = false"
          >
            나가기
          </v-btn>
        </v-col>
      </v-layout>
    </v-overlay>
    <br></br>
    <v-card>
      {{ roomData }}
    </v-card>
  </v-container>
</template>

<script>
  import axios from 'axios'; // axios 라이브러리를 import
  import VueApexCharts from "vue-apexcharts";

  export default {
    components: {
        apexcharts: VueApexCharts,
    },
    data () {
      return {
        userId : this.$route.params.userId,
        roomData: null, // API에서 가져온 데이터 저장
        chartOptions: {
          chart: {
              type: 'bar',
              height: 350,
              stacked: true,
              toolbar: {
                show: false
              }
          },
          plotOptions: {
              bar: {
                  horizontal: true,
                  dataLables: {
                      total: {
                          enabled: true,
                          offsetX: 0,
                          style: {
                              fontSize: '13px',
                              fontWeight: 900
                          }
                      }
                  }
              }
          },
          stroke: {
              width: 1,
              colors: ['#fff']
          },
          title: {
              text: '참여 현황'
          },
          xaxis: {
            max: 100,
            min: 0
          },
          yaxis: {
            labels: {
              show: false,
            }
          },
          tooltip: {
            y: {
              formatter: function (val) {
                return val + "K"
              }
            }
          },
          fill: {
              opacity: 1
          },
          legend: {
              position: 'top',
              horizontalAlign: 'left',
              offsetX: 40
          }
      },
      series: [{
          name: 'A',
          data: [40]
      }, {
          name: 'B',
          data: [30]
      }],
      stockInfo:[],
      array: [5, 4, 3, 2, 1, 0, -1, -2, -3, -4],
      /*
      stock: [
        {
          price: 10500,
          ind: 1,
        },
        {
          price: 10400,
          ind: 2,
        },
        {
          price: 10300,
          ind: 3,
        },
        {
          price: 10200,
          ind: 4,
        },
        {
          price: 10100,
          ind: 5,
        },
        {
          price: 10000,
          ind: 6,
        },
        {
          price: 9900,
          ind: 7,
        },
        {
          price: 9800,
          ind: 8,
        },
        {
          price: 9700,
          ind: 9,
        },
        {
          price: 9600,
          ind: 10,
        },
      ],*/
      exit: false, // 가격 범위 벗어났는지 나타내는 bool 변수
      buy: false,  // 100%가 되어 체결되었는지 나타내는 bool 변수
      }
    },
    created() {
      this.fetchRoomData();

      // 호가 정보를 1초마다 업데이트
      setInterval(this.fetchStockData, 1000);
      // 참여 퍼센테이지 합계 1초마다 업데이트
      setInterval(this.buy_function, 1000);
    },
    methods: {
      async fetchRoomData() {
        try {
          const roomId = this.$route.params.roomId; // URL에서 roomId 가져오기
          const response = await axios.get('http://localhost:8081/chat/room/'+this.$route.params.userId+'/'+this.$route.params.roomMemberId);
          this.roomData = response.data; // 데이터를 로컬 상태에 저장
        } catch (error) {
          console.error("Error fetching room data:", error);
          // 오류 처리 (예: 사용자에게 오류 메시지 표시)
        }
      },
      async fetchStockData() {
        try {
          if(this.roomData != null) {
            const response = await axios.get('http://localhost:8081/kospi/stock/' + this.roomData.no); // 호가 정보가 있는 엔드포인트
            this.stockInfo = response.data;
          }
          // console.log(this.stock)
        } catch (error) {
          console.error("Error fetching stock data:", error);
        }
      },
      get_unit(num) {
        if (num < 2000) {
          return 1;
        }
        else if (num < 5000) {
          return 5;
        }
        else if (num < 20000) {
          return 10;
        }
        else if (num < 50000) {
          return 50;
        }
        else if (num < 200000) {
          return 100;
        }
        else if (num < 500000) {
          return 500;
        }
        else {
          return 1000;
        }
      },
      // 참여 퍼센테이지가 100% 이상이 되면 buy 변수를 true로 변경
      buy_function() {
        var total = 0;
        // roomData에서 personPercent 전체 합계 계산
        for (var mem in this.roomData) {
          total = total + mem.personPercent;
        }
        if (total >= 100) {
          this.buy = true;
        }
        else {
          this.buy = false;
        }
      }
    },
  }
</script>
<style lang="">
  
</style>