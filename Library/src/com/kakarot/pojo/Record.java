package com.kakarot.pojo;

public class Record {
    private Integer id;
    private Integer userid;
    private Integer bookid;
    private String borrowTime;
    private String returnTime;

    private String uname;
    private String bname;

    private String bstime;
    private String betime;

    private String rstime;
    private String retime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBstime() {
        return bstime;
    }

    public void setBstime(String bstime) {
        this.bstime = bstime;
    }

    public String getBetime() {
        return betime;
    }

    public void setBetime(String betime) {
        this.betime = betime;
    }

    public String getRstime() {
        return rstime;
    }

    public void setRstime(String rstime) {
        this.rstime = rstime;
    }

    public String getRetime() {
        return retime;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }
}
