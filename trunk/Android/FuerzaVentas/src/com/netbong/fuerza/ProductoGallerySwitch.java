// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza;

import com.netbong.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class ProductoGallerySwitch extends Activity
{
    public class ImageAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return imageIDs.length;
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ImageView imageview = new ImageView(context);
            imageview.setImageResource(imageIDs[i].intValue());
            imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
            imageview.setLayoutParams(new android.widget.Gallery.LayoutParams(150, 120));
            imageview.setBackgroundResource(itemBackground);
            return imageview;
        }

        private Context context;
        private int itemBackground;
        final ProductoGallerySwitch this$0;

        public ImageAdapter(Context context1)
        {
        	super();
        	this$0 = ProductoGallerySwitch.this;
            context = context1;
            //TypedArray typedarray = obtainStyledAttributes(com.ehp.R.array.Gallery1);
            TypedArray typedarray = obtainStyledAttributes(new int[]{0x101004c});
            itemBackground = typedarray.getResourceId(0, 0);
            typedarray.recycle();
        }
    }


    public ProductoGallerySwitch()
    {
        Integer ainteger[] = new Integer[1];
        ainteger[0] = Integer.valueOf(0x7f02001f);
        imageIDs = ainteger;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.pedido_seleccion_producto_layout);
        setContentView(R.layout.pedido_gallery_switch_producto);
        Gallery gallery = (Gallery)findViewById(0x7f06010f);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                ((ImageView)findViewById(0x7f060110)).setImageResource(imageIDs[i].intValue());
            }
        }
);
    }

    Integer imageIDs[];
}
