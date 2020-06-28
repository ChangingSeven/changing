package com.changing.customer.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * IP获取工具
 */
@Slf4j
public class IpUtil {

    private static final String LOCAL_IP_ADDRESS = "127.0.0.1";

    /**
     * 获取登录用户IP地址
     *
     * @param request servlet请求
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddr = request.getHeader("x-forwarded-for");
        if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
            ipAddr = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
            ipAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddr == null || ipAddr.length() == 0 || "unknown".equalsIgnoreCase(ipAddr)) {
            ipAddr = request.getRemoteAddr();
        }
        if (ipAddr.equals("0:0:0:0:0:0:0:1")) {
            ipAddr = LOCAL_IP_ADDRESS;
        }
        return ipAddr;
    }

    /**
     * 根据IP地址获取mac地址
     *
     * @param ipAddress IP地址
     * @return MAC地址
     * @throws SocketException      异常类
     * @throws UnknownHostException 异常类
     */
    public static String getMacAddrByIP(String ipAddress) throws SocketException, UnknownHostException {
        String macAddress = "";
        // 如果为127.0.0.1,则获取本地MAC地址。
        if (LOCAL_IP_ADDRESS.equals(ipAddress)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            // 貌似此方法需要JDK1.6。
            byte[] macAddr = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            // 下面代码是把mac地址拼装成String
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < macAddr.length; i++) {
                if (i != 0) {
                    stringBuilder.append("-");
                }
                // macAddr[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(macAddr[i] & 0xFF);
                stringBuilder.append(s.length() == 1 ? 0 + s : s);
            }
            // 把字符串所有小写字母改为大写成为正规的mac地址并返回
            macAddress = stringBuilder.toString().trim().toUpperCase();
            return macAddress;
        } else {
            // 获取非本地IP的MAC地址
            try {
                System.out.println(ipAddress);
                Process process = Runtime.getRuntime().exec("nbtstat -A " + ipAddress);
                InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String lineStr = "";
                while ((lineStr = bufferedReader.readLine()) != null) {
                    if (lineStr.indexOf("MAC") > 1) {
                        macAddress = lineStr.substring(lineStr.indexOf("MAC") + 9);
                        macAddress = macAddress.trim();
                        log.info("IP地址 " + ipAddress + " 对应的MAC地址为：" + macAddress);
                        break;
                    }
                }
                process.destroy();
                bufferedReader.close();
                inputStreamReader.close();
            } catch (IOException ex) {
                log.error("获取MAC地址异常", ex);
            }

            return macAddress;
        }
    }

    /**
     * 根据IP获取机器名
     *
     * @param ipAddr IP地址
     * @return 计算机名
     */
    public static String getHostnameByIP(String ipAddr) {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(ipAddr);
            String computername = inetAddress.getHostName();
            if (computername.equalsIgnoreCase("localhost")) {
                computername = InetAddress.getLocalHost().getCanonicalHostName();
            }
            return computername;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ipAddr;
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        System.out.println(IpUtil.getHostnameByIP("192.168.141.132"));
    }
}