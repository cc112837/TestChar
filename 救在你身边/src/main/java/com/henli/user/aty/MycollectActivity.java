package com.henli.user.aty;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.save.R;
import com.henli.db.FinalValue;
import com.henli.db.MySqliteOpenHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MycollectActivity extends Activity implements OnClickListener{
private ListView lv_dis;
private MySqliteOpenHelper helper;// 数据库连接对象
private SQLiteDatabase database;// 数据库
private Cursor cursor;// 游标
private List<HashMap<String, String>> data;// 数据源
private SimpleAdapter adapter;
private Button btn_cang_return;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_mycollect);
    init();
    //初始化数据库
    initdata();
    aboutList();
    if (!cursor.moveToFirst()) {
    } else {
        dataDB();
    }


}

private void aboutList() {
    data = new LinkedList<HashMap<String,String>>();
    adapter = new SimpleAdapter(this, data, R.layout.collect_item
            ,new String[] { "title" }, new int[] {
            R.id.tv_titleid });
    lv_dis.setAdapter(adapter);// 绑定适配器
    lv_dis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, String> content = data.get(position);
            
            String detail = content.get("detail");
            String item = content.get("title");
            Intent intent=new Intent(MycollectActivity.this,CollectNextActivity.class);
            intent.putExtra("text", detail);
            intent.putExtra("item", item);
            startActivity(intent);

        }
    });
    lv_dis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MycollectActivity.this);
            builder.setTitle("删除内容");// 对话框标题
            builder.setMessage("你确定要删除吗");// 对话框内容
            DialogButtonOnClickListener listener = new DialogButtonOnClickListener(position);
            builder.setNegativeButton("取消", listener);
            builder.setPositiveButton("确定", listener);
            builder.create().show();
            return true;
        }
    });
}
private class DialogButtonOnClickListener implements DialogInterface.OnClickListener {
    private int position;
    public DialogButtonOnClickListener(int position){
        this.position=position;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_NEGATIVE:
                break;
            case DialogInterface.BUTTON_POSITIVE:
                HashMap<String, String> map = data.get(position);
                String content = map.get("title");
                database.delete(FinalValue.TB_COLLECT, "title=?", new String[] {
                        content + "" });
                data.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}

private void dataDB() {
    cursor = database.query(FinalValue.TB_COLLECT, null, null, null, null,
            null, null);
    while (cursor.moveToNext()) {
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String detail = cursor.getString(cursor.getColumnIndex("detail"));
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", title);
        map.put("detail", detail);
        data.add(map);
    }
    adapter.notifyDataSetChanged();
}


private void initdata() {
    helper = new MySqliteOpenHelper(this, FinalValue.DB_NAME, null,
            FinalValue.DB_VERSION);
    database = helper.getReadableDatabase();
    cursor = database.query(FinalValue.TB_COLLECT, null, null, null, null,
            null, null);
}

private void init() {
    lv_dis=(ListView) findViewById(R.id.lv_dis);
    btn_cang_return=(Button) findViewById(R.id.btn_cang_return);
    btn_cang_return.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	if (v==btn_cang_return) {
		finish();
	}
	
}
}
