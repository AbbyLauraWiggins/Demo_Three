package com.degree.abbylaura.demothree.Database.Schema;

/**
 * Created by abbylaura on 06/03/2018.
 */

public class KPI {

    public static final String TAG = KPI.class.getSimpleName();
    public static final String TABLE = "KPI";
    public static final String KEY_KPIPrimary = "KPIPrimary";
    public static final String KEY_MemberID = "MemberID";
    public static final String KEY_FixtureID = "FixtureID";
    public static final String KEY_sTackles = "sTackles";
    public static final String KEY_uTackles = "uTackles";
    public static final String KEY_sCarries = "sCarries";
    public static final String KEY_uCarries = "uCarries";
    public static final String KEY_sPasses = "sPasses";
    public static final String KEY_uPasses = "uPasses";
    public static final String KEY_HandlingErrors = "HandlingErrors";
    public static final String KEY_Penalties = "Penalties";
    public static final String KEY_YellowCards = "YellowCards";
    public static final String KEY_TriesScored = "TriesScored";
    public static final String KEY_TurnoversWon = "TurnoversWon";
    public static final String KEY_sThrowIns = "sThrowIns";
    public static final String KEY_uThrowIns = "uThrowIns";
    public static final String KEY_sLineOutTakes = "sLineOutTakes";
    public static final String KEY_uLineOutTakes = "uLineOutTakes";
    public static final String KEY_sKicks = "sKicks";
    public static final String KEY_uKicks = "uKicks";

    private String kpiPrimary;
    private String memberid;
    private String fixtureid;
    private String stackles;
    private String utackles;
    private String scarries;
    private String ucarries;
    private String spasses;
    private String upasses;
    private String handlingerrors;
    private String penalties;
    private String yellowcards;
    private String triesscored;
    private String turnoverswon;
    private String sthrowins;
    private String uthrowins;
    private String slineouttakes;
    private String ulineouttakes;
    private String skicks;
    private String ukicks;

    public String getKpiPrimary(){ return kpiPrimary; }

    public void setKpiPrimary(String kpiPrimary){ this.kpiPrimary = kpiPrimary; }

    public String getMemberID(){ return memberid; }

    public void setMemberID(String memberid){ this.memberid = memberid; }

    public String getFixtureID(){ return fixtureid; }

    public void setFixtureID(String fixtureid){ this.fixtureid = fixtureid; }

    public String getsTackles(){ return stackles; }

    public void setsTackles(String stackles){ this.stackles = stackles; }

    public String getuTackles(){ return utackles; }

    public void setuTackles(String utackles){ this.utackles = utackles; }

    public String getsCarries(){ return scarries; }

    public void setsCarries(String scarries){ this.scarries = scarries; }

    public String getuCarries(){ return ucarries; }

    public void setuCarries(String ucarries){ this.ucarries = ucarries; }

    public String getsPasses(){ return spasses; }

    public void setsPasses(String spasses){ this.spasses = spasses; }

    public String getuPasses(){ return upasses; }

    public void setuPasses(String upasses){ this.upasses = upasses; }

    public String getHandlingErrors(){ return handlingerrors; }

    public void setHandlingErrors(String handlingerrors){ this.handlingerrors = handlingerrors; }

    public String getPenalties(){ return penalties; }

    public void setPenalties(String penalties){ this.penalties = penalties; }

    public String getYellowCards(){ return yellowcards; }

    public void setYellowCards(String yellowcards){ this.yellowcards = yellowcards; }

    public String getTriesScored(){ return triesscored; }

    public void setTriesScored(String triesscored){ this.triesscored = triesscored; }

    public String getTurnoversWon(){ return turnoverswon; }

    public void setTurnoversWon(String turnoverswon){ this.turnoverswon = turnoverswon; }

    public String getsThrowIns(){ return sthrowins; }

    public void setsThrowIns(String sthrowins){ this.sthrowins = sthrowins; }

    public String getuThrowIns(){ return uthrowins; }

    public void setuThrowIns(String uthrowins){ this.uthrowins = uthrowins; }

    public String getsLineOutTakes(){ return slineouttakes; }

    public void setsLineOutTakes(String slineouttakes){ this.slineouttakes = slineouttakes; }

    public String getuLineOutTakes(){ return ulineouttakes; }

    public void setuLineOutTakes(String ulineouttakes){ this.ulineouttakes = ulineouttakes; }

    public String getsKicks(){ return skicks; }

    public void setsKicks(String skicks){ this.skicks = skicks; }

    public String getuKicks(){ return ukicks; }

    public void setuKicks(String ukicks){ this.ukicks = ukicks; }


}
