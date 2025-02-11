## Code Challenge Android

- [Descripción del Challenge](https://github.com/LeoIsasmendi/codechallenge01/blob/dev/docs/Mobile%20Challenge%20-%20v0.6%20(2).pdf)

### Requerimientos:

- Clonar el **branch dev** del repositorio
- Generar Google Maps ApiKey ([documentacion](https://developers.google.com/maps/documentation/embed/get-api-key?hl=es-419))
    - Reemplazar API_KEY_VALUE en [AndroidManifest.xml](https://github.com/LeoIsasmendi/codechallenge01/blob/dev/app/src/main/AndroidManifest.xml) con la key generada

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
   ...
        <meta-data android:name="com.google.android.geo.API_KEY"
            android:value="API_KEY_VALUE"/>
    </application>

</manifest>
```
