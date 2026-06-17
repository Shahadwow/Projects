import React, { useState } from 'react';
import { registerVolunteer } from '../services/authService';
import {
  View,
  Text,
  StyleSheet,
  Image,
 TouchableOpacity,
  TextInput,
  Alert,
  ScrollView,
} from 'react-native';

type Props = {
  onBack: () => void;
  onRegisterSuccess: () => void;
};

export default function RegisterScreen({ onBack, onRegisterSuccess }: Props) {
  const [fullName, setFullName] = useState('');
  const [nationalId, setNationalId] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = async () => {

    if (!/^05\d{8}$/.test(phone)) {
        Alert.alert('خطأ', 'رقم الجوال يجب أن يبدأ بـ 05 ويتكون من 10 أرقام');
        return;
      }
    if (!fullName || !nationalId || !phone || !password) {
      Alert.alert('خطأ', 'أكمل جميع الحقول');
      return;
    }
    try {
      const data = await registerVolunteer({
        fullName,
        nationalId,
        phone,
        password,
      });

      Alert.alert('نجاح', 'تم إنشاء الحساب', [
        {
          text: 'OK',
          onPress: onRegisterSuccess,
        },
      ]);
    } catch (error: any) {
      console.log('REGISTER ERROR:', error?.response?.data || error.message);

      Alert.alert(
        'فشل التسجيل',
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
        <ScrollView
          showsVerticalScrollIndicator={false}
          contentContainerStyle={styles.scrollContent}
        >
          <View style={styles.headerContainer}>
            <TouchableOpacity onPress={onBack} style={styles.backButton}>
              <Text style={styles.backArrow}>{'<'}</Text>
            </TouchableOpacity>

            <Text style={styles.title}>إنشاء حساب</Text>
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>الاسم الكامل</Text>
            <TextInput
              style={styles.input}
              placeholder="اكتبي الاسم الكامل"
              placeholderTextColor="#b8b8b8"
              value={fullName}
              onChangeText={setFullName}
              textAlign="right"
            />
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
            <Text style={styles.label}>رقم الجوال</Text>
            <TextInput
              style={styles.input}
              placeholder="05XXXXXXXX"
              placeholderTextColor="#b8b8b8"
              value={phone}
              onChangeText={setPhone}
              maxLength={10}
              keyboardType="phone-pad"
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

          <TouchableOpacity style={styles.registerButton} onPress={handleRegister}>
            <Text style={styles.registerButtonText}>إنشاء الحساب</Text>
          </TouchableOpacity>
        </ScrollView>
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

  scrollContent: {
    paddingBottom: 30,
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

  registerButton: {
    backgroundColor: '#20b77a',
    height: 70,
    borderRadius: 18,
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 12,
  },

  registerButtonText: {
    color: '#fff',
    fontSize: 22,
    fontWeight: '800',
  },
});