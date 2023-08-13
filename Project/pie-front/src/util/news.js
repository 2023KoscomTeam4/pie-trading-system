import axios from 'axios';

const config = {
  baseUrl: 'https://openapi.naver.com/v1/'
};

function fetchNews() {
  return axios.get(`${config.baseUrl}search/news.json`);
}

function fetchAsk() {
  return axios.get(`${config.baseUrl}ask/1.json`);
}

function fetchJobs() {
  return axios.get(`${config.baseUrl}jobs/1.json`);
}

function fetchUser(id) {
  return axios.get(`${config.baseUrl}user/${id}.json`);
}

function fetchItem(id) {
  return axios.get(`${config.baseUrl}item/${id}.json`);
}

export {
  fetchNews,
  fetchAsk,
  fetchJobs,
  fetchUser,
  fetchItem
}