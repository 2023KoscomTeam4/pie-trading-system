const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  lintOnSave: false,

  devServer: {
    proxy: {
      '/chat': {
        target: 'http://localhost:8081',
        changeOrigin: true
      }
    }
  }
})