import re
import uuid

filepath = r"c:\Users\edgar\OneDrive\Desktop\Chamba insana\BaseDeDatos\Proyecto-Base-de-Datos\ucab-services\backend\src\main\resources\reports\reporte_pagos.jrxml"

with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

def replace_uuid(m):
    val = m.group(1)
    if val.count('-') == 4:
        return m.group(0)
    return f'uuid="{str(uuid.uuid4())}"'

content = re.sub(r'uuid="([^"]+)"', replace_uuid, content)

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)
