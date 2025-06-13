package fr.miage.lroux.compositelocation.dto;

public class AccessCard {

    private long accessCardId;

    private String password;

    private long userId;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAccessCardid(long accessCardid) {
        this.accessCardId = accessCardid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccessCardid() {
        return accessCardId;
    }

    public String getPassword() {
        return password;
    }

    public AccessCard() {
    }

    public AccessCard(long accessCardid, String password, long userId) {
        this.accessCardId = accessCardid;
        this.password = password;
        this.userId = userId;
    }
}
