<template>
  <div id="app">
    <navigation @nav="nav"></navigation>
    <!-- <router-view/> -->
    <page :curPage='curPage'></page>
    <router-view/>
  </div>
</template>

<script>
import Page from './components/Page.vue'
import Main from './components/Navigation'

export default {
  name: 'App',
  data () {
    return {
      curPage: 0,
      pages: [
        {'id': 1, name: 'debate'},
        {'id': 2, name: 'debate'}
      ]
    }
  },
  methods: {
    nav (page) {
      this.curPage = page
    }
  },
  // methods: {
  //   movePage (page) {
  //     this.curPage = page.name
  //     this.$router.push(this.curPage)
  //   }
  // },
  components: {
    'page': Page,
    'navigation': Main
  }
}
</script>

<style>
/* #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */

</style>
