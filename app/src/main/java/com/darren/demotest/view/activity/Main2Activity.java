package com.darren.demotest.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.darren.demotest.MainActivity;
import com.darren.demotest.R;
import com.darren.demotest.utils.MyDBHelper;

public class Main2Activity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private EditText et_inputPwd,et_inputName;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbHelper = new MyDBHelper(this,"UserStore.db",null,1);
        findViews();
    }

    private void findViews() {
        et_inputName= (EditText) findViewById(R.id.et_inputName);
        et_inputPwd= (EditText) findViewById(R.id.et_inputPwd);
    }
    public void logon(View view){
        String newname=et_inputName.getText().toString();
        String newpwd=et_inputPwd.getText().toString();
        if(CheckIsDataAlreadyInDBorNot(newname)){
            Toast.makeText(this,"该用户名已被注册，注册失败",Toast.LENGTH_SHORT).show();
        }else {
            if (register(newname, newpwd)) {
                Toast.makeText(this, "插入数据表成功", Toast.LENGTH_SHORT).show();
                intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public boolean register(String newname, String newpwd) {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        /*String sql = "insert into userData(name,password) value(?,?)";
        Object obj[]={username,password};
        db.execSQL(sql,obj);*/
        ContentValues values=new ContentValues();
        values.put("name",newname);
        values.put("password",newpwd);
        db.insert("userData",null,values);
        db.close();
        //db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
        return true;
    }

    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String Query = "Select * from userData where name =?";
        Cursor cursor = db.rawQuery(Query,new String[] { value });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }
}
