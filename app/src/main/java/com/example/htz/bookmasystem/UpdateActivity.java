package com.example.htz.bookmasystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.htz.bookmasystem.model.Book;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UpdateActivity";

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
        setContentView(R.layout.update_layout);
        initView();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        Book book = (Book) bundle.getSerializable("book");
        initData(book);
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
                Book newBook = new Book();
                newBook.setBno(mTvbnov.getText().toString());
                newBook.setBname(mTvbnamev.getText().toString());
                newBook.setAuthor(mTvbauthorv.getText().toString());
                newBook.setBnum((Integer.parseInt(mTvbnumv.getText().toString())));
                newBook.setBprice(Double.parseDouble(mTvbpricev.getText().toString()));
                newBook.setBversion(mTvbversionv.getText().toString());
                newBook.setBpress(mTvbpressv.getText().toString());

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("newBook",newBook);
                intent.putExtra("bundle",bundle);
                setResult(RESULT_OK,intent);
                finish();
                break;
            }
        }
    }
}
