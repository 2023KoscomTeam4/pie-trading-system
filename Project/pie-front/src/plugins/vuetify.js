import '@fortawesome/fontawesome-free/css/all.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import Vue from "vue";
import Vuetify from 'vuetify'
import colors from 'vuetify/lib/util/colors'
import VueApexCharts from 'vue-apexcharts'

Vue.use(Vuetify);
Vue.use(VueApexCharts)

export default new Vuetify({
    icons: {
        iconfont: 'md' || 'fa'
    },
    theme: {
        themes: {
            light: {
                background: colors.grey.lighten2,
            },
            dark: {
                background: colors.shades.white,
            },
        },
    },
});
