package com.example.htz.bookmasystem.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.htz.bookmasystem.dao.BookDao;
import com.example.htz.bookmasystem.model.Book;
import com.example.htz.bookmasystem.util.HttpClientUtil;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class BookDaoImpl implements BookDao {

    JSONObject sendObject = new JSONObject();
    JSONObject receObject = new JSONObject();
    JSONObject midObject = new JSONObject();
    String recMsg = null;
    String sendMsg = null;

    @Override
    public List<Book> getAllBooks() {
        sendObject.put("type", "selectall");
        sendMsg = sendObject.toString();
        recMsg = HttpClientUtil.request(sendMsg, "AppServlet");
        receObject = JSONObject.parseObject(recMsg);
        if (receObject.getBoolean("result")) {
            JSONArray receArr = receObject.getJSONArray("data");
            List<Book> books = JSONObject.parseArray(receArr.toJSONString(), Book.class);
            return books;
        }else {
            return null;
        }
    }

    @Override
    public Book getBookByBno(String bno) {
        sendObject.put("type", "selectone");
        midObject.put("bno", bno);
        sendObject.put("data", midObject.toJSONString());
        sendMsg = sendObject.toJSONString();
        recMsg = HttpClientUtil.request(sendMsg, "AppServlet");
        receObject = JSONObject.parseObject(recMsg);
        if (receObject.getBoolean("result")) {
            Book book = JSONObject.parseObject(receObject.get("data").toString(), Book.class);
            return book;
        }else {
            return null;
        }

    }

    @Override
    public boolean insertBook(Book book) {
        sendObject.put("type", "insert");
        sendObject.put("data", JSON.toJSONString(book));
        sendMsg = sendObject.toJSONString();
        recMsg = HttpClientUtil.request(sendMsg, "AppServlet");
        receObject = JSONObject.parseObject(recMsg);
        if (receObject.getBoolean("result")) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delBook(String bno) {
        sendObject.put("type", "delete");
        midObject.put("bno", bno);
        sendObject.put("data", midObject.toJSONString());
        sendMsg = sendObject.toJSONString();
        recMsg = HttpClientUtil.request(sendMsg, "AppServlet");
        receObject = JSONObject.parseObject(recMsg);
        if (receObject.getBoolean("result")) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        sendObject.put("type", "update");
        sendObject.put("data", JSON.toJSONString(book));
        sendMsg = sendObject.toJSONString();
        recMsg = HttpClientUtil.request(sendMsg, "AppServlet");
        receObject = JSONObject.parseObject(recMsg);
        if (receObject.getBoolean("result")) {
            return true;
        }else {
            return false;
        }
    }
}
