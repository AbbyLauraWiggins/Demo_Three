package com.degree.abbylaura.demothree.Database.Schema;


import java.io.Serializable;

/**
 * Created by abbylaura on 02/03/2018.
 */

public class Notice{

    public static final String TAG = Notice.class.getSimpleName();
    public static final String TABLE = "Notice";
    public static final String KEY_NoticeId = "NoticeId";
    public static final String KEY_MemberId = "MemberId";
    public static final String KEY_Contents = "Contents";
    public static final String KEY_Date = "Date";

    private String noticeId;
    private String memberId;
    private String contents;
    private String date;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId){
        this.noticeId = noticeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
