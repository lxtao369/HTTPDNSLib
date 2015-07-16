package com.sina.util.dnscache.speedtest;

import java.util.HashMap;

import android.util.Log;

import com.sina.util.dnscache.Tools;
import com.sina.util.dnscache.httpdns.requests.ApacheHttpClientNetworkRequests;
import com.sina.util.dnscache.httpdns.requests.INetworkRequests;

/**
 * 测速类
 *
 * Created by fenglei on 15/4/22.
 */
public class SpeedtestManager implements ISpeedtest {

    private INetworkRequests netWork = null ;

    public static HashMap<String, String> ServerSpeedPaht= new HashMap<String, String>();
    
    public SpeedtestManager(){

        netWork = new ApacheHttpClientNetworkRequests() ;
    }

    /**
     * 测速方法 1.0版本相对简单粗暴
     * @param url
     * @param host
     * @return
     */
    @Override
    public float speedTest(String ip, String host) {

    	String paht = ServerSpeedPaht.get(host) ;
    	
    	if( paht == null ) return 0;
    	
    	String url = "http://" + ip + "/" + paht ;
    	
        // 这里记录请求开始的时间 , 这里计算的速度比较粗暴，后期可以考虑改成记录首包时间 和 传输完成时间（或者 上行、下行）。
        long startTime = System.currentTimeMillis()  ;

        String netData = netWork.requests(url, host) ;

        float speed = 0 ; 
        
        if( netData != null ){
            // 这里记录请求结束的时间
            long stopTime = System.currentTimeMillis()  ;

            // 本次请求的数据大小
            int dataSize = netData.getBytes().length ;

            // 这里的计算速度相对粗暴， 但只是一个相对的数值，就不去按照正规的转换计算了。
            speed = speedFormula(startTime,  stopTime, dataSize) ;
        }

        Tools.log("TAG_NET", "开始测速 url = " + url + "    speed = " + speed) ; 
        
        // speed = 字节大小 / 毫秒
        return speed;
    }


    /**
     * 计算速度函数方法
     * @param startTime
     * @param stopTime
     * @param size
     * @return
     */
    public float speedFormula(long startTime , long stopTime,  int size){

        // 消耗时间
        float time = (int)(stopTime - startTime) ;
        time = time == 0 ? 1 : time ;
        return size / time ;
    }

}
