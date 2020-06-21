<template>
    <div id="medicalChart" style="width:100%; height:400px;"></div>
</template>
<script>
import axios from 'axios'

// document.addEventListener('DOMContentLoaded', function () {
//         var myChart = Highcharts.chart('medicalChart', {
//             chart: {
//                 type: 'bar'
//             },
//             title: {
//                 text: 'Fruit Consumption'
//             },
//             xAxis: {
//                 categories: ['Apples', 'Bananas', 'Oranges']
//             },
//             yAxis: {
//                 title: {
//                     text: 'Fruit eaten'
//                 }
//             },
//             series: [{
//                 name: 'Jane',
//                 data: [1, 0, 4]
//             }, {
//                 name: 'John',
//                 data: [5, 7, 3]
//             }]
//         });
//     });
export default {
  name: 'medical',
  data () {
    return {
        medicalList: [],
        rankList: {}
    }
  },
  created: function () {
    axios.get('http://localhost:8081/getMedical/')
      .then(response => {
        console.log(response)
        this.medicalList = response.data
        console.log(this.medicalList)
        // this.rankList.sidoCdNm = []; 
        // this.rankList.sidoCdNm = this.medicalList.map(medical => {
        //   return medical.sidoNm
        // });
        this.rankList.seoul = []; 
        this.rankList.gyeonggi = [];
        this.rankList.asmGrd = [0,0,0];
        this.medicalList.forEach(medical => {
          // if(medical.sidoCdNm === '서울')
          //   this.rankList.seoul.push(medical.asmGrd);
          // else if(medical.sidoCdNm === '경기')
          //   this.rankList.gyeonggi.push(medical.asmGrd);
          if(medical.asmGrd === '1')
            this.rankList.asmGrd[0]++; 
          else if(medical.asmGrd === '2')
            this.rankList.asmGrd[1]++;
          else if(medical.asmGrd === '3')
            this.rankList.asmGrd[2]++;
        });

        this.category=[];
        console.log(this.rankList);
        var Highcharts = require('highcharts');  
      
    // Load module after Highcharts is loaded
      require('highcharts/modules/exporting')(Highcharts);  
      // Create the chart
      Highcharts.chart('medicalChart', {
        renderto: "medicalChart",
            chart: {
                type: 'bar'
            },
            title: {
                text: '건강'
            },
            xAxis: {
                categories: ['1', '2', '3']
            },
            yAxis: {
                title: {
                    text: '등급'
                }
            },
            series: [{
                name: '1',
                data: this.rankList.asmGrd
            }]
        });


      })
      .catch(e => {
        console.log('error : ', e)
      });
  },
  mounted: function() {
     
  }
}
</script>
<style>
</style>
