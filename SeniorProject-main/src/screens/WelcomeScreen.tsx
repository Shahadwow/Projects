import React from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity } from 'react-native';

type Props = {
  onLoginPress: () => void;
  onRegisterPress: () => void;
};

export default function WelcomeScreen({ onLoginPress, onRegisterPress }: Props) {
  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/SANAD_APPlogo.png')}
        style={styles.logo}
        resizeMode="contain"
      />

      <TouchableOpacity style={styles.loginButton} onPress={onLoginPress}>
        <Text style={styles.loginText}>تسجيل الدخول</Text>
      </TouchableOpacity>
      
      <TouchableOpacity onPress={onRegisterPress}>
       <Text style={{ textAlign: 'center', marginTop: 16, color: '#007AFF' }}>
        إنشاء حساب
       </Text>
      </TouchableOpacity>

      <Text style={styles.footerText}>
        قال ﷺ: (مثل المؤمنين في توادهم وتراحمهم وتعاطفهم مثل الجسد إذا اشتكى منه عضو
        تداعى له سائر الجسد بالسهر والحمى)
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f2f2f2',
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  logo: {
    width: 350,
    height: 350,
    marginBottom: 40,
  },
  loginButton: {
    width: '100%',
    backgroundColor: '#1fa971',
    padding: 16,
    borderRadius: 14,
    alignItems: 'center',
    marginBottom: 40,
  },
  loginText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
  footerText: {
    fontSize: 15,
    textAlign: 'center',
    color: '#666',
    lineHeight: 22,
    paddingHorizontal: 10,
  },
});