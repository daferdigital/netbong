// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import android.graphics.Bitmap;
import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import com.zebra.android.graphics.internal.*;
import com.zebra.android.printer.ZebraIllegalArgumentException;
import com.zebra.android.util.internal.FileUtilities;
import java.io.*;

// Referenced classes of package com.zebra.android.printer.internal:
//            GraphicsUtilA, RleEncodedImage

public class GraphicsUtilCpcl extends GraphicsUtilA
{

    public GraphicsUtilCpcl(ZebraPrinterConnection zebraprinterconnection)
    {
        printerConnection = zebraprinterconnection;
    }

    private static String getCorrectedFileName(String s)
        throws ZebraIllegalArgumentException
    {
        String s1 = FileUtilities.parseDriveAndExtension(s).getFileName();
        return (new StringBuilder()).append(s1).append(".PCX").toString();
    }

    private static int getWidthOfImage(int i)
    {
        return (i + 7) / 8;
    }

    public byte[] createPcxHeader(int i, int j)
        throws IOException
    {
        byte abyte0[] = new byte[4];
        abyte0[0] = 10;
        abyte0[1] = 5;
        abyte0[2] = 1;
        abyte0[3] = 1;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bytearrayoutputstream.write(abyte0);
        byte abyte1[] = new byte[4];
        abyte1[0] = 0;
        abyte1[1] = 0;
        abyte1[2] = 0;
        abyte1[3] = 0;
        bytearrayoutputstream.write(abyte1);
        bytearrayoutputstream.write(integerToLittleEndianByteArray(i - 1));
        bytearrayoutputstream.write(integerToLittleEndianByteArray(j - 1));
        byte abyte2[] = new byte[4];
        abyte2[0] = -56;
        abyte2[1] = 0;
        abyte2[2] = -56;
        abyte2[3] = 0;
        bytearrayoutputstream.write(abyte2);
        for(int k = 0; k < 48; k++)
            bytearrayoutputstream.write(0);

        bytearrayoutputstream.write(0);
        bytearrayoutputstream.write(1);
        int l = getWidthOfImage(i);
        bytearrayoutputstream.write(integerToLittleEndianByteArray((char)(l + (char)(l % 2))));
        byte abyte3[] = new byte[2];
        abyte3[0] = 0;
        abyte3[1] = 0;
        bytearrayoutputstream.write(abyte3);
        for(int i1 = 0; i1 < 58; i1++)
            bytearrayoutputstream.write(0);

        return bytearrayoutputstream.toByteArray();
    }

    public byte[] createPcxImage(int i, int j, Bitmap bitmap)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DitheredImageProvider.getDitheredImage(bitmap, bytearrayoutputstream);
        byte abyte0[] = createPcxImage(i, j, bytearrayoutputstream.toByteArray());
        bytearrayoutputstream.close();
        return abyte0;
    }

    public byte[] createPcxImage(int i, int j, byte abyte0[])
        throws IOException
    {
        int k = getWidthOfImage(i);
        byte abyte1[] = (new RleEncodedImage()).rleEncoding(abyte0, k);
        byte abyte2[] = createPcxHeader(i, j);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bytearrayoutputstream.write(abyte2);
        bytearrayoutputstream.write(abyte1);
        return bytearrayoutputstream.toByteArray();
    }

    byte[] integerToLittleEndianByteArray(int i)
    {
        byte abyte0[] = new byte[2];
        abyte0[0] = (byte)i;
        abyte0[1] = (byte)(0xff & i >>> 8);
        return abyte0;
    }

    public void printImage(Bitmap bitmap, int i, int j, int k, int l, boolean flag)
        throws ZebraPrinterConnectionException
    {
        Bitmap bitmap1 = scaleImage(k, l, bitmap);
        int i1 = (7 + bitmap1.getWidth()) / 8;
        
        String s1;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        String s;
        CompressedBitmapOutputStreamCpcl compressedbitmapoutputstreamcpcl;
        
        if(flag) {
            s = "";
            s1 = "";
        } else {
            s = (new StringBuilder()).append("! 0 200 200 ").append(bitmap1.getHeight()).append(" 1\r\n").toString();
            s1 = "FORM\r\nPRINT\r\n";
        }
        
        try {
        	bytearrayoutputstream.write(s.getBytes());
            bytearrayoutputstream.write("CG ".getBytes());
            bytearrayoutputstream.write(String.valueOf(i1).getBytes());
            bytearrayoutputstream.write(" ".getBytes());
            bytearrayoutputstream.write(String.valueOf(bitmap1.getHeight()).getBytes());
            bytearrayoutputstream.write(" ".getBytes());
            bytearrayoutputstream.write(String.valueOf(i).getBytes());
            bytearrayoutputstream.write(" ".getBytes());
            bytearrayoutputstream.write(String.valueOf(j).getBytes());
            bytearrayoutputstream.write(" ".getBytes());
            printerConnection.write(bytearrayoutputstream.toByteArray());
            compressedbitmapoutputstreamcpcl = new CompressedBitmapOutputStreamCpcl(printerConnection);
            DitheredImageProvider.getDitheredImage(bitmap1, compressedbitmapoutputstreamcpcl);
            compressedbitmapoutputstreamcpcl.close();
            printerConnection.write("\r\n".getBytes());
            printerConnection.write(s1.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			throw new ZebraPrinterConnectionException(e.getMessage());
		}
    }

    public void storeImage(String s, Bitmap bitmap, int i, int j)
        throws ZebraPrinterConnectionException, ZebraIllegalArgumentException
    {
        try
        {
            byte abyte0[] = createPcxImage(i, j, scaleImage(i, j, bitmap));
            String s1 = CpclCrcHeader.getCRC16ForCertificateFilesOnly(abyte0).toUpperCase();
            String s2 = CpclCrcHeader.convertTo8dot3(getCorrectedFileName(s));
            String s3 = CpclCrcHeader.stringPadToPlaces(8, "0", Integer.toHexString(abyte0.length).toUpperCase());
            String s4 = CpclCrcHeader.getWChecksum(abyte0).toUpperCase();
            byte abyte1[] = "\r\n".getBytes();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            bytearrayoutputstream.write("! CISDFCRC16".getBytes(), 0, "! CISDFCRC16".length());
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            bytearrayoutputstream.write(s1.getBytes(), 0, s1.length());
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            bytearrayoutputstream.write(s2.getBytes(), 0, s2.length());
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            bytearrayoutputstream.write(s3.getBytes(), 0, s3.length());
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            bytearrayoutputstream.write(s4.getBytes(), 0, s4.length());
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            bytearrayoutputstream.write(abyte0, 0, abyte0.length);
            bytearrayoutputstream.write(abyte1, 0, abyte1.length);
            printerConnection.write(bytearrayoutputstream.toByteArray());
            bytearrayoutputstream.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new ZebraPrinterConnectionException(ioexception.getMessage());
        }
    }

    protected ZebraPrinterConnection printerConnection;
}
