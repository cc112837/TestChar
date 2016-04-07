package com.henli.user.aty;

import com.example.save.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class KnowledgeActivity extends Activity implements View.OnClickListener {
    private Button btn_head;
    private Button btn_brest;
    private Button btn_hand1;
    private Button btn_hand2;
    private Button btn_buttom;
    private Button btn_foot;
    private int flag=1;
    private Button btn_sex_child;
    private Button btn_sex_women;
    private Button btn_sex_man;
    private Button btn_remind;
    private ImageView iv_class;
    private ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        init();

    }

    private void init() {
    	iv_search=(ImageView) findViewById(R.id.iv_search);
    	iv_search.setOnClickListener(this);
    	iv_class = (ImageView) findViewById(R.id.iv_class);
        btn_head = (Button) findViewById(R.id.btn_head);
        btn_brest = (Button) findViewById(R.id.btn_brest);
        btn_hand1 = (Button) findViewById(R.id.btn_hand1);
        btn_hand2 = (Button) findViewById(R.id.btn_hand2);
        btn_buttom = (Button) findViewById(R.id.btn_buttom);
        btn_foot = (Button) findViewById(R.id.btn_foot);
        btn_sex_child = (Button) findViewById(R.id.btn_sex_child);
        btn_sex_women = (Button) findViewById(R.id.btn_sex_women);
        btn_sex_man = (Button) findViewById(R.id.btn_sex_man);
        btn_remind=(Button) findViewById(R.id.btn_remind);
        btn_sex_child.setOnClickListener(this);
        btn_sex_women.setOnClickListener(this);
        btn_sex_man.setOnClickListener(this);
        btn_head.setOnClickListener(this);
        btn_brest.setOnClickListener(this);
        btn_hand1.setOnClickListener(this);
        btn_hand2.setOnClickListener(this);
        btn_buttom.setOnClickListener(this);
        btn_foot.setOnClickListener(this);
        btn_remind.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        Intent intent = new Intent(KnowledgeActivity.this, ItemActivity.class);
        switch (v.getId()) {
            case R.id.btn_sex_man:
                iv_class.setImageResource(R.drawable.man);
                flag=1;
                break;
            case R.id.btn_sex_women:

                iv_class.setImageResource(R.drawable.women);
                flag=2;
                break;
            case R.id.btn_sex_child:

                iv_class.setImageResource(R.drawable.child);
                flag=0;
                break;
            case R.id.btn_head:
                if (flag==0) {
                    intent.putExtra("tag", 6);
                } else {
                    intent.putExtra("tag", 1);
                }
                startActivity(intent);
                break;
            case R.id.btn_hand1:
            case R.id.btn_hand2:
                if (flag==0) {  
                    intent.putExtra("tag", 6);
                } else {
                    intent.putExtra("tag", 2);
                }
                startActivity(intent);
                break;

            case R.id.btn_brest:
                if (flag==1) {
                    intent.putExtra("tag", 3);
                } else if (flag==2) {
                    intent.putExtra("tag", 7);
                } else if (flag==0) {
                    intent.putExtra("tag", 6);
                }
                startActivity(intent);
                break;
            case R.id.btn_foot:
                if (flag==0) {
                    intent.putExtra("tag", 6);
                } else {
                    intent.putExtra("tag", 4);
                }
                startActivity(intent);
                break;
            case R.id.btn_buttom:
                if (flag==1) {
                    intent.putExtra("tag", 5);
                } else if (flag==2) {
                    intent.putExtra("tag", 8);
                } else if (flag==0) {
                    intent.putExtra("tag", 6);
                }
                startActivity(intent);
                 break;
            case R.id.btn_remind:
                startActivity(new Intent(KnowledgeActivity.this, MycollectActivity.class));
                break;
            case R.id.iv_search:
            	startActivity(new Intent(KnowledgeActivity.this, SearchActivity.class));
            	break;
        }
    }
}
