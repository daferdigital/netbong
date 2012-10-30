// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;


public enum PrinterLanguage {

	ZPL("ZPL"),
    CPCL("CPCL");
    
	public String toString() {
        return name;
    }

    private final String name;

    private PrinterLanguage(String name) {
		// TODO Auto-generated constructor stub
    	this.name = name;
	}
}
