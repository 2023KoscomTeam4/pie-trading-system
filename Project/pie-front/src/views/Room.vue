<template>
  <v-container>
    <!-- 종목명-------------------------------------------->
    <v-row>
      <v-col cols="12">
        <v-card class="pa-3 text-center">{{ roomData.no }} (\{{ roomData.price }} <span class="caption grey--text">&#177;{{roomData.pricePercent}}%</span>)</v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2">

        <!-- NOTE: 실시간 금액 API-------------------------------------------->
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


        <!-- 호가창-------------------------------------------->
        <v-simple-table class="text-center">
          <tbody>
          <tr
            v-for="item in stock"
            :key="item.price"
          >
            <td :class="{
              'blue lighten-4': item.ind <= 5,
              'red lighten-1': item.ind > 5
            }">{{ item.price }}</td>
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
          <v-card class="pa-3 text-center">콤이</v-card>
      </v-col>
    </v-row>
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
      ],
      }
    },
    created() {
      this.fetchRoomData();

      // 호가 정보를 1초마다 업데이트
      setInterval(this.fetchStockData, 1000);
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
    },
  }

</script>
<style lang="">
  
</style>