package com.example.htz.bookmasystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.htz.bookmasystem.model.Book;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class InsertActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "InsertActivity";

    private EditText mTvbnov;
    private EditText mTvbnamev;
    private EditText mTvbauthorv;
    private EditText mTvbnumv;
    private EditText mTvbpricev;
    private EditText mTvbversionv;
    private EditText mTvbpressv;
    private Button backBtn;
    private Button submitBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);
        initView();
    }

    private void initData(Book book) {
        mTvbnov.setText(book.getBno());
        mTvbnamev.setText(book.getBname());
        mTvbauthorv.setText(book.getAuthor());
        mTvbnumv.setText(String.valueOf(book.getBnum()));
        mTvbpricev.setText(String.valueOf(book.getBprice()));
        mTvbversionv.setText(book.getBversion());
        mTvbpressv.setText(book.getBpress());
    }

    private void initView() {
        mTvbnov = (EditText) findViewById(R.id.tvbnov);
        mTvbnamev = (EditText) findViewById(R.id.tvbnamev);
        mTvbauthorv = (EditText) findViewById(R.id.tvbauthorv);
        mTvbnumv = (EditText) findViewById(R.id.tvbnumv);
        mTvbpricev = (EditText) findViewById(R.id.tvbpricev);
        mTvbversionv = (EditText) findViewById(R.id.tvbversionv);
        mTvbpressv = (EditText) findViewById(R.id.tvbpressv);
        backBtn = (Button)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
        submitBtn = (Button)findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:{
                finish();
                break;
            }
            case R.id.submit_btn:{
                String no = mTvbnov.getText().toString();
                String name = mTvbnamev.getText().toString();
                String author = mTvbauthorv.getText().toString();
                String bnum = mTvbnumv.getText().toString();
                String price = mTvbpricev.getText().toString();
                String version = mTvbversionv.getText().toString();
                String press = mTvbpressv.getText().toString();

                if(Book.check(no,name,author,bnum,price,version,press)){
                    Book newBook = new Book();
                    newBook.setBno(no);
                    newBook.setBname(name);
                    newBook.setAuthor(author);
                    newBook.setBnum(Integer.valueOf(bnum));
                    newBook.setBprice(Double.parseDouble(price));
                    newBook.setBversion(version);
                    newBook.setBpress(press);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newBook",newBook);
                    intent.putExtra("bundle",bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(InsertActivity.this,"",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}

