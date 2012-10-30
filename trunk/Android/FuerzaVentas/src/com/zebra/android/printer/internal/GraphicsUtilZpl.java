// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import android.graphics.Bitmap;
import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.graphics.internal.CompressedBitmapOutputStreamZpl;
import com.zebra.android.graphics.internal.DitheredImageProvider;
import com.zebra.android.printer.ZebraIllegalArgumentException;
import com.zebra.android.util.internal.*;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.zebra.android.printer.internal:
//            GraphicsUtilA

public class GraphicsUtilZpl extends GraphicsUtilA
{

    public GraphicsUtilZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
    }

    private static String getCorrectedFileName(String s)
        throws ZebraIllegalArgumentException
    {
        PrinterFilePath printerfilepath = FileUtilities.parseDriveAndExtension(s);
        String s1 = printerfilepath.getDrive();
        String s2 = printerfilepath.getFileName();
        if(s1 == null || s1.length() == 0)
            s1 = "E";
        else
        if(s1.length() > 1)
            throw new ZebraIllegalArgumentException((new StringBuilder()).append("Invalid drive specified : ").append(s1).toString());
        return (new StringBuilder()).append(s1).append(":").append(s2).append(".GRF").toString();
    }

    private void sendBitmapToPrinter(Bitmap bitmap, String s)
        throws ZebraPrinterConnectionException
    {
    	CompressedBitmapOutputStreamZpl compressedbitmapoutputstreamzpl = null;
        CompressedBitmapOutputStreamZpl compressedbitmapoutputstreamzpl1 = null;
        
    	try {
    		printerConnection.write(s.getBytes());
            compressedbitmapoutputstreamzpl1 = new CompressedBitmapOutputStreamZpl(printerConnection);
            DitheredImageProvider.getDitheredImage(bitmap, compressedbitmapoutputstreamzpl1);
            
		} catch (Exception e) {
			// TODO: handle exception
			throw new ZebraPrinterConnectionException(e.getMessage());
		} finally {
			try {
				compressedbitmapoutputstreamzpl1.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    }

    public void printImage(Bitmap bitmap, int i, int j, int k, int l, boolean flag)
        throws ZebraPrinterConnectionException
    {
        Bitmap bitmap1 = scaleImage(k, l, bitmap);
        int i1 = (7 + bitmap1.getWidth()) / 8;
        int j1 = i1 * bitmap1.getHeight();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("^FO");
        stringbuffer.append(i);
        stringbuffer.append(",");
        stringbuffer.append(j);
        stringbuffer.append("^GFA");
        stringbuffer.append(",");
        stringbuffer.append(j1);
        stringbuffer.append(",");
        stringbuffer.append(j1);
        stringbuffer.append(",");
        stringbuffer.append(i1);
        stringbuffer.append(",");
        String s = stringbuffer.toString();
        if(!flag)
            s = (new StringBuilder()).append("^XA").append(s).toString();
        String s1 = ZPLUtilities.decorateWithFormatPrefix(s);
        printerConnection.write(s1.getBytes());
        CompressedBitmapOutputStreamZpl compressedbitmapoutputstreamzpl = new CompressedBitmapOutputStreamZpl(printerConnection);
        try
        {
            DitheredImageProvider.getDitheredImage(bitmap1, compressedbitmapoutputstreamzpl);
            compressedbitmapoutputstreamzpl.close();
        }
        catch(IOException ioexception)
        {
            throw new ZebraPrinterConnectionException(ioexception.getMessage());
        }
        if(!flag)
        {
            String s2 = ZPLUtilities.decorateWithFormatPrefix("^XZ");
            printerConnection.write(s2.getBytes());
        }
    }

    public void storeImage(String s, Bitmap bitmap, int i, int j)
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException
    {
        if(bitmap == null)
        {
            throw new ZebraIllegalArgumentException("Invalid image file.");
        } else
        {
            Bitmap bitmap1 = scaleImage(i, j, bitmap);
            int k = (7 + bitmap1.getWidth()) / 8;
            int l = k * bitmap1.getHeight();
            String s1 = getCorrectedFileName(s);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("~DG");
            stringbuilder.append(s1);
            stringbuilder.append(",");
            stringbuilder.append(l);
            stringbuilder.append(",");
            stringbuilder.append(k);
            stringbuilder.append(",");
            sendBitmapToPrinter(bitmap1, ZPLUtilities.decorateWithCommandPrefix(stringbuilder.toString()));
            return;
        }
    }

    protected ZebraPrinterConnection printerConnection;
}
