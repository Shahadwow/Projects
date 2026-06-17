import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { WebView } from 'react-native-webview';
import { WEB_MAP_URL } from '../config/api';
import { ReportListItem } from '../types/Report';

type Props = {
  focusedReport?: ReportListItem | null;
  onBack: () => void;
};

const getMapUrl = (focusedReport?: ReportListItem | null) => {
  const report = focusedReport || null;
  const { lat, lng } = report?.location || {};

  if (!report || typeof lat !== 'number' || typeof lng !== 'number') {
    return WEB_MAP_URL;
  }

  const params = new URLSearchParams({
    lat: String(lat),
    lng: String(lng),
    reportId: report.id,
  });

  return `${WEB_MAP_URL}&${params.toString()}`;
};

export default function VolunteerMapScreen({ focusedReport, onBack }: Props) {
  return (
    <View style={styles.container}>
      <WebView
        source={{ uri: getMapUrl(focusedReport) }}
        style={styles.webView}
        javaScriptEnabled
        domStorageEnabled
        geolocationEnabled
        originWhitelist={['*']}
        startInLoadingState
      />

      <TouchableOpacity style={styles.backButton} onPress={onBack}>
        <Text style={styles.backText}>رجوع</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },

  webView: {
    flex: 1,
  },

  backButton: {
    position: 'absolute',
    top: 50,
    left: 20,
    zIndex: 10,
    backgroundColor: '#fff',
    paddingVertical: 8,
    paddingHorizontal: 14,
    borderRadius: 10,
    elevation: 5,
    shadowColor: '#000',
    shadowOpacity: 0.12,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 3 },
  },

  backText: {
    color: '#111',
    fontSize: 16,
    fontWeight: '700',
  },
});
