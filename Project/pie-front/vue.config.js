const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  lintOnSave: false,

  devServer: {
    proxy: {
      '/chat': {
        target: 'http://54.180.115.36:8081',
        changeOrigin: true
      },
      // '/dash-board': {
      //   target: 'https://api.finance.naver.com',
      //   ws: true,
      //   changeOrigin: true,
      //   secure: false,
      // },
    }
  }
})