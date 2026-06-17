import re

with open(r'c:\Src\SeniorProject\SeniorProject-main\src\screens\AllReportsScreen.tsx', 'r', encoding='utf-8') as f:
    text = f.read()

# Replace ReportItem type
text = text.replace('id: number;', 'id: string | number;')

# Add axios
text = text.replace("import {\n  View", "import { useEffect, useState } from 'react';\nimport axios from 'axios';\nimport {\n  View")

# Remove static reports array, just replace export default function ...
text = text.replace('export default function AllReportsScreen({ onBack, onAcceptReportPress }: Props) {',
'''export default function AllReportsScreen({ onBack, onAcceptReportPress }: Props) {
  const [liveReports, setLiveReports] = useState<ReportItem[]>(reports);

  useEffect(() => {
    const fetchReports = async () => {
      try {
        const res = await axios.get('http://192.168.0.117:3000/api/reports');
        const apiReports = res.data;
        const mapped = apiReports.map((r: any) => {
          let icon = '📍';
          let iconBg = '#d9ebff';
          let sideColor = '#1f8fff';
          if (r.type === 'طبي' || r.type === 'حريق' || r.type === 'medical') {
            icon = '🛡️';
            iconBg = '#f6d7d7';
            sideColor = '#ff2d2d';
          } else if (r.type === 'أمني' || r.type === 'أمن' || r.type === 'security') {
            icon = '🛡️';
            iconBg = '#d9f3ea';
            sideColor = '#18c98b';
          }
          return {
            id: r._id,
            screenNumber: '71',
            neighborhood: 'منى',
            distanceText: 'الآن',
            title: r.description,
            icon,
            iconBg,
            sideColor,
          };
        });
        if(mapped.length > 0) setLiveReports(mapped);
      } catch (e) {
        console.log('Error fetching reports:', e);
      }
    };
    
    fetchReports();
    const interval = setInterval(fetchReports, 5000);
    return () => clearInterval(interval);
  }, []);
''')

# 3. Update map render
text = text.replace('{reports.map', '{liveReports.map')

with open(r'c:\Src\SeniorProject\SeniorProject-main\src\screens\AllReportsScreen.tsx', 'w', encoding='utf-8') as f:
    f.write(text)

print("AllReportsScreen updated!")
