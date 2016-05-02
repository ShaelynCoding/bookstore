package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lyn on 16-3-29.
 */
@Entity(name = "book")
public class Book {
    private String bookIsdn;
    private String bookAuth;
    private String bookName;
    private Double bookPrice;
    private Integer bookNum;
    private String bookType;

    @Id
    @Column(name = "bookISDN")
    public String getBookIsdn() {
        return bookIsdn;
    }

    public void setBookIsdn(String bookIsdn) {
        this.bookIsdn = bookIsdn;
    }

    @Basic
    @Column(name = "bookAuth")
    public String getBookAuth() {
        return bookAuth;
    }

    public void setBookAuth(String bookAuth) {
        this.bookAuth = bookAuth;
    }

    @Basic
    @Column(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "bookPrice")
    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Basic
    @Column(name = "bookNum")
    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    @Basic
    @Column(name = "bookType")
    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookIsdn != null ? !bookIsdn.equals(book.bookIsdn) : book.bookIsdn != null) return false;
        if (bookAuth != null ? !bookAuth.equals(book.bookAuth) : book.bookAuth != null) return false;
        if (bookName != null ? !bookName.equals(book.bookName) : book.bookName != null) return false;
        if (bookPrice != null ? !bookPrice.equals(book.bookPrice) : book.bookPrice != null) return false;
        if (bookNum != null ? !bookNum.equals(book.bookNum) : book.bookNum != null) return false;
        if (bookType != null ? !bookType.equals(book.bookType) : book.bookType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookIsdn != null ? bookIsdn.hashCode() : 0;
        result = 31 * result + (bookAuth != null ? bookAuth.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookPrice != null ? bookPrice.hashCode() : 0);
        result = 31 * result + (bookNum != null ? bookNum.hashCode() : 0);
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        return result;
    }
}
