package com.example.htz.bookmasystem.util;

import com.example.htz.bookmasystem.dao.BookDao;
import com.example.htz.bookmasystem.dao.impl.BookDaoImpl;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DaoFactory {
    public static BookDao getBookDao(){
        return new BookDaoImpl();
    }
}
