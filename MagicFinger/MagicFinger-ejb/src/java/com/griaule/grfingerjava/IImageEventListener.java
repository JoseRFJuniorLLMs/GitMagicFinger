// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   IImageEventListener.java

package com.griaule.grfingerjava;


// Referenced classes of package com.griaule.grfingerjava:
//            FingerprintImage

public interface IImageEventListener {

    public abstract void onImageAcquired(String s, FingerprintImage fingerprintimage);
}
