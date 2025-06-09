package fr.miage.lroux.compositelocation.dto;

public class AccessCard {

    private long accessCardid;

    private String password;

    private long userId;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAccessCardid(long accessCardid) {
        this.accessCardid = accessCardid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccessCardid() {
        return accessCardid;
    }

    public String getPassword() {
        return password;
    }

    public AccessCard() {
    }

    public AccessCard(long accessCardid, String password, long userId) {
        this.accessCardid = accessCardid;
        this.password = password;
        this.userId = userId;
    }
}
