<template>
    <v-container fluid>
      <!-- 종목명-------------------------------------------->
      <v-card class="pa-3 text-center">삼성전자</v-card>
      <v-row>
        <v-col>
          <!-- 호가창-------------------------------------------->
          <v-simple-table style="width: 70%" class="text-center">
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
        <v-col>
          <!-- 소수점 매매 거래 range 최소-------------------------------------------->
          <v-card class="pa-3 text-center">최소가격:9000</v-card>
        </v-col>
        <v-col>
          <!-- 소수점 매매 거래 range 최대-------------------------------------------->
          <v-card class="pa-3 text-center">최대가격:11000</v-card>
        </v-col>
        <v-col>
            <v-card class="pa-3 text-center">이건희님</v-card>
        </v-col>
      </v-row>
      <br>
      <div>
        <!-- 소수점 거래를 위한 참여자 퍼센테이지-------------------------------------------->
        <v-progress-linear
          v-model="progress"
          color="green"
          height="25"
        >
          <strong>{{ Math.ceil(progress) }}%</strong>
        </v-progress-linear>
      </div>
    </v-container>
</template>

<script>
  export default {
    data () {
      return {
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
      progress: 33,
      }
    },
  }

</script>
<style lang="">
  
</style>