import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import request from '@/utils/request'
import router from './router'
import store from './store'


Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});
Vue.prototype.request=request
new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
