import React, { useEffect, useState } from 'react';
import { ActivityIndicator, Alert, StyleSheet, View } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import WelcomeScreen from './src/screens/WelcomeScreen';
import LoginScreen from './src/screens/LoginScreen';
import RegisterScreen from './src/screens/RegisterScreen';
import HomeScreen from './src/screens/HomeScreen';
import ProfileScreen from './src/screens/ProfileScreen';
import AllReportsScreen from './src/screens/AllReportsScreen';
import ReportDetailsScreen from './src/screens/ReportDetailsScreen';
import VolunteerMapScreen from './src/screens/VolunteerMapScreen';
import { updateReportStatus } from './src/services/reportService';
import { ReportListItem, ReportStatus } from './src/types/Report';

export default function App() {
  const [currentScreen, setCurrentScreen] = useState<
    | 'welcome'
    | 'login'
    | 'register'
    | 'home'
    | 'profile'
    | 'map'
    | 'allReports'
    | 'reportDetails'
  >('welcome');
  const [selectedReport, setSelectedReport] = useState<ReportListItem | null>(
    null,
  );
  const [mapFocusReport, setMapFocusReport] = useState<ReportListItem | null>(
    null,
  );
  const [isRestoringSession, setIsRestoringSession] = useState(true);

  useEffect(() => {
    const restoreSession = async () => {
      try {
        const [token, storedUser] = await Promise.all([
          AsyncStorage.getItem('token'),
          AsyncStorage.getItem('user'),
        ]);

        if (token && storedUser) {
          setCurrentScreen('home');
        }
      } catch (error) {
        console.log('RESTORE SESSION ERROR:', error);
      } finally {
        setIsRestoringSession(false);
      }
    };

    restoreSession();
  }, []);

  const handleAcceptReport = async (report: ReportListItem) => {
    try {
      const storedUser = await AsyncStorage.getItem('user');
      const user = storedUser ? JSON.parse(storedUser) : null;

      if (report.status === 'pending') {
        await updateReportStatus(report.id, 'in-progress', {
          assignedVolunteerId: user?.id,
          assignedVolunteerName: user?.fullName,
        });
      }

      setSelectedReport({
        ...report,
        status: 'in-progress',
        assignedVolunteerId: user?.id,
        assignedVolunteerName: user?.fullName,
      });
      setCurrentScreen('reportDetails');
    } catch (error) {
      console.log('ACCEPT REPORT ERROR:', error);
      Alert.alert('خطأ', 'تعذر قبول البلاغ');
    }
  };

  const handleReportStatusUpdated = (status: ReportStatus) => {
    if (selectedReport) {
      setSelectedReport({ ...selectedReport, status });
    }

    setCurrentScreen('home');
  };

  const openMap = (report?: ReportListItem | null) => {
    setMapFocusReport(report || null);
    setCurrentScreen('map');
  };

  if (isRestoringSession) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#1fb77a" />
      </View>
    );
  }

  if (currentScreen === 'login') {
    return (
      <LoginScreen
        onBack={() => setCurrentScreen('welcome')}
        onLoginSuccess={() => setCurrentScreen('home')}
      />
    );
  }

  if (currentScreen === 'register') {
    return (
      <RegisterScreen
        onBack={() => setCurrentScreen('welcome')}
        onRegisterSuccess={() => setCurrentScreen('login')}
      />
    );
  }

  if (currentScreen === 'profile') {
    return (
      <ProfileScreen
        onHomePress={() => setCurrentScreen('home')}
        onMapPress={() => openMap()}
        onProfilePress={() => setCurrentScreen('profile')}
        onLogout={() => setCurrentScreen('welcome')}
      />
    );
  }

  if (currentScreen === 'map') {
    return (
      <VolunteerMapScreen
        focusedReport={mapFocusReport}
        onBack={() => setCurrentScreen('home')}
      />
    );
  }

  if (currentScreen === 'allReports') {
    return (
      <AllReportsScreen
        onBack={() => setCurrentScreen('home')}
        onAcceptReportPress={handleAcceptReport}
      />
    );
  }
  if (currentScreen === 'reportDetails') {
    return (
      <ReportDetailsScreen
        onBack={() => setCurrentScreen('home')}
        onOpenMap={openMap}
        onStatusUpdated={handleReportStatusUpdated}
        report={selectedReport}
      />
    );
  }

  if (currentScreen === 'home') {
    return (
      <HomeScreen
        onHomePress={() => setCurrentScreen('home')}
        onMapPress={() => openMap()}
        onProfilePress={() => setCurrentScreen('profile')}
        onAllReportsPress={() => setCurrentScreen('allReports')}
        onAcceptReportPress={handleAcceptReport}
      />
    );
  }

  return (
    <WelcomeScreen
      onLoginPress={() => setCurrentScreen('login')}
      onRegisterPress={() => setCurrentScreen('register')}
    />
  );
}

const styles = StyleSheet.create({
  loadingContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
