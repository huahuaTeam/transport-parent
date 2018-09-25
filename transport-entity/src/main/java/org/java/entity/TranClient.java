package org.java.entity;

public class TranClient {
    private String clientId;

    private String clientPwd;

    private String clientCom;

    private String clientShort;

    private String clientType;

    private String clientName;

    private String clientTel;

    private Integer clientCredit;

    private String clientEmail;

    private String clientAddress;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientPwd() {
        return clientPwd;
    }

    public void setClientPwd(String clientPwd) {
        this.clientPwd = clientPwd == null ? null : clientPwd.trim();
    }

    public String getClientCom() {
        return clientCom;
    }

    public void setClientCom(String clientCom) {
        this.clientCom = clientCom == null ? null : clientCom.trim();
    }

    public String getClientShort() {
        return clientShort;
    }

    public void setClientShort(String clientShort) {
        this.clientShort = clientShort == null ? null : clientShort.trim();
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getClientTel() {
        return clientTel;
    }

    public void setClientTel(String clientTel) {
        this.clientTel = clientTel == null ? null : clientTel.trim();
    }

    public Integer getClientCredit() {
        return clientCredit;
    }

    public void setClientCredit(Integer clientCredit) {
        this.clientCredit = clientCredit;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail == null ? null : clientEmail.trim();
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress == null ? null : clientAddress.trim();
    }

    @Override
    public String toString() {
        return "TranClient{" +
                "clientId='" + clientId + '\'' +
                ", clientPwd='" + clientPwd + '\'' +
                ", clientCom='" + clientCom + '\'' +
                ", clientShort='" + clientShort + '\'' +
                ", clientType='" + clientType + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientTel='" + clientTel + '\'' +
                ", clientCredit=" + clientCredit +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                '}';
    }
}