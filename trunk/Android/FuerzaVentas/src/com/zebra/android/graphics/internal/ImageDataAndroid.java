// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;

import android.graphics.Bitmap;

// Referenced classes of package com.zebra.android.graphics.internal:
//            ImageData

public class ImageDataAndroid
    implements ImageData
{

    public ImageDataAndroid(Bitmap bitmap1)
    {
        bitmap = bitmap1;
    }

    public int[] getRow(int i)
    {
        int ai[];
        if(i >= bitmap.getHeight())
        {
            ai = null;
        } else
        {
            int j = bitmap.getWidth();
            ai = new int[j];
            bitmap.getPixels(ai, 0, j, 0, i, j, 1);
        }
        return ai;
    }

    private Bitmap bitmap;
}
