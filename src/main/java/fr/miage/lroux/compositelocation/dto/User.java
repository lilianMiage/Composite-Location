package fr.miage.lroux.compositelocation.dto;

public class User {

    private long userId;

    /**
     *  User last name.
     */
    private String lastName;

    /**
     *  User first name.
     */
    private String firstName;

    private long accessCardId;

    public User(long userId, String lastName, String firstName, long accessCardId) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.accessCardId = accessCardId;
    }

    public long getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getAccessCardId() {
        return accessCardId;
    }
}
