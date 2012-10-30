// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.printer.ZebraIllegalArgumentException;
import com.zebra.android.util.internal.StringUtilities;

// Referenced classes of package com.zebra.android.printer.internal:
//            FileUtilA, PrinterFilePropertiesList, PrinterFilePropertiesCpcl

public class FileUtilCpcl extends FileUtilA
{

    public FileUtilCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        super(zebraprinterconnection);
    }

    public PrinterFilePropertiesList extractFilePropertiesFromDirResult(String s)
        throws ZebraIllegalArgumentException {
    	
        int i, j;
        PrinterFilePropertiesList printerfilepropertieslist = new PrinterFilePropertiesList();
        String[] as = StringUtilities.split(s, "\r\n");
        i = -1;
        
        for (j = 0; j < as.length; j++) {
        	if(as[j].indexOf("Directory") >= 0){
        		i = j;
        	} else {
        		i = -1;
        	}
        	
        	if(i >= 0){
        		int k = i + 1;
        		if(k < as.length && as[k].indexOf("Bytes Free") < 0){
        			printerfilepropertieslist.add(new PrinterFilePropertiesCpcl(as[k]));
        		}
        	}
		}

        return printerfilepropertieslist;
    }
}
