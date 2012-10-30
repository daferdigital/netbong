// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;

import com.zebra.android.comm.ZebraPrinterConnection;
import com.zebra.android.comm.ZebraPrinterConnectionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.zebra.android.graphics.internal:
//            CompressedBitmapOutputStreamA

public class CompressedBitmapOutputStreamZpl extends CompressedBitmapOutputStreamA
{

    public CompressedBitmapOutputStreamZpl(ZebraPrinterConnection zebraprinterconnection)
    {
        previousByteWritten = 0;
        previousByteWrittenRepeatCount = 0;
        printerConnection = zebraprinterconnection;
        internalEncodedBuffer = new ByteArrayOutputStream();
    }

    private void computeAndOutput()
        throws IOException, ZebraPrinterConnectionException
    {
        if(previousByteWrittenRepeatCount > 1)
        {
            int i = previousByteWrittenRepeatCount / 400;
            int j = previousByteWrittenRepeatCount % 400;
            for(int k = 0; k < i; k++)
                bufferAndWrite('z');

            for(int l = 0; l < charMap.length; l++)
                if(j >= charMap[l])
                {
                    bufferAndWrite(charVal[l]);
                    j -= charMap[l];
                }

        }
        bufferAndWrite(Integer.toHexString(0xf & previousByteWritten).toUpperCase().charAt(0));
    }

    private byte[] extractNibblesFromByte(byte byte0) {
        byte abyte0[] = new byte[2];
        abyte0[0] = (byte) (0xf & ~byte0 >> 4);
        abyte0[1] = (byte) (0xf & ~byte0);
        return abyte0;
    }

    private void sendBufferedDataToPrinter()
        throws IOException
    {
        try
        {
            computeAndOutput();
            return;
        }
        catch(ZebraPrinterConnectionException zebraprinterconnectionexception)
        {
            throw new IOException(zebraprinterconnectionexception.getMessage());
        }
    }

    private void writeNibbleToStream(byte byte0)
        throws IOException
    {
        if(previousByteWrittenRepeatCount == 0)
        {
            previousByteWritten = byte0;
            previousByteWrittenRepeatCount = 1 + previousByteWrittenRepeatCount;
        } else
        if(previousByteWritten == byte0)
        {
            previousByteWrittenRepeatCount = 1 + previousByteWrittenRepeatCount;
        } else
        {
            sendBufferedDataToPrinter();
            previousByteWritten = byte0;
            previousByteWrittenRepeatCount = 1;
        }
    }

    private void writeNibblesToStream(byte abyte0[])
        throws IOException
    {
        for(int i = 0; i < abyte0.length; i++)
            writeNibbleToStream(abyte0[i]);

    }

    public void flush()
        throws IOException
    {
        if(previousByteWrittenRepeatCount > 0)
        {
            sendBufferedDataToPrinter();
            previousByteWrittenRepeatCount = 0;
        }
        super.flush();
    }

    public void write(byte abyte0[])
        throws IOException
    {
        for(int i = 0; i < abyte0.length; i++)
            writeNibblesToStream(extractNibblesFromByte(abyte0[i]));

    }

    private static final int charMap[];
    private static final char charVal[];
    private byte previousByteWritten;
    private int previousByteWrittenRepeatCount;

    static 
    {
        int ai[] = new int[38];
        ai[0] = 380;
        ai[1] = 360;
        ai[2] = 340;
        ai[3] = 320;
        ai[4] = 300;
        ai[5] = 280;
        ai[6] = 260;
        ai[7] = 240;
        ai[8] = 220;
        ai[9] = 200;
        ai[10] = 180;
        ai[11] = 160;
        ai[12] = 140;
        ai[13] = 120;
        ai[14] = 100;
        ai[15] = 80;
        ai[16] = 60;
        ai[17] = 40;
        ai[18] = 20;
        ai[19] = 19;
        ai[20] = 18;
        ai[21] = 17;
        ai[22] = 16;
        ai[23] = 15;
        ai[24] = 14;
        ai[25] = 13;
        ai[26] = 12;
        ai[27] = 11;
        ai[28] = 10;
        ai[29] = 9;
        ai[30] = 8;
        ai[31] = 7;
        ai[32] = 6;
        ai[33] = 5;
        ai[34] = 4;
        ai[35] = 3;
        ai[36] = 2;
        ai[37] = 1;
        charMap = ai;
        char ac[] = new char[38];
        ac[0] = 'y';
        ac[1] = 'x';
        ac[2] = 'w';
        ac[3] = 'v';
        ac[4] = 'u';
        ac[5] = 't';
        ac[6] = 's';
        ac[7] = 'r';
        ac[8] = 'q';
        ac[9] = 'p';
        ac[10] = 'o';
        ac[11] = 'n';
        ac[12] = 'm';
        ac[13] = 'l';
        ac[14] = 'k';
        ac[15] = 'j';
        ac[16] = 'i';
        ac[17] = 'h';
        ac[18] = 'g';
        ac[19] = 'Y';
        ac[20] = 'X';
        ac[21] = 'W';
        ac[22] = 'V';
        ac[23] = 'U';
        ac[24] = 'T';
        ac[25] = 'S';
        ac[26] = 'R';
        ac[27] = 'Q';
        ac[28] = 'P';
        ac[29] = 'O';
        ac[30] = 'N';
        ac[31] = 'M';
        ac[32] = 'L';
        ac[33] = 'K';
        ac[34] = 'J';
        ac[35] = 'I';
        ac[36] = 'H';
        ac[37] = 'G';
        charVal = ac;
    }
}
