import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Volunteer } from '../types/Volunteer';
import { API_ENDPOINTS } from '../config/api';

const API_URL = API_ENDPOINTS.auth;

const getAuthHeaders = async () => {
  const token = await AsyncStorage.getItem('token');

  return {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
};

export interface LoginResponse {
  message: string;
  token: string;
  volunteer: Volunteer;
}

export interface RegisterData {
  fullName: string;
  nationalId: string;
  password: string;
  phone: string;
}

export const loginVolunteer = async (
  nationalId: string,
  password: string
): Promise<LoginResponse> => {
  const response = await axios.post(`${API_URL}/login`, {
    nationalId,
    password,
  });

  return response.data;
};

export const registerVolunteer = async (
  data: RegisterData
): Promise<LoginResponse> => {
  const response = await axios.post(`${API_URL}/register`, data);
  return response.data;
};

export interface UpdateProfileData {
  fullName: string;
  phone: string;
  birthDate?: string;
  bloodType?: string;
}

export const updateVolunteerProfile = async (data: UpdateProfileData) => {
  const config = await getAuthHeaders();
  const response = await axios.put(`${API_URL}/profile`, data, config);
  return response.data;
};

export const getMyProfile = async () => {
  const config = await getAuthHeaders();
  const response = await axios.get(`${API_URL}/me`, config);
  return response.data;
};
