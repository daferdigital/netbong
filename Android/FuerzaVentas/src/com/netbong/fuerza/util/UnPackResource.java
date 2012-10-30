// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.netbong.fuerza.MainActivity;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnPackResource {

    public UnPackResource()
    {
    }

    public static void unpackCatalogo(Context context, String s)
        throws FileNotFoundException, IOException {
        InputStream inputstream = context.getAssets().open(s);
        
        if(inputstream != null){
        	ZipInputStream zipinputstream = new ZipInputStream(
        			new BufferedInputStream(inputstream, 8192));
        	String s1 = null;
        	File file = new File(Environment.getExternalStorageDirectory(), "droidsf");
            
        	if(!file.exists()){
        		file.mkdir();
        	}
            
            s1 = file.getAbsolutePath();
            
            ZipEntry zipentry = null;
            while((zipentry = zipinputstream.getNextEntry()) != null){
            	BufferedOutputStream bufferedoutputstream;
                byte[] abyte0 = new byte[8192];;
                bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(new File((new StringBuilder(String.valueOf(s1))).append("/").append(zipentry.getName()).toString())), 8192);
                
                
                int i;
                while((i = zipinputstream.read(abyte0, 0, 8192)) != -1){
                	bufferedoutputstream.write(abyte0, 0, i);
                }
                bufferedoutputstream.flush();
                bufferedoutputstream.close();
            }
            
            zipinputstream.close();
        }
    }

    public static void unpackDatabase(Context context, String s)
        throws FileNotFoundException, IOException
    {
        java.io.InputStream inputstream = context.getAssets().open(s);
        
        if(inputstream != null) {
        	ZipInputStream zipinputstream = new ZipInputStream(new BufferedInputStream(inputstream, 8192));
            zipinputstream.getNextEntry();
            File file = new File(context.getDir("DB_DIR", 0), "sfdroid.sqlite");
            Log.i("ruta db", file.getAbsolutePath());
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(file), 8192);
            byte abyte0[] = new byte[8192];
            int i = -1;
            
            while((i = zipinputstream.read(abyte0, 0, 8192)) != -1){
            	bufferedoutputstream.write(abyte0, 0, i);
            }
            
            bufferedoutputstream.flush();
            bufferedoutputstream.close();
            zipinputstream.close();
            MainActivity.settings.edit().putString("db-path", file.getAbsolutePath()).commit();
        }
    }
}
