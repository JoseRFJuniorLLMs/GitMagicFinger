// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   GrFingerJavaException.java
package com.griaule.grfingerjava;

public class GrFingerJavaException extends Exception {

    private static final long serialVersionUID = 0x2c21c26b8ccf65b0L;
    private int errno;
    public static final int ERROR_INITIALIZATION_FAILURE = -1;
    public static final int ERROR_NOT_INITIALIZED = -2;
    public static final int ERROR_LICENSE_READ_ERROR = -3;
    public static final int ERROR_NO_VALID_LICENSE = -4;
    public static final int ERROR_ALLOC = -7;
    public static final int ERROR_BAD_USAGE = -107;
    public static final int ERROR_EXTRACTION = -108;
    public static final int ERROR_INVALID_CONTEXT = -112;
    public static final int ERROR_ERROR = -113;
    public static final int ERROR_SENSOR = -201;
    public static final int ERROR_INVALID_SENSOR_ID = -204;

    GrFingerJavaException(int i) {
        /*  76*/ errno = i;
    }

    public int getError() {
        /*  85*/ return errno;
    }

    public String getMessage() {
        /*  94*/ switch (errno) {
            /*  96*/ case -1:
                /*  96*/ return "Initialization failure";

            /*  98*/ case -2:
                /*  98*/ return "GrFinger Java isn't initialized";

            /* 100*/ case -3:
                /* 100*/ return "License not found or read error";

            /* 102*/ case -4:
                /* 102*/ return "Invalid license";

            /* 104*/ case -7:
                /* 104*/ return "Not enough memory";

            /* 106*/ case -107:
                /* 106*/ return "Improper library usage";

            /* 108*/ case -108:
                /* 108*/ return "Fingerprint minutiae extraction error";

            /* 110*/ case -112:
                /* 110*/ return "Context already destroyed";

            /* 112*/ case -113:
                /* 112*/ return "General error";

            /* 114*/ case -201:
                /* 114*/ return "Error connecting to fingerprint sensor";

            /* 116*/ case -204:
                /* 116*/ return "Invalid fingerprint sensor identifier";
        }
        /* 120*/ return "Unknown error";
    }
}
