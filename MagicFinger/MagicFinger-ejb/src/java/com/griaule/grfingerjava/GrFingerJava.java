// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   GrFingerJava.java

package com.griaule.grfingerjava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

// Referenced classes of package com.griaule.grfingerjava:
//            GrFingerJavaException, GrFingerJavaNative, Template, FingerprintImage, 
//            MatchingContext, IStatusEventListener, IFingerEventListener, IImageEventListener

public class GrFingerJava {

            public static final int GRFINGER_JAVA_FULL = 1;
            public static final int GRFINGER_JAVA_LIGHT = 2;
            private static final int GR_IMAGE_NO_COLOR = 0x1fffffff;
            public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
            private static int majorVersion[];
            private static int minorVersion[];
            private static int licenseType;
            private static File nativeLibrariesDirectory = null;

            private GrFingerJava() {
            }

            static File getNativeLibrariesDirectory() {
/*  56*/        return nativeLibrariesDirectory;
            }

            public static synchronized void setNativeLibrariesDirectory(File file) throws IllegalArgumentException {
/*  70*/        if (!file.isDirectory()) {
/*  71*/            throw new IllegalArgumentException(file.toString() + " is not a directory.");
                } else {
/*  74*/            nativeLibrariesDirectory = file;
/*  75*/            return;
                }
            }

            public static synchronized void setLicenseDirectory(File file) throws GrFingerJavaException, IllegalArgumentException {
/*  93*/        if (!file.isDirectory()) {
/*  94*/            throw new IllegalArgumentException(file.toString() + " is not a directory.");
                }
/*  96*/        int i = GrFingerJavaNative.GrSetLicenseFolder((file.getAbsolutePath() + "\0").getBytes());
/*  98*/        if (i < 0) {
/*  99*/            throw new GrFingerJavaException(i);
                } else {
/* 101*/            return;
                }
            }

            public static synchronized void initializeCapture(IStatusEventListener istatuseventlistener) throws GrFingerJavaException, NullPointerException {
/* 121*/        if (istatuseventlistener == null) {
/* 122*/            throw new NullPointerException("statusEventListener is null.");
                }
/* 124*/        GrFingerJavaNative.setStatusCallBack(istatuseventlistener);
/* 125*/        int i = GrFingerJavaNative.GrCapInitialize();
/* 126*/        if (i < 0) {
/* 127*/            throw new GrFingerJavaException(i);
                } else {
/* 128*/            return;
                }
            }

            public static synchronized void finalizeCapture() throws GrFingerJavaException {
/* 138*/        int i = GrFingerJavaNative.GrCapFinalize();
/* 139*/        if (i < 0) {
/* 140*/            throw new GrFingerJavaException(i);
                } else {
/* 141*/            GrFingerJavaNative.removeStatusCallBack();
/* 142*/            return;
                }
            }

            public static synchronized void startCapture(String s, IFingerEventListener ifingereventlistener, IImageEventListener iimageeventlistener) throws GrFingerJavaException {
/* 170*/        if (ifingereventlistener == null) {
/* 171*/            throw new NullPointerException("fingerEventListener is null.");
                }
/* 172*/        if (iimageeventlistener == null) {
/* 173*/            throw new NullPointerException("imageEventListener is null.");
                }
/* 175*/        GrFingerJavaNative.setFingerCallBack(s, ifingereventlistener);
/* 176*/        GrFingerJavaNative.setImageCallBack(s, iimageeventlistener);
/* 178*/        int i = GrFingerJavaNative.GrCapStartCapture(s);
/* 180*/        if (i < 0) {
/* 181*/            throw new GrFingerJavaException(i);
                } else {
/* 182*/            return;
                }
            }

            public static synchronized void stopCapture(String s) throws GrFingerJavaException {
/* 196*/        int i = GrFingerJavaNative.GrCapStopCapture(s);
/* 198*/        if (i < 0) {
/* 199*/            throw new GrFingerJavaException(i);
                } else {
/* 201*/            GrFingerJavaNative.removeFingerCallBack(s);
/* 202*/            GrFingerJavaNative.removeImageCallBack(s);
/* 203*/            return;
                }
            }

            public static synchronized BufferedImage getBiometricImage(Template template, FingerprintImage fingerprintimage, MatchingContext matchingcontext) throws GrFingerJavaException, IllegalArgumentException {
/* 231*/        if (template.getData() == null) {
/* 232*/            throw new IllegalArgumentException("Template data is null.");
                }
/* 234*/        BufferedImage bufferedimage = new BufferedImage(fingerprintimage.getWidth(), fingerprintimage.getHeight(), 5);
/* 236*/        byte abyte0[] = ((DataBufferByte)bufferedimage.getRaster().getDataBuffer()).getData();
/* 239*/        int i = GrFingerJavaNative.GrBiometricDisplay(template.getData(), ((DataBufferByte)fingerprintimage.getRaster().getDataBuffer()).getData(), fingerprintimage.getWidth(), fingerprintimage.getHeight(), fingerprintimage.getResolution(), abyte0, matchingcontext != null ? matchingcontext.getId() : -1);
/* 244*/        if (i < 0) {
/* 245*/            throw new GrFingerJavaException(i);
                } else {
/* 247*/            return bufferedimage;
                }
            }

            public static synchronized BufferedImage getBiometricImage(Template template, FingerprintImage fingerprintimage) throws GrFingerJavaException, IllegalArgumentException {
/* 269*/        return getBiometricImage(template, fingerprintimage, null);
            }

            private static int getBGR(Color color) {
/* 273*/        if (color == null) {
/* 274*/            return 0x1fffffff;
                }
/* 275*/        if (color.getAlpha() < 128) {
/* 276*/            return 0x1fffffff;
                } else {
/* 278*/            return color.getBlue() << 16 | color.getGreen() << 8 | color.getRed();
                }
            }

            public static synchronized void setBiometricImageColors(Color color, Color color1, Color color2, Color color3, Color color4, Color color5) throws GrFingerJavaException {
/* 319*/        int i = GrFingerJavaNative.GrSetBiometricDisplayColors(getBGR(color), getBGR(color1), getBGR(color2), getBGR(color3), getBGR(color4), getBGR(color5));
/* 323*/        if (i < 0) {
/* 324*/            throw new GrFingerJavaException(i);
                } else {
/* 326*/            return;
                }
            }

            private static synchronized void getLicenseInformation() throws GrFingerJavaException {
/* 330*/        if (majorVersion == null) {
/* 331*/            majorVersion = new int[1];
/* 332*/            minorVersion = new int[1];
/* 333*/            licenseType = GrFingerJavaNative.GrGetGrFingerVersion(majorVersion, minorVersion);
/* 335*/            if (licenseType < 0) {
/* 336*/                majorVersion = null;
/* 337*/                minorVersion = null;
/* 338*/                throw new GrFingerJavaException(licenseType);
                    }
                }
            }

            public static synchronized int getMajorVersion() throws GrFingerJavaException {
/* 353*/        getLicenseInformation();
/* 354*/        return majorVersion[0];
            }

            public static synchronized int getMinorVersion() throws GrFingerJavaException {
/* 366*/        getLicenseInformation();
/* 367*/        return minorVersion[0];
            }

            public static synchronized int getLicenseType() throws GrFingerJavaException {
/* 382*/        getLicenseInformation();
/* 383*/        return licenseType;
            }

}
