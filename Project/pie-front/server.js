'use strict'

// server.js
var express = require('express');
var cors = require('cors');
var request = require('request');
var serveStatic = require('serve-static');
var history = require('connect-history-api-fallback');
    // redirect to index. https://github.com/bripkens/connect-history-api-fallback

var client_id = '';
var client_secret = '';

var app = express();
app.use('/', [history(), serveStatic(__dirname + "/dist")]);
    // multiple middleware. http://expressjs.com/en/4x/api.html#app.use

app.use(cors())


app.get('/', (req, res)=>{
    res.sendFile(path.join(__dirname, '/dist/index.html'));  
})

var server_port = process.env.PORT || 80
app.listen(server_port);
console.log( "Listening on port " + server_port );


function fetchNews() {
    var api_url = 'https://openapi.naver.com/v1/search/news?display=30&query="주식"'; // json 결과
   
    var options = {
        url: api_url,
        headers: {'X-Naver-Client-Id':client_id, 'X-Naver-Client-Secret': client_secret}
     };
    request.get(options, function (error, response, body) {
      if (!error && response.statusCode == 200) {
        // response.writeHead(200, {'Content-Type': 'text/json;charset=utf-8'});
        // response.end(body);
        console.log(response)
        console.log(body)
      } else {
        console.log('error = ' + response.statusCode);
      }
    });
  };

//   fetchNews();

// const config = {
//   baseUrl: 'https://openapi.naver.com/v1/'
// };

// function fetchNews() {
//     axios.get(`${config.baseUrl}search/news.json`)
//     .then(function (response){
//         console.log(response);
//     });
// }

// fetchNews();