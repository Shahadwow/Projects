export type ReportStatus = 'pending' | 'in-progress' | 'resolved' | 'failed';

export interface ApiReport {
  _id: string;
  type: string;
  description: string;
  status: ReportStatus;
  screenNumber?: string;
  screen_number?: number;
  language?: string;
  full_name?: string;
  phone?: string;
  preferred_language?: string;
  assignedVolunteerId?: string;
  assignedVolunteerName?: string;
  location?: {
    lat?: number;
    lng?: number;
  };
  createdAt?: string;
  updatedAt?: string;
}

export interface ReportListItem {
  id: string;
  type: string;
  neighborhood: string;
  screenNumber?: string;
  location?: {
    lat?: number;
    lng?: number;
  };
  hajjName?: string;
  hajjPhone?: string;
  preferredLanguage?: string;
  assignedVolunteerId?: string;
  assignedVolunteerName?: string;
  distanceText: string;
  typeLabel: string;
  title: string;
  icon: string;
  iconBg: string;
  iconColor: string;
  sideColor: string;
  status: ReportStatus;
  createdAt?: string;
  updatedAt?: string;
}
