package com.andela.javadevsnairobi.util;


import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {

    public static void checkInternetConnection(final NetworkUtilContract view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String host = "github.com";
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(host);
                    if (inetAddress.equals("")) {
                        view.onInternetUnavailable();
                    } else {
                        view.onInternetRestored();
                    }
                } catch (UnknownHostException e) {
                    view.onInternetUnavailable();
                }

            }
        }).start();
    }

}
