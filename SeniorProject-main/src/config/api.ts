export const API_BASE_URL = 'http://10.0.2.2:3000/api';
export const WEB_BASE_URL = API_BASE_URL.replace(/\/api$/, '');
export const WEB_MAP_URL = `${WEB_BASE_URL}/map.html?app=1`;

export const API_ENDPOINTS = {
  auth: `${API_BASE_URL}/auth`,
  kiosks: `${API_BASE_URL}/kiosks`,
  reports: `${API_BASE_URL}/reports`,
  translate: `${API_BASE_URL}/translate`,
};
