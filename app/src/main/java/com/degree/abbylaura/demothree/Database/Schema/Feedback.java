package com.degree.abbylaura.demothree.Database.Schema;

public class Feedback {

    public static final String TAG = Feedback.class.getSimpleName();
    public static final String TABLE = "Feedback";
    public static final String KEY_FeedbackPrimary = "FeedbackPrimary";
    public static final String KEY_MemberId = "MemberId";
    public static final String KEY_FixtureId = "FixtureId";
    public static final String KEY_FeedbackText = "FeedbackText";
    public static final String KEY_Attack = "Attack";
    public static final String KEY_Defence = "Defence";
    public static final String KEY_Effort = "Effort";
    public static final String KEY_Overall = "Overall";

    private String memberId;
    private String fixtureId;
    private String feedbackText;
    private String attack;
    private String defence;
    private String effort;
    private String overall;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefence() {
        return defence;
    }

    public void setDefence(String defence) {
        this.defence = defence;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }

}
