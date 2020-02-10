package com.example.htz.bookmasystem.model;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

import android.text.TextUtils;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 定义一本书的实体类
 * 属性有：
 * 书号
 * 书名
 * 作者
 * 馆藏数量
 * 价格
 * 版次
 * 出版社
 * 书的图片
 */
public class Book implements Serializable {
    public String bno;
    public String bname;
    public String author;
    public Integer bnum;
    public Double bprice;
    public String bversion;
    public String bpress;
    private InputStream bookphoto;

    public static boolean check(String bno,String bname,
                                String author,String bnum,String bprice,String bversion,String bpress){
        return !TextUtils.isEmpty(bno)
                && !TextUtils.isEmpty(bname)
                && !TextUtils.isEmpty(author)
                && !TextUtils.isEmpty(bnum)
                && !TextUtils.isEmpty(bprice)
                && !TextUtils.isEmpty(bversion)
                && !TextUtils.isEmpty(bpress);
    }

    public Book(){}

    public Book(String bno, String bname, String author, Integer bnum, Double bprice, String bversion, String bpress, InputStream bookphoto) {
        this.bno = bno;
        this.bname = bname;
        this.author = author;
        this.bnum = bnum;
        this.bprice = bprice;
        this.bversion = bversion;
        this.bpress = bpress;
        this.bookphoto = bookphoto;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBnum() {
        return bnum;
    }

    public void setBnum(Integer bnum) {
        this.bnum = bnum;
    }

    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    public String getBversion() {
        return bversion;
    }

    public void setBversion(String bversion) {
        this.bversion = bversion;
    }

    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }
    public InputStream getBookphoto() {
        return bookphoto;
    }

    public void setBookphoto(InputStream bookphoto) {
        this.bookphoto = bookphoto;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bno='" + bno + '\'' +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", bnum=" + bnum +
                ", bprice=" + bprice +
                ", bversion='" + bversion + '\'' +
                ", bpress='" + bpress + '\'' +
                ", bookphoto=" + bookphoto +
                '}';
    }
}
