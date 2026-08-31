package entity;

public class Member {
    private int memberID;
    private String memberName;
    private String password;
    private int points;

    // Getters & Setters
    public int getMemberID() { return memberID; }
    public void setMemberID(int memberID) { this.memberID = memberID; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}