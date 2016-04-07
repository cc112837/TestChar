package com.henli.user.aty;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.save.R;
import com.google.gson.Gson;
import com.henli.adapter.ChestAdapter;
import com.henli.adapter.ChildAdapter;
import com.henli.adapter.EngAdapter;
import com.henli.adapter.HandAdapter;
import com.henli.adapter.HeadAdapter;
import com.henli.adapter.LegAdapter;
import com.henli.adapter.WomenChestAdapter;
import com.henli.adapter.WomenbutAdapter;
import com.henli.data.Small;
import com.henli.data.Small.HeadEntity;
import com.henli.db.FinalValue;
import com.henli.db.MySqliteOpenHelper;
import com.henli.util.ReadUtil;

import java.util.List;

public class ItemActivity extends Activity {
    private ListView lv_showid;
    private MySqliteOpenHelper helper;// 数据库连接对�?
    private SQLiteDatabase database;// 数据�?
    private RelativeLayout rl_video;
    private int point;
    private Button btn_return;
    private ContentValues content;
	Small small;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item);
        initDB();
        initView();
        init();
    }


    private void initView() {
    	btn_return=(Button) findViewById(R.id.btn_return);
    	rl_video=(RelativeLayout) findViewById(R.id.rl_video);
        lv_showid = (ListView) findViewById(R.id.lv_showid);
        btn_return.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}


	private void initDB() {
        helper = new MySqliteOpenHelper(this, FinalValue.DB_NAME, null,
                FinalValue.DB_VERSION);
        database = helper.getReadableDatabase();
    }


    private void init() {
    	rl_video=(RelativeLayout) findViewById(R.id.rl_video);
        lv_showid = (ListView) findViewById(R.id.lv_showid);
        final Intent intent = getIntent();
        small = new Gson().fromJson(ReadUtil.readFromRaw(getApplicationContext()), Small.class);
        //insertData();
        final int tag = intent.getIntExtra("tag", 0);
       
        lv_showid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            
   
			@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(ItemActivity.this, DetailActivity.class);
                if (tag==1) {
                	detail.putExtra("TAG",1);
                	detail.putExtra("title", small.getHead().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getHead().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getHead().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                  
                } else if (tag == 2) {
                	detail.putExtra("TAG",2);
                	detail.putExtra("title", small.getHand().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getHand().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getHand().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                    
                } else if (tag == 3) {
                	detail.putExtra("TAG",3);
                	detail.putExtra("title", small.getChest().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getChest().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getChest().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                    
                } else if (tag == 4) {
                	detail.putExtra("TAG",4);
                	detail.putExtra("title", small.getLeg().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getLeg().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						
						detail.putExtra("text", small.getLeg().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                    
                } else if (tag == 5) {
                	detail.putExtra("TAG",5);
                	detail.putExtra("title", small.getEngender().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getEngender().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getEngender().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                   
                } else if (tag == 6) {
                	detail.putExtra("TAG",6);
                	detail.putExtra("title", small.getChildren().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getChildren().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getChildren().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                    
                } else if (tag == 7) {
                	detail.putExtra("TAG",7);
                	detail.putExtra("title", small.getWomenchest().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getWomenchest().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getWomenchest().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                    
                } else if (tag == 8) {
                	detail.putExtra("TAG",8);
                	detail.putExtra("title", small.getWomensanjiao().get(position).getTitle() + "");
                	if (position==0) {
                		detail.putExtra("single", small.getWomensanjiao().get(0).getDetail()+"");
                 		detail.putExtra("point",0);
					}else {
						detail.putExtra("text", small.getWomensanjiao().get(position).getDetail() + "");
						detail.putExtra("point",1);
					}
                   
                }
                startActivity(detail);
            }
        });
        
        ContentValues contentValues=new ContentValues();
        switch (tag) {
            case 1:
                List<Small.HeadEntity> head = small.getHead();
                HeadAdapter adapter1 = new HeadAdapter(getApplicationContext(), head,contentValues,database);
                
                lv_showid.setAdapter(adapter1);
                break;
            case 2:
                HandAdapter adapter2 = new HandAdapter(getApplicationContext(), small.getHand(),contentValues,database);
               
                lv_showid.setAdapter(adapter2);
                break;
            case 3:
                ChestAdapter adapter3 = new ChestAdapter(getApplicationContext(), small.getChest(),contentValues,database);
                
                lv_showid.setAdapter(adapter3);
                break;
            case 5:
                EngAdapter adapter4 = new EngAdapter(getApplicationContext(), small.getEngender(),contentValues,database);
               
                lv_showid.setAdapter(adapter4);
                break;
            case 4:
                LegAdapter adapter5 = new LegAdapter(getApplicationContext(), small.getLeg(),contentValues,database);
               
                lv_showid.setAdapter(adapter5);
                break;
            case 6:
                ChildAdapter adapter6 = new ChildAdapter(getApplicationContext(), small.getChildren(),contentValues,database);
                
                lv_showid.setAdapter(adapter6);
                break;
            case 7:
                WomenChestAdapter adapter7 = new WomenChestAdapter(getApplicationContext(), small.getWomenchest(),contentValues,database);
                
                lv_showid.setAdapter(adapter7);
                break;
            case 8:
                WomenbutAdapter adapter8 = new WomenbutAdapter(getApplicationContext(), small.getWomensanjiao(),contentValues,database);
               
                lv_showid.setAdapter(adapter8);
                break;

        }


    }


	/*private void insertData() {
		content=new ContentValues();
        for (int i = 0; i <  small.getHead().size(); i++) {
            content.put("title",small.getHead().get(i).getTitle());
            content.put("detail",small.getHead().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getHand().size(); i++) {
            content.put("title",small.getHand().get(i).getTitle());
            content.put("detail",small.getHand().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getChest().size(); i++) {
            content.put("title",small.getChest().get(i).getTitle());
            content.put("detail",small.getChest().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getWomensanjiao().size(); i++) {
            content.put("title",small.getWomensanjiao().get(i).getTitle());
            content.put("detail",small.getWomensanjiao().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getLeg().size(); i++) {
            content.put("title",small.getLeg().get(i).getTitle());
            content.put("detail",small.getLeg().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getEngender().size(); i++) {
            content.put("title",small.getEngender().get(i).getTitle());
            content.put("detail",small.getEngender().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getWomenchest().size(); i++) {
            content.put("title",small.getWomenchest().get(i).getTitle());
            content.put("detail",small.getWomenchest().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
        for (int i = 0; i <  small.getChildren().size(); i++) {
            content.put("title",small.getChildren().get(i).getTitle());
            content.put("detail",small.getChildren().get(i).getDetail());
            database.insert(FinalValue.TB_BODY, null, content);
        }
	}*/
}
