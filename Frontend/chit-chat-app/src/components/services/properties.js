import axios from "axios";

const BASE_URL = "http://localhost:8080";
const AXIOS = axios.create({
    baseURL: BASE_URL,
});

export {BASE_URL, AXIOS};
