import axios from 'axios'
import { encoding_key } from '../env/api'


var stockLists = {};
const itemsName = [{
  stockName: "삼성전자"},
{
  stockName: "POSCO홀딩스"
},{
  stockName: "비츠로테크"
},{
  stockName: "에코프로"
},{
  stockName: "금양"
},{
  stockName: "카카오"
},{
  stockName: "NAVER"
},{
  stockName: "신성이엔지"
},{
  stockName: "SK하이닉스"
},{
  stockName: "셀트리온"
},{
  stockName: "현대차"
},{
  stockName: "NH투자증권"
},{
  stockName: "마녀공장"
},
]

const findStock = (value) => 
  { 
    axios.get(`https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=${encoding_key.key}&itmsNm=${itemsName[value].stockName}&numOfRows=1&resultType=json`)
    .then(res => {
      stockLists = res.data.response.body.items.item[0];
      // console.log('11111', stockLists);
      return stockLists;
    })
    .catch(err => {
      console.log('err', err);
    })
  }



export { findStock };