package com.jd.survey.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author victor
 *
 */
public class Des {
    private static final String Algorithm = "DES"; //瀹氫箟 鍔犲瘑绠楁硶,鍙敤 DES,DESede,Blowfish

    //src涓鸿鍔犲瘑鐨勬暟鎹紦鍐插尯锛堟簮锛�
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
        //鐢熸垚瀵嗛挜
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //鍔犲瘑
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        }
            catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
            catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
            catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //keybyte涓哄姞瀵嗗瘑閽ワ紝闀垮害涓�4瀛楄妭
    //src涓哄姞瀵嗗悗鐨勭紦鍐插尯
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
        //鐢熸垚瀵嗛挜
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //瑙ｅ瘑
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        }
            catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
            catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
            catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    //杞崲鎴愬崄鍏繘鍒跺瓧绗︿覆
    public static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";
        for (int n=0;n<b.length;n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1) hs=hs+"";
        }
        return hs.toUpperCase();
    }

    //16 杩涘埗 杞�2 杩涘埗
    public static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("闀垮害涓嶆槸鍋舵暟");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    //鍔犲瘑
    public static String Encrypt(String str, byte[] key){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] encrypt = encryptMode(key, str.getBytes());
        return byte2hex(encrypt);
    }

    //鍔犲瘑
    public static byte[] EncryptRetByte(byte[] src, byte[] key){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] encrypt = encryptMode(key, src);
        return encrypt;
    }

    //瑙ｅ瘑
    public static String Decrypt(String str, byte[] key){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] decrypt = decryptMode(key, hex2byte(str));
        return new String(decrypt);
    }

    public static void main(String arg[]){
//		String str1 = "uficc";
        String strKey = "0002000200020002";
		String str = "jdbc:mysql://192.168.11.44:3306/openfire?useUnicode=true&characterEncoding=utf-8";
		String str2 = "root";
		String str3 = "com.mysql.jdbc.Driver";
		String str4 = "123456";
//
		strKey = "0002000200020002";
//		str1 = "uficc";
		str = "jdbc:mysql://121.40.130.192:3306/ucc?useUnicode=true&characterEncoding=utf-8";
		str3 = "com.mysql.jdbc.Driver";
		str2 = "root";
		str4 = "client";

		System.out.println(Encrypt(str, hex2byte(strKey)));
		System.out.println(Encrypt(str3, hex2byte(strKey)));
		System.out.println(Encrypt(str2, hex2byte(strKey)));
		System.out.println(Encrypt(str4, hex2byte(strKey)));
//
        String aa = "jdbc:mysql://192.168.11.44:3306/openfire?useUnicode=true&characterEncoding=utf-8";
////		aa = "D328BFB8B7892920";
//        System.out.println(Decrypt(aa, hex2byte(strKey)));
        System.out.println(Des.Encrypt(aa, Des.hex2byte(strKey)));
//		System.out.println("=====================");
//		System.out.println(Decrypt("F7333F572A623AB345FF13A1D5AFD452", hex2byte(strKey)));
////		Connection

        String url = "jdbc:mysql://127.0.0.1:3306/ucc?useUnicode=true&characterEncoding=utf-8";
//        url = "jdbc:mysql://121.40.196.48:3306/ucc";
//        url = "jdbc:mysql://192.168.11.44:3306/openfire?useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        String sql = "select table_name from information_schema.tables where table_schema='openfire'";
//        try {
//            Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet rs =  statement.executeQuery();
//            while(rs.next()){
//                System.out.println(rs.getString(1));
//            }
//            rs.close();
//            statement.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
