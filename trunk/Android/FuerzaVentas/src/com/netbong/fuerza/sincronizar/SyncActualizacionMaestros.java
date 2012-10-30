// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.sincronizar;

import android.util.Log;

import com.netbong.fuerza.MainActivity;

public class SyncActualizacionMaestros
{

    public SyncActualizacionMaestros()
    {
    }

    public static final boolean actualizar(String s)
    {
        String as[] = s.split("\r\n");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return true;
            String s1 = construitStatement(as[j]);
            String s2;
            if(s1 == null)
                s2 = "no implementado";
            else
                s2 = s1;
            Log.i("sync-sql", s2);
            if(s1 != null)
                MainActivity.mDbHelper.execSQL(s1);
            j++;
        } while(true);
    }

    private static String construirDeleteStatement(String as[])
    {
        String as1[] = as[3].split("##");
        sb.delete(0, sb.length());
        sb.append("DELETE FROM ").append(as[2]);
        int i = as1.length;
        sb.append(" WHERE ");
        int j = 0;
        do
        {
            if(j >= i)
                return sb.toString();
            StringBuilder stringbuilder = sb.append(as1[j]);
            String s;
            if(j == i - 1)
                s = "";
            else
                s = " AND ";
            stringbuilder.append(s);
            j++;
        } while(true);
    }

    private static String construirInsertStatement(String as[])
    {
        String as1[] = as[3].split("##");
        String s;
        if(as[2].equalsIgnoreCase("Vendedor_x_linea"))
            s = insertVendedoresLinea(as1);
        else
        if(as[2].equalsIgnoreCase("Promociones"))
            s = insertPromociones(as1);
        else
        if(as[2].equalsIgnoreCase("Promociones_Productos"))
            s = insertPromocionesProductos(as1);
        else
        if(as[2].equalsIgnoreCase("imagenes_producto"))
            s = insertImagenesProducto(as1);
        else
            s = null;
        return s;
    }

    private static String construirUpdateStatement(String as[]) {
        String[] as1 = as[3].split("##");
        String[] as2 = as[4].split("##");
        int i = as1.length, j = 0;
        int k = 0, l;
        
        sb.delete(0, sb.length());
        sb.append("UPDATE ").append(as[2]);
        sb.append(" SET ");
        
        for (j = 0; j < as1.length; j++) {
        	sb.append(as1[j]);
            
        	String s;
            if(j == i - 1) {
                s = "";
            } else {
                s = ", ";
            }
            
            sb.append(s);
		}
        
        sb.append(" WHERE ");
        
        for (l = 0; l < as2.length; l++) {
        	sb.append(as2[l]);
            
        	String s1;
            if(l == k - 1) {
                s1 = "";
            } else {
                s1 = " AND ";
            }
            sb.append(s1);
		}
        
        return sb.toString();
    }

    private static final String construitStatement(String s) {
        String[] as = s.split(";");
        String s1 = null;
        Log.i("script", s);
        
        if(!as[1].equalsIgnoreCase("1")) {
        	if(as[1].equalsIgnoreCase("2")) {
        		s1 = construirInsertStatement(as);
        	} else if(as[1].equalsIgnoreCase("3")) {
                s1 = construirDeleteStatement(as);
        	} else if(as[1].equalsIgnoreCase("4")) {
                s1 = inactivarRegistro(as);
        	}
        } else {
        	s1 = construirUpdateStatement(as);
        }
        
        return s1;
    }

    private static String inactivarRegistro(String as[])
    {
        sb.delete(0, sb.length());
        sb.append("UPDATE ").append(as[2]);
        sb.append("SET activo = 0 WHERE _id = ").append(as[3]);
        return sb.toString();
    }

    private static String insertImagenesProducto(String as[])
    {
        sb.delete(0, sb.length());
        sb.append("INSERT INTO imagenes_producto(id_producto, imagen) values(");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return sb.toString();
            StringBuilder stringbuilder = sb.append(as[j]);
            String s;
            if(j == i - 1)
                s = ");";
            else
                s = ", ";
            stringbuilder.append(s);
            j++;
        } while(true);
    }

    private static String insertPromociones(String as[])
    {
        sb.delete(0, sb.length());
        sb.append("INSERT INTO Promociones(_id, descripcion, vigencia, activo) values(");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return sb.toString();
            StringBuilder stringbuilder = sb.append(as[j]);
            String s;
            if(j == i - 1)
                s = ", 0);";
            else
                s = ", ";
            stringbuilder.append(s);
            j++;
        } while(true);
    }

    private static String insertPromocionesProductos(String as[])
    {
        sb.delete(0, sb.length());
        sb.append("INSERT INTO Promociones_Productos(id_promocion, id_producto, precio, cantidad, iva) values(");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return sb.toString();
            StringBuilder stringbuilder = sb.append(as[j]);
            String s;
            if(j == i - 1)
                s = ");";
            else
                s = ", ";
            stringbuilder.append(s);
            j++;
        } while(true);
    }

    private static String insertVendedoresLinea(String as[])
    {
        sb.delete(0, sb.length());
        sb.append("INSERT INTO Vendedor_x_linea(id_linea) values(");
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return sb.toString();
            StringBuilder stringbuilder = sb.append(as[j]);
            String s;
            if(j == i - 1)
                s = ");";
            else
                s = ", ";
            stringbuilder.append(s);
            j++;
        } while(true);
    }

    private static final int DELETE_INDICE_FILTROS = 3;
    private static final int DELETE_INDICE_TABLA = 2;
    private static final int INACTIVAR_INDICE_FILTRO = 3;
    private static final int INACTIVAR_INDICE_TABLE = 2;
    private static final int INSERT_INDICE_TABLA = 2;
    private static final int INSERT_INDICE_VALORES = 3;
    private static final int UPDATE_INDICE_CAMPOS = 3;
    private static final int UPDATE_INDICE_FILTROS = 4;
    private static final int UPDATE_INDICE_TABLA = 2;
    private static StringBuilder sb = new StringBuilder();

}
