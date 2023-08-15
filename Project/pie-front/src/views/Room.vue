<template>
  <v-app id="inspire">
    <v-main class="orange lighten-4">
      <v-container>
        <v-card shaped elevation="24" class="pa-4">
          <!-- 종목명-------------------------------------------->
          <v-row class="justify-center">
            <v-col cols="10">
              <v-card class="pa-3 text-center">
                <span class="font-weight-bold"> {{ roomData.stockName }}</span>
                (\{{ roomData.price }}
                <span class="caption grey--text">&#177;{{roomData.pricePercent}}%</span>
                )
              </v-card>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="2">
              <v-card class="pa-3 text-center">호가창</v-card>
            </v-col>
            <v-col cols="4">
              <!-- 소수점 매매 거래 range 최소-------------------------------------------->
              <v-card class="pa-3 text-center">최소가격: \{{ roomData.minPrice.toLocaleString('ko-KR') }}</v-card>
            </v-col>
            <v-col cols="4">
              <!-- 소수점 매매 거래 range 최대-------------------------------------------->
              <v-card class="pa-3 text-center">최대가격: \{{ roomData.maxPrice.toLocaleString('ko-KR') }}</v-card>
            </v-col>
            <v-col cols="1">
              <v-card class="pa-3 text-center">{{ userId }}님</v-card>
            </v-col>
            <v-col cols="1">
              <v-card class="pa-3 text-center">
                <v-icon medium color="orange darken-1">person</v-icon>
                {{ roomData.personCnt }}
              </v-card>
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
              <v-simple-table>
                <tbody>
                <tr v-for="i in array" :key="i">
                  <td :class="colorClass(i)" class="styled-cell">
                    \{{ get_unit(stockInfo.now, i) }}
                  </td>
                </tr>
                </tbody>
              </v-simple-table>
            </v-col>
            <v-col cols="10">
              <apexcharts height="350" type="bar" :options="chartOptions" :series="series"/>
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
                  새로운 파이
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
          <v-overlay v-model="buy" >
            <v-card shaped class="overlay-card">
              <v-card-title class="overlay-title">
                체결되었습니다!
              </v-card-title>
              <v-card-text class="overlay-content">
                축하합니다! 거래가 성공적으로 체결되었습니다.
              </v-card-text>
              <v-card-actions class="overlay-actions">
                <v-btn
                    color="red darken-1"
                    @click="exitRoom"
                    class="exit-button"
                >
                  나가기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-overlay>
        </v-card>
      </v-container>
    </v-main>
  </v-app>
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
        roomData: {}, // API에서 가져온 데이터 저장
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
              text: '참여 현황',
              style: {
                fontSize: '23px'
              }
          },
          xaxis: {
            max: 100,
            min: 0,
            labels: {
              formatter: function (val) {
                if (val == 0) {
                  return "모집";
                }
                else if (val == 100) {
                  return val + "% (완성)";
                }
                else {
                  return val + "%";
                }
              }
            }
          },
          yaxis: {
            labels: {
              show: false,
            }
          },
          tooltip: {
            y: {
              formatter: function (val) {
                return val + "%";
              }
            }
          },
          colors: ['#FFE0B2', '#FFB74D', '#FB8C00', '#EF6C00'],
          fill: {
              opacity: 1
          },
          legend: {
              position: 'top',
              horizontalAlign: 'left',
              offsetX: 40,
              showForSingleSeries: true
          },
          dataLabels: {
            formatter: function (val) {
              return val + "%";
            },
            style: {
              fontSize: '40px'
            }
          },
      },
      series: [],
      stockInfo:[],
      array: [5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5],
      exit: false,
      buy: false,  // 100%가 되어 체결되었는지 나타내는 bool 변수
      }
    },
    mounted() {

      // 참여 퍼센테이지 합계 1초마다 업데이트
      setInterval(this.fetchRoomData, 1000);

    },
    methods: {
      async fetchRoomData() {
        try {
          this.fetchStockData();
          const roomId = this.$route.params.roomId; // URL에서 roomId 가져오기
          const response = await axios.get('http://54.180.115.36:8081/chat/room/'+this.$route.params.userId+'/'+this.$route.params.roomMemberId);
          this.roomData = response.data; // 데이터를 로컬 상태에 저장
          this.percent_update(); // 참여 현황을 위한 series 데이터 업데이트
        } catch (error) {
          console.error("Error fetching room data:", error);
          // 오류 처리 (예: 사용자에게 오류 메시지 표시)
        }
      },
      async fetchStockData() {
        try {
            const response = await axios.get('http://54.180.115.36:8081/kospi/stock/' + this.roomData.no); // 호가 정보가 있는 엔드포인트
            this.stockInfo = response.data;
          } catch (error) {
          console.error("Error fetching stock data:", error);
        }
      },
      colorClass(i) {
        if (i >= 1 && i <= 5) return 'blue lighten-3';
        if (i == 0) return 'blue-grey lighten-5';
        if (i >= -5 && i <= -1) return 'red lighten-3';
        return '';
      },
      get_unit(num, a) {
        if (num < 2000) {
          return (num+a).toLocaleString('ko-KR');
        }
        else if (num < 5000) {
          return (num+a*5).toLocaleString('ko-KR');
        }
        else if (num < 20000) {
          return (num+a*10).toLocaleString('ko-KR');
        }
        else if (num < 50000) {
          return (num+a*50).toLocaleString('ko-KR');
        }
        else if (num < 200000) {
          return (num+a*100).toLocaleString('ko-KR');
        }
        else if (num < 500000) {
          return (num+a*500).toLocaleString('ko-KR');
        }
        else {
          return (num+a*1000).toLocaleString('ko-KR');
        }
      },
      // 참여 퍼센테이지가 100% 이상이 되면 buy 변수를 true로 변경
      percent_update () {
        var total = 0;
        this.series = []
        // roomData에서 personPercent 전체 합계 계산
        for (var mem in this.roomData.roomMemberList) {
          total = total + this.roomData.roomMemberList[mem].personPercent;
          this.series.push({
            name: this.roomData.roomMemberList[mem].userId,
            data: [this.roomData.roomMemberList[mem].personPercent]
          });
        }
        if (total >= 100) {
          this.buy = true;
        }
        else {
          this.buy = false;
        }
        return total
      },
      // 파이 나가기
      exitRoom() {
        window.location.href = "/my-page/"+this.userId; // 파이 리스트 화면으로 이동
      }
      /*
      get_color(ind) {
        if (ind > 0) {
          return "blue-lighten-"+ind.toString();
        }
        else {
          return "red-lighten-"+ind.toString();
        }
      }
      */
    },
  }
</script>

<style scoped>
.info-box {
  max-width: 400px;
  margin: 20px;
  padding: 16px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.styled-cell {
  font-weight: bold;
  text-align: center;
  border: 1px solid #e0e0e000;
  padding: 10px;
}

.blue.lighten-3 {
  background-color: #64b5f6;
  color: #fff;
}

.blue-grey.lighten-5 {
  background-color: #eceff1;
  color: #424242;
}

.red.lighten-3 {
  background-color: #e57373;
  color: #fff;
}

.overlay-card {
  max-width: 400px;
  //border-radius: 12px;
  //box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.overlay-title {
  //color: #2c3e50;
  font-weight: 700;
  text-align: center;
}

.overlay-content {
  color: #ffffff;
  text-align: center;
  font-size: 16px;
  margin-bottom: 20px;
}

.overlay-actions {
  justify-content: center;
}

.exit-button {
  padding: 30px 30px;
  font-weight: bold;
  font-size: 16px;
}
</style>