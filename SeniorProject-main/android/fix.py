import re

with open(r'c:\Src\SeniorProject\SeniorProject-main\src\screens\AllReportsScreen.tsx', 'r', encoding='utf-8') as f:
    text = f.read()

# Fix the duplicate imports
text = text.replace('''import { useEffect, useState } from 'react';
import axios from 'axios';
import { useEffect, useState } from 'react';
import axios from 'axios';''', 
'''import { useEffect, useState } from 'react';
import axios from 'axios';''')

with open(r'c:\Src\SeniorProject\SeniorProject-main\src\screens\AllReportsScreen.tsx', 'w', encoding='utf-8') as f:
    f.write(text)

print("Fixed imports in AllReportsScreen!")
