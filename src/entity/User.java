package entity;

public class User {

    public int userId;
    public int balance;

    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public String toString() {
        return "User " + userId + " (Balance: " + balance + ")";
    }

}
