// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.sincronizar;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.ehp.droidsf.sync:
//            HttpSync

public class SyncImagenes
{

    public SyncImagenes()
    {
    }

    public static final boolean descargar(String s)
        throws UnsupportedEncodingException, IOException
    {
        String as[] = s.split("\r\n");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return true;
            String s1 = as[j];
            Log.i("imagen-", s1);
            HttpSync.descargarImagen(s1);
            j++;
        } while(true);
    }
}
