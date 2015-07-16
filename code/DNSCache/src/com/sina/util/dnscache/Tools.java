package com.sina.util.dnscache;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

/**
*
* 项目名称: DNSCache <br>
* 类名称: Tools <br>
* 类描述: 一些常用的小方法 <br>
* 创建人: fenglei <br>
* 创建时间: 2015-4-21 下午5:26:04 <br>
* 
* 修改人:  <br>
* 修改时间:  <br>
* 修改备注:  <br>
* 
* @version V1.0
*/
public class Tools {

    /**
     * 从 url 中截取 domain
     * @param url
     * @return
     */
    public static String getHostName(String url){

        if (url == null)
            return "";

        url = url.trim();
        String host = url.toLowerCase();
        int end;

        if (host.startsWith("http://")) {
            end = url.indexOf("/", 8);
            if (end > 7) {
                host = url.substring(7, end);
            } else {
                host = url.substring(7);
            }

        } else if (host.startsWith("https://")) {
            end = url.indexOf("/", 9);
            if (end > 8) {
                host = url.substring(8, end);
            } else {
                host = url.substring(8);
            }
        } else {
            end = url.indexOf("/", 1);
            if (end > 1) {
                host = url.substring(0, url.indexOf("/", 1));
            } else {
                host = url;
            }
        }

        return host;
    }


    /**
     * 转换url 主机头为ip地址
     * @param url 原url
     * @param host 主机头
     * @param ip 服务器ip
     * @return
     */
    public static String getIpUrl(String url, String host, String ip ){

    	if( url == null ){
    		Tools.log("TAG", "URL NULL") ;
    	}
    	if( host == null ){
    		Tools.log("TAG", "host NULL") ;
    	}
    	if( ip == null ){
    		Tools.log("TAG", "ip NULL") ;
    	}
    	
    	if( url == null || host == null || ip == null ) return url ;
    	
        String ipUrl = url.replaceFirst(host, ip);

        return ipUrl ;
    }



//    /**
//     * 将ip转换成整形
//     */
//    public static long parseIpAddress(String ipAddress) {
//        if (ipAddress == null || ipAddress.equals("0") || TextUtils.isEmpty(ipAddress)) {
//            return 0;
//        }
//        ipAddress = ipAddress.trim();
//        long[] ip = new long[4];
//        // 先找到IP地址字符串中.的位置
//        int position1 = ipAddress.indexOf(".");
//        int position2 = ipAddress.indexOf(".", position1 + 1);
//        int position3 = ipAddress.indexOf(".", position2 + 1);
//        // 将每个.之间的字符串转换成整型
//        try {
//            ip[0] = Long.parseLong(ipAddress.substring(0, position1));
//            ip[1] = Long.parseLong(ipAddress.substring(position1 + 1, position2));
//            ip[2] = Long.parseLong(ipAddress.substring(position2 + 1, position3));
//            ip[3] = Long.parseLong(ipAddress.substring(position3 + 1));
//        } catch (NumberFormatException e) {
//
//        }
//        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
//    }
//
//    /**
//     * 将整形ip转成成字符串ip地址
//     * @param longIp
//     * @return
//     */
//    public static String longToIP(long longIp) {
//        StringBuffer sb = new StringBuffer("");
//        // 直接右移24位
//        sb.append(String.valueOf((longIp >>> 24)));
//        sb.append(".");
//        // 将高8位置0，然后右移16位
//        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
//        sb.append(".");
//        // 将高16位置0，然后右移8位
//        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
//        sb.append(".");
//        // 将高24位置0
//        sb.append(String.valueOf((longIp & 0x000000FF)));
//        return sb.toString();
//    }

    
	@SuppressLint("SimpleDateFormat")
	public static String getStringDateShort(long time) {
		Date currentTime = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	public static String getStringDateShort(String time) {
		return getStringDateShort( Long.valueOf(time) );
	}
	
	public static void log( String tag, String msg ){
		if( DNSCacheConfig.DEBUG == false ) return ; 
		Log.d(tag, msg) ; 
	}
    
}
