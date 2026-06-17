import { ApiReport, ReportListItem, ReportStatus } from '../types/Report';

const reportStatusText: Record<ReportStatus, string> = {
  pending: 'جديد',
  'in-progress': 'قيد المعالجة',
  resolved: 'مغلق',
  failed: 'متعذر',
};

export const getReportLocationText = (report: ApiReport) => {
  const { lat, lng } = report.location || {};

  if (typeof lat === 'number' && typeof lng === 'number') {
    return `${lat.toFixed(5)}, ${lng.toFixed(5)}`;
  }

  return 'غير متوفر';
};

export const getReportVisual = (type = '') => {
  const normalizedType = type.toLowerCase();

  if (
    normalizedType.includes('medical') ||
    normalizedType.includes('طبي') ||
    normalizedType.includes('حريق')
  ) {
    return {
      icon: '+',
      iconBg: '#fde2e2',
      iconColor: '#d92525',
      sideColor: '#e53935',
      label: 'بلاغ طبي',
    };
  }

  if (
    normalizedType.includes('security') ||
    normalizedType.includes('أمني') ||
    normalizedType.includes('أمن')
  ) {
    return {
      icon: '!',
      iconBg: '#ddf3e9',
      iconColor: '#087a4a',
      sideColor: '#0b8f5a',
      label: 'بلاغ أمني',
    };
  }

  if (
    normalizedType.includes('lost_items') ||
    normalizedType.includes('lost') ||
    normalizedType.includes('مفقودات')
  ) {
    return {
      icon: '?',
      iconBg: '#dcecff',
      iconColor: '#1d64b8',
      sideColor: '#1d75d8',
      label: 'مفقودات',
    };
  }

  return {
    icon: 'i',
    iconBg: '#e6edf3',
    iconColor: '#44515f',
    sideColor: '#607d8b',
    label: 'بلاغ عام',
  };
};

export const mapApiReportToListItem = (report: ApiReport): ReportListItem => {
  const visual = getReportVisual(report.type);

  return {
    id: report._id,
    type: report.type,
    neighborhood: getReportLocationText(report),
    screenNumber: report.screenNumber || report.screen_number?.toString(),
    location: report.location,
    hajjName: report.full_name,
    hajjPhone: report.phone,
    preferredLanguage: report.preferred_language,
    assignedVolunteerId: report.assignedVolunteerId,
    assignedVolunteerName: report.assignedVolunteerName,
    distanceText: reportStatusText[report.status],
    typeLabel: visual.label,
    title: report.description,
    icon: visual.icon,
    iconBg: visual.iconBg,
    iconColor: visual.iconColor,
    sideColor: visual.sideColor,
    status: report.status,
    createdAt: report.createdAt,
    updatedAt: report.updatedAt,
  };
};
