<template>
  <v-app :style="{background: $vuetify.theme.themes.light.background}">
    <v-layout row>      
      <v-flex md3>
        <v-app :style="{background: $vuetify.theme.themes.light.background}">
          <v-container>

            <!-- 왼쪽1 --->
            <v-flex>
              <v-card class="ma-5 text-center mt-8" shaped elevation="10">
                <v-avatar class="mt-n7" size="60" elevation="10">
                  <!-- 프로필 이미지 -->
                  <img src="../assets/person.png">
                </v-avatar>
      
                <v-card-title class="layout justify-center">{{ name }}</v-card-title>
                <v-card-subtitle class="layout justify-center">{{age}}세, {{job}}</v-card-subtitle>
                <v-list class="mt-n5">
                  <v-list-item>
                    <v-list-item-title class="green--text text--darken-1">주식 총액</v-list-item-title>
                    <v-list-item-title class="green--text text--darken-1">수익률</v-list-item-title>
                    <v-list-item-title class="green--text text--darken-1">가입일자</v-list-item-title>
                  </v-list-item>
                  <v-list-item class="mt-n5">
                    <v-list-item-subtitle>78만</v-list-item-subtitle>
                    <v-list-item-subtitle>7.56%</v-list-item-subtitle>
                    <v-list-item-subtitle>{{today}}</v-list-item-subtitle>
                  </v-list-item>
                </v-list>
              </v-card>
            </v-flex>

            <!-- 왼쪽2 -->
            <v-flex>
              <v-card class="ma-5 mt-n2" shaped elevation="10">
              
                <!-- 왼쪽 2-1  -------------------->
                <v-list two-line="" subheader="">
                  <v-list-item>
                    <v-list-item-avatar size="60" color="green darken-3">
                      <v-icon color="white">fas fa-person</v-icon>
                    </v-list-item-avatar>
                    <v-list-item-content class="ml-n2">
                      <v-list-item-title>{{grade}}</v-list-item-title>
                        </v-list-item-content>
                  </v-list-item>
                  <v-divider></v-divider>
                </v-list>
                <v-list>
                  <v-list-item>
                      <v-list-item-avatar size="10" color="green darken-3"></v-list-item-avatar>
                      <v-list-item-title class="ml-n2">투자성향</v-list-item-title>
                      <v-list-item-subtitle>{{investOpt}}</v-list-item-subtitle>
                  </v-list-item>

                  <v-list-item>
                      <v-list-item-avatar size="10" color="green darken-3"></v-list-item-avatar>
                      <v-list-item-title class="ml-n2">연봉</v-list-item-title>
                      <v-list-item-subtitle>{{salary}}만원</v-list-item-subtitle>
                  </v-list-item>

                  <v-list-item>
                      <v-list-item-avatar size="10" color="green darken-3"></v-list-item-avatar>
                      <v-list-item-title class="ml-n2">재산</v-list-item-title>
                      <v-list-item-subtitle>{{property}}만원</v-list-item-subtitle>
                  </v-list-item>
                  <v-divider></v-divider>
                </v-list>
              </v-card>
            </v-flex>

            <!-- 왼쪽 3------->
            <v-flex> 
              <v-card class="ma-5 text text-center mt-6" shaped elevation="10">
                <v-list-item three-line router to="/">
                  <v-list-item-content>
                    <v-list-item-title class="headline mb-1">
                        <v-img
                          :src="require('../assets/koscom.jpg')"
                          width="80"
                        />
                     </v-list-item-title>
                    </v-list-item-content>
                  <v-list-item-avatar size="60" color="orange darken-3">
                    <v-icon color="white">fas fa-person</v-icon>
                  </v-list-item-avatar>
                </v-list-item>
              </v-card>
            </v-flex>
          </v-container>
        </v-app>
      </v-flex>

  <!-- 뉴스 레터 및 주식 종목 칸---------------------->
  <v-flex md9>
    <v-app :style="{background: $vuetify.theme.themes.dark.background}">
      <v-container>
        <v-flex>
          <v-list class="mt-5">
            <v-list-item>
              <v-list-item-title class="green--text text--darken-3">최근 인기 종목</v-list-item-title>
                <v-list-item-action>
                  <v-btn class="ma-2" tile outlined="" color="green darken-3"> 
                   <v-icon left>fas fa-eye</v-icon> 보기
                  </v-btn>
                </v-list-item-action>
            </v-list-item>
          </v-list>
        </v-flex>

  
  <v-flex>    
    <v-row no-gutter>    
      <v-col cols="12" md="4">
        <v-row>
          <v-col cols="12" md="2">
            <v-card height="80px" width="10px" color="orange darken-1"></v-card>
          </v-col>
          <v-col cols="12" md="10">            
            <v-list two-line="" subheader="" class="ml-n8" >
              <v-list-item>
                <v-list-item-avatar size="60" >
                    <v-icon color="orange darken-3">fas fa-sort-up</v-icon>  
                </v-list-item-avatar>
                <v-list-item-content>                    
                    <v-list-item-subtitle>삼성전자</v-list-item-subtitle>
                    <v-list-item-title>68,000원</v-list-item-title>                    
                </v-list-item-content>
                
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-col>

      <v-col cols="12" md="4">
        <v-row>
          <v-col cols="12" md="2">
            <v-card height="80px" width="10px" color="orange darken-1"></v-card>
          </v-col>
          <v-col cols="12" md="10">
            <v-list two-line subheader class="ml-n8">
              <v-list-item>
                <v-list-item-avatar size="60" >
                    <v-icon color="red accent-4" size="30">fas fa-sort-up</v-icon>  
              </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-subtitle>에코프로</v-list-item-subtitle>
                  <v-list-item-title>1,137,000원</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-col>
      
      <v-col cols="12" md="4">
        <v-row>
          <v-col cols="12" md="2">
            <v-card height="80px" width="10px" color="orange darken-1"></v-card>
          </v-col>
          <v-col cols="12" md="10">
            <v-list two-line subheader class="ml-n8">
              <v-list-item>
                <v-list-item-avatar size="60" >
                    <v-icon color="red accent-4" size="30">fas fa-sort-up</v-icon>  
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-subtitle>POSCO홀딩스</v-list-item-subtitle>
                  <v-list-item-title>577,000원</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-col>

    </v-row>
  </v-flex>

  <v-flex class="mt-12">
    <v-row no-gutters="">
      

    <v-col md="8">
          <v-flex class="ml-10">
            <v-list>
              <v-list-item>
                <v-list-item-title class="green--text text--darken-3">*주식 종목 관련 뉴스</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-flex>
          <v-flex class="ml-10">
            <v-row>
              <v-card>
                <!-- <v-card-title>
                  <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-card-title> -->
                <v-data-table
                  :headers="headers"
                  :items="list"
                  :search="search"
                ></v-data-table>
              </v-card>
            </v-row>
          </v-flex>
    </v-col>

    </v-row>
  </v-flex>
      </v-container>

    </v-app>
  </v-flex>
    </v-layout>
  </v-app>
</template>

<script>
import axios from 'axios'
// import stock from '../util/stock.js'
import * as news from '../util/news.js'
import list from '../assets/data/news.json'

export default {
  name: "DashBoard",
  data: () => ({
    today: new Date().toLocaleDateString(),
    search: '',
    headers: [
      { text: '목차', sortable: false, value: 'NO'},
      { text: '뉴스 제목', value: 'title' },
      { text: '뉴스사', value: 'author' }
    ],
    list: list,
    //   {
    //     type: '국내주식',
    //     stockNm: '삼성전자',
    //     valEvalu: '880,000원',
    //     earningRate: '25.71%',
    //     profit: '180,000원',
    //     qty: 10,
    //     valTrade: '70,000원',
    //     valstock: '88,000원',
    //   },
    //   {
    //     type: '국내주식',
    //     stockNm: '대한항공',
    //     valEvalu: '303000원',
    //     earningRate: '21.20%',
    //     profit: '53,000원',
    //     qty: 10,
    //     valTrade: '25,000원',
    //     valstock: '30,300원',
    //   },
    //   {
    //     type: '국내주식',
    //     stockNm: '기아차',
    //     valEvalu: '892,000원',
    //     earningRate: '4.94%',
    //     profit: '42,000원',
    //     qty: 10,
    //     valTrade: '85,000원',
    //     valstock: '89,200원',
    //   },
      
    name: '콤이',
    grade: '중급 투자자',
    age: '28',
    job: '전업 투자자',
    investOpt: '3등급 (중위험)',
    salary: '5800만원',
    property: '1억 2천5백',
  }),
  created(){
  //   axios.get(''')
  //     .then(res => {
  //       // console.log(res.data);
    // console.log(stock('000020'));
    
  },
  mounted(){
    // console.log("")
    // console.log(news.fetchNews())
  },
  methods: {
    // stock,
    // news
  }
}
</script>



<style scoped>
.rounded {
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
}
</style>

