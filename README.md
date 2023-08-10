## 2023 신입사원 코스콤 해커톤 4조 프로젝트
## Pie Trading System


### API 명세서
[API 명세서 공유드라이브 링크](https://docs.google.com/spreadsheets/d/1FOqF6F19sRKvJNei5W1T_9T602tRyfQBc271wNGJjXk/edit?pli=1#gid=0)


### 스펙
- FrontEnd : Vue Framework
- BackEnd : Java & Spring
- DB : 

### URL 및 포트 설정 (소켓통신 개발시, CORS 보안 관련 필요)
- FrontEnd : http://localhost:3000/
- BackEnd : http://localhost:8081/

### 개발시 공유 및 유의사항
- FrongEnd
```
--터미널에서 package.json 파일이 있는 폴더까지 내려와서 명령어 작성
\workspace\pie-trading-system\Project\pie-front>  npm i

--서버 시작하는 명령어
\workspace\pie-trading-system\Project\pie-front>  npm run dev

--개발시 필요로 인해 설치한 내용 공유
 npm install --no-fund
 npm config set fund false --location=global
 npm install --save axios
 npm install sockjs-client 
 npm install stomp-websocket
 npm install @stomp/stompjs
```