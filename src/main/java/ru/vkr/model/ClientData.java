package ru.vkr.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс запрос клиент содержит информацию о клиенте
 */
public class ClientData {
    private Long id;
    private String token;
    private String hostname;
    private String os;
    private String osType;
    private String macAddr;
    private List<TaskData> taskList;

    public static Map<String, ClientData> connectedClients = new HashMap<>();

    public ClientData(String token, String hostname, String os, String osType, String macAddr) {
        this.id = id;
        this.token = token;
        this.hostname = hostname;
        this.os = os;
        this.osType = osType;
        this.macAddr = macAddr;
    }

    public void setTaskList(List<TaskData> taskList) {
        this.taskList = taskList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public List<TaskData> getTaskList() {
        return taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientData)) return false;
        ClientData that = (ClientData) o;
        return getToken().equals(that.getToken()) &&
                getId().equals(that.getId()) &&
                getHostname().equals(that.getHostname()) &&
                getOs() == that.getOs() &&
                getOsType() == that.getOsType() &&
                getMacAddr().equals(that.getMacAddr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getId(), getHostname(), getOs(), getOsType(), getMacAddr());
    }
}
