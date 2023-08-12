import axios from 'axios'

var stockLists = {};

axios.get('https://api.finance.naver.com/siseJson.naver?symbol=005930')
  .then(res => {
    stockLists = res.data;
    console.log(res.data);
  })
  .catch(err => {
    console.log('err', err);
  })

const findStock = ()=>{
    console.log(stockLists);
    return stockLists;
  }
  export default findStock;