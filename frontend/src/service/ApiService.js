import { API_BASE_URL } from "../api-config";

export function call(api, method, request, headers = {}, url = API_BASE_URL){

    let options ={
        headers: new Headers({
            "Content-Type": "application/json",
            ...headers
        }),
        url: url + api,
        method: method,
    };
    // HTTP GET 요청에서는 request 객체를 query string으로 변환하여 전송합니다.
    if(request && method === "GET") {
        const queryString = Object.entries(request)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");
        options.url += `?${queryString}`;
    } else if (request) {
        // HTTP GET 요청이 아닐 경우 request 객체를 body에 담아 전송합니다.
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options).then((response) => {
        if(response.status === 200){
            return response.json();
        }
    }).catch((error) => {
        console.log("http error");
        console.log(error);
    });
}