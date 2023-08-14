<template>
    <v-container grid-list-xs pa-0 style="background: white;">
  
      <!-- 타이틀 -->
      <v-layout row style="height: 64px;" align-center>
        <v-btn @click="clickBack()" absolute color="#fff" fab small style="min-width: 24; min-height: 24; margin-left: 16px; margin-top: 0; margin-bottom: 0;"><v-icon>keyboard_backspace</v-icon></v-btn>  
        <v-layout row align-center justify-center ma-0 pa-0>
          <p class="noto16">로그인</p>    
        </v-layout>
      </v-layout>
      
      <v-layout column style="padding: 16px 250px 16px 250px; margin-top: 8px;">

            <form @submit.prevent="submit()">
            <v-text-field
                label="아이디"
                v-model="user.username"
                :rules="[() => !!user.username || '필수 입력 항목입니다.']"
                autofocus
                solo
                height="48"
                class="noto"
            ></v-text-field>
            
    
            <v-text-field
                label="비밀번호"
                ref="password"
                :type="'password'"
                :rules="[() => !!user.password || '필수 입력 항목입니다.']"
                v-model="user.password"
                @keydown="checkLogin = false"
                solo
                height="48"
                style="margin-top: 16px;"
                class="noto"
            ></v-text-field>
    
            <v-btn 
                class="noto16 pa-0 ml-0 mr-0" 
                color="primary" 
                block
                style="margin-top: 24px; height: 48px;"
                type="submit"
            >로그인</v-btn>
    
            </form>
  
          <v-layout v-if="checkLogin" row style="margin-top: 12px;" mb-0>
            <p class="noto error-text">{{loginErrorMessage}}</p>
          </v-layout>
  
          <!-- <v-layout row wrap style="margin-top: 14px; margin-bottom: 0px;">
            <v-checkbox color="primary" v-model="saveId" class="mt-0 pt-0">
              <template v-slot:label>
                <div class="saveId">자동로그인</div>
              </template>
            </v-checkbox>
          </v-layout> -->
  
          <v-layout style="margin-top: 14px;">
            <p class="noto12" style="color: #9e9e9e;">아이디/비밀번호 찾기</p>
          </v-layout>
  
          <v-layout row wrap align-center justify-center style="margin-top: 40px;">
            <v-btn color="white" class="noto" @click="join()">회원가입</v-btn>
          </v-layout>
        
      </v-layout>
      
    </v-container>
  </template>
  
  <script>
  export default {
  
    data: () => ({
      user: {
        username: '',
        password: ''
      },
      
      saveId: true,
      loginErrorMessage: '이이디 또는 비밀번호를 확인해주세요.',
  
      // 로그인 실패 유무
      checkLogin: false
    }),
  
    mounted () {
    //   const id = this.$cookies.get('loginId')
    },
  
    methods: {
      submit () {
            if (this.user.username !== "" && this.user.password !== "") {      
                this.$router.push('/dash-board')
            } else {
              this.showLoginInvalidation()
              this.loginErrorMessage = '아이디 또는 비밀번호를 확인해주세요'
            }
      },
  
      clickBack () {
        this.$router.push('/dash-board')
      },
  
      showLoginInvalidation () {
        this.checkLogin = true
  
        this.$refs.password.focus()
      },
  
      returnLoginInvalidation () {
        this.checkLogin = false
      },
  
      findId () {
        this.$router.push('/findId')
      },
  
      findPassword () {
        this.$router.push('/findPwd')
      },
  
      join () {
        this.$router.push('/dash-board')
      },
  
      rememberId () {
        this.$cookies.set('loginId', this.user.username)
      },
  
      removeId () {
        this.$cookies.remove('loginId')
      }
    }
  } 
  </script>
  
  <style scoped>
  .noto {
    font: 400 13px/19px Noto Sans CJK KR !important;
    letter-spacing: -0.26px;
  }
  
  .noto12 {
    font: 400 12px/18px Noto Sans CJK KR !important;
    letter-spacing: -0.24px;
  }
  
  .noto16 {
    font: 400 16px/18px Noto Sans CJK KR !important;
    letter-spacing: -0.32px;
    color: #333;
  }
  
  .login-input.v-input--is-focused >>> .v-input__control > div {
    border: 1px solid #3e81f6 !important;
  }
  
  .login-input input:-webkit-autofill,
  .login-input input:-webkit-autofill:hover,
  .login-input input:-webkit-autofill:focus,
  .login-input input:-webkit-autofill:active {
    transition: background-color 5000s ease-in-out 0s;
    -webkit-transition: background-color 9999s ease-out;
    -webkit-box-shadow: 0 0 0px 1000px white inset !important;
  }
  
  @media (min-width: 1904px) {
    .container {
      max-width: 1080px !important;
    }
  }
  
  .saveId {
    font-size: 14px;
  }
  
  .v-checkbox {
    color: #3e81f6 !important;
  }
  
  .error-text {
    color: #ff5252 !important;
    font-size: 12px;
  }
  </style>