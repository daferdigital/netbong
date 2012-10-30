// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

public enum ZplPrintMode {
	
	REWIND("Rewind"),
    PEEL_OFF("Peel-Off"),
    TEAR_OFF("Tear-Off"),
    CUTTER("Cutter"),
    APPLICATOR("Applicator"),
    DELAYED_CUT("Delayed Cut"),
    LINERLESS_PEEL("Linerless Peel"),
    LINERLESS_REWIND("Linerless Rewind"),
    PARTIAL_CUTTER("Partial Cutter"),
    RFID("RFID"),
    KIOSK("Kiosk"),
    UNKNOWN("Unknown");
    
	private final String name;
	
    private ZplPrintMode(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
