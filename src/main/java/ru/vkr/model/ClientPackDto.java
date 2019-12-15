package ru.vkr.model;

import java.util.List;

public class ClientPackDto {

    public ClientPackDto(List<ClientData> clientDatas) {
        this.clientDatas = clientDatas;
    }
    private List<ClientData> clientDatas;

    public List<ClientData> getClientDatas() {
        return clientDatas;
    }

    public void setClientDatas(List<ClientData> clientDatas) {
        this.clientDatas = clientDatas;
    }

    @Override
    public String toString() {
        return "ClientPackDto{" +
                "clientDatas=" + clientDatas +
                '}';
    }
}
