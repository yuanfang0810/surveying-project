package com.aaa.hou.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/11 23:50
 * @Description
 *      根据IP地址获取详细的地域信息
 *      百度API : http://api.map.baidu.com/location/ip?ak=您的AK&ip=您的IP&coor=bd09ll
 **/
public class AddressUtils {

    /**
     * @param content 请求的参数 格式为：ip=""
     * @return
     * @throws UnsupportedEncodingException
     * @param//encoding 服务器端请求编码。如GBK,UTF-8等
     */
    public static Map<String, Object> getAddresses(String ipAddr, String encodingString) {
        // 这里调用淘宝API
        String urlStr = "http://api.map.baidu.com/location/ip?ak=6P28Z5GDb4sUhPMgRx7bX8pyG2Vj6iXv&ip="+ipAddr+"&coor=bd09ll";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = getResult(urlStr, ipAddr, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
            System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return null;//无效IP，局域网测试
            }
            Map addrMap = JSONUtils.toObject(returnStr, Map.class);
            String address = (String)addrMap.get("address");
            String[] addrStr = address.split("\\|");
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("country", addrStr[0]);
            resultMap.put("province", addrStr[1]);
            resultMap.put("city", addrStr[2]);
            return resultMap;
        }
        return null;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(80000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(80000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            //out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                }
                else {
                    if (aChar == 't') {
                        aChar = '\t';
                    }
                    else if (aChar == 'r') {
                        aChar = '\r';
                    }
                    else if (aChar == 'n') {
                        aChar = '\n';
                    }
                    else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            }
            else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    public static void main(String[] args) {
        String ip = "222.137.210.39";
        Map<String, Object> addressMap = com.aaa.hou.utils.AddressUtils.getAddresses(ip, "utf-8");
        System.out.println(addressMap.get("country"));
        System.out.println(addressMap.get("province"));
        System.out.println(addressMap.get("city"));
    }

}
