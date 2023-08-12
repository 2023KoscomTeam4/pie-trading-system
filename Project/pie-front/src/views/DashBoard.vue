<template>

  <v-app :style="{background: $vuetify.theme.themes.light.background}">
      <v-layout row>      
          <v-flex md3>
              <v-app :style="{background: $vuetify.theme.themes.light.background}">
                  <v-container>

                      <!-- 왼쪽 프로필 Box1-------------------------------------------->
                      <v-flex>
                          <v-card class="ma-5 text-center mt-8" shaped elevation="10">
                              <v-avatar class="mt-n7" size="60" elevation="10">
                              <!-- 프로필(개인) 이미지 -->
                                  <img src="../assets/logo.png">
                              </v-avatar>

                              <!-- 이름: 회원DB NAME -->            
                              <v-card-title class="layout justify-center">{{ name }}님</v-card-title>
                              <!-- 나이, 직업: 회원DB -->
                              <v-card-subtitle class="layout justify-center">{{age}}세, {{job}}</v-card-subtitle>
                              <v-list class="mt-n5">
                              <v-list-item>
                                  <!-- 순위, 구독자, 수익률: TEXT  -->
                                  <v-list-item-title class="orange--text text--darken-3">순위</v-list-item-title>
                                  <v-list-item-title class="orange--text text--darken-3">구독자</v-list-item-title>
                                  <v-list-item-title class="orange--text text--darken-3">수익률</v-list-item-title>
                              </v-list-item>
                              <v-list-item class="mt-n5">
                                  <!-- 순위: 계산, 구독자: 구독 DB, 수익률: 계산 -->
                                  <v-list-item-subtitle>1위</v-list-item-subtitle>
                                  <v-list-item-subtitle>1.2만</v-list-item-subtitle>
                                  <v-list-item-subtitle>543%</v-list-item-subtitle>
                              </v-list-item>
                              </v-list>
                          </v-card>
                      </v-flex>
                  </v-container>
              </v-app>
          </v-flex>


          <v-flex>    
    <v-row no-gutter>   
      <!-- 오른쪽 상단 BOX1 --------------------------------------->   
      <v-col cols="12" md="4">
        <v-row>
          <v-col cols="12" md="2">
            <v-card height="80px" width="10px" color="orange darken-1"></v-card>
          </v-col>
          <v-col cols="12" md="10">            
            <v-list two-line="" subheader="" class="ml-n8" >
              <v-list-item>
                <v-list-item-avatar size="60" >
                    <v-icon color="orange darken-3">fas fa-wallet</v-icon>  
                </v-list-item-avatar>
                <v-list-item-content>                    
                    <v-list-item-subtitle>총 자산</v-list-item-subtitle>
                    <v-list-item-title>2,000,000원</v-list-item-title>                    
                </v-list-item-content>
                
              </v-list-item>
            </v-list>
          </v-col>
        </v-row>
      </v-col>
      </v-row>
      </v-flex>


       
      </v-layout>
  </v-app>

</template>
<script>
export default {
  data: () => ({
      drawer: true,
      name: "코스콤",
      age: 13,
      job: "프로그래머"
  })
}
</script>