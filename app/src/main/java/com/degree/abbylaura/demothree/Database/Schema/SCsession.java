package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class SCsession {

    public static final String TAG = SCsession.class.getSimpleName();
    public static final String TABLE = "SCsession";
    public static final String KEY_SessionId = "SessionId"; //FOREIGN KEY FROM STRENGTHANDCONDITIONING
    public static final String KEY_MemberId = "MemberId";
    public static final String KEY_Deadlifts = "Deadlifts";
    public static final String KEY_BackSquat = "BackSquat";
    public static final String KEY_BackSquatJumps = "BackSquatJumps";
    public static final String KEY_GobletSquat = "GobletSquat";
    public static final String KEY_BenchPress = "BenchPress";
    public static final String KEY_MilitaryPress = "MilitaryPress";
    public static final String KEY_SupineRow = "SupineRow";
    public static final String KEY_ChinUps = "ChinUps";
    public static final String KEY_Trunk = "Trunk";
    public static final String KEY_RDL = "RDL";
    public static final String KEY_SplitSquat = "SplitSquat";
    public static final String KEY_FourWayArms = "FourWayArms";

    private String sessionID;
    private String memberId;
    private String deadlifts;
    private String backSquat;
    private String backSquatJumps;
    private String gobletSquat;
    private String benchPress;
    private String militaryPress;
    private String supineRow;
    private String chinUps;
    private String trunk;
    private String rdl;
    private String splitSquat;
    private String fourWayArms;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getMemberID() {
        return memberId;
    }

    public void setMemberID(String memberID) {
        this.memberId = memberID;
    }

    public String getDeadlifts() {
        return deadlifts;
    }

    public void setDeadlifts(String deadlifts) {
        this.deadlifts = deadlifts;
    }

    public String getBackSquat(){
        return backSquat;
    }

    public void setBackSquat(String backSquat){
        this.backSquat = backSquat;
    }

    public String getBackSquatJumps(){
        return backSquatJumps;
    }

    public void setBackSquatJumps(String backSquatJumps){
        this.backSquatJumps = backSquatJumps;
    }

    public String getGobletSquat(){
        return gobletSquat;
    }

    public void setGobletSquat(String gobletSquat){
        this.gobletSquat = gobletSquat;
    }

    public String getBenchPress(){
        return benchPress;
    }

    public void setBenchPress(String benchPress){
        this.benchPress = benchPress;
    }

    public String getMilitaryPress(){
        return militaryPress;
    }

    public void setMilitaryPress(String militaryPress){
        this.militaryPress = militaryPress;
    }

    public String getSupineRow(){
        return supineRow;
    }

    public void setSupineRow(String supineRow){
        this.supineRow = supineRow;
    }

    public String getChinUps(){
        return chinUps;
    }

    public void setChinUps(String chinUps){
        this.chinUps = chinUps;
    }

    public String getTrunk(){
        return trunk;
    }

    public void setTrunk(String trunk){
        this.trunk = trunk;
    }

    public String getRdl(){
        return rdl;
    }

    public void setRdl(String rdl){
        this.rdl = rdl;
    }

    public String getSplitSquat(){
        return splitSquat;
    }

    public void setSplitSquat(String splitSquat){
        this.splitSquat = splitSquat;
    }

    public String getFourWayArms(){
        return fourWayArms;
    }

    public void setFourWayArms(String fourWayArms){
        this.fourWayArms = fourWayArms;
    }

}
