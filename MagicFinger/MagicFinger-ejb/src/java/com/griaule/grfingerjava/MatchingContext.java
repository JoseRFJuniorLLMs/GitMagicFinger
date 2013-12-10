// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   MatchingContext.java
package com.griaule.grfingerjava;

import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

// Referenced classes of package com.griaule.grfingerjava:
//            GrFingerJavaException, Template, GrFingerJavaNative, FingerprintImage
public class MatchingContext {

    private static final int GR_MAX_THRESHOLD = 200;
    private static final int GR_MIN_THRESHOLD = 10;
    private static final int GR_ROT_MIN = 0;
    private static final int GR_ROT_MAX = 180;
    private static final int GR_VERYLOW_FRR = 25;
    private static final int GR_LOW_FRR = 45;
    private static final int GR_LOW_FAR = 60;
    private static final int GR_VERYLOW_FAR = 80;
    protected static final int GR_MAX_TPT_SIZE = 2706;
    private static final int GR_MAX_CONTEXTS = 32;
    private static final int GR_MATCH = 1;
    private static final int GR_NOT_MATCH = 0;
    private int id;
    private int score[];
    private int identifyRotationTolerance[];
    private int verifyRotationTolerance[];
    private int identifyThreshold[];
    private int verifyThreshold[];
    private boolean destroyed;
    private static int numberOfContexts = 0;

    private static synchronized int createContext() throws GrFingerJavaException {
        /*  57*/ if (numberOfContexts == 0) {
            /*  58*/ int i = GrFingerJavaNative.GrInitialize();
            /*  59*/ if (i < 0) {
                /*  60*/ throw new GrFingerJavaException(i);
            } else {
                /*  61*/ numberOfContexts++;
                /*  62*/ return 0;
            }
        }
        /*  64*/ int ai[] = new int[1];
        /*  66*/ int j = GrFingerJavaNative.GrCreateContext(ai);
        /*  68*/ if (j < 0) {
            /*  69*/ throw new GrFingerJavaException(j);
        } else {
            /*  70*/ numberOfContexts++;
            /*  71*/ return ai[0];
        }
    }

    private static synchronized void destroyContext(int i) throws GrFingerJavaException {
        /*  79*/ int j = GrFingerJavaNative.GrDestroyContext(i);
        /*  81*/ if (j < 0) {
            /*  82*/ throw new GrFingerJavaException(j);
        }
        /*  84*/ numberOfContexts--;
        /*  86*/ if (numberOfContexts == 0) {
            /*  87*/ int k = GrFingerJavaNative.GrFinalize();
            /*  89*/ if (k < 0) {
                /*  90*/ throw new GrFingerJavaException(k);
            }
        }
    }

    public MatchingContext() throws GrFingerJavaException {
        /*  51*/ destroyed = true;
        /* 103*/ id = createContext();
        /* 104*/ destroyed = false;
        /* 105*/ score = new int[1];
        /* 106*/ score[0] = -1;
        /* 107*/ identifyRotationTolerance = new int[1];
        /* 108*/ identifyThreshold = new int[1];
        /* 109*/ verifyRotationTolerance = new int[1];
        /* 110*/ verifyThreshold = new int[1];
        /* 111*/ GrFingerJavaNative.GrGetIdentifyParameters(identifyThreshold, identifyRotationTolerance, id);
        /* 113*/ GrFingerJavaNative.GrGetVerifyParameters(verifyThreshold, verifyRotationTolerance, id);
    }

    int getId() throws GrFingerJavaException {
        /* 118*/ if (destroyed) {
            /* 119*/ throw new GrFingerJavaException(-112);
        } else {
            /* 121*/ return id;
        }
    }

    public synchronized Template extract(FingerprintImage fingerprintimage) throws GrFingerJavaException {
        /* 136*/ return extract(fingerprintimage, 0);
    }

    public synchronized Template extract(FingerprintImage fingerprintimage, int i) throws GrFingerJavaException {
        /* 151*/ if (destroyed) {
            /* 152*/ throw new GrFingerJavaException(-112);
        }
        /* 155*/ byte abyte0[] = new byte[2706];
        /* 156*/ int ai[] = new int[1];
        /* 157*/ ai[0] = 2706;
        /* 158*/ int j = GrFingerJavaNative.GrExtract(((DataBufferByte) fingerprintimage.getRaster().getDataBuffer()).getData(), fingerprintimage.getWidth(), fingerprintimage.getHeight(), fingerprintimage.getResolution(), abyte0, ai, id, i);
        /* 162*/ if (j < 0) {
            /* 163*/ throw new GrFingerJavaException(j);
        } else {
            /* 165*/ return new Template(j, abyte0, ai[0]);
        }
    }

    public synchronized Template convertTemplate(Template template, int i) throws GrFingerJavaException {
        /* 176*/ int ai[] = {
            /* 176*/2706
        };
        /* 177*/ byte abyte0[] = new byte[ai[0]];
        /* 178*/ int j = GrFingerJavaNative.GrTemplateConvert(template.getData(), abyte0, ai, id, i);
        /* 179*/ if (j < 0) {
            /* 180*/ throw new GrFingerJavaException(j);
        } else {
            /* 182*/ return new Template(template.getQuality(), abyte0, ai[0]);
        }
    }

    public synchronized boolean verify(Template template, Template template1) throws GrFingerJavaException, IllegalArgumentException {
        /* 215*/ if (destroyed) {
            /* 216*/ throw new GrFingerJavaException(-112);
        }
        /* 219*/ if (template.getData() == null) {
            /* 220*/ throw new IllegalArgumentException("Query template data is null.");
        }
        /* 221*/ if (template1.getData() == null) {
            /* 222*/ throw new IllegalArgumentException("Reference template data is null.");
        }
        /* 225*/ int i = GrFingerJavaNative.GrVerify(template.getData(), template1.getData(), score, id);
        /* 228*/ if (i < 0) {
            /* 229*/ throw new GrFingerJavaException(i);
        }
        /* 231*/ return i == 1;
    }

    public synchronized void prepareForIdentification(Template template) throws GrFingerJavaException, IllegalArgumentException {
        /* 254*/ if (destroyed) {
            /* 255*/ throw new GrFingerJavaException(-112);
        }
        /* 258*/ if (template.getData() == null) {
            /* 259*/ throw new IllegalArgumentException("Query template data is null.");
        }
        /* 261*/ int i = GrFingerJavaNative.GrIdentifyPrepare(template.getData(), id);
        /* 264*/ if (i < 0) {
            /* 265*/ throw new GrFingerJavaException(i);
        } else {
            /* 267*/ return;
        }
    }

    public synchronized boolean identify(Template template) throws GrFingerJavaException, IllegalArgumentException {
        /* 295*/ if (destroyed) {
            /* 296*/ throw new GrFingerJavaException(-112);
        }
        /* 299*/ if (template.getData() == null) {
            /* 300*/ throw new IllegalArgumentException("Reference template data is null.");
        }
        /* 303*/ int i = GrFingerJavaNative.GrIdentify(template.getData(), score, id);
        /* 306*/ if (i < 0) {
            /* 307*/ throw new GrFingerJavaException(i);
        }
        /* 309*/ return i == 1;
    }

    public synchronized int getIdentificationRotationTolerance() throws GrFingerJavaException {
        /* 331*/ if (destroyed) {
            /* 332*/ throw new GrFingerJavaException(-112);
        } else {
            /* 335*/ return identifyRotationTolerance[0];
        }
    }

    public synchronized void setIdentificationRotationTolerance(int i) throws GrFingerJavaException {
        /* 356*/ if (destroyed) {
            /* 357*/ throw new GrFingerJavaException(-112);
        }
        /* 360*/ int j = GrFingerJavaNative.GrSetIdentifyParameters(identifyThreshold[0], i, id);
        /* 363*/ if (j < 0) {
            /* 364*/ throw new GrFingerJavaException(j);
        } else {
            /* 366*/ identifyRotationTolerance[0] = i;
            /* 367*/ return;
        }
    }

    public synchronized int getIdentificationThreshold() throws GrFingerJavaException {
        /* 384*/ if (destroyed) {
            /* 385*/ throw new GrFingerJavaException(-112);
        } else {
            /* 388*/ return identifyThreshold[0];
        }
    }

    public synchronized void setIdentificationThreshold(int i) throws GrFingerJavaException {
        /* 408*/ if (destroyed) {
            /* 409*/ throw new GrFingerJavaException(-112);
        }
        /* 412*/ int j = GrFingerJavaNative.GrSetIdentifyParameters(i, identifyRotationTolerance[0], id);
        /* 415*/ if (j < 0) {
            /* 416*/ throw new GrFingerJavaException(j);
        } else {
            /* 418*/ identifyThreshold[0] = i;
            /* 419*/ return;
        }
    }

    public synchronized int getVerificationRotationTolerance() throws GrFingerJavaException {
        /* 438*/ if (destroyed) {
            /* 439*/ throw new GrFingerJavaException(-112);
        } else {
            /* 442*/ return verifyRotationTolerance[0];
        }
    }

    public synchronized void setVerificationRotationTolerance(int i) throws GrFingerJavaException {
        /* 463*/ if (destroyed) {
            /* 464*/ throw new GrFingerJavaException(-112);
        }
        /* 467*/ int j = GrFingerJavaNative.GrSetVerifyParameters(verifyThreshold[0], i, id);
        /* 470*/ if (j < 0) {
            /* 471*/ throw new GrFingerJavaException(j);
        } else {
            /* 473*/ verifyRotationTolerance[0] = i;
            /* 474*/ return;
        }
    }

    public synchronized int getVerificationThreshold() throws GrFingerJavaException {
        /* 491*/ if (destroyed) {
            /* 492*/ throw new GrFingerJavaException(-112);
        } else {
            /* 495*/ return verifyThreshold[0];
        }
    }

    public synchronized void setVerificationThreshold(int i) throws GrFingerJavaException {
        /* 515*/ if (destroyed) {
            /* 516*/ throw new GrFingerJavaException(-112);
        }
        /* 519*/ int j = GrFingerJavaNative.GrSetVerifyParameters(i, verifyRotationTolerance[0], id);
        /* 522*/ if (j < 0) {
            /* 523*/ throw new GrFingerJavaException(j);
        } else {
            /* 525*/ verifyThreshold[0] = i;
            /* 526*/ return;
        }
    }

    public synchronized int getScore() throws GrFingerJavaException {
        /* 540*/ if (destroyed) {
            /* 541*/ throw new GrFingerJavaException(-112);
        } else {
            /* 544*/ return score[0];
        }
    }

    public synchronized void destroy() throws GrFingerJavaException {
        /* 558*/ if (!destroyed) {
            /* 559*/ destroyContext(id);
            /* 561*/ destroyed = true;
        }
    }

    protected void finalize() throws Throwable {
        /* 567*/ destroy();
        /* 568*/ super.finalize();
    }

    public static int getThresholdForLowFAR() {
        /* 579*/ return 60;
    }

    public static int getThresholdForLowFRR() {
        /* 590*/ return 45;
    }

    public static int getMaximumThresholdValue() {
        /* 603*/ return 200;
    }

    public static int getMinimumThresholdValue() {
        /* 616*/ return 10;
    }

    public static int getMaximumRotationToleranceValue() {
        /* 629*/ return 180;
    }

    public static int getMinimumRotationToleranceValue() {
        /* 642*/ return 0;
    }

    public static int getThresholdForVeryLowFAR() {
        /* 653*/ return 80;
    }

    public static int getThresholdForVeryLowFRR() {
        /* 664*/ return 25;
    }

    public static int getMaximumNumberOfContexts() {
        /* 674*/ return 32;
    }
}
