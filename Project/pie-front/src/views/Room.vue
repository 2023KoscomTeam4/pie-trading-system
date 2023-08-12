<template>
  <v-container>
    <!-- 종목명-------------------------------------------->
    <v-row>
      <v-col cols="12">
        <v-card class="pa-3 text-center">삼성전자</v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2">
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
        <v-card class="pa-3 text-center">최소가격:9000</v-card>
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
        <v-card class="pa-3 text-center">최대가격:11000</v-card>
      </v-col>
      <v-col cols="2">
          <v-card class="pa-3 text-center">콤이</v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import VueApexCharts from "vue-apexcharts";
  export default {
    components: {
        apexcharts: VueApexCharts,
    },
    data () {
      return {
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
  }

</script>
<style lang="">
  
</style>