package com.jd.survey.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * java后台get、post请求
 * @author kxm
 *
 */
public class HttpRequest {

    private static transient Logger Log = LoggerFactory.getLogger(HttpRequest.class);
	
	//转换成十六进制字符串 
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

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是Map的形式,请在key为参数，value为值。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString =param==null ? url: url + "?" + getParam(param);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是Map的形式,请在key为参数，value为值。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, String> param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpUrlConn = (HttpURLConnection) conn;
            // 设置通用的请求属性
            httpUrlConn.setRequestProperty("accept", "*/*");
            httpUrlConn.setRequestProperty("connection", "Keep-Alive");
            httpUrlConn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("POST");
            
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(httpUrlConn.getOutputStream());
            // 发送请求参数
            out.print(getParam(param));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(httpUrlConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPUT(String url, Map<String, String> param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection httpUrlConn = null ;
        try {
            // 打开和URL之间的连接
            httpUrlConn = (HttpURLConnection) new URL(url).openConnection();
            // 设置通用的请求属性
            httpUrlConn.setRequestProperty("accept", "*/*");
            httpUrlConn.setRequestProperty("connection", "Keep-Alive");
            httpUrlConn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpUrlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 发送POST请求必须设置如下两行
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("PUT");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(httpUrlConn.getOutputStream());
            // 发送请求参数
            out.print(getParam(param));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 PUT 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            if(null != out)
                out.close();
            try{
                if(null != in){
                    in.close();
                }
            }
            catch(IOException ex){ex.printStackTrace();}

            if(null != httpUrlConn)
                httpUrlConn.disconnect();
        }
        return result;


//        URL realUrl;
//        PrintWriter out = null;
//        String result = "";
//        BufferedReader in = null;
//        HttpURLConnection httpURLConnection = null;
//        try {
//            realUrl = new URL(url1);
//            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
//            httpURLConnection.setRequestProperty("accept", "*/*");
//            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
//            httpURLConnection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
////            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            httpURLConnection.setRequestMethod("PUT");
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.setDoOutput(true);
//
//            out = new PrintWriter(httpURLConnection.getOutputStream());
//            // 发送请求参数
//            out.print(getParam(param));
//            // flush输出流的缓冲
//            out.flush();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            in = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }  finally {
//            if (out != null) {
//                    out.flush();
//                    out.close();
//            }
//            if(in != null){
//                try {
//                    in.close();
//                } catch (IOException e) {}
//            }
//
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();
//            }
//        }
//        return result ;
    }

    /**
     * 构建请求参数
     * @param params
     * @return
     */
    public static String getParam(Map<String, String> params) {
    	StringBuffer sb = new StringBuffer();
    	if(params!=null && !params.isEmpty()){
	    	for (Entry<String, String> e : params.entrySet()) {
		    	sb.append(e.getKey());
		    	sb.append("=");
		    	sb.append(e.getValue());
		    	sb.append("&");
	    	}
	    	sb.substring(0, sb.length() - 1);
    	}
    	return sb.toString();
    }

}
