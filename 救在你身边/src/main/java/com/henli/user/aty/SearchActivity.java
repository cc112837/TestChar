package com.henli.user.aty;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.example.save.R;
import com.google.gson.Gson;
import com.henli.data.Knowledge;
import com.henli.data.Small;
import com.henli.db.FinalValue;
import com.henli.db.MySqliteOpenHelper;
import com.henli.util.ReadUtil;

import java.util.ArrayList;

public class SearchActivity extends Activity implements OnItemClickListener,OnClickListener{
	private SearchView sv;
	private ListView lv;
	private MySqliteOpenHelper helper;
	private SQLiteDatabase database;
	private ArrayAdapter<Knowledge> knowAdapter;
	private Small small;
	
	private Cursor cursor;
	
	private ArrayList<Knowledge> knows=new ArrayList<Knowledge>();
	private String detail;
	private Button btn_search_back;
	private ContentValues content;
	
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.search_knowledge);
    	
    	initDB();
    	init();
		initView();
		setAdapter();
		addListener();
 		
	  }
      
       private void init() {
    	   
    	   small = new Gson().fromJson(ReadUtil.readFromRaw(getApplicationContext()), Small.class);
		  
    	   content=new ContentValues();
   		  
				// TODO Auto-generated catch block
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
				
               
		}
          
  


	private void initDB() {
           helper = new MySqliteOpenHelper(this, FinalValue.DB_NAME, null,
                   FinalValue.DB_VERSION);
           database = helper.getReadableDatabase();
          
           
       }
       
    private void addListener() {
    	sv.setOnQueryTextListener(new OnQueryTextListener() {
	      	
			@Override
			 public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return true;
			}
			@Override
			public boolean onQueryTextChange(String newText) {
				ArrayList<Knowledge> newKnows=new ArrayList<Knowledge>();
				if (newText==null ||newText.length()==0) {
					update(null);
				}else {
		
						cursor=database.query(FinalValue.TB_BODY, new String[]{"_id","title","detail"},
								"title like ?",new String[]{"%"+newText+"%"},null,null,null);
					
					if (cursor!=null) {
				        while (cursor.moveToNext()) {
				        	newKnows.add(new Knowledge(cursor.getString(cursor.getColumnIndex("title"))));
              				/*newKnows.add(new Knowledge(cursor.getInt(cursor.getColumnIndex("_id")),null,
									cursor.getString(cursor.getColumnIndex("Item_name")),null,null));*/
				        	detail=cursor.getString(cursor.getColumnIndex("detail"));
              					
				        }
				        
					   //cursor.close();
				        update(newKnows);
					   } 
				       //db.close();
					   
				   }
				return true;
			}
		});
		
	}
   
       @Override
   	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	   
   		Intent intent=new Intent(SearchActivity.this, CollectNextActivity.class);
   	    String item= knows.get(position).getTitle();//cursor.getString(cursor.getColumnIndex("Item_name"));
   	 System.out.println("---222"+item);
   	    System.out.println("---111"+detail);
   	    intent.putExtra("detail", detail);
   	    intent.putExtra("item", item);
   		startActivity(intent);
   		
   	}

 	private void update(ArrayList<Knowledge> newKnows){
 		knows.clear();
 		if(newKnows != null && newKnows.size() != 0){
 			knows.addAll(newKnows);
 		}
 		knowAdapter.notifyDataSetChanged();
 	}
     
	private void initView(){
		btn_search_back=(Button) findViewById(R.id.btn_search_back);
		btn_search_back.setOnClickListener(this);
	    sv=(SearchView) findViewById(R.id.sv);
   		lv = (ListView)findViewById(R.id.lv);
   		lv.setOnItemClickListener(this);
   	}
     
     private void setAdapter(){
 		knowAdapter = new ArrayAdapter<Knowledge>(this, android.R.layout.simple_list_item_1,knows);
 		lv.setAdapter(knowAdapter);
    	 
 	}

	@Override
	public void onClick(View v) {
		if (v==btn_search_back) {
			finish();
		}
		
	}
}
