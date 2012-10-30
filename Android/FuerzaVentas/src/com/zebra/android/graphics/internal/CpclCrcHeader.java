// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.zebra.android.graphics.internal;


public class CpclCrcHeader
{

    public CpclCrcHeader()
    {
    }

    public static int byte2int(byte byte0)
    {
        int i = byte0;
        if(i < 0)
            i += 256;
        return i;
    }

    public static String convertTo8dot3(String s)
    {
        int i = 8;
        int j = s.lastIndexOf('.');
        String s1;
        if(j != -1)
        {
            String s2;
            String s3;
            if(j <= i)
                i = j;
            s2 = s.substring(0, i);
            s3 = s.substring(j);
            if(s3.length() > 4)
                s3 = s3.substring(0, 4);
            s1 = (new StringBuilder()).append(s2).append(s3).toString();
        } else
        {
            s1 = s;
        }
        return s1;
    }

    public static String getCRC16ForCertificateFilesOnly(byte abyte0[])
    {
        int i = 0;
        int j = 0;
        for(int k = abyte0.length; j < k; j++)
            i = (char)(CRC16_Lookup[(char)(i >> 8)] ^ i << 8) ^ (char)byte2int(abyte0[j]);

        return stringPadToPlaces(4, '0', Integer.toHexString(i), false);
    }

    public static String getWChecksum(byte abyte0[])
    {
        int i = 0;
        for(int j = 0; j < abyte0.length; j++)
            i += (char)byte2int(abyte0[j]);

        return Integer.toHexString(1 + (0xffff ^ i));
    }

    public static String stringPadToPlaces(int i, char c, String s, boolean flag)
    {
        String s2;
        if(s.length() >= i)
        {
            s2 = s;
        } else
        {
            String s1 = "";
            for(int j = 0; j < i - s.length(); j++)
                s1 = (new StringBuilder()).append(s1).append(c).toString();

            if(flag)
                s2 = (new StringBuilder()).append(s).append(s1).toString();
            else
                s2 = (new StringBuilder()).append(s1).append(s).toString();
        }
        return s2;
    }

    public static String stringPadToPlaces(int i, String s, String s1)
    {
        if(s1.length() < i)
        {
            String s2 = "";
            for(int j = 0; j < i - s1.length(); j++)
                s2 = (new StringBuilder()).append(s2).append(s).toString();

            s1 = (new StringBuilder()).append(s2).append(s1).toString();
        }
        return s1;
    }

    private static int CRC16_Lookup[];

    static 
    {
        int ai[] = new int[256];
        ai[0] = 0;
        ai[1] = 4129;
        ai[2] = 8258;
        ai[3] = 12387;
        ai[4] = 16516;
        ai[5] = 20645;
        ai[6] = 24774;
        ai[7] = 28903;
        ai[8] = 33032;
        ai[9] = 37161;
        ai[10] = 41290;
        ai[11] = 45419;
        ai[12] = 49548;
        ai[13] = 53677;
        ai[14] = 57806;
        ai[15] = 61935;
        ai[16] = 4657;
        ai[17] = 528;
        ai[18] = 12915;
        ai[19] = 8786;
        ai[20] = 21173;
        ai[21] = 17044;
        ai[22] = 29431;
        ai[23] = 25302;
        ai[24] = 37689;
        ai[25] = 33560;
        ai[26] = 45947;
        ai[27] = 41818;
        ai[28] = 54205;
        ai[29] = 50076;
        ai[30] = 62463;
        ai[31] = 58334;
        ai[32] = 9314;
        ai[33] = 13379;
        ai[34] = 1056;
        ai[35] = 5121;
        ai[36] = 25830;
        ai[37] = 29895;
        ai[38] = 17572;
        ai[39] = 21637;
        ai[40] = 42346;
        ai[41] = 46411;
        ai[42] = 34088;
        ai[43] = 38153;
        ai[44] = 58862;
        ai[45] = 62927;
        ai[46] = 50604;
        ai[47] = 54669;
        ai[48] = 13907;
        ai[49] = 9842;
        ai[50] = 5649;
        ai[51] = 1584;
        ai[52] = 30423;
        ai[53] = 26358;
        ai[54] = 22165;
        ai[55] = 18100;
        ai[56] = 46939;
        ai[57] = 42874;
        ai[58] = 38681;
        ai[59] = 34616;
        ai[60] = 63455;
        ai[61] = 59390;
        ai[62] = 55197;
        ai[63] = 51132;
        ai[64] = 18628;
        ai[65] = 22757;
        ai[66] = 26758;
        ai[67] = 30887;
        ai[68] = 2112;
        ai[69] = 6241;
        ai[70] = 10242;
        ai[71] = 14371;
        ai[72] = 51660;
        ai[73] = 55789;
        ai[74] = 59790;
        ai[75] = 63919;
        ai[76] = 35144;
        ai[77] = 39273;
        ai[78] = 43274;
        ai[79] = 47403;
        ai[80] = 23285;
        ai[81] = 19156;
        ai[82] = 31415;
        ai[83] = 27286;
        ai[84] = 6769;
        ai[85] = 2640;
        ai[86] = 14899;
        ai[87] = 10770;
        ai[88] = 56317;
        ai[89] = 52188;
        ai[90] = 64447;
        ai[91] = 60318;
        ai[92] = 39801;
        ai[93] = 35672;
        ai[94] = 47931;
        ai[95] = 43802;
        ai[96] = 27814;
        ai[97] = 31879;
        ai[98] = 19684;
        ai[99] = 23749;
        ai[100] = 11298;
        ai[101] = 15363;
        ai[102] = 3168;
        ai[103] = 7233;
        ai[104] = 60846;
        ai[105] = 64911;
        ai[106] = 52716;
        ai[107] = 56781;
        ai[108] = 44330;
        ai[109] = 48395;
        ai[110] = 36200;
        ai[111] = 40265;
        ai[112] = 32407;
        ai[113] = 28342;
        ai[114] = 24277;
        ai[115] = 20212;
        ai[116] = 15891;
        ai[117] = 11826;
        ai[118] = 7761;
        ai[119] = 3696;
        ai[120] = 65439;
        ai[121] = 61374;
        ai[122] = 57309;
        ai[123] = 53244;
        ai[124] = 48923;
        ai[125] = 44858;
        ai[126] = 40793;
        ai[127] = 36728;
        ai[128] = 37256;
        ai[129] = 33193;
        ai[130] = 45514;
        ai[131] = 41451;
        ai[132] = 53516;
        ai[133] = 49453;
        ai[134] = 61774;
        ai[135] = 57711;
        ai[136] = 4224;
        ai[137] = 161;
        ai[138] = 12482;
        ai[139] = 8419;
        ai[140] = 20484;
        ai[141] = 16421;
        ai[142] = 28742;
        ai[143] = 24679;
        ai[144] = 33721;
        ai[145] = 37784;
        ai[146] = 41979;
        ai[147] = 46042;
        ai[148] = 49981;
        ai[149] = 54044;
        ai[150] = 58239;
        ai[151] = 62302;
        ai[152] = 689;
        ai[153] = 4752;
        ai[154] = 8947;
        ai[155] = 13010;
        ai[156] = 16949;
        ai[157] = 21012;
        ai[158] = 25207;
        ai[159] = 29270;
        ai[160] = 46570;
        ai[161] = 42443;
        ai[162] = 38312;
        ai[163] = 34185;
        ai[164] = 62830;
        ai[165] = 58703;
        ai[166] = 54572;
        ai[167] = 50445;
        ai[168] = 13538;
        ai[169] = 9411;
        ai[170] = 5280;
        ai[171] = 1153;
        ai[172] = 29798;
        ai[173] = 25671;
        ai[174] = 21540;
        ai[175] = 17413;
        ai[176] = 42971;
        ai[177] = 47098;
        ai[178] = 34713;
        ai[179] = 38840;
        ai[180] = 59231;
        ai[181] = 63358;
        ai[182] = 50973;
        ai[183] = 55100;
        ai[184] = 9939;
        ai[185] = 14066;
        ai[186] = 1681;
        ai[187] = 5808;
        ai[188] = 26199;
        ai[189] = 30326;
        ai[190] = 17941;
        ai[191] = 22068;
        ai[192] = 55628;
        ai[193] = 51565;
        ai[194] = 63758;
        ai[195] = 59695;
        ai[196] = 39368;
        ai[197] = 35305;
        ai[198] = 47498;
        ai[199] = 43435;
        ai[200] = 22596;
        ai[201] = 18533;
        ai[202] = 30726;
        ai[203] = 26663;
        ai[204] = 6336;
        ai[205] = 2273;
        ai[206] = 14466;
        ai[207] = 10403;
        ai[208] = 52093;
        ai[209] = 56156;
        ai[210] = 60223;
        ai[211] = 64286;
        ai[212] = 35833;
        ai[213] = 39896;
        ai[214] = 43963;
        ai[215] = 48026;
        ai[216] = 19061;
        ai[217] = 23124;
        ai[218] = 27191;
        ai[219] = 31254;
        ai[220] = 2801;
        ai[221] = 6864;
        ai[222] = 10931;
        ai[223] = 14994;
        ai[224] = 64814;
        ai[225] = 60687;
        ai[226] = 56684;
        ai[227] = 52557;
        ai[228] = 48554;
        ai[229] = 44427;
        ai[230] = 40424;
        ai[231] = 36297;
        ai[232] = 31782;
        ai[233] = 27655;
        ai[234] = 23652;
        ai[235] = 19525;
        ai[236] = 15522;
        ai[237] = 11395;
        ai[238] = 7392;
        ai[239] = 3265;
        ai[240] = 61215;
        ai[241] = 65342;
        ai[242] = 53085;
        ai[243] = 57212;
        ai[244] = 44955;
        ai[245] = 49082;
        ai[246] = 36825;
        ai[247] = 40952;
        ai[248] = 28183;
        ai[249] = 32310;
        ai[250] = 20053;
        ai[251] = 24180;
        ai[252] = 11923;
        ai[253] = 16050;
        ai[254] = 3793;
        ai[255] = 7920;
        CRC16_Lookup = ai;
    }
}
