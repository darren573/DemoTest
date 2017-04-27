package com.darren.demotest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.darren.demotest.R;

public class SelectPicPopupWindowActivity extends Activity implements View.OnClickListener {
private Button btn_register,btn_forget,btn_cancel;
    private LinearLayout layout;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        findViews();
        init();
    }

    private void init() {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btn_register.setOnClickListener(this);
        btn_forget.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    private void findViews() {
        btn_register= (Button) findViewById(R.id.btn_register);
        btn_forget= (Button) findViewById(R.id.btn_forget);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        layout= (LinearLayout) findViewById(R.id.pop_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                intent=new Intent(SelectPicPopupWindowActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_forget:
                intent=new Intent(SelectPicPopupWindowActivity.this,ForgetNumActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cancel:
                break;
        }
        finish();
    }
}
