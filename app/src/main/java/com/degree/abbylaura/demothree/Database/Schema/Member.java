package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 01/03/2018.
 */

public class Member {

    public static final String TAG = Member.class.getSimpleName();
    public static final String TABLE = "Member";
    public static final String KEY_MemberId = "MemberId"; //PRIMARY KEY
    public static final String KEY_Name = "Name";
    public static final String KEY_Email = "Email";
    public static final String KEY_Password = "Password";
    public static final String KEY_DOB = "DOB";
    public static final String KEY_Positions = "Positions";
    public static final String KEY_Responsibilities = "Responsibilities";
    public static final String KEY_TeamId = "TeamId"; //FOREIGN KEY FROM TEAM

    // Labels Table Columns names

    private String memberId;
    private String name;
    private String email;
    private String password;
    private String dob;
    private String positions;
    private String responsibilities;
    private String teamId;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
        this.dob = dob;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
