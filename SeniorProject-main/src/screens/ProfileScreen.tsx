import React, { useEffect, useState } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { updateVolunteerProfile } from '../services/authService';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Image,
  TextInput,
  ScrollView,
  Alert,
} from 'react-native';

type Props = {
  onHomePress: () => void;
  onMapPress: () => void;
  onProfilePress: () => void;
  onLogout: () => void;
};

type StoredUser = {
  id: string;
  fullName: string;
  nationalId: string;
  phone: string;
  birthDate?: string;
  bloodType?: string;
  role: string;
  status: 'active' | 'pending' | 'blocked';
};

export default function ProfileScreen({
  onHomePress,
  onMapPress,
  onProfilePress,
  onLogout,
}: Props) {
  const [name, setName] = useState('');
  const [nationalId, setNationalId] = useState('');
  const [phone, setPhone] = useState('');
  const [birthDate, setBirthDate] = useState('');
  const [bloodType, setBloodType] = useState('');

  useEffect(() => {
    const loadUser = async () => {
      try {
        const storedUser = await AsyncStorage.getItem('user');

        if (storedUser) {
          const parsedUser: StoredUser = JSON.parse(storedUser);
          setName(parsedUser.fullName || '');
          setNationalId(parsedUser.nationalId || '');
          setPhone(parsedUser.phone || '');
          setBirthDate(parsedUser.birthDate || '');
          setBloodType(parsedUser.bloodType || '');
        }
      } catch (error) {
        console.log('LOAD PROFILE USER ERROR:', error);
      }
    };

    loadUser();
  }, []);

  const handleLogout = async () => {
    try {
      await AsyncStorage.removeItem('token');
      await AsyncStorage.removeItem('user');
      onLogout();
    } catch (error) {
      console.log('LOGOUT ERROR:', error);
    }
  };
  const handleSaveChanges = async () => {

    if (!/^05\d{8}$/.test(phone)) {
      Alert.alert('خطأ', 'رقم الجوال يجب أن يبدأ بـ 05 ويتكون من 10 أرقام');
      return;
    }

    try {
      const response = await updateVolunteerProfile({
        fullName: name,
        phone,
        birthDate,
        bloodType,
      });

      await AsyncStorage.setItem('user', JSON.stringify(response.volunteer));

      Alert.alert('نجاح', 'تم حفظ التغييرات');
    } catch (error: any) {
      console.log('SAVE PROFILE ERROR:', error?.response?.data || error.message);
      Alert.alert(
        'خطأ',
        error?.response?.data?.message || 'تعذر حفظ التغييرات'
      );
    }
  };

  return (
    <View style={styles.container}>
      <ScrollView
        contentContainerStyle={styles.scrollContent}
        showsVerticalScrollIndicator={false}
      >
        <View style={styles.header}>
          <Text style={styles.headerTitle}>حسابي</Text>
        </View>

        <View style={styles.summaryCard}>
          <View style={styles.summaryLeft}>
            <Image
              source={require('../assets/SANAD_APPlogo.png')}
              style={styles.summaryLogo}
              resizeMode="contain"
            />
          </View>

          <View style={styles.summaryRight}>
            <View style={styles.nameRow}>
              <Image
                source={require('../assets/profile_icon.png')}
                style={styles.profileTopIcon}
                resizeMode="contain"
              />
              <Text style={styles.userName}>{name || '---'}</Text>
            </View>

            <View style={styles.infoRow}>
              <Text style={styles.infoText}>
                رقم الهوية: {nationalId || '---'}
              </Text>
            </View>

            <View style={styles.infoRow}>
              <Text style={styles.infoText}>
                تاريخ الميلاد: {birthDate || '---'}
              </Text>
            </View>

            <View style={styles.infoRow}>
              <Text style={styles.infoText}>
                فصيلة الدم: {bloodType || '---'}
              </Text>
            </View>
          </View>
        </View>

        <View style={styles.detailsCard}>
          <View style={styles.sectionTitleWrap}>
            <Text style={styles.sectionTitle}>المعلومات الشخصية</Text>
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>الإسم</Text>
            <TextInput
              style={styles.input}
              value={name}
              onChangeText={setName}
              textAlign="right"
            />
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>رقم الهوية</Text>
            <TextInput
              style={styles.input}
              value={nationalId}
              editable={false}
              textAlign="left"
            />
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>رقم الهاتف</Text>
            <TextInput
              style={styles.input}
              value={phone}
              onChangeText={setPhone}
              keyboardType="number-pad"
              maxLength={10}
              textAlign="left"
            />
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>تاريخ الميلاد</Text>
            <TextInput
              style={styles.input}
              value={birthDate}
              onChangeText={setBirthDate}
              placeholder="YYYY-MM-DD"
              textAlign="left"
            />
          </View>

          <View style={styles.fieldGroup}>
            <Text style={styles.label}>فصيلة الدم</Text>
            <TextInput
              style={styles.input}
              value={bloodType}
              onChangeText={setBloodType}
              placeholder="مثال: A+"
              textAlign="left"
            />
          </View>

          <TouchableOpacity style={styles.saveButton} onPress={handleSaveChanges}>
            <Text style={styles.saveButtonText}>حفظ التغييرات</Text>
          </TouchableOpacity>
        </View>

        <TouchableOpacity style={styles.logoutButton} onPress={handleLogout}>
          <Text style={styles.logoutText}>تسجيل الخروج</Text>
        </TouchableOpacity>

        <View style={styles.bottomSpacing} />
      </ScrollView>

      <View style={styles.bottomNav}>
        <TouchableOpacity style={styles.navItem} onPress={onProfilePress}>
          <View style={styles.activeIndicator} />
          <Image
            source={require('../assets/profile_icon.png')}
            style={styles.navImageActive}
            resizeMode="contain"
          />
          <Text style={styles.navTextActive}>حسابي</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.centerNavButton} onPress={onMapPress}>
          <Image
            source={require('../assets/location_icon.png')}
            style={styles.centerNavImage}
            resizeMode="contain"
          />
        </TouchableOpacity>

        <TouchableOpacity style={styles.navItem} onPress={onHomePress}>
          <Image
            source={require('../assets/home_icon.png')}
            style={styles.navImageInactive}
            resizeMode="contain"
          />
          <Text style={styles.navTextInactive}>الرئيسية</Text>
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
    paddingTop: 26,
    paddingHorizontal: 18,
    paddingBottom: 120,
  },

  header: {
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 22,
  },

  headerTitle: {
    fontSize: 24,
    fontWeight: '800',
    color: '#111',
    textAlign: 'center',
  },

  summaryCard: {
    backgroundColor: '#dcdcdc',
    borderRadius: 18,
    paddingVertical: 16,
    paddingHorizontal: 18,
    marginBottom: 22,
    flexDirection: 'row-reverse',
    justifyContent: 'space-between',
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.08,
    shadowRadius: 6,
    shadowOffset: { width: 0, height: 2 },
    elevation: 3,
  },

  summaryLeft: {
    width: 82,
    alignItems: 'center',
    justifyContent: 'center',
  },

  summaryLogo: {
    width: 130,
    height: 120,
  },

  summaryRight: {
    flex: 1,
    alignItems: 'flex-end',
  },

  nameRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
  },

  profileTopIcon: {
    flexDirection: 'row',
    width: 28,
    height: 28,
    tintColor: '#000',
    marginLeft: 8,
  },

  userName: {
    fontSize: 16,
    fontWeight: '800',
    color: '#111',
    width: '90%',
  },

  infoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
  },

  infoText: {
    flexDirection: 'row',
    fontSize: 14,
    color: '#222',
    textAlign: 'left',
    width: '100%',
  },

  detailsCard: {
    backgroundColor: '#f3f3f3',
    borderRadius: 18,
    borderWidth: 2,
    borderColor: '#111',
    paddingTop: 34,
    paddingHorizontal: 16,
    paddingBottom: 18,
    position: 'relative',
  },

  sectionTitleWrap: {
    position: 'absolute',
    top: -13,
    left: 22,
    backgroundColor: '#f3f3f3',
    paddingHorizontal: 10,
  },

  sectionTitle: {
    fontSize: 16,
    fontWeight: '800',
    color: '#111',
  },

  fieldGroup: {
    marginBottom: 16,
  },

  label: {
    fontSize: 14,
    color: '#666',
    marginBottom: 8,
    textAlign: 'left',
    alignSelf: 'flex-start',
  },

  input: {
    height: 46,
    backgroundColor: '#fff',
    borderRadius: 12,
    paddingHorizontal: 12,
    fontSize: 15,
    color: '#111',
    textAlign: 'right',
  },

  saveButton: {
    backgroundColor: '#1fb77a',
    height: 48,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    alignSelf: 'center',
    width: '52%',
    marginTop: 4,
    shadowColor: '#000',
    shadowOpacity: 0.12,
    shadowRadius: 5,
    shadowOffset: { width: 0, height: 2 },
    elevation: 3,
  },

  saveButtonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: '800',
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
    shadowOpacity: 0.12,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 3 },
    elevation: 6,
  },

  centerNavImage: {
    width: 45,
    height: 45,
    tintColor: '#f4a261',
  },

  logoutButton: {
    marginTop: 12,
    alignItems: 'center',
    justifyContent: 'center',
  },

  logoutText: {
    color: '#767372',
    fontSize: 16,
    fontWeight: '400',
    textDecorationLine: 'underline',
  },
});