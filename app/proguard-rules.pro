# Firebase
# Menjaga semua kelas terkait Firebase dan Google Play services agar tidak terpengaruh oleh minification
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Menjaga kelas-kelas yang digunakan oleh Firebase Authentication
-keep class com.google.firebase.auth.internal.** { *; }

# WebView JavaScript Interface (Jika Anda menggunakan WebView)
# Menjaga antarmuka JavaScript agar tidak terpengaruh oleh minification
# Jika Anda menggunakan WebView dengan JS, pastikan untuk mengganti fqcn dengan nama lengkap kelas antarmuka
# -keepclassmembers class fqcn.of.javascript.interface.for.webview {
#     public *;
# }

# Menjaga informasi baris untuk debugging stack trace
# Jika Anda ingin mempertahankan informasi nomor baris untuk debugging, gunakan aturan ini
-keepattributes SourceFile,LineNumberTable

# Menyembunyikan nama file sumber
# Jika Anda ingin menyembunyikan nama file sumber asli
-renamesourcefileattribute SourceFile

# Menjaga komponen penting seperti Services atau Receivers yang digunakan
# Menjaga service RevocationBoundService dari Firebase agar tidak terhapus
-keep class com.google.android.gms.auth.api.signin.RevocationBoundService { *; }

# Menjaga Receiver dari AndroidX ProfileInstaller agar tidak terhapus
-keep class androidx.profileinstaller.ProfileInstallReceiver { *; }

# Jika menggunakan Firebase Cloud Messaging (FCM) atau lainnya, pastikan untuk menjaga konfigurasi berikut
-keep class com.google.firebase.messaging.** { *; }

# Jika menggunakan fitur lainnya dari Firebase, tambahkan aturan berikut
-keep class com.google.firebase.analytics.** { *; }

# Jika Anda menggunakan fitur lain dari Firebase, seperti Firestore, tambahkan aturan yang sesuai
-keep class com.google.firebase.firestore.** { *; }

# Menjaga aktivitas dan komponen internal dari Firebase Auth yang sudah ada di dalam AndroidManifest
# Jangan hapus atau ubah nama aktivitas GenericIdpActivity dan RecaptchaActivity
-keep class com.google.firebase.auth.internal.GenericIdpActivity { *; }
-keep class com.google.firebase.auth.internal.RecaptchaActivity { *; }

# Menjaga kelas-kelas di dalam AndroidX yang mungkin diperlukan dalam aplikasi
-keep class androidx.** { *; }

# Mengabaikan pengecilan untuk semua metode yang diubah oleh Reflection atau digunakan dalam konfigurasi lainnya
-keep class * extends java.lang.Exception { *; }
-keep class * extends android.content.BroadcastReceiver { *; }
-keep class * extends android.app.Service { *; }

# Menjaga kelas-kelas terkait RNG yang aman
-keep class java.security.SecureRandom { *; }

# Menghapus log statements di versi rilis
# Menjaga agar log tidak ada di versi rilis
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** w(...);
}
