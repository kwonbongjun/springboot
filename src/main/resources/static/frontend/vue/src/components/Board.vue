<template>
    <div class="board-body">
        <b-button
            class="add"
            v-on:click="add()" 
            variant="primary"
            size="sm"
        >
        add
        </b-button>

        <ag-grid-vue
            v-if="! EditMode"
            style="width: 500px; height: 500px;"
            class="ag-theme-alpine"
            :columnDefs="columnDefs"
            :rowData="rowData"
        ></ag-grid-vue>

        <div class="edit" v-if="EditMode === 1">
            <b-form v-on:submit.prevent="onSubmit">
                <input name="title" class="title" type="text" v-model="title" />
                <textarea name="content" class="content" v-model="content"></textarea>
                <b-button class="btn" type="submit">저장</b-button>
            </b-form>
        </div>
    </div>
</template>
<script>
import { AgGridVue } from "ag-grid-vue";
import axios from 'axios'

export default {
    name: "board",
    components: {
        AgGridVue
    },
    beforeMount() {
        axios.get('http://localhost:8081/getBoard/')
      .then(response => {
        console.log(response)
        this.rowData = response.data
        console.log(this.rowData)
      })
      .catch(e => {
        console.log('error : ', e)
      })
    },
    data() {
        return {
            EditMode : 0,
            title: undefined,
            content: undefined,
            columnDefs: [
                {
                    headerName: 'No',
                    field: 'no'
                }
            ],
            rowData: [],
            gridOptions: {
                columnDefs: [
                    {
                        headerName: 'No',
                        field: 'no'
                    }
                ],
                rowData: []
            }
        }
    },
    methods: {
        onSubmit() {
            let params = new URLSearchParams();
            params.append('title', this.title)
            params.append('content', this.content);
            axios.post("http://localhost:8081/addBoard/", params)
                .then(response => {
                    console.log(response);
                    this.EditMode = 0;
                })
                .catch(e => {
                    console.log("error : ", e.response);
                });
        },
        add() {
            this.EditMode = 1;
        }
    }
};
</script>
<style scoped>
.board-body {
    position: absolute;
    width: calc(100% - 10px);
    height: 70%;

    > .add {
    position: absolute;
    left: 0;
    }
}
</style>
