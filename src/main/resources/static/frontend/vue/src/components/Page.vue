<template>
  <!-- <div id="page" v-if="curPage != 0" v-bind:cur-page="setPage()"> -->
  <div id="page">
    <component component :is="curPage"></component>
    <!-- <router-link to="debate"></router-link> -->
    <!-- <router-view/> -->
  </div>
</template>

<script>
import Debate from './Debate'
export default {
  name: 'page',
  data () {
    return {
      page: 0,
      pages: [{'id': 1, name: 'debate'}, {'id': 2, name: 'debate'}]
    }
  },
  methods: {
    setPage () {
      this.page = this.curPage
    }
  },
  props: ['curPage'],
  components: {
    'debate': Debate
  }
}
</script>
