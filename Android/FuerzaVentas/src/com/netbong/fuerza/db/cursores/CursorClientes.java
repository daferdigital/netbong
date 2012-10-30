// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

// Referenced classes of package com.ehp.droidsf.db:
//            DroidSFDatabase

public class CursorClientes extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorClientes(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }

    public enum SortBy
    {
    	nombre(0),
        _id(1);
        
        private SortBy(int i){
            this.i = i;
        }
        
        final int i;
    }


    private CursorClientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    CursorClientes(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorClientes cursorclientes)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    public static CursorClientes getCliente(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(i);
        String s = String.format("select id_profitplus, nombre, rif, direccion_fiscal, direccion_envio_mercancia, contacto_persona, contacto_telefono, contacto_fax, contacto_correo, limite_credito, dias_caja from Clientes where _id = %d ", aobj);
        CursorClientes cursorclientes = (CursorClientes)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientes.moveToFirst();
        return cursorclientes;
    }

    public static CursorClientes getClientes(SQLiteDatabase sqlitedatabase, SortBy sortby)
    {
        String s = (new StringBuilder("SELECT _id, id_profitplus, nombre, direccion_fiscal, contacto_persona, limite_credito FROM Clientes  ORDER BY ")).append(sortby.toString()).toString();
        CursorClientes cursorclientes = (CursorClientes)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorclientes.moveToFirst();
        return cursorclientes;
    }

    public static CursorClientes getClientes(SQLiteDatabase sqlitedatabase, String s, SortBy sortby)
    {
        String s1 = (new StringBuilder("SELECT _id, id_profitplus, nombre, direccion_fiscal, contacto_persona, limite_credito FROM Clientes  WHERE like('%")).append(s).append("%', nombre) or like('%").append(s).append("%', id_profitplus) ").append(" ORDER BY ").append(sortby.toString()).toString();
        CursorClientes cursorclientes = (CursorClientes)sqlitedatabase.rawQueryWithFactory(new Factory(null), s1, null, null);
        cursorclientes.moveToFirst();
        return cursorclientes;
    }

    public static CursorClientes getClientesNoSync()
    {
        CursorClientes cursorclientes = (CursorClientes)MainActivity.mDbHelper.getWritableDatabase().rawQueryWithFactory(new Factory(null), "select id_profitplus, _id, nombre, rif, direccion_fiscal, direccion_envio_mercancia, contacto_persona, contacto_telefono, contacto_fax, contacto_correo, limite_credito, dias_caja, id_zona_cliente, id_tipo_cliente, observaciones from Clientes where sync = 0 ", null, null);
        cursorclientes.moveToFirst();
        return cursorclientes;
    }

    private String getTramaCliente()
    {
        Object aobj[] = new Object[13];
        aobj[0] = Integer.valueOf(getIdZona());
        aobj[1] = Integer.valueOf(getIdTipoCliente());
        aobj[2] = getNombre();
        aobj[3] = getRif();
        aobj[4] = getDireccion();
        aobj[5] = getDireccionMercancia();
        aobj[6] = getContacto();
        aobj[7] = getContactoTelefono();
        aobj[8] = getContactoFax();
        aobj[9] = getContactoCorreo();
        aobj[10] = getDiasCaja();
        aobj[11] = getObservaciones();
        aobj[12] = Integer.valueOf(getId());
        return String.format("%d;;%d;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%d", aobj);
    }

    public String getCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public String getContacto()
    {
        return getString(getColumnIndexOrThrow("contacto_persona"));
    }

    public String getContactoCorreo()
    {
        return getString(getColumnIndexOrThrow("contacto_correo"));
    }

    public String getContactoFax()
    {
        return getString(getColumnIndexOrThrow("contacto_fax"));
    }

    public String getContactoTelefono()
    {
        return getString(getColumnIndexOrThrow("contacto_telefono"));
    }

    public String getDiasCaja()
    {
        return getString(getColumnIndexOrThrow("dias_caja"));
    }

    public String getDireccion()
    {
        return getString(getColumnIndexOrThrow("direccion_fiscal"));
    }

    public String getDireccionMercancia()
    {
        return getString(getColumnIndexOrThrow("direccion_envio_mercancia"));
    }

    public int getId()
    {
        return getInt(getColumnIndexOrThrow("_id"));
    }

    public int getIdTipoCliente()
    {
        return getInt(getColumnIndexOrThrow("id_tipo_cliente"));
    }

    public int getIdZona()
    {
        return getInt(getColumnIndexOrThrow("id_zona_cliente"));
    }

    public double getLimiteCredito()
    {
        return getDouble(getColumnIndexOrThrow("limite_credito"));
    }

    public String getNombre()
    {
        return getString(getColumnIndexOrThrow("nombre"));
    }

    public String getObservaciones()
    {
        return getString(getColumnIndexOrThrow("observaciones"));
    }

    public String getRif()
    {
        return getString(getColumnIndexOrThrow("rif"));
    }

    public String getTramaClientesNoSync(int i)
    {
        moveToPosition(i);
        return (new StringBuilder(MainActivity.settings.getString("login", MainActivity.getDefaultLogin()))).append("==").append(getTramaCliente()).toString();
    }

    public static final String COLUMNA_CODIGO = "id_profitplus";
    public static final String COLUMNA_CONTACTO = "contacto_persona";
    public static final String COLUMNA_CONTACTO_CORREO = "contacto_correo";
    public static final String COLUMNA_CONTACTO_FAX = "contacto_fax";
    public static final String COLUMNA_CONTACTO_TELEFONO = "contacto_telefono";
    public static final String COLUMNA_DIAS_CAJA = "dias_caja";
    public static final String COLUMNA_DIRECCION = "direccion_fiscal";
    public static final String COLUMNA_DIRECCION_MERCANCIA = "direccion_envio_mercancia";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_ID_TIPO_CLIENTE = "id_tipo_cliente";
    public static final String COLUMNA_ID_ZONA = "id_zona_cliente";
    public static final String COLUMNA_LIMITE_CREDITO = "limite_credito";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_OBSERVACIONES = "observaciones";
    public static final String COLUMNA_RIF = "rif";
    private static final String QUERY = "SELECT _id, id_profitplus, nombre, direccion_fiscal, contacto_persona, limite_credito FROM Clientes ";
    private static final String QUERY_DATOS_CLIENTE = "select id_profitplus, nombre, rif, direccion_fiscal, direccion_envio_mercancia, contacto_persona, contacto_telefono, contacto_fax, contacto_correo, limite_credito, dias_caja from Clientes where _id = %d ";
    private static final String QUERY_DATOS_CLIENTE_NO_SYNC = "select id_profitplus, _id, nombre, rif, direccion_fiscal, direccion_envio_mercancia, contacto_persona, contacto_telefono, contacto_fax, contacto_correo, limite_credito, dias_caja, id_zona_cliente, id_tipo_cliente, observaciones from Clientes where sync = 0 ";
    private static final String QUERY_ORDER = " ORDER BY ";
}
