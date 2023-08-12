<template>
  <v-container fluid :grid-list-md="!$vuetify.breakpoint.xs">
    <v-layout wrap row>
      <v-flex xs12 sm6 md3 class="pb-2"
              v-for="item in stockList"
              :key="item.stockId">
        <v-card>
          <v-container pa-1>
            <v-layout row>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <h3 class="headline">{{ item.stockName }}</h3>
                  </div>
                </v-card-title>
              </v-flex>
              <v-flex xs5>
                <v-card-title primary-title>
                  <v-avatar>
                    <v-icon medium color="orange darken-1">person</v-icon>
                    <div>{{item.peopleCnt}}</div>
                  </v-avatar>
                </v-card-title>
              </v-flex>
            </v-layout>
            <v-divider light></v-divider>
            <v-card-actions class="pa-2">
              <v-icon small color="primary">update</v-icon>
              <span class="grey--text caption font-italic">&nbsp;5분전</span>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-container>
        </v-card>
      </v-flex>

    </v-layout>

  </v-container>
</template>
<script>
export default {
  data () {
    return {
      stockList: [
        {
          roomId : '1',
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          roomId : '2',
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          roomId : '3',
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          roomId : '4',
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
      ]
    }
  },
}

</script>
<style lang="">

</style>