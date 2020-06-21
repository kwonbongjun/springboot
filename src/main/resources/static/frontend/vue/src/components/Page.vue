<template>
  <!-- <div id="page" v-if="curPage != 0" v-bind:cur-page="setPage()"> -->
  <div id="page" class="page">
    <component component :is="curPage"></component>
    <!-- <router-link to="debate"></router-link> -->
    <!-- <router-view/> -->
  </div>
</template>

<script>
import Debate from "./Debate";
import Medical from "./Medical";
import Board from "./Board";
export default {
  name: "page",
  data() {
    return {
      page: 0,
      pages: [
        { id: 1, name: "debate" },
        { id: 2, name: "medical" },
        { id: 3, name: "board" }
      ]
    };
  },
  methods: {
    setPage() {
      this.page = this.curPage;
    }
  },
  props: ["curPage"],
  components: {
    debate: Debate,
    medical: Medical,
    board: Board
  }
};
</script>
<style scoped>
.page {
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>
