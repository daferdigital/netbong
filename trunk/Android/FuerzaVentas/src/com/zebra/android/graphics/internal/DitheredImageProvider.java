// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.zebra.android.graphics.internal:
//            ImageData, ImageDataAndroid

public class DitheredImageProvider
{

    public DitheredImageProvider()
    {
    }

    private static int convertByteToGrayscale(int i) {
        int j = (0xff0000 & i) >>> 16;
        int k = (0xff00 & i) >>> 8;
        int l = i & 0xff;
        int i1 = (j * 30 + k * 59 + l * 11) / 100;
        
        if(i1 <= 255) {
        	if(i1 < 0) {
        		i1 = 0;
        	}
        } else {
        	i1 = 255;
        }
        
        return i1;
    }

    protected static void getDitheredImage(int i, int j, ImageData imagedata, OutputStream outputstream)
        throws IOException
    {
        int ai[] = imagedata.getRow(0);
        int ai1[] = imagedata.getRow(1);
        int k = i / 8;
        int l;
        int i1;
        int j1;
        byte abyte0[];
        byte byte0;
        if(i % 8 == 0)
            l = 0;
        else
            l = 1;
        i1 = k + l;
        j1 = 8 - i % 8;
        if(j1 == 8)
            j1 = 0;
        abyte0 = new byte[i1];
        byte0 = 0;
        for(int k1 = 0; k1 < i; k1++)
            ai[k1] = convertByteToGrayscale(ai[k1]);

        for(int l1 = 0; l1 < j; l1++)
        {
            for(int i2 = 0; i2 < abyte0.length; i2++)
                abyte0[i2] = 0;

            int j2 = 0;
            int k2 = 0;
            while(k2 < i) 
            {
                if(k2 % 8 == 0)
                    byte0 = -128;
                int l2 = ai[k2];
                j2 = k2 / 8;
                byte byte1;
                int i3;
                if(l2 >= 128)
                    byte1 = -1;
                else
                    byte1 = 0;
                abyte0[j2] = (byte)(abyte0[j2] | byte0 & byte1);
                i3 = l2 - (byte1 & 0xff);
                if(k2 < i - 1)
                    ai[k2 + 1] = ai[k2 + 1] + (i3 * 7) / 16;
                if(k2 > 0 && l1 < j - 1)
                    ai1[k2 - 1] = ai1[k2 - 1] + (i3 * 3) / 16;
                if(l1 < j - 1)
                {
                    if(k2 == 0)
                        ai1[k2] = convertByteToGrayscale(ai1[k2]);
                    ai1[k2] = ai1[k2] + (i3 * 5) / 16;
                }
                if(l1 < j - 1 && k2 < i - 1)
                {
                    ai1[k2 + 1] = convertByteToGrayscale(ai1[k2 + 1]);
                    ai1[k2 + 1] = ai1[k2 + 1] + (i3 * 1) / 16;
                }
                byte0 = (byte)((byte0 & 0xff) >>> 1);
                k2++;
            }
            abyte0[j2] = (byte)(abyte0[j2] | 255 >>> 8 - j1);
            outputstream.write(abyte0);
            ai = ai1;
            ai1 = imagedata.getRow(l1 + 2);
        }

    }

    public static void getDitheredImage(Bitmap bitmap, OutputStream outputstream)
        throws IOException
    {
        getDitheredImage(bitmap.getWidth(), bitmap.getHeight(), ((ImageData) (new ImageDataAndroid(bitmap))), outputstream);
    }
}
