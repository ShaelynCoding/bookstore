package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lyn on 16-3-29.
 */
@Entity(name = "Information")
public class Information {
    private int infoId;
    private Integer userId;
    private String bookIsdn;
    private Integer buyNum;
    private String time;

    @Id
    @Column(name = "infoId")
    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "bookISDN")
    public String getBookIsdn() {
        return bookIsdn;
    }

    public void setBookIsdn(String bookIsdn) {
        this.bookIsdn = bookIsdn;
    }



    @Basic
    @Column(name = "buyNum")
    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Information that = (Information) o;

        if (infoId != that.infoId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bookIsdn != null ? !bookIsdn.equals(that.bookIsdn) : that.bookIsdn != null) return false;
        if (buyNum != null ? !buyNum.equals(that.buyNum) : that.buyNum != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = infoId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (bookIsdn != null ? bookIsdn.hashCode() : 0);
        result = 31 * result + (buyNum != null ? buyNum.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
