// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer;

import com.zebra.android.comm.ZebraPrinterConnectionException;

// Referenced classes of package com.zebra.android.printer:
//            ZebraIllegalArgumentException

public interface FileUtil
{

    public abstract String[] retrieveFileNames()
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException;

    public abstract String[] retrieveFileNames(String as[])
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException;

    public abstract void sendFileContents(String s)
        throws ZebraPrinterConnectionException;
}
