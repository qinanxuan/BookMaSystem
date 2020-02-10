package com.example.htz.bookmasystem.adapter;

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.htz.bookmasystem.R;
//import com.example.htz.bookmasystem.model.Book;
//
//import java.util.List;
//
///**
// * @author Admin
// * @version $Rev$
// * @des ${TODO}
// * @updateAuthor $Author$
// * @updateDes ${TODO}
// */
//public class BookListAdapter extends BaseAdapter {
//    private TextView tvbnov, tvbnamev, tvbauthorv, tvbnumv, tvbpricev, tvbversionv, tvbpressv;
//    private List<Book> bookList;
//    private Context context;
//    private int resource;
//
//
//    public BookListAdapter(Context context, int resource, List<Book> bookList) {
//        this.context = context;
//        this.resource = resource;
//        this.bookList = bookList;
//    }
//
//    @Override
//
//    public int getCount() {
//        return bookList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return bookList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            //设置数据项的布局样式
//            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
//        }
//
//        //获取数据项布局里面的ImageView,TextView句柄
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.bookimg);
//        tvbnov = (TextView) convertView.findViewById(R.id.tvbnov);
//        tvbnamev = (TextView) convertView.findViewById(R.id.tvbnamev);
//        tvbauthorv = (TextView) convertView.findViewById(R.id.tvbauthorv);
//        tvbnumv = (TextView) convertView.findViewById(R.id.tvbnumv);
//        tvbpricev = (TextView) convertView.findViewById(R.id.tvbpricev);
//        tvbversionv = (TextView) convertView.findViewById(R.id.tvbversionv);
//        tvbpressv = (TextView) convertView.findViewById(R.id.tvbpressv);
//        //获取点击的数据项信息
//        Book book = bookList.get(position);
//
//        //将数据项信息填充到imageView,TextView
//        imageView.setImageResource(R.drawable.ic_launcher_background);
//        tvbnov.setText("编号："+book.getBno());
//        tvbnamev.setText("书名：《" + book.getBname() + "》");
//        tvbauthorv.setText("作者：" + book.getAuthor());
//        tvbnumv.setText("馆藏数量：" + book.getBnum());
//        tvbpricev.setText("价格：¥" + book.getBprice());
//        tvbversionv.setText("版次：" + book.getBversion());
//        tvbpressv.setText("出版社：" + book.getBpress());
//
//        return convertView;
//    }
//}



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.htz.bookmasystem.R;
import com.example.htz.bookmasystem.model.Book;

import java.util.List;




public class BookListAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Book> bookList;
    private Context context;
    private int resource;
    private InnerItemOnclickListener mListener;


    public BookListAdapter(Context context, int resource, List<Book> bookList) {
        this.context = context;
        this.resource = resource;
        this.bookList = bookList;
    }

    @Override

    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;


        if (convertView == null) {
            viewHolder = new ViewHolder();
            //设置数据项的布局样式
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder.book_img=(ImageView) convertView.findViewById(R.id.bookimg);
            viewHolder.tvbnov = (TextView) convertView.findViewById(R.id.tvbnov);
            viewHolder.tvbnamev = (TextView) convertView.findViewById(R.id.tvbnamev);
            viewHolder.tvbauthorv = (TextView) convertView.findViewById(R.id.tvbauthorv);
            viewHolder.tvbnumv = (TextView) convertView.findViewById(R.id.tvbnumv);
            viewHolder.tvbpricev = (TextView) convertView.findViewById(R.id.tvbpricev);
            viewHolder.tvbversionv = (TextView) convertView.findViewById(R.id.tvbversionv);
            viewHolder.tvbpressv = (TextView) convertView.findViewById(R.id.tvbpressv);
            viewHolder.img_delete = (ImageView) convertView.findViewById(R.id.img_delete);
            viewHolder.img_compile = (ImageView) convertView.findViewById(R.id.img_compile);

            convertView.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.img_compile.setOnClickListener(this);
        viewHolder.img_delete.setOnClickListener(this);
        viewHolder.tvbnov.setTag(position);
        viewHolder.img_compile.setTag(position);
        viewHolder.img_delete.setTag(position);

        //获取点击的数据项信息
        Book book = bookList.get(position);

        //将数据项信息填充到ImageView,TextView
        viewHolder.book_img.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.tvbnov.setText("编号："+book.getBno());
        viewHolder.tvbnamev.setText("书名：《" + book.getBname() + "》");
        viewHolder.tvbauthorv.setText("作者：" + book.getAuthor());
        viewHolder.tvbnumv.setText("馆藏数量：" + book.getBnum());
        viewHolder.tvbpricev.setText("价格：¥" + book.getBprice());
        viewHolder.tvbversionv.setText("版次：" + book.getBversion());
        viewHolder.tvbpressv.setText("出版社：" + book.getBpress());

        return convertView;
    }

    class ViewHolder {
        TextView tvbnov, tvbnamev, tvbauthorv, tvbnumv, tvbpricev, tvbversionv, tvbpressv;
        ImageView book_img,img_delete, img_compile;
    }

    public interface InnerItemOnclickListener  {
        void itemClick(View v);
    }

    public void setListener(InnerItemOnclickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
