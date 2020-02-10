package com.example.htz.bookmasystem;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.htz.bookmasystem.adapter.BookListAdapter;
import com.example.htz.bookmasystem.model.Book;
import com.example.htz.bookmasystem.util.DaoFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookListAdapter.InnerItemOnclickListener,AdapterView.OnItemClickListener {
    public static final int UPDATE_REQUEST_CODE = 1;
    public static final int INSERT_REQUEST_CODE = 2;
    private static final String TAG = "MainActivity";
    Button mButton;
    private Spinner spfindtype;
    private EditText et_find;
    private ImageView img_find, img_compile, img_delete;
    private String findtype;
    List<Book> bookList = new ArrayList<>();
    BookListAdapter bookListAdapter;
    ListView lvbookinfo;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvbookinfo = (ListView) findViewById(R.id.lvbookinfo);

        String inistr = "{\"data\":[{\"bno\":\"0000001\",\"bname\":\"西游记\",\"author\":\"吴承恩\",\"bnum\":9,\"bpress\":\"人民出版社\",\"bprice\":24.5,\"bversion\":\"第四版\"},{\"bno\":\"0000005\",\"bname\":\"深入理解计算机系统\",\"author\":\"Randal E.Bryant\",\"bnum\":3,\"bpress\":\"机械工业出版社\",\"bprice\":139.0,\"bversion\":\"第三版\"},{\"bno\":\"0000003\",\"bname\":\"水浒传\",\"author\":\"施耐庵\",\"bnum\":10,\"bpress\":\"人民出版社\",\"bprice\":26.0,\"bversion\":\"第三版\"}]}";
        JSONObject receObject = JSONObject.parseObject(inistr);
        JSONArray receArr = receObject.getJSONArray("data");
        bookList = JSONObject.parseArray(receArr.toJSONString(), Book.class);
        bookListAdapter = new BookListAdapter(this, R.layout.list_cell, bookList);
        bookListAdapter.setListener(this);
        lvbookinfo.setAdapter(bookListAdapter);
        lvbookinfo.setOnItemClickListener(this);


        //        final String selallstr = "{\"type\":\"selectall\"}";
        //        final String selonestr = "{\"type\":\"selectone\",\"data\":{\"bno\":\"0000001\"}}";
        //        final String insstr = "{\"type\":\"insert\",\"data\":{\"author\":\"吴承恩\",\"bname\":\"西游记后传\",\"bno\":\"0000008\",\"bnum\":5,\"bpress\":\"人民出版社\",\"bprice\":24.5,\"bversion\":\"第一版\"}}";
        //        final String updstr = "{\"type\":\"update\",\"data\":{\"author\":\"吴承恩\",\"bname\":\"西游记后传\",\"bno\":\"0000008\",\"bnum\":14,\"bpress\":\"人民出版社\",\"bprice\":24.5,\"bversion\":\"第二版\"}}";
        //        final String delstr = "{\"type\":\"delete\",\"data\":{\"bno\":\"0000008\"}}";
        //        mButton = (Button) findViewById(R.id.button);
        //        mButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                new Thread(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        List<Book> books = DaoFactory.getBookDao().getAllBooks();
        //                        Log.d(TAG, books.toString());
        //                        showSelAllResponse(books);
        //
        //                        String selallreq = HttpClientUtil.request(selallstr, "AppServlet");
        //                        Log.d(TAG, "run:  " + selallreq);
        //
        //                        String selonereq = HttpClientUtil.request(selonestr, "AppServlet");
        //                        Log.d(TAG, "run:  " + selonereq);
        //
        //                        String inreq = HttpClientUtil.request(insstr, "AppServlet");
        //                        Log.d(TAG, "run:" + inreq);
        //
        //                        String updreq = HttpClientUtil.request(updstr, "AppServlet");
        //                        Log.d(TAG, "run:" + updreq);
        //
        //                        String delreq = HttpClientUtil.request(delstr, "AppServlet");
        //                        Log.d(TAG, "run:" + delreq);
        //                    }
        //
        //
        //                }).start();
        //
        //            }
        //        });

        //        下拉列表

        /**
         * 选择查询类型
         * 对Spinner添加事件监听
         */
        String[] findtypes = new String[]{"所有", "按书号", "更多（按作者）"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, findtypes);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        spfindtype = (Spinner) findViewById(R.id.spfindtype);
        spfindtype.setAdapter(adapter);
        spfindtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findtype = (String) spfindtype.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                findtype = "所有";
            }
        });

        /**
         *对查询内容文本编辑框添加事件监听
         */
        et_find = (EditText) findViewById(R.id.et_find);
        img_find = (ImageView) findViewById(R.id.imgv_find);
        img_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (findtype.equals("按书号")) {
                            String selbno = et_find.getText().toString().trim();
                            if (selbno == null || selbno.length() == 0) {
                                showTips(1, "输入内容不能为空！");
                            } else {
                                Book book = DaoFactory.getBookDao().getBookByBno(selbno);
                                List<Book> books= new ArrayList<>(Arrays.asList(book));
                                showResponse(books);
                            }
                        }
                        if (findtype.equals("所有")) {
                            List<Book> books= DaoFactory.getBookDao().getAllBooks();
                            showResponse(books);
                        }
                    }
                }).start();
            }


        });

        add_btn = (Button) findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);

                startActivityForResult(intent,INSERT_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showTips(1,"hello!");
    }

    @Override
    public void itemClick(View v) {
        Log.d(TAG, "itemClick: "+v);
        int position = (Integer) v.getTag();
        final Book book = bookList.get(position);
        switch (v.getId()) {
            case R.id.img_delete:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        buildDialog();
                        if (DaoFactory.getBookDao().delBook(book.getBno())) {
                            List<Book> books= DaoFactory.getBookDao().getAllBooks();
                            showResponse(books);
                            showTips(1, "删除成功！");
                        }else{
                            showTips(1, "删除失败！");
                        }
                    }
                }).start();
                break;
            case R.id.img_compile:{
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",book);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("bundle", bundle);
                this.startActivityForResult(intent,UPDATE_REQUEST_CODE);
                break;
            }
            default:
                break;
        }
    }

    private void showResponse(final List<Book> books) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bookList.clear();
                bookList.addAll(books);
                bookListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showTips(final int i, final String str) {
        switch (i) {
            case 1:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                    }
                });

                /*
另法：
                Looper.prepare();
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                Looper.loop();

                */
                break;
            default:
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case UPDATE_REQUEST_CODE:{
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getBundleExtra("bundle");
                    final Book newBook = (Book) bundle.getSerializable("newBook");
                    Log.d(TAG, "onActivityResult: "+newBook.toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (DaoFactory.getBookDao().updateBook(newBook)){
                                List<Book> books= DaoFactory.getBookDao().getAllBooks();
                                showResponse(books);
                                showTips(1, "修改成功！");
                            }else{
                                showTips(1, "修改失败！");

                            }
                        }
                    }).start();
                }
                break;
            }
            case INSERT_REQUEST_CODE:{
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getBundleExtra("bundle");
                    final Book newBook = (Book) bundle.getSerializable("newBook");
                    Log.d(TAG, "onActivityResult: "+newBook.toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //传给Servelt
                            if (DaoFactory.getBookDao().insertBook(newBook)){
                                List<Book> books= DaoFactory.getBookDao().getAllBooks();
                                showResponse(books);
                                showTips(1, "插入成功！");
                            }else{
                                showTips(1, "插入失败！");

                            }
                        }
                    }).start();
                }
                break;
            }
        }
    }

    private void buildDialog(){
        Builder builder= new Builder(MainActivity.this);
        builder.setTitle("确认删除？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("确认", null);
        builder.show();
    }
}
