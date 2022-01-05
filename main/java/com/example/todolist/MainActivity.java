package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> toDoList;
//ArrayAdapterはデータの一覧をリストなどのビューに使用するためのクラス
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
//オーバーライドする
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList = new ArrayList<>();
// xmlファイルで作ったファイルがR.javaファイルに保存される
        arrayAdapter = new ArrayAdapter<>( this,R.layout.list_view_layout,toDoList);
        //findViewByIdでxmlで定義したIDを指定
        listView = findViewById(R.id.id_list_view);

        listView.setAdapter(arrayAdapter);
        //対象となるボタンがイベントを受け取れるように設定
        //引数にインターフェースを実装したクラスオブジェクトの指定か、this
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        editText = findViewById(R.id.id_edit_text);
    }

    public void addItemToList(View view){
        toDoList.add(editText.getText().toString());
        //Adapterにデータの内容が変更になったことを通知して初めて再描画される。
        arrayAdapter.notifyDataSetChanged();
        editText.setText("");
    }
}