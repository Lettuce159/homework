import Vue from 'vue'
import Router from 'vue-router'
import Home from "../views/Home"
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '/Login',
      component: Login
    },
    {
      path: '/Register',
      name: '/Register',
      component: Register
    },
    {
      path: '/Home',
      name: '/Home',
      component: Home
    },

  ]
})
