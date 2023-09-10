package com.mulan.mulan_auto.service.keygen;

import java.net.InetAddress;

public class InfoCustomerKey {
    public String getComputerName() {
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            String computerName = localMachine.getHostName();
            return computerName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIpAddress() {
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            String ipAddress = localMachine.getHostAddress();
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getMacAddress() {
        String macAddress = "Bảo mật này chưa được phát triển";
        return macAddress;
    }
}
