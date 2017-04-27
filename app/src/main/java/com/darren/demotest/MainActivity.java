package com.darren.demotest;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.darren.demotest.utils.MyDBHelper;
import com.darren.demotest.view.activity.SelectPicPopupWindowActivity;

import java.util.HashMap;

import cn.sharesdk.alipay.friends.Alipay;
import cn.sharesdk.douban.Douban;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.friends.Wechat;


public class MainActivity extends AppCompatActivity implements PlatformActionListener {
    private EditText et_name, et_pwd;
    private MyDBHelper dbHelper;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDBHelper(this, "UserStore.db", null, 1);
        findViews();
    }

    private void findViews() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = et_name.getText().toString();
                String pwd = et_pwd.getText().toString();
                if (login(name, pwd)) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    //intent = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_newUser:
                intent = new Intent(MainActivity.this, SelectPicPopupWindowActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_forgetNumber:
                startActivity(new Intent(MainActivity.this,SelectPicPopupWindowActivity.class));
                break;
            case R.id.ibtn_sina:
                loginBySina();
                break;
            case R.id.ibtn_wechat:
                loginByWechat();
                break;
            case R.id.ibtn_qq:
                loginByQQ();
                break;
            case R.id.ibtn_pay:
                loginByPay();
                break;
            case R.id.ibtn_douban:
                loginByDouBan();
                break;
            case R.id.ibtn_tencentweibo:
                loginByTencentWeibo();
                break;
        }
    }

    private boolean login(String name, String pwd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where name =? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{name, pwd});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    private void loginByTencentWeibo() {
        Platform tencent = ShareSDK.getPlatform(this, TencentWeibo.NAME);
        tencent.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        tencent.authorize();//单独授权,OnComplete返回的hashmap是空的
        //tencent.showUser(null);//授权并获取用户信息
        //移除授权
        //tencent.removeAccount(true);

    }

    private void loginByDouBan() {
        Platform douban = ShareSDK.getPlatform(this, Douban.NAME);
        douban.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        douban.authorize();//单独授权,OnComplete返回的hashmap是空的
        //meipai.showUser(null);//授权并获取用户信息
        //移除授权
        //meipai.removeAccount(true);

    }

    private void loginByPay() {
        Platform pay = ShareSDK.getPlatform(this, Alipay.NAME);
        pay.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        pay.authorize();//单独授权,OnComplete返回的hashmap是空的
        //pay.showUser(null);//授权并获取用户信息
        //移除授权
        //pay.removeAccount(true);

    }

    private void loginByQQ() {
        Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
        qq.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        qq.authorize();//单独授权,OnComplete返回的hashmap是空的
        //qq.showUser(null);//授权并获取用户信息
        //移除授权
        //qq.removeAccount(true);

    }

    private void loginByWechat() {
        Platform wechat = ShareSDK.getPlatform(this, Wechat.NAME);
        wechat.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        wechat.authorize();//单独授权,OnComplete返回的hashmap是空的
        //wechat.showUser(null);//授权并获取用户信息
        //移除授权
        //wechat.removeAccount(true);

    }

    private void loginBySina() {
        Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
        weibo.setPlatformActionListener(this);
        //authorize与showUser单独调用一个即可
        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        //weibo.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Looper.prepare();
        String result = platform.getDb().exportData();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, "授权取消", Toast.LENGTH_SHORT).show();
    }
}
