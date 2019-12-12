package ru.vkr.model;

/**
 * Класс запрос клиент содержит информацию о клиенте
 */
public class ClientData {
    private String token;
    private String os;
    private String arch;
    private String hostName;
    private String mac;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "token='" + token + '\'' +
                ", os='" + os + '\'' +
                ", arch='" + arch + '\'' +
                ", hostName='" + hostName + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
