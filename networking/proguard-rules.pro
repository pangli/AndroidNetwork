# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

#---------------------------------1.实体类---------------------------------

#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
-keep class com.google.gson.* {*;}

#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------


#----------------------------------------------------------------------------
#---------------------------------5.自定义相关的类和方法-----------------------
#保持包机构
-keeppackagenames  com.zorro.networking.**
-keep class com.zorro.networking.common.*{*;}
-keep class com.zorro.networking.core.*{*;}
-keep class com.zorro.networking.AndroidNetworking{*;}
-keep class com.zorro.networking.interfaces.*{*;}
-keep class com.zorro.networking.error.*{*;}
-keep class com.zorro.networking.interceptors.*{*;}




#----------------------------------------------------------------------------

#---------------------------------基本指令区----------------------------------
#序列化不混淆
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}
# 重命名抛出异常时的文件名称
-renamesourcefileattribute SourceFile
#类文件除了定义类，字段，方法外，还为它们附加了一些属性，例如注解，异常，行号等，优化操作会删除不必要的属性，使用-keepattributes可以保留指定的属性
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
#----------------------------------------------------------------------------



