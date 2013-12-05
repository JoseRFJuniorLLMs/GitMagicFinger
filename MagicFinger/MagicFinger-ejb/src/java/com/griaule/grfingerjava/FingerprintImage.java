// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   FingerprintImage.java

package com.griaule.grfingerjava;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;

public class FingerprintImage extends BufferedImage {

            private static final int GR_MAX_IMAGE_WIDTH = 1280;
            private static final int GR_MAX_IMAGE_HEIGHT = 1280;
            private static final int GR_MAX_RESOLUTION = 1000;
            private static final int GR_MIN_IMAGE_WIDTH = 50;
            private static final int GR_MIN_IMAGE_HEIGHT = 50;
            private static final int GR_MIN_RESOLUTION = 125;
            private int resolution;

            public FingerprintImage(int i, int j, int k) throws IllegalArgumentException {
/*  49*/        super(i, j, 10);
/*  51*/        if (j < 50 || j > 1280) {
/*  52*/            throw new IllegalArgumentException("Invalid image height.");
                }
/*  53*/        if (k < 125 || k > 1000) {
/*  55*/            throw new IllegalArgumentException("Invalid image resolution.");
                }
/*  56*/        if (i < 50 || i > 1280) {
/*  57*/            throw new IllegalArgumentException("Invalid image width.");
                } else {
/*  59*/            resolution = k;
/*  61*/            return;
                }
            }

            public FingerprintImage(byte abyte0[], int i, int j, int k) throws IllegalArgumentException {
/*  89*/        this(i, j, k);
/*  91*/        byte abyte1[] = ((DataBufferByte)getRaster().getDataBuffer()).getData();
/*  93*/        System.arraycopy(abyte0, 0, abyte1, 0, i * j);
            }

            public FingerprintImage(RenderedImage renderedimage, int i) throws IllegalArgumentException {
/* 115*/        this(renderedimage.getWidth(), renderedimage.getHeight(), i);
/* 117*/        Graphics2D graphics2d = createGraphics();
/* 118*/        graphics2d.drawRenderedImage(renderedimage, new AffineTransform());
/* 119*/        graphics2d.dispose();
            }

            public int getResolution() {
/* 130*/        return resolution;
            }

            public void setResolution(int i) throws IllegalArgumentException {
/* 144*/        if (i < 125 || i > 1000) {
/* 146*/            throw new IllegalArgumentException("Invalid image resolution.");
                } else {
/* 148*/            resolution = i;
/* 149*/            return;
                }
            }

            public static int getMaximumHeight() {
/* 157*/        return 1280;
            }

            public static int getMaximumWidth() {
/* 166*/        return 1280;
            }

            public static int getMaximumResolution() {
/* 175*/        return 1000;
            }

            public static int getMinimumHeight() {
/* 184*/        return 50;
            }

            public static int getMinimumWidth() {
/* 193*/        return 50;
            }

            public static int getMinimumResolution() {
/* 202*/        return 125;
            }
}
