import { API_BASE_URL } from "../app-config";

export function call(api, method, request) {
  let headers = new Headers({
    "Content-Type": "application/json"
  }); 

  const accessToken = localStorage.getItem("ACCESS_TOKEN");
  if(accessToken && accessToken !== null) {
    headers.append("Authorization", "Bearer "+accessToken);
  }

  const options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method
  };

  if(request) {
    options.body = JSON.stringify(request);
  };

  return fetch(options.url, options)
    .then((response) =>
      response.json().then((json) => {
        if(!response.ok) {
          return Promise.reject(json);
        }
        return json;
      })
    )
    .catch((error) => {
      console.log(error)
      if(error.status === 403) {
        window.location.href = "/login"; // redirect
      }
      return Promise.reject(error);
    });

}

export function signin(userDTO) {
  return call("/auth/signin", "POST", userDTO)
    .then((response) => {
      if(response.token) {
        localStorage.setItem("ACCESS_TOKEN", response.token);
        window.location.href = "/today";
      }
    })
    .catch((error) => {
      if(error.error === "Login failed") {
        alert("이메일 또는 패스워드를 다시 확인해주세요.")
      }
    });
}

export function signout() {
  localStorage.setItem("ACCESS_TOKEN", null);
  window.location.href = "/login";
}

export function signup(userDTO) {
  return call("/auth/signup", "POST", userDTO);
}

export function callForUpload(api, file) {

  if(!file) {
    const msg = "이미지를 업로드해주세요.";
    return Promise.reject(msg);
  }

  let headers = new Headers({
  }); 

  const accessToken = localStorage.getItem("ACCESS_TOKEN");
  if(accessToken && accessToken !== null) {
    headers.append("Authorization", "Bearer "+accessToken);
  }

  let formData = new FormData();
  formData.append("uploadImage", file, file.name);

  const options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: "POST",
    body: formData
  };

  return fetch(options.url, options)
    .then((response) =>
      response.json().then((json) => {
        if(!response.ok) {
          return Promise.reject(json);
        }
        return json;
      })
    )
    .catch((error) => {
      console.log(error)
      if(error.status === 403) {
        window.location.href = "/login"; // redirect
      }
      return Promise.reject(error);
    });

}
