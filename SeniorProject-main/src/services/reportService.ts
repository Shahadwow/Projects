import axios from 'axios';
import { API_ENDPOINTS } from '../config/api';
import { ApiReport, ReportStatus } from '../types/Report';

const API_URL = API_ENDPOINTS.reports;

export const getReports = async (): Promise<ApiReport[]> => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const updateReportStatus = async (
  reportId: string,
  status: ReportStatus,
  assignment?: {
    assignedVolunteerId?: string;
    assignedVolunteerName?: string;
  }
): Promise<ApiReport> => {
  const response = await axios.patch(`${API_URL}/${reportId}/status`, {
    status,
    ...assignment,
  });

  return response.data;
};

export const translateText = async (
  text: string,
  target = 'ar'
): Promise<string> => {
  const response = await axios.post(API_ENDPOINTS.translate, {
    text,
    target,
  });

  return response.data.translatedText;
};
