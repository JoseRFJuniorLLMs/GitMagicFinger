// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   Template.java
package com.griaule.grfingerjava;

import java.io.Serializable;

public class Template
        implements Serializable {

    public static final int UNKNOWN_QUALITY = -1;
    public static final int LOW_QUALITY = 0;
    public static final int MEDIUM_QUALITY = 1;
    public static final int HIGH_QUALITY = 2;
    public static final int FORMAT_DEFAULT = 0;
    public static final int FORMAT_GR001 = 1;
    public static final int FORMAT_GR002 = 2;
    public static final int FORMAT_GR003 = 3;
    public static final int FORMAT_CLASSIC = 100;
    public static final int FORMAT_ISO = 200;
    public static final int FORMAT_ANSI = 201;
    private static final long serialVersionUID = 0x40bed568cea839b8L;
    private byte data[];
    private int quality;

    public Template(byte abyte0[]) {
        /* 113*/ data = (byte[]) (byte[]) abyte0.clone();
        /* 114*/ quality = -1;
    }

    public Template(int i, byte abyte0[]) {
        /* 130*/ data = (byte[]) (byte[]) abyte0.clone();
        /* 131*/ quality = i;
    }

    public Template(int i, byte abyte0[], int j) {
        /* 150*/ data = new byte[j];
        /* 151*/ System.arraycopy(abyte0, 0, data, 0, j);
        /* 152*/ quality = i;
    }

    public Template(int i, byte abyte0[], int j, int k) {
        /* 172*/ data = new byte[k];
        /* 173*/ System.arraycopy(abyte0, j, data, 0, k);
        /* 174*/ quality = i;
    }

    public Template() {
        /* 184*/ data = null;
        /* 185*/ quality = -1;
    }

    public byte[] getData() {
        /* 195*/ return data;
    }

    public void setData(byte abyte0[]) {
        /* 205*/ data = abyte0;
    }

    public int getQuality() {
        /* 219*/ return quality;
    }

    public void setQuality(int i) {
        /* 234*/ quality = i;
    }
}
