<template>
    <div class="">
    <h1>Adventures of Brain Cells</h1>
    <div class="navs-bar">
      <ul>
        <li class="nav-bar" v-for="page in pages" v-bind:key="page.id" v-on:click="movePage(page)">
            <!-- <a :href="page.name">{{page.name}}</a> -->
            {{ page.name}}
        </li>
      </ul>
    </div>
    </div>
</template>

<script>
export default {
  name: 'navigation',
  data () {
    return {
      curPage: 0,
      pages: [{'id': 1, name: 'debate'}, {'id': 2, name: 'board'}]
    }
  },
  methods: {
    movePage (page) {
      console.log(page.id)
      this.curPage = page.name
      // location.href = this.curPage
      // this.$router.push(this.curPage).catch(err => err)
      this.$emit('nav', this.curPage)
      // return '/' + this.curPage
      return this.curPage
    }
  }
}
</script>
<style>
  .navs-bar {
      background-color: aliceblue;
  }

  .nav-bar {
      display: inline-block;
      height: 50px;
      margin: 10px;
  }
</style>
