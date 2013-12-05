// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   GrFingerJavaNative.java

package com.griaule.grfingerjava;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.griaule.grfingerjava:
//            FingerprintImage, IImageEventListener, IFingerEventListener, GrFingerJava, 
//            IStatusEventListener

final class GrFingerJavaNative {

            private static Map imageCallBackMap = new TreeMap();
            private static Map fingerCallBackMap = new TreeMap();
            private static final int GR_PLUG = 21;
            private static final int GR_UNPLUG = 20;
            private static final int GR_FINGER_DOWN = 11;
            private static final int GR_FINGER_UP = 10;

            private GrFingerJavaNative() {
            }

            private static void loadLibrary(String s) {
/*  36*/        File file = GrFingerJava.getNativeLibrariesDirectory();
/*  37*/        if (file != null) {
/*  38*/            File file1 = new File(file, System.mapLibraryName(s));
/*  39*/            if (file1.exists()) {
/*  41*/                try {
/*  41*/                    System.load(file1.getAbsolutePath());
/*  42*/                    return;
                        }
/*  43*/                catch (Throwable throwable) { }
                    }
                }
/*  46*/        System.loadLibrary(s);
            }

            static native int GrCapInitialize();

            static native int GrCapFinalize();

            static native int GrCapStartCapture(String s);

            static native int GrCapStopCapture(String s);

            static native int GrExtract(byte abyte0[], int i, int j, int k, byte abyte1[], int ai[], int l, int i1);

            static native int GrTemplateConvert(byte abyte0[], byte abyte1[], int ai[], int i, int j);

            static native int GrInitialize();

            static native int GrFinalize();

            static native int GrCreateContext(int ai[]);

            static native int GrDestroyContext(int i);

            static native int GrVerify(byte abyte0[], byte abyte1[], int ai[], int i);

            static native int GrIdentifyPrepare(byte abyte0[], int i);

            static native int GrIdentify(byte abyte0[], int ai[], int i);

            static native int GrSetIdentifyParameters(int i, int j, int k);

            static native int GrSetVerifyParameters(int i, int j, int k);

            static native int GrGetIdentifyParameters(int ai[], int ai1[], int i);

            static native int GrGetVerifyParameters(int ai[], int ai1[], int i);

            static native int GrSetTemplateParameters(short word0, short word1, int i);

            static native int GrSetBiometricDisplayColors(int i, int j, int k, int l, int i1, int j1);

            static native int GrBiometricDisplay(byte abyte0[], byte abyte1[], int i, int j, int k, byte abyte2[], int l);

            static native int GrGetGrFingerVersion(int ai[], int ai1[]);

            static native int GrSetLicenseFolder(byte abyte0[]);


            static void removeFingerCallBack(String s) {
/* 137*/        fingerCallBackMap.remove(s);
            }

            static void removeImageCallBack(String s) {
/* 145*/        imageCallBackMap.remove(s);
            }

 

            static  {
/*  24*/        String s = System.getProperty("os.name");
/*  25*/        if (s != null && s.startsWith("Windows")) {
/*  26*/            loadLibrary("pthreadVC2");
                }
/*  28*/        loadLibrary("grfingerjava");
            }
}
