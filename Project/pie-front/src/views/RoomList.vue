<template>
  <v-container fluid :grid-list-md="!$vuetify.breakpoint.xs">
    <v-layout wrap row>
      <v-flex md12 class="pb-2"
              v-for="item in roomList"
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

              <v-chart type="pie" :options="options" :series="series" />

              <v-flex xs5>
                <v-card-title primary-title>
                  <v-avatar>
                    <v-icon medium color="orange darken-1">person</v-icon>
                    <div>{{item.personCnt}}</div>
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
      series: [],
      options: {
        labels: [],
      },
    };
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
                this.updateSeriesAndLabels(); // series와 labels 업데이트
              });
    },
    updateSeriesAndLabels() {
      // roomList로부터 series와 labels 추출
      this.series = this.roomList.map(item => item.roomMemberList?.length || 0);
      // this.options.labels = this.roomList.map(item => item.userId); // 또는 필요한 라벨

      // 색상 계열을 생성하고 options에 설정
      this.options.colors = this.generateColors(this.series.length, '#5C4742');

      // 합계를 100%로 만들기 위한 스케일링
      const sum = this.series.reduce((total, value) => total + value, 0);
      this.series = this.series.map(value => (value / sum) * 100);

      // 합계가 100% 미만인 경우
      if (sum < 100) {
        this.series.push(100 - sum); // 빈칸 부분 추가
        this.options.labels.push('Empty'); // 라벨 추가
        this.options.colors.push('#FFFFFF'); // 빈칸 색상 (흰색 또는 원하는 색상)
      }
    },
    generateColors(count, baseColor) {
      let colors = [];
      for (let i = 0; i < count; i++) {
        const color = this.lightenDarkenColor(baseColor, i * 10); // 색상 변경 단계를 조정할 수 있습니다.
        colors.push(color);
      }
      return colors;
    },
    lightenDarkenColor(col, amt) {
      let usePound = false;
      if (col[0] === '#') {
        col = col.slice(1);
        usePound = true;
      }
      const num = parseInt(col, 16);
      let r = (num >> 16) + amt;
      if (r > 255) r = 255;
      else if (r < 0) r = 0;
      let b = ((num >> 8) & 0x00FF) + amt;
      if (b > 255) b = 255;
      else if (b < 0) b = 0;
      let g = (num & 0x0000FF) + amt;
      if (g > 255) g = 255;
      else if (g < 0) g = 0;
      return (usePound ? '#' : '') + (g | (b << 8) | (r << 16)).toString(16);
    },
  }
}
</script>
<style lang="">

</style>