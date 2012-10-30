// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.printer.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RleEncodedImage
{
    public class DataBuffer
    {

        private int bytesLeft()
        {
            return imageData.length - currentIndex;
        }

        private byte peek()
        {
            return imageData[currentIndex];
        }

        public byte getByte()
        {
            byte abyte0[] = imageData;
            int i = currentIndex;
            currentIndex = i + 1;
            return abyte0[i];
        }

        int currentIndex;
        byte imageData[];
        final RleEncodedImage this$0;



        public DataBuffer(byte abyte0[])
        {
        	super();
        	this$0 = RleEncodedImage.this;
            currentIndex = 0;
            imageData = abyte0;
        }
    }


    public RleEncodedImage()
    {
        outputImageByteStream = new ByteArrayOutputStream();
    }

    private boolean bothUpperBitsAreSet(byte byte0)
    {
        boolean flag;
        if((byte0 & 0xc0) == 192)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private byte[] createRun(int i, byte byte0)
    {
        int j = 0xc0 | (byte) i;
        byte abyte0[] = new byte[2];
        abyte0[0] = (byte) j;
        abyte0[1] = byte0;
        return abyte0;
    }

    private byte[] encodeRun(byte byte0, DataBuffer databuffer)
    {
        int i;
        for(i = 1; databuffer.bytesLeft() > 0 && byte0 == databuffer.peek() && i < 63; i++)
            databuffer.getByte();

        return createRun(i, byte0);
    }

    private byte[] getNextElement(DataBuffer databuffer)
    {
        byte byte0 = databuffer.getByte();
        byte abyte0[];
        if(databuffer.bytesLeft() > 0 && byte0 == databuffer.peek())
            abyte0 = encodeRun(byte0, databuffer);
        else
        if(bothUpperBitsAreSet(byte0))
        {
            abyte0 = createRun(1, byte0);
        } else
        {
            abyte0 = new byte[1];
            abyte0[0] = byte0;
        }
        return abyte0;
    }

    private void outputElement(byte abyte0[])
    {
        outputImageByteStream.write(abyte0, 0, abyte0.length);
    }

    private void rleEncoding(DataBuffer databuffer)
    {
        outputElement(getNextElement(databuffer));
        if(databuffer.bytesLeft() > 0)
            rleEncoding(databuffer);
    }

    public byte[] rleEncoding(byte abyte0[], int i)
        throws IOException
    {
        boolean flag;
        int j;
        if(i % 2 != 0)
            flag = true;
        else
            flag = false;
        j = 0;
        while(j < abyte0.length) 
        {
            int k = abyte0.length - j;
            int l;
            byte abyte2[];
            if(k < i)
                l = k;
            else
                l = i;
            if(flag)
            {
                abyte2 = new byte[l + 1];
                for(int i1 = 0; i1 < l; i1++)
                    abyte2[i1] = abyte0[j + i1];

                abyte2[l] = 0;
            } else
            {
                abyte2 = new byte[l];
                System.arraycopy(abyte0, j, abyte2, 0, abyte2.length);
            }
            rleEncoding(new DataBuffer(abyte2));
            j += i;
        }
        byte abyte1[] = outputImageByteStream.toByteArray();
        outputImageByteStream.close();
        return abyte1;
    }

    private ByteArrayOutputStream outputImageByteStream;
}
