import React, { useState } from 'react';
import { loginVolunteer } from '../services/authService';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {
  View,
  Text,
  StyleSheet,
  Image,
  TouchableOpacity,
  TextInput,
  Alert,
} from 'react-native';

type Props = {
  onBack: () => void;
  onLoginSuccess: () => void;
};

export default function LoginScreen({ onBack, onLoginSuccess }: Props) {
  const [nationalId, setNationalId] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    if (!nationalId || !password) {
      Alert.alert('خطأ', 'أدخل رقم الهوية وكلمة المرور');
      return;
    }

    try {
      const data = await loginVolunteer(nationalId, password);

      await AsyncStorage.setItem('token', data.token);
      await AsyncStorage.setItem('user', JSON.stringify(data.volunteer));

      Alert.alert('نجاح', 'تم تسجيل الدخول');
      onLoginSuccess();
    } catch (error: any) {
      console.log('LOGIN ERROR:', error?.response?.data || error.message);

      Alert.alert(
        'فشل تسجيل الدخول',
        error?.response?.data?.message || 'حدث خطأ'
      );
    }
  };

  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/SANAD_APPlogo.png')}
        style={styles.logo}
        resizeMode="contain"
      />

      <View style={styles.card}>
        <View style={styles.headerContainer}>
          <TouchableOpacity onPress={onBack} style={styles.backButton}>
            <Text style={styles.backArrow}>{'<'}</Text>
          </TouchableOpacity>

          <Text style={styles.title}>مرحبا بعودتك</Text>
        </View>

        <View style={styles.fieldGroup}>
          <Text style={styles.label}>رقم الهوية</Text>
          <TextInput
            style={styles.input}
            placeholder="XXXXXXXXXX"
            placeholderTextColor="#b8b8b8"
            value={nationalId}
            onChangeText={setNationalId}
            keyboardType="number-pad"
            maxLength={10}
            textAlign="left"
          />
        </View>

        <View style={styles.fieldGroup}>
          <Text style={styles.label}>كلمة المرور</Text>
          <TextInput
            style={styles.input}
            placeholder="••••••••"
            placeholderTextColor="#b8b8b8"
            value={password}
            onChangeText={setPassword}
            secureTextEntry
            textAlign="left"
          />
        </View>

        <TouchableOpacity style={styles.loginButton} onPress={handleLogin}>
          <Text style={styles.loginButtonText}>تسجيل الدخول</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#efefef',
    alignItems: 'center',
    paddingTop: 55,
  },

  logo: {
    width: 260,
    height: 230,
    marginBottom: 18,
  },

  card: {
    flex: 1,
    width: '100%',
    backgroundColor: '#fff',
    borderTopLeftRadius: 32,
    borderTopRightRadius: 32,
    paddingTop: 34,
    paddingHorizontal: 24,
    marginTop: 8,
  },

  headerContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 30,
    position: 'relative',
  },

  title: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
    textAlign: 'center',
    flex: 1,
    marginRight: 44,
  },

  backButton: {
    width: 44,
    height: 44,
    alignItems: 'center',
    justifyContent: 'center',
    marginLeft: -8,
  },

  backArrow: {
    fontSize: 36,
    color: '#f29a2e',
    fontWeight: '400',
  },

  fieldGroup: {
    marginBottom: 24,
  },

  label: {
    width: '100%',
    fontSize: 16,
    color: '#666',
    marginBottom: 10,
    textAlign: 'left',
  },

  input: {
    height: 68,
    borderWidth: 2,
    borderColor: '#cfcfcf',
    borderRadius: 18,
    paddingHorizontal: 20,
    backgroundColor: '#fff',
    fontSize: 18,
    color: '#222',
    textAlign: 'right',
  },

  loginButton: {
    backgroundColor: '#20b77a',
    height: 70,
    borderRadius: 18,
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 12,
  },

  loginButtonText: {
    color: '#fff',
    fontSize: 22,
    fontWeight: '800',
  },
});