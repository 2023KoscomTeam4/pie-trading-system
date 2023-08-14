<template>
  <v-app id="inspire">
    <v-main class="orange lighten-4">
      <!-- Login Component -->
      <v-container style="max-width: 450px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card>
              <div class="pa-10">
                <h1 style="text-align: center" class="mb-10">LOGIN</h1>
                <form>
                  <v-text-field
                      v-model="form.id"
                      label="ID"
                      prepend-inner-icon="mdi-account"
                  ></v-text-field>
                  <v-text-field
                      v-model="form.password"
                      prepend-inner-icon="mdi-lock"
                      type="password"
                      label="Password"
                  >
                  </v-text-field>
                  <v-btn
                      @click="login"
                      color="orange lighten-1 text-capitalize"
                      depressed
                      large
                      block
                      dark
                      class="mb-3"
                  >
                    로그인
                  </v-btn>
                </form>
              </div>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      form: {
        id: "",
        password: ""
      }
    };
  },
  methods: {
    login() {
      // 여기서는 DB 검색을 하지 않고, ID와 비밀번호가 일치하는지만 확인합니다.
      // 실제로는 서버에서 검증을 수행해야 하며, 이 예제에서는 클라이언트 측에서만 처리합니다.
      sessionStorage.setItem("userId", this.form.id);
      window.location.href ="/dash-board"; //홈 페이지로 리디렉션
    }
  }
};
</script>
