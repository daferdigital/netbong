// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.netbong.fuerza.db.cursores;

import android.database.Cursor;
import android.database.sqlite.*;

import com.netbong.fuerza.MainActivity;

public class CursorPrintDatosPedido extends SQLiteCursor
{
    private static class Factory
        implements android.database.sqlite.SQLiteDatabase.CursorFactory
    {

        public Cursor newCursor(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
        {
            return new CursorPrintDatosPedido(sqlitedatabase, sqlitecursordriver, s, sqlitequery, null);
        }

        private Factory()
        {
        }

        Factory(Factory factory)
        {
            this();
        }
    }


    private CursorPrintDatosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery)
    {
        super(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
        avanzarLinea = true;
    }

    CursorPrintDatosPedido(SQLiteDatabase sqlitedatabase, SQLiteCursorDriver sqlitecursordriver, String s, SQLiteQuery sqlitequery, CursorPrintDatosPedido cursorprintdatospedido)
    {
        this(sqlitedatabase, sqlitecursordriver, s, sqlitequery);
    }

    private void agregarLinea(StringBuilder stringbuilder, String s)
    {
        if(avanzarLinea)
            line = 24 + line;
        avanzarLinea = true;
    }

    private void cerrarArchivoImpresion(StringBuilder stringbuilder)
    {
        stringbuilder.append("! U1 END-PAGE").append("\r\n");
    }

    public static CursorPrintDatosPedido getDatosPedido(SQLiteDatabase sqlitedatabase, int i)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Integer.valueOf(i);
        String s = String.format("select cast(c.nombre as CHARACTER(30)) as nombre, c.rif, c.id_profitplus cod_cliente, pd.id_pedido, pro.id_profitplus, pro.nombre as nombre_producto, pd.precio, pd.cantidad, pd.iva, pd.total_iva_gravament as item_iva_monto,pd.total_excento + pd.total_gravament + pd.total_iva_gravament as item_subtotal, p.total_iva_gravament as iva_monto, p.total_gravament as gravament, p.total_excento as excento, date(p.fecha) as fecha, time(p.fecha) as hora, p.sync from Pedidos p, Pedidos_Detalle pd, Clientes c, Productos pro where p._id = %d and pd.id_pedido = %d and p.id_cliente = c._id and pd.id_producto = pro._id ", aobj);
        CursorPrintDatosPedido cursorprintdatospedido = (CursorPrintDatosPedido)sqlitedatabase.rawQueryWithFactory(new Factory(null), s, null, null);
        cursorprintdatospedido.moveToFirst();
        return cursorprintdatospedido;
    }

    private String lineaCentrada(String s, int i)
    {
        String s2;
        if(s.length() > i)
        {
            s2 = s.substring(0, i);
        } else
        {
            String s1 = "                                                     ".substring(0, (i - s.length()) / 2);
            s2 = (new StringBuilder(String.valueOf(s1))).append(s).append(s1).toString();
        }
        return s2;
    }

    private String lineaDerecha(String s, int i)
    {
        String s1;
        if(s.length() > i)
            s1 = s.substring(0, i);
        else
            s1 = (new StringBuilder(String.valueOf("                                                     ".substring(0, i - s.length())))).append(s).toString();
        return s1;
    }

    private String lineaIzquierda(String s, int i)
    {
        String s1;
        if(s.length() > i)
            s1 = s.substring(0, i);
        else
            s1 = (new StringBuilder(String.valueOf(s))).append("                                                     ".substring(0, i - s.length())).toString();
        return s1;
    }

    private String lineaIzquierdaDerecha(String s, int i)
    {
        StringBuilder stringbuilder = new StringBuilder(s);
        StringBuilder stringbuilder1 = new StringBuilder();
        do
        {
            if(stringbuilder.length() <= 0)
                return stringbuilder1.toString();
            if(stringbuilder.length() < i)
            {
                stringbuilder1.append(stringbuilder).append("\r\n");
                stringbuilder.delete(0, stringbuilder.length());
            } else
            {
                stringbuilder1.append(stringbuilder.substring(0, i - 1)).append("\r\n");
                stringbuilder.delete(0, i - 1);
            }
        } while(true);
    }

    private void printCabeceraDatosClientePrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append(lineaCentrada((new StringBuilder("CLIENTE: ")).append(getClienteCodigo()).append(" / ").append("RIF: ").append(getClienteRif()).toString(), 45)).append("\r\n");
        stringbuilder.append(lineaCentrada(getClienteNombre().trim(), 45)).append("\r\n").append("\r\n");
    }

    private void printCabeceraDatosEmpresa(StringBuilder stringbuilder)
    {
        stringbuilder.append("! 0 200 200 210 1").append("\r\n");
        stringbuilder.append("BOX 30 10 550 200 2").append("\r\n");
        stringbuilder.append("CENTER").append("\r\n");
        Object aobj[] = new Object[2];
        int i = 0 + 24;
        aobj[0] = Integer.valueOf(i);
        aobj[1] = "CR Computacion C.A.";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj));
        Object aobj1[] = new Object[2];
        int j = i + 24;
        aobj1[0] = Integer.valueOf(j);
        aobj1[1] = "Urb. Colinas de Bello Monte, Caracas Zona";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj1));
        Object aobj2[] = new Object[2];
        int k = j + 24;
        aobj2[0] = Integer.valueOf(k);
        aobj2[1] = "Postal 1050. Venezuela";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj2));
        Object aobj3[] = new Object[2];
        int l = k + 24;
        aobj3[0] = Integer.valueOf(l);
        aobj3[1] = "Telfs.: (212) 844.89.78/ (426) 513.23.97";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj3));
        Object aobj4[] = new Object[2];
        int i1 = l + 24;
        aobj4[0] = Integer.valueOf(i1);
        aobj4[1] = "Fax: (212) 844.89.78";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj4));
        Object aobj5[] = new Object[2];
        int j1 = i1 + 24;
        aobj5[0] = Integer.valueOf(j1);
        aobj5[1] = "Email: ventas@crcomputacion.com";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj5));
        Object aobj6[] = new Object[2];
        aobj6[0] = Integer.valueOf(j1 + 24);
        aobj6[1] = "RIF: J-30844793-5";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj6));
        stringbuilder.append("PRINT").append("\r\n");
    }

    private void printCabeceraDatosEmpresaPrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append(lineaCentrada("ELECTRODOMESTICOS HOME PRODUCTS C.A.", 45)).append("\r\n");
        stringbuilder.append(lineaCentrada("E.H.P.", 45)).append("\r\n").append("\r\n");
    }

    private void printCabeceraDatosPedido(StringBuilder stringbuilder)
    {
        stringbuilder.append("! 0 200 200 130 1").append("\r\n");
        stringbuilder.append("LINE 20 105 550 105 1").append("\r\n");
        Object aobj[] = new Object[2];
        int i = 0 + 24;
        aobj[0] = Integer.valueOf(i);
        aobj[1] = "PEDIDO:";
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj));
        stringbuilder.append("RIGHT").append("\r\n");
        Object aobj1[] = new Object[2];
        aobj1[0] = Integer.valueOf(i);
        aobj1[1] = getPedidoId();
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj1));
        stringbuilder.append("LEFT").append("\r\n");
        Object aobj2[] = new Object[2];
        int j = i + 24;
        aobj2[0] = Integer.valueOf(j);
        aobj2[1] = getPedidoFecha();
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj2));
        stringbuilder.append("RIGHT").append("\r\n");
        Object aobj3[] = new Object[2];
        aobj3[0] = Integer.valueOf(j);
        aobj3[1] = getPedidoHora();
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj3));
        stringbuilder.append("LEFT").append("\r\n");
        Object aobj4[] = new Object[2];
        aobj4[0] = Integer.valueOf(j + 24);
        aobj4[1] = getClienteNombre();
        stringbuilder.append(String.format("TEXT 7 0 24 %d %s\r\n", aobj4));
        stringbuilder.append("PRINT").append("\r\n");
    }

    private void printCabeceraDatosPedidoPrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append(lineaCentrada((new StringBuilder("PEDIDO N\260:")).append(getPedidoId()).toString(), 45)).append("\r\n").append("\r\n");
    }

    private void printCabeceraDatosVendedorPrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append(lineaCentrada("VEND: XXXX / XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", 45)).append("\r\n").append("\r\n");
    }

    private void printCondicionesPoliticas(StringBuilder stringbuilder)
    {
        stringbuilder.append("! U1 SETLP 0 0 9").append("\r\n").append("\r\n").append("\r\n");
        stringbuilder.append("CONDICIONES:").append("\r\n");
        stringbuilder.append("1) Queda entendido que el presente contrato de venta esta sujeto a la aprobacion de la Gerencia de Electronics Home Products, C.A.").append("\r\n");
        stringbuilder.append("2) Los precios que figuren en este pedido pueden ser modificados en caso de que, antes de la entrega total de la mercancia, nuestros costos sufrieren alteracion como consecuencia de aumento del costo de mano de obra, encarecimiento de la materia prima, modificacion de caracter arancelario, cambiario y otroas cosas fuera de nuestro control.").append("\r\n");
        stringbuilder.append("3) El comprador acepta despachos parciales.").append("\r\n").append("\r\n");
        if(getPedidoSync() == 0)
        {
            stringbuilder.append("Nota:").append("\r\n");
            stringbuilder.append("Sujeto a disponibilidad").append("\r\n");
        }
        stringbuilder.append("\r\n").append("\r\n");
    }

    private void printCondicionesPoliticasPrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append("\r\n").append("\r\n").append("\r\n");
        stringbuilder.append(lineaIzquierda("CONDICIONES:", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierdaDerecha("1) Queda entendido que el presente contrato de venta esta sujeto a la aprobacion de la Gerencia de Electronics Home Products, C.A.", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierdaDerecha("2) Los precios que figuren en este pedido pueden ser modificados en caso de que, antes de la entrega total de la mercancia, nuestros costos sufrieren alteracion como consecuencia de aumento del costo de mano de obra, encarecimiento de la materia prima, modificacion de caracter arancelario, cambiario y otroas cosas fuera de nuestro control.", 45)).append("\r\n");
        stringbuilder.append(lineaIzquierdaDerecha("3) El comprador acepta despachos parciales.", 45)).append("\r\n").append("\r\n");
        if(moveToFirst() && getPedidoSync() == 0)
        {
            stringbuilder.append("Nota:").append("\r\n");
            stringbuilder.append(lineaIzquierdaDerecha("El pedido no ha sido validado, est\341 sujeto a cambios por disponibilidad de inventario", 45)).append("\r\n");
        }
        stringbuilder.append("\r\n").append("\r\n");
    }

    private void printDetalleDatosPedido(StringBuilder stringbuilder)
    {
        moveToFirst();
        do
        {
            if(isAfterLast())
            {
                stringbuilder.append("! 0 200 200 15 1").append("\r\n");
                stringbuilder.append("LINE 20 4 550 4 1").append("\r\n");
                stringbuilder.append("PRINT").append("\r\n");
                stringbuilder.append("! U1 SETLP 7 1 48").append("\r\n");
                Object aobj2[] = new Object[1];
                aobj2[0] = Double.valueOf(0.0D);
                stringbuilder.append(String.format("  TOTAL Bs. %.2f", aobj2)).append("\r\n");
                return;
            }
            stringbuilder.append("! 0 200 200 34 1").append("\r\n");
            Object aobj[] = new Object[1];
            aobj[0] = getProductoCodigo();
            stringbuilder.append(String.format("TEXT 7 0 24 5 %s\r\n", aobj));
            stringbuilder.append("RIGHT").append("\r\n");
            Object aobj1[] = new Object[1];
            aobj1[0] = getProductoSubtotal();
            stringbuilder.append(String.format("TEXT 7 0 24 5 %s\r\n", aobj1));
            stringbuilder.append("PRINT").append("\r\n");
            stringbuilder.append("! U1 SETLP 7 0 24").append("\r\n");
            stringbuilder.append(getProductoNombre()).append("\r\n");
            stringbuilder.append(getProductoDetalleSubtotal()).append("\r\n");
            moveToNext();
        } while(true);
    }

    private void printDetalleDatosProductosPrintPreview(StringBuilder stringbuilder)
    {
        stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda("CODIGO", 20)))).append(lineaCentrada("CANT", 5)).append(lineaDerecha("PRECIO", 20)).toString()).append("\r\n");
        double d = getTotalMontoExcento();
        double d1 = getTotalMontoGravament();
        double d2 = getTotalMontoIvaGravement();
        moveToFirst();
        do
        {
            if(isAfterLast())
            {
                stringbuilder.append(lineaDerecha("==========", 45)).append("\r\n");
                stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda("Exento Bs.:", 20)))).append(lineaDerecha(MainActivity.formatVE(d), 25)).toString()).append("\r\n");
                stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda("BI G 12.00%", 20)))).append(lineaDerecha(MainActivity.formatVE(d1), 25)).toString()).append("\r\n");
                stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda("IVA G 12.00%", 20)))).append(lineaDerecha(MainActivity.formatVE(d2), 25)).toString()).append("\r\n");
                stringbuilder.append(lineaDerecha("==========", 45)).append("\r\n");
                stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda("TOTAL GENERAL Bs.:", 20)))).append(lineaDerecha(MainActivity.formatVE(d2 + (d + d1)), 25)).toString()).append("\r\n");
                return;
            }
            stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda(getProductoCodigo(), 20)))).append(lineaCentrada(getProductoCantidad(), 5)).append(lineaDerecha(getProductoPrecio(), 20)).toString()).append("\r\n");
            stringbuilder.append(lineaIzquierda(getProductoNombre(), 45)).append("\r\n");
            stringbuilder.append(lineaIzquierda("----------------------------------------------------", 45)).append("\r\n");
            stringbuilder.append((new StringBuilder(String.valueOf(lineaIzquierda((new StringBuilder("IVA Bs: ")).append(getProductoIvaMonto()).toString(), 20)))).append(lineaDerecha((new StringBuilder("SUB-TOTAL Bs.: ")).append(getProductoSubtotal()).toString(), 25)).toString()).append("\r\n");
            stringbuilder.append(lineaIzquierda("=====================================================", 45)).append("\r\n").append("\r\n");
            moveToNext();
        } while(true);
    }

    private void printLogoEmpresa(StringBuilder stringbuilder)
    {
        line = 0;
        stringbuilder.append("! U1 BEGIN-PAGE").append("\r\n");
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(7);
        aobj[1] = Integer.valueOf(24);
        stringbuilder.append(String.format("SETLP %d 0 %d", aobj)).append("\r\n");
    }

    private void retenerAvanceLinea()
    {
        avanzarLinea = false;
    }

    public String getClienteCodigo()
    {
        return getString(getColumnIndexOrThrow("cod_cliente")).trim();
    }

    public String getClienteNombre()
    {
        return getString(getColumnIndexOrThrow("nombre")).trim();
    }

    public String getClienteRif()
    {
        return getString(getColumnIndexOrThrow("rif")).trim();
    }

    public String getPedidoFecha()
    {
        Object aobj[] = new Object[1];
        aobj[0] = getString(getColumnIndexOrThrow("fecha"));
        return String.format("FECHA: %s  ", aobj);
    }

    public String getPedidoHora()
    {
        Object aobj[] = new Object[1];
        aobj[0] = getString(getColumnIndexOrThrow("hora"));
        return String.format("HORA: %s  ", aobj);
    }

    public String getPedidoId()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(getInt(getColumnIndexOrThrow("id_pedido")));
        return String.format("%d  ", aobj);
    }

    public int getPedidoSync()
    {
        return getInt(getColumnIndexOrThrow("sync"));
    }

    public String getPrintPreviewVersion()
    {
        StringBuilder stringbuilder = new StringBuilder();
        printCabeceraDatosEmpresaPrintPreview(stringbuilder);
        printCabeceraDatosClientePrintPreview(stringbuilder);
        printCabeceraDatosVendedorPrintPreview(stringbuilder);
        printCabeceraDatosPedidoPrintPreview(stringbuilder);
        printDetalleDatosProductosPrintPreview(stringbuilder);
        printCondicionesPoliticasPrintPreview(stringbuilder);
        return stringbuilder.toString();
    }

    public String getPrintPreviewVersionToMZ320Format()
    {
        String s = getPrintPreviewVersion();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("! U1 BEGIN-PAGE").append("\r\n");
        stringbuilder.append("! U SETLP 7 0 24 PRINT").append("\r\n");
        stringbuilder.append(s).append("\r\n");
        stringbuilder.append("! U1 END-PAGE").append("\r\n");
        return stringbuilder.toString();
    }

    public String getPrintVersion()
    {
        StringBuilder stringbuilder = new StringBuilder();
        printCabeceraDatosEmpresa(stringbuilder);
        printCabeceraDatosPedido(stringbuilder);
        printDetalleDatosPedido(stringbuilder);
        printCondicionesPoliticas(stringbuilder);
        return stringbuilder.toString();
    }

    public String getProductoCantidad()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(getInt(getColumnIndexOrThrow("cantidad")));
        return String.format("%d", aobj);
    }

    public String getProductoCodigo()
    {
        return getString(getColumnIndexOrThrow("id_profitplus"));
    }

    public String getProductoDetalleSubtotal()
    {
        Object aobj[] = new Object[3];
        aobj[0] = getProductoPrecio();
        aobj[1] = getProductoCantidad();
        aobj[2] = getProductoIva();
        return String.format("  %s - %s - %s", aobj);
    }

    public String getProductoIva()
    {
        double d = getDouble(getColumnIndexOrThrow("iva"));
        String s;
        if(d > 0.0D)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Double.valueOf(d);
            s = String.format("%.2f", aobj);
        } else
        {
            s = "(E)";
        }
        return s;
    }

    public String getProductoIvaMonto()
    {
        double d = getDouble(getColumnIndexOrThrow("item_iva_monto"));
        String s;
        if(d > 0.0D)
            s = MainActivity.formatVE(d);
        else
            s = "(E)";
        return s;
    }

    public String getProductoNombre()
    {
        return getString(getColumnIndexOrThrow("nombre_producto")).trim();
    }

    public String getProductoPrecio()
    {
        Object aobj[] = new Object[1];
        aobj[0] = Double.valueOf(getDouble(getColumnIndexOrThrow("precio")));
        return String.format("Bs. %.2f", aobj);
    }

    public String getProductoSubtotal()
    {
        return MainActivity.formatVE(getDouble(getColumnIndexOrThrow("item_subtotal")));
    }

    public double getTotalMontoExcento()
    {
        return getDouble(getColumnIndexOrThrow("excento"));
    }

    public double getTotalMontoGravament()
    {
        return getDouble(getColumnIndexOrThrow("gravament"));
    }

    public double getTotalMontoIvaGravement()
    {
        return getDouble(getColumnIndexOrThrow("iva_monto"));
    }

    private static final int ALTURA_LINEA = 24;
    public static final String COLUMNA_CLIE_CODIGO = "cod_cliente";
    public static final String COLUMNA_CLIE_NOMBRE = "nombre";
    public static final String COLUMNA_CLIE_RIF = "rif";
    public static final String COLUMNA_PEDIDO_FECHA = "fecha";
    public static final String COLUMNA_PEDIDO_HORA = "hora";
    public static final String COLUMNA_PEDIDO_ID = "id_pedido";
    public static final String COLUMNA_PEDIDO_SYNC = "sync";
    public static final String COLUMNA_PRODUCTO_CANTIDAD = "cantidad";
    public static final String COLUMNA_PRODUCTO_CODIGO = "id_profitplus";
    public static final String COLUMNA_PRODUCTO_IVA = "iva";
    public static final String COLUMNA_PRODUCTO_IVA_GRAVAMENT = "item_iva_monto";
    public static final String COLUMNA_PRODUCTO_NOMBRE = "nombre_producto";
    public static final String COLUMNA_PRODUCTO_PRECIO = "precio";
    public static final String COLUMNA_PRODUCTO_SUBTOTAL = "item_subtotal";
    public static final String COLUMNA_TOTAL_MONTO_EXCENTO = "excento";
    public static final String COLUMNA_TOTAL_MONTO_GRAVAMENT = "gravament";
    public static final String COLUMNA_TOTAL_MONTO_IVA_GRAVAMENT = "iva_monto";
    private static final int ID_FUENTE = 7;
    private static final String QUERY = "select cast(c.nombre as CHARACTER(30)) as nombre, c.rif, c.id_profitplus cod_cliente, pd.id_pedido, pro.id_profitplus, pro.nombre as nombre_producto, pd.precio, pd.cantidad, pd.iva, pd.total_iva_gravament as item_iva_monto,pd.total_excento + pd.total_gravament + pd.total_iva_gravament as item_subtotal, p.total_iva_gravament as iva_monto, p.total_gravament as gravament, p.total_excento as excento, date(p.fecha) as fecha, time(p.fecha) as hora, p.sync from Pedidos p, Pedidos_Detalle pd, Clientes c, Productos pro where p._id = %d and pd.id_pedido = %d and p.id_cliente = c._id and pd.id_producto = pro._id ";
    private static final String SALTO_LINEA = "\r\n";
    private static final String blancos = "                                                     ";
    private static final String seperador1 = "----------------------------------------------------";
    private static final String seperador2 = "=====================================================";
    private boolean avanzarLinea;
    private int line;
}
