// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.util.internal;

import android.util.Log;


public class Sleeper
{

    protected Sleeper()
    {
    }

    private static Sleeper getInstance()
    {
        if(sleeper == null)
            sleeper = new Sleeper();
        return sleeper;
    }

    public static void sleep(long l)
    {
        getInstance().performSleep(l);
    }

    protected void performSleep(long l) {
    	
    	try {
    		Thread.sleep(l);
		} catch (InterruptedException ie) {
			// TODO: handle exception
			Log.e("", "Error sleeping thread");
		}
    }

    static Sleeper sleeper;
}
