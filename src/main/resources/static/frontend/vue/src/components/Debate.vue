<template>
    <div>
      <div class="debate-list">
        <div class="btn">
          <button v-on:click="add()">추가</button>
          <button v-on:click="cancel()">취소</button>
        </div>
        <div class="edit" v-if="EditMode === 1">
          <form method="POST" action="http://127.0.0.1:8080/addDebate">
            <input name="title" class = "title" type="text" v-model="title"/>
            <textarea name="content" class="content" v-model="content"></textarea>
            <button class  = "btn" type="submit">저장</button>
          </form>
        </div>
        <div class="edit" v-if="EditMode === 2">
          <form method="POST" action="http://127.0.0.1:8080/addDebate">
            <input name="title" class = "title" type="text" v-model="title"/>
            <div class="streaming">
              <video playsinline id="cam" controls preload="metadata" autoplay></video>
            </div>
          </form>
        </div>
        <div class="edit" v-if="! EditMode">
          <div class = "debate-content-list" v-for="i in debateList" v-bind:key="i.no" v-on:click="clickContent(i.no)">
            {{ i.title }}
          </div>
        </div>
      </div>
    </div>
</template>
<script>
import axios from 'axios'
const constraints = {
  'video': true,
  'audio': true
}
navigator.mediaDevices.getUserMedia(constraints)
  .then(stream => {
    console.log('Got MediaStream:', stream)
  })
  .catch(error => {
    console.error('Error accessing media devices.', error)
  })

function getConnectedDevices (type, callback) {
  navigator.mediaDevices.enumerateDevices()
    .then(devices => {
      const filtered = devices.filter(device => device.kind === type)
      callback(filtered)
    })
}

getConnectedDevices('videoinput', cameras => console.log('Cameras found', cameras))

export default {
  name: 'debate',
  data () {
    return {
      title: undefined,
      content: null,
      EditMode: 0,
      debateList: []
    }
  },
  methods: {
    add () {
      this.EditMode = 1
    },
    cancel () {
      this.EditMode = 0
      console.log(this.debateList)
    },
    save () {
      this.EditMode = 0
    },
    clickContent (boardNo) {
      let params = new URLSearchParams()
      params.append('bNo', boardNo)
      axios.post('http://127.0.0.1:8080/getDebateContent/', params)
        .then(response => {
          console.log(response)
          this.debateList[0] = response.data
          console.log(this.debateList)
          this.EditMode = 2
          this.title = response.data.title
          this.content = response.data.content
        })
        .catch(e => {
          console.log('error : ', e.response)
        })
    },
    getDebate () {
      axios.get('http://127.0.0.1:8080/getDebate/')
        .then(response => {
          console.log(response)
          this.debateList = response.data
          console.log(this.debateList)
        })
        .catch(e => {
          console.log('error : ', e)
        })
    }
  },
  created: function () {
    axios.get('http://127.0.0.1:8080/getDebate/')
      .then(response => {
        console.log(response)
        this.debateList = response.data
        console.log(this.debateList)
      })
      .catch(e => {
        console.log('error : ', e)
      })
  }
}
</script>
<style>
  .debate-list{
    width: 80%;
    margin: 0 0 0 10%;
    height: 100%;
  }
  .btn{
    float: right;
  }
  .edit{
    clear: both;
  }
  input {
    width:100%;
    height:20px;
    margin : 10px 0 10px 0;
  }
  textarea{
    width:100%;
    height: 300px;
  }
  .debate-content-list{
    width: 100%;
    height: 10%;
    border: 1px solid antiquewhite;
  }
  .streaming{
    width: 100%;
    height: 500px;
    border: 1px solid;
  }
</style>
