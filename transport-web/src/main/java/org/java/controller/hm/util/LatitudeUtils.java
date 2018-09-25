package org.java.controller.hm.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过地址字符串得到经纬度坐标
 */
public class LatitudeUtils {

    public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";

    /**
     * 返回输入地址的经纬度坐标
     * key lng(经度),lat(纬度)
     */
    public static Map<String,String> getGeocoderLatitude(String address){
        BufferedReader in = null;
        try {
            //将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);


            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while((res = in.readLine())!=null){
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map<String,String> map = null;
            if(StringUtils.isNotEmpty(str)){
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
                    String lng = str.substring(lngStart+5, lngEnd);
                    String lat = str.substring(lngEnd+7, latEnd);
                    map = new HashMap<String,String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 拆分地址的  /
     * @param
     */
    public static String spilt(String address){

        String[] names = address.split("/");
        String str="";
        for (int i = 0; i < names.length; i++) {
            str += names[i];
        }
        return str;
    }




    public static void main(String args[]){
        try {
           /* Map<String, String> json = LatitudeUtils.getGeocoderLatitude("四川省/成都市/龙泉驿区/北泉路1188号");
            System.out.println("lng : " + json.get("lng"));
            System.out.println("lat : " + json.get("lat"));*/
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

}
