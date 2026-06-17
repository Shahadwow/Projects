import React, { useEffect, useState } from 'react';
import {
  Alert,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
} from 'react-native';
import { getReports, translateText } from '../services/reportService';
import { ReportListItem } from '../types/Report';
import { mapApiReportToListItem } from '../utils/reportPresentation';

type Props = {
  onBack: () => void;
  onAcceptReportPress: (report: ReportListItem) => void;
};

export default function AllReportsScreen({ onBack, onAcceptReportPress }: Props) {
  const [liveReports, setLiveReports] = useState<ReportListItem[]>([]);
  const [translatedReports, setTranslatedReports] = useState<Record<string, string>>({});

  useEffect(() => {
    const fetchReports = async () => {
      try {
        const apiReports = await getReports();
        setLiveReports(apiReports.map(mapApiReportToListItem));
      } catch (e) {
        console.log('Error fetching reports:', e);
      }
    };
    
    fetchReports();
    const interval = setInterval(fetchReports, 5000);
    return () => clearInterval(interval);
  }, []);

  const activeReports = liveReports.filter(report => report.status !== 'resolved' && report.status !== 'failed');

  const handleTranslateReport = async (report: ReportListItem) => {
    try {
      const translated = await translateText(report.title, 'ar');
      setTranslatedReports(current => ({
        ...current,
        [report.id]: translated,
      }));
    } catch (error) {
      console.log('TRANSLATE REPORT ERROR:', error);
      Alert.alert('خطأ', 'تعذرت ترجمة وصف البلاغ');
    }
  };

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

  <Text style={styles.headerTitle}>خريطة بلاغات</Text>

  <View style={styles.headerSpacer} />
</View>

        {activeReports.length === 0 && (
          <Text style={styles.emptyText}>لا توجد بلاغات حالياً</Text>
        )}

        {activeReports.map(report => (
          <View
            key={report.id}
            style={[styles.reportCard, { borderRightColor: report.sideColor }]}
          >
            <View style={styles.reportContent}>
              <View style={styles.reportTopRow}>
                <View
                  style={[styles.reportIconBox, { backgroundColor: report.iconBg }]}
                >
                  <Text style={[styles.reportIcon, { color: report.iconColor }]}>
                    {report.icon}
                  </Text>
                </View>

                <View style={styles.reportTextArea}>
                  <View style={styles.reportTitleRow}>
                    <Text style={styles.reportTypeText}>{report.type}</Text>
                    <Text
                      style={[
                        styles.statusPill,
                        { backgroundColor: report.sideColor },
                      ]}
                    >
                      {report.distanceText}
                    </Text>
                  </View>

                  <Text style={styles.reportTitle}>{report.title}</Text>
                  <Text style={styles.metaText}>
                    {report.screenNumber
                      ? `شاشة ${report.screenNumber}`
                      : 'موقع البلاغ'} - {report.neighborhood}
                  </Text>
                  {translatedReports[report.id] ? (
                    <Text style={styles.translatedReportTitle}>
                      {translatedReports[report.id]}
                    </Text>
                  ) : null}
                </View>
              </View>

              <View style={styles.cardActionsRow}>
                <TouchableOpacity
                  style={styles.translateMiniButton}
                  onPress={() => handleTranslateReport(report)}
                >
                  <Text style={styles.translateMiniText}>ترجمة</Text>
                </TouchableOpacity>

                <TouchableOpacity
                  style={styles.acceptButton}
                  onPress={() => onAcceptReportPress(report)}
                >
                  <Text style={styles.acceptButtonText}>قبول البلاغ</Text>
                </TouchableOpacity>
              </View>
            </View>
          </View>
        ))}

        <View style={styles.bottomSpacing} />
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f3f3f3',
  },

  scrollContent: {
    paddingTop: 26,
    paddingHorizontal: 14,
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
    fontSize: 30,
    color: '#e89a55',
    fontWeight: '400',
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

  emptyText: {
    fontSize: 16,
    color: '#777',
    fontWeight: '700',
    textAlign: 'center',
    marginTop: 12,
  },

  reportCard: {
    marginBottom: 16,
    borderRightWidth: 5,
    borderRadius: 18,
  },

  sideBadge: {
    width: 38,
    borderTopLeftRadius: 18,
    borderBottomLeftRadius: 18,
    borderTopRightRadius: 8,
    borderBottomRightRadius: 8,
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 12,
  },

  sideBadgeText: {
    color: '#fff',
    fontSize: 12,
    fontWeight: '800',
    transform: [{ rotate: '90deg' }],
    width: 110,
    textAlign: 'center',
  },

  reportContent: {
    backgroundColor: '#fff',
    borderRadius: 18,
    padding: 14,
    shadowColor: '#000',
    shadowOpacity: 0.06,
    shadowRadius: 6,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  reportTopRow: {
    flexDirection: 'row-reverse',
    alignItems: 'flex-start',
    marginBottom: 12,
  },

  reportIconBox: {
    width: 58,
    height: 58,
    borderRadius: 14,
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 12,
  },

  reportIcon: {
    fontSize: 30,
    fontWeight: '900',
  },

  reportTextArea: {
    flex: 1,
  },

  metaRow: {
    flexDirection: 'row-reverse',
    justifyContent: 'space-between',
    marginBottom: 8,
  },

  metaText: {
    fontSize: 13,
    color: '#666',
    textAlign: 'right',
    lineHeight: 20,
    marginTop: 8,
  },

  reportTitleRow: {
    flexDirection: 'row-reverse',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 8,
  },

  reportTypeText: {
    flex: 1,
    fontSize: 16,
    color: '#111',
    fontWeight: '800',
    textAlign: 'right',
  },

  statusPill: {
    color: '#fff',
    fontSize: 12,
    fontWeight: '800',
    paddingVertical: 4,
    paddingHorizontal: 10,
    borderRadius: 999,
    overflow: 'hidden',
    marginRight: 8,
  },

  reportTitle: {
    fontSize: 20,
    fontWeight: '700',
    color: '#222',
    lineHeight: 28,
    textAlign: 'right',
  },

  translatedReportTitle: {
    fontSize: 16,
    fontWeight: '700',
    color: '#2c8a57',
    lineHeight: 24,
    textAlign: 'right',
    marginTop: 6,
  },

  translateMiniButton: {
    backgroundColor: '#efefef',
    borderRadius: 10,
    paddingVertical: 6,
    paddingHorizontal: 14,
    height: 42,
    justifyContent: 'center',
  },

  translateMiniText: {
    color: '#111',
    fontSize: 13,
    fontWeight: '800',
  },

  acceptButton: {
    backgroundColor: '#1fb77a',
    height: 42,
    borderRadius: 14,
    alignItems: 'center',
    justifyContent: 'center',
    flex: 1,
  },

  acceptButtonText: {
    color: '#fff',
    fontSize: 17,
    fontWeight: '800',
  },

  cardActionsRow: {
    flexDirection: 'row-reverse',
    alignItems: 'center',
    gap: 10,
  },

  bottomSpacing: {
    height: 10,
  },
});
