// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import Datetime from 'vue-datetime'
import StarRating from 'vue-star-rating'
import moment from 'moment'
import VueMomentJS from 'vue-momentjs'

// You need a specific loader for CSS files
import 'vue-datetime/dist/vue-datetime.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(Datetime)
Vue.use(VueMomentJS, moment)
Vue.component('star-rating', StarRating)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
