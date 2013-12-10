// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   IStatusEventListener.java
package com.griaule.grfingerjava;

public interface IStatusEventListener {

    public abstract void onSensorPlug(String s);

    public abstract void onSensorUnplug(String s);
}
