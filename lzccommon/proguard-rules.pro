# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/chuan/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn android.support.**
-keep class android.support.** { *; }


-keepattributes InnerClasses
-dontoptimize


-keep class com.jph.takephoto.** { *; }
-dontwarn com.jph.takephoto.**

-keep class com.darsh.multipleimageselect.** { *; }
-dontwarn com.darsh.multipleimageselect.**

-keep class com.soundcloud.android.crop.** { *; }
-dontwarn com.soundcloud.android.crop.**

 -keep class com.tamic.novate.** {*;}
 -keep class retrofit2.** {*;}

 -dontwarn okhttp3**
 -keep  class okhttp3.**{*;}
 -dontwarn okio**
 -keep  class okio.**{*;}
 -keep  class rx.**{*;}

 # glide 的混淆代码
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }
 # banner 的混淆代码
 -keep class com.youth.banner.** {
     *;
  }