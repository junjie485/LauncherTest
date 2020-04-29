package com.kuaqu.launchertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetWorkReceiver extends BroadcastReceiver {

    public int NET_ETHERNET = 1;
    public int NET_WIFI = 2;
    public int NET_NOCONNECT = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)
                || action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {

            switch (isNetworkAvailable(context)) {
                case 1:
                    System.out.println("-----------networktest---------有线");
                    ping(1);
                    break;
                case 2:
                    System.out.println("-----------networktest---------无线");

                    break;
                case 0:
                    System.out.println("-----------networktest---------无网络");

                    break;
                default:
                    break;
            }
        }

    }

    private int isNetworkAvailable(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ethNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (ethNetInfo != null && ethNetInfo.isConnected()) {
            return NET_ETHERNET;
        } else if (wifiNetInfo != null && wifiNetInfo.isConnected()) {
            return NET_WIFI;
        } else {
            return NET_NOCONNECT;
        }
    }
    private int ping(int netStatus) {
        InputStream input = null;
        BufferedReader in;
        StringBuffer stringBuffer;
        if (netStatus==0||netStatus==1) {
            try {
                String ip = "www.baidu.com";//之所以写百度是因为百度比较稳定，一般不会出现问题，也可以ping自己的服务器
                Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping3次
                // 读取ping的内容
                input = p.getInputStream();
                in = new BufferedReader(new InputStreamReader(input));
                stringBuffer = new StringBuffer();
                String content = "";
                while ((content = in.readLine()) != null) {
                    stringBuffer.append(content);
                }
                // PING的状态
                int status = p.waitFor();  //status 为 0 ，ping成功，即为可以对外访问；为2则失败，即联网但不可以上网
                Log.e("网络","ping结果"+status);
                if (status == 0) {
                    Log.e("net","net is  available");
                    return 1;
                } else {
                    Log.e("net","net is not available");
                    return -1;
                }
            } catch (IOException e) {
                Log.e("net", "IOException");
            } catch (InterruptedException e) {
                Log.e("net", "InterruptedException");
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        }
        return -1;
    }
}
