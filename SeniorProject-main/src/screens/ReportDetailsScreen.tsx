import React, { useState } from 'react';
import {
  Alert,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  Image,
  type DimensionValue,
} from 'react-native';
import { translateText, updateReportStatus } from '../services/reportService';
import { ReportListItem, ReportStatus } from '../types/Report';

type Props = {
  onBack: () => void;
  onOpenMap: (report: ReportListItem) => void;
  onStatusUpdated: (status: ReportStatus) => void;
  report: ReportListItem | null;
};

const statusText = {
  pending: 'جديد',
  'in-progress': 'جاري التنفيذ',
  resolved: 'منتهي',
  failed: 'تعذر التنفيذ',
};

const statusProgress: Record<ReportStatus, DimensionValue> = {
  pending: '25%',
  'in-progress': '65%',
  resolved: '100%',
  failed: '100%',
};

const statusIcon = {
  pending: '!',
  'in-progress': '…',
  resolved: '✓',
  failed: '×',
};

const formatReportTime = (createdAt?: string) => {
  if (!createdAt) {
    return 'غير متوفر';
  }

  return new Date(createdAt).toLocaleString('ar-SA');
};

export default function ReportDetailsScreen({
  onBack,
  onOpenMap,
  onStatusUpdated,
  report,
}: Props) {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [translatedDescription, setTranslatedDescription] = useState('');
  const [isTranslating, setIsTranslating] = useState(false);

  const handleUpdateStatus = async (status: ReportStatus) => {
    if (!report) {
      return;
    }

    try {
      setIsSubmitting(true);
      await updateReportStatus(report.id, status);
      onStatusUpdated(status);
    } catch (error) {
      console.log('UPDATE REPORT STATUS ERROR:', error);
      Alert.alert('خطأ', 'تعذر تحديث حالة البلاغ');
    } finally {
      setIsSubmitting(false);
    }
  };

  const openReportMap = () => {
    if (!report) {
      return;
    }

    const { lat, lng } = report?.location || {};

    if (typeof lat !== 'number' || typeof lng !== 'number') {
      Alert.alert('تنبيه', 'موقع البلاغ غير متوفر');
      return;
    }

    onOpenMap(report);
  };

  const handleTranslate = async () => {
    if (!report?.title) {
      return;
    }

    try {
      setIsTranslating(true);
      const translated = await translateText(report.title, 'ar');
      setTranslatedDescription(translated);
    } catch (error) {
      console.log('TRANSLATE ERROR:', error);
      Alert.alert('خطأ', 'تعذرت ترجمة وصف البلاغ');
    } finally {
      setIsTranslating(false);
    }
  };

  if (!report) {
    return (
      <View style={styles.container}>
        <View style={styles.emptyContainer}>
          <Text style={styles.emptyText}>لم يتم اختيار بلاغ</Text>
          <TouchableOpacity onPress={onBack} style={styles.finishButton}>
            <Text style={styles.finishButtonText}>رجوع</Text>
          </TouchableOpacity>
        </View>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <ScrollView
        contentContainerStyle={styles.scrollContent}
        showsVerticalScrollIndicator={false}
      >
        <View style={styles.headerContainer}>
          <TouchableOpacity onPress={onBack} style={styles.backButton}>
            <Text style={styles.backArrow}>‹</Text>
          </TouchableOpacity>

          <Text style={styles.headerTitle}>تفاصيل البلاغ</Text>

          <View style={styles.headerSpacer} />
        </View>

        <View style={styles.statusCard}>
          <View style={styles.statusTopRow}>
            <View style={styles.checkCircle}>
              <Text style={styles.checkIcon}>{statusIcon[report.status]}</Text>
            </View>

            <Text style={styles.statusText}>{statusText[report.status]}</Text>
          </View>

          <View style={styles.progressBarBackground}>
            <View
              style={[
                styles.progressBarFill,
                { width: statusProgress[report.status] },
              ]}
            />
          </View>

          <Text style={styles.durationText}>
            وقت البلاغ : {formatReportTime(report.createdAt)}
          </Text>
        </View>

        <View style={styles.infoCard}>
          <View style={styles.infoRow}>
            <Text style={styles.infoIcon}>📍</Text>
            <Text style={styles.infoValue}>
              الموقع : {report.neighborhood}
              {report.screenNumber ? ` . شاشة ${report.screenNumber}` : ''}
            </Text>
          </View>

          <View style={styles.divider} />

          <View style={styles.infoRow}>
            <Text style={styles.infoIcon}>🕒</Text>
            <Text style={styles.infoValue}>
              آخر تحديث : {formatReportTime(report.updatedAt)}
            </Text>
          </View>

          <View style={styles.divider} />

          <View style={styles.infoRow}>
            <Text style={styles.infoIcon}>❔</Text>
            <Text style={styles.infoValue}>النوع : {report.typeLabel}</Text>
          </View>

          <View style={styles.divider} />

          <View style={styles.infoRow}>
            <Text style={styles.infoIcon}>✈</Text>
            <Text style={styles.infoValue}>
              الحالة : {statusText[report.status]}
            </Text>
          </View>

          {report.assignedVolunteerName && (
            <>
              <View style={styles.divider} />

              <View style={styles.infoRow}>
                <Text style={styles.infoIcon}>✓</Text>
                <Text style={styles.infoValue}>
                  المتطوع : {report.assignedVolunteerName}
                </Text>
              </View>
            </>
          )}
        </View>

        <View style={styles.hajjCard}>
          <Text style={styles.hajjCardTitle}>بيانات الحاج</Text>

          <View style={styles.hajjRow}>
            <Text style={styles.hajjLabel}>الاسم</Text>
            <Text style={styles.hajjValue}>
              {report.hajjName || 'غير متوفر'}
            </Text>
          </View>

          <View style={styles.hajjRow}>
            <Text style={styles.hajjLabel}>الجوال</Text>
            <Text style={styles.hajjValue}>
              {report.hajjPhone || 'غير متوفر'}
            </Text>
          </View>

          <View style={styles.hajjRow}>
            <Text style={styles.hajjLabel}>اللغة</Text>
            <Text style={styles.hajjValue}>
              {report.preferredLanguage || 'غير متوفر'}
            </Text>
          </View>
        </View>

        <View style={styles.descriptionCard}>
          <Text style={styles.descriptionTitle}>وصف البلاغ :</Text>

          <Text style={styles.descriptionText}>{report.title}</Text>

          {translatedDescription ? (
            <Text style={styles.translatedText}>{translatedDescription}</Text>
          ) : null}

          <TouchableOpacity
            disabled={isTranslating}
            style={[
              styles.translateButton,
              isTranslating && styles.disabledButton,
            ]}
            onPress={handleTranslate}
          >
            <Text style={styles.translateButtonText}>
              {isTranslating ? 'جاري الترجمة...' : 'ترجمة الوصف'}
            </Text>
          </TouchableOpacity>

          <View style={styles.mapCard}>
            <Image
              source={require('../assets/location_icon.png')}
              style={styles.mapImage}
              resizeMode="contain"
            />

            <TouchableOpacity
              style={styles.mapOverlay}
              activeOpacity={0.8}
              onPress={openReportMap}
            >
              <Text style={styles.mapButtonText}>عرض على خريطة سند</Text>
            </TouchableOpacity>
          </View>
        </View>

        <TouchableOpacity
          disabled={isSubmitting}
          style={[styles.finishButton, isSubmitting && styles.disabledButton]}
          onPress={() => handleUpdateStatus('resolved')}
        >
          <Text style={styles.finishButtonText}>انهاء البلاغ</Text>
        </TouchableOpacity>

        <TouchableOpacity
          disabled={isSubmitting}
          style={[styles.failButton, isSubmitting && styles.disabledButton]}
          onPress={() => handleUpdateStatus('failed')}
        >
          <Text style={styles.failButtonText}>تعذر التنفيذ</Text>
        </TouchableOpacity>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f3f3f3',
  },

  emptyContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 24,
  },

  emptyText: {
    fontSize: 18,
    color: '#777',
    fontWeight: '800',
    marginBottom: 16,
  },

  scrollContent: {
    paddingTop: 26,
    paddingHorizontal: 16,
    paddingBottom: 30,
  },

  headerContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 22,
  },

  backButton: {
    width: 40,
    height: 40,
    alignItems: 'center',
    justifyContent: 'center',
  },

  backArrow: {
    fontSize: 42,
    color: '#e89a55',
    lineHeight: 42,
    fontWeight: '300',
  },

  headerTitle: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
    textAlign: 'center',
  },

  headerSpacer: {
    width: 40,
  },

  statusCard: {
    backgroundColor: '#dff0e6',
    borderRadius: 18,
    padding: 14,
    marginBottom: 14,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 5,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  statusTopRow: {
    flexDirection: 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 12,
  },

  checkCircle: {
    width: 30,
    height: 30,
    borderRadius: 15,
    backgroundColor: '#23b574',
    alignItems: 'center',
    justifyContent: 'center',
  },

  checkIcon: {
    color: '#fff',
    fontSize: 18,
    fontWeight: '800',
  },

  statusText: {
    fontSize: 24,
    fontWeight: '800',
    color: '#2c8a57',
    textAlign: 'right',
  },

  progressBarBackground: {
    height: 20,
    borderRadius: 6,
    backgroundColor: '#fff',
    overflow: 'hidden',
    marginBottom: 10,
  },

  progressBarFill: {
    height: '100%',
    backgroundColor: '#1fb77a',
    borderRadius: 6,
  },

  durationText: {
    fontSize: 14,
    color: '#333',
    textAlign: 'center',
  },

  infoCard: {
    backgroundColor: '#fff',
    borderRadius: 18,
    paddingHorizontal: 16,
    paddingVertical: 8,
    marginBottom: 14,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 5,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  infoRow: {
    flexDirection: 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 14,
  },

  infoIcon: {
    fontSize: 22,
    width: 28,
    textAlign: 'center',
  },

  infoValue: {
    flex: 1,
    fontSize: 16,
    fontWeight: '700',
    color: '#111',
    textAlign: 'right',
    marginLeft: 10,
  },

  divider: {
    height: 1,
    backgroundColor: '#e5e5e5',
  },

  descriptionCard: {
    backgroundColor: '#fff',
    borderRadius: 18,
    padding: 16,
    marginBottom: 18,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 5,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  hajjCard: {
    backgroundColor: '#fff',
    borderRadius: 18,
    padding: 16,
    marginBottom: 14,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 5,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  hajjCardTitle: {
    fontSize: 19,
    fontWeight: '900',
    color: '#0b5733',
    textAlign: 'right',
    marginBottom: 12,
  },

  hajjRow: {
    flexDirection: 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 8,
  },

  hajjLabel: {
    width: 72,
    fontSize: 15,
    fontWeight: '800',
    color: '#666',
    textAlign: 'right',
  },

  hajjValue: {
    flex: 1,
    fontSize: 16,
    fontWeight: '800',
    color: '#111',
    textAlign: 'right',
  },

  descriptionTitle: {
    fontSize: 18,
    fontWeight: '800',
    color: '#111',
    textAlign: 'left',
    marginBottom: 8,
  },

  descriptionText: {
    fontSize: 16,
    color: '#111',
    textAlign: 'left',
    lineHeight: 26,
    marginBottom: 14,
  },

  translatedText: {
    fontSize: 16,
    color: '#2c8a57',
    textAlign: 'left',
    lineHeight: 26,
    marginBottom: 12,
    fontWeight: '700',
  },

  translateButton: {
    backgroundColor: '#efefef',
    height: 42,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 14,
  },

  translateButtonText: {
    color: '#111',
    fontSize: 16,
    fontWeight: '800',
  },

  mapCard: {
    borderRadius: 14,
    overflow: 'hidden',
    backgroundColor: '#f1f1f1',
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 10,
  },

  mapImage: {
    width: '100%',
    height: 120,
    backgroundColor: '#f1f1f1',
    alignSelf: 'center',
  },

  mapOverlay: {
    height: 46,
    backgroundColor: '#efefef',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    borderTopWidth: 1,
    borderTopColor: '#d6d6d6',
  },

  mapButtonText: {
    fontSize: 18,
    fontWeight: '700',
    color: '#111',
    textDecorationLine: 'underline',
  },

  finishButton: {
    backgroundColor: '#1fb77a',
    height: 54,
    borderRadius: 14,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 12,
  },

  finishButtonText: {
    color: '#fff',
    fontSize: 22,
    fontWeight: '800',
  },

  failButton: {
    backgroundColor: '#e9e9e9',
    height: 54,
    borderRadius: 14,
    alignItems: 'center',
    justifyContent: 'center',
  },

  disabledButton: {
    opacity: 0.6,
  },

  failButtonText: {
    color: '#111',
    fontSize: 22,
    fontWeight: '800',
  },
});
