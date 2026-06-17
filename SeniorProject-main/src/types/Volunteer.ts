export interface Volunteer {
    id: string;
    fullName: string;
    nationalId: string;
    phone: string;
    birthDate?: string;
    bloodType?: string;
    role: string;
    status: 'active' | 'pending' | 'blocked';
  }