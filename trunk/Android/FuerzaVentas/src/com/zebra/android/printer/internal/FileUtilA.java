// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.printer.FileUtil;
import com.zebra.android.printer.ZebraIllegalArgumentException;
import com.zebra.android.sgd.SGD;
import java.io.InputStream;

// Referenced classes of package com.zebra.android.printer.internal:
//            ZebraFileConnectionImpl, PrinterFilePropertiesList, ZebraFileConnection

public abstract class FileUtilA implements FileUtil {

    public FileUtilA(ZebraPrinterConnection zebraprinterconnection) {
        printerConnection = zebraprinterconnection;
    }

    public abstract PrinterFilePropertiesList extractFilePropertiesFromDirResult(String s)
        throws ZebraIllegalArgumentException;

    protected ZebraFileConnection getFileConnection(String s)
        throws ZebraPrinterConnectionException
    {
        return new ZebraFileConnectionImpl(s);
    }

    public String[] retrieveFileNames()
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException
    {
        return extractFilePropertiesFromDirResult(SGD.GET("file.dir", printerConnection)).getFileNamesFromProperties();
    }

    public String[] retrieveFileNames(String as[])
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException
    {
        return extractFilePropertiesFromDirResult(SGD.GET("file.dir", printerConnection)).filterByExtension(as).getFileNamesFromProperties();
    }

    public void sendFileContents(String s) throws ZebraPrinterConnectionException {
        InputStream inputstream = null;
        
        if(!printerConnection.isConnected()){ 
            throw new ZebraPrinterConnectionException("Connection is not open.");
        }
        
        try {
        	ZebraFileConnection zebrafileconnection = getFileConnection(s);
            inputstream = ((ZebraFileConnectionImpl)zebrafileconnection).openInputStream();
            int i = zebrafileconnection.fileSize();
            int k, j;
            
            while(i > 0){
            	if(i > 4096 ){
            		j = 4096;
            	} else {
            		j = i;
            	}
            	
            	byte abyte0[] = new byte[j];
            	k = inputstream.read(abyte0);
            	printerConnection.write(abyte0, 0, k);
            	i -= k;
            }
		} catch (Exception e) {
			throw new ZebraPrinterConnectionException(e.getMessage());
		} finally {
			try {
				inputstream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    }

    private static final String FILE_DIR = "file.dir";
    protected ZebraPrinterConnection printerConnection;
}
