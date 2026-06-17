import React, { useEffect, useState } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {
  Alert,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  Image,
} from 'react-native';
import { getReports, translateText } from '../services/reportService';
import { ReportListItem } from '../types/Report';
import { mapApiReportToListItem } from '../utils/reportPresentation';

type Props = {
  onHomePress: () => void;
  onMapPress: () => void;
  onProfilePress: () => void;
  onAllReportsPress: () => void;
  onAcceptReportPress: (report: ReportListItem) => void;
};

type StoredUser = {
  id: string;
  fullName: string;
  nationalId: string;
  phone: string;
  role: string;
  status: 'active' | 'pending' | 'blocked';
};

export default function HomeScreen({
  onHomePress,
  onMapPress,
  onProfilePress,
  onAllReportsPress,
  onAcceptReportPress,
}: Props) {
  const [user, setUser] = useState<StoredUser | null>(null);
  const [liveReports, setLiveReports] = useState<ReportListItem[]>([]);
  const [translatedReports, setTranslatedReports] = useState<
    Record<string, string>
  >({});

  useEffect(() => {
    const loadUser = async () => {
      try {
        const storedUser = await AsyncStorage.getItem('user');

        if (storedUser) {
          setUser(JSON.parse(storedUser));
        }
      } catch (error) {
        console.log('LOAD USER ERROR:', error);
      }
    };

    loadUser();

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

  const activeReports = liveReports.filter(
    report => report.status !== 'resolved' && report.status !== 'failed',
  );
  const resolvedCount = liveReports.filter(
    report => report.status === 'resolved',
  ).length;
  const inProgressCount = liveReports.filter(
    report => report.status === 'in-progress',
  ).length;
  const pendingCount = liveReports.filter(
    report => report.status === 'pending',
  ).length;

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
        <Text style={styles.headerTitle}>الرئيسية</Text>

        <View style={styles.welcomeCard}>
          <Text style={styles.welcomeText}>أهلاً بك</Text>
          <Text style={styles.userNameText}>
            {user?.fullName || 'جارٍ تحميل البيانات...'}
          </Text>
          <Text style={styles.userSubText}>
            رقم الهوية: {user?.nationalId || '---'}
          </Text>
        </View>

        <View style={styles.statsCard}>
          <Text style={styles.totalNumber}>{liveReports.length}</Text>
          <Text style={styles.totalLabel}>إجمالي البلاغات</Text>

          <View style={styles.statsRow}>
            <View style={styles.statItem}>
              <Text style={styles.statNumber}>{resolvedCount}</Text>
              <Text style={styles.statLabel}>البلاغات{'\n'}المغلقة</Text>
            </View>

            <View style={styles.statDivider} />

            <View style={styles.statItem}>
              <Text style={styles.statNumber}>{inProgressCount}</Text>
              <Text style={styles.statLabel}>البلاغات قيد{'\n'}المعالجة</Text>
            </View>

            <View style={styles.statDivider} />

            <View style={styles.statItem}>
              <Text style={styles.statNumber}>{pendingCount}</Text>
              <Text style={styles.statLabel}>البلاغات{'\n'}الجديدة</Text>
            </View>
          </View>
        </View>

        <View style={styles.sectionHeader}>
          <Text style={styles.sectionTitle}>البلاغات</Text>
          <TouchableOpacity activeOpacity={0.7} onPress={onAllReportsPress}>
            <Text style={styles.moreText}>عرض الكل</Text>
          </TouchableOpacity>
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
                  style={[
                    styles.reportIconBox,
                    { backgroundColor: report.iconBg },
                  ]}
                >
                  <Text
                    style={[styles.reportIcon, { color: report.iconColor }]}
                  >
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
                      : 'موقع البلاغ'}{' '}
                    - {report.neighborhood}
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

      <View style={styles.bottomNav}>
        <TouchableOpacity style={styles.navItem} onPress={onProfilePress}>
          <Image
            source={require('../assets/profile_icon.png')}
            style={styles.navImageInactive}
            resizeMode="contain"
          />
          <Text style={styles.navTextInactive}>حسابي</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.centerNavButton} onPress={onMapPress}>
          <Image
            source={require('../assets/location_icon.png')}
            style={styles.centerNavImage}
            resizeMode="contain"
          />
        </TouchableOpacity>

        <TouchableOpacity style={styles.navItem} onPress={onHomePress}>
          <View style={styles.activeIndicator} />
          <Image
            source={require('../assets/home_icon.png')}
            style={styles.navImageActive}
            resizeMode="contain"
          />
          <Text style={styles.navTextActive}>الرئيسية</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f3f3f3',
  },

  scrollContent: {
    paddingTop: 30,
    paddingHorizontal: 16,
    paddingBottom: 120,
  },

  headerTitle: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
    textAlign: 'center',
    marginBottom: 18,
  },

  statsCard: {
    backgroundColor: '#f4a261',
    borderRadius: 20,
    paddingTop: 20,
    paddingBottom: 18,
    paddingHorizontal: 16,
    marginBottom: 22,
  },

  totalNumber: {
    fontSize: 42,
    fontWeight: '900',
    color: '#fff',
    textAlign: 'center',
  },

  totalLabel: {
    fontSize: 18,
    color: '#fff',
    textAlign: 'center',
    marginTop: 2,
    marginBottom: 18,
    fontWeight: '600',
  },

  statsRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'stretch',
  },

  statItem: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },

  statDivider: {
    width: 1,
    backgroundColor: 'rgba(255,255,255,0.22)',
    marginHorizontal: 6,
  },

  statNumber: {
    fontSize: 22,
    fontWeight: '900',
    color: '#fff',
    marginBottom: 4,
  },

  statLabel: {
    fontSize: 13,
    color: '#fff',
    textAlign: 'center',
    lineHeight: 18,
    fontWeight: '600',
  },

  sectionHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 14,
  },

  sectionTitle: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
  },

  moreText: {
    fontSize: 18,
    color: '#e8893d',
    fontWeight: '700',
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
    borderRadius: 14,
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
    borderRadius: 14,
    padding: 14,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 8,
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
    borderRadius: 12,
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
    height: 30,
  },

  bottomNav: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 92,
    backgroundColor: '#f4a261',
    borderTopLeftRadius: 24,
    borderTopRightRadius: 24,
    flexDirection: 'row-reverse',
    justifyContent: 'space-around',
    alignItems: 'center',
    paddingBottom: 10,
  },

  navItem: {
    alignItems: 'center',
    justifyContent: 'center',
    width: 82,
    position: 'relative',
  },

  activeIndicator: {
    position: 'absolute',
    top: -6,
    width: 26,
    height: 4,
    borderRadius: 3,
    backgroundColor: '#fff',
  },

  navImageActive: {
    width: 40,
    height: 40,
    tintColor: '#fff',
    marginBottom: 4,
  },

  navImageInactive: {
    width: 40,
    height: 40,
    tintColor: 'rgba(255, 255, 255, 0.65)',
    marginBottom: 4,
  },

  navTextActive: {
    fontSize: 16,
    color: '#fff',
    fontWeight: '800',
  },

  navTextInactive: {
    fontSize: 16,
    color: 'rgba(255,255,255,0.75)',
    fontWeight: '700',
  },

  centerNavButton: {
    width: 78,
    height: 78,
    borderRadius: 39,
    backgroundColor: '#fff',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: -36,
    shadowColor: '#000',
    shadowOpacity: 0.14,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 3 },
    elevation: 6,
  },

  centerNavImage: {
    width: 45,
    height: 45,
    tintColor: '#f4a261',
  },
  welcomeCard: {
    backgroundColor: '#fff',
    borderRadius: 18,
    paddingVertical: 18,
    paddingHorizontal: 18,
    marginBottom: 18,
    shadowColor: '#000',
    shadowOpacity: 0.05,
    shadowRadius: 6,
    shadowOffset: { width: 0, height: 2 },
    elevation: 2,
  },

  welcomeText: {
    fontSize: 16,
    color: '#666',
    textAlign: 'right',
    marginBottom: 6,
  },

  userNameText: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
    textAlign: 'right',
    marginBottom: 4,
  },

  userSubText: {
    fontSize: 15,
    color: '#888',
    textAlign: 'right',
  },
});
