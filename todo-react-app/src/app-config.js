let backendHost;

const hostname = window && window.location && window.location.hostname;

if(hostname === "localhost") {
  backendHost = "http://localhost:8080";
}

export const API_BASE_URL = `${backendHost}`;

export const API_STAMP_IMG_URL = `${backendHost}/image/stamp/`;