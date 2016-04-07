package com.henli.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.save.R;
import com.henli.data.Small;
import com.henli.db.FinalValue;

import java.util.ArrayList;
import java.util.List;


public class ChestAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> list = new ArrayList<String>();
    private Context context;
    private List<Small.ChestEntity> entitylist;
    private ContentValues contentValues;
    private SQLiteDatabase database;

    public ChestAdapter(Context context, List<Small.ChestEntity> entitylist, ContentValues contentValues, SQLiteDatabase database) {
        this.context = context;
        this.entitylist = entitylist;
        this.contentValues=contentValues;
        this.database=database;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if (entitylist != null) {
            return entitylist.size();
        }
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return entitylist.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_small, null);
            vh.cb_check = (CheckBox) view.findViewById(R.id.cb_check);
            vh.tv_titleid = (TextView) view.findViewById(R.id.tv_titleid);
            vh.tv_col=(TextView) view.findViewById(R.id.tv_col);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        vh.tv_titleid.setText(entitylist.get(position).getTitle());
        vh.cb_check.setTag(R.id.cb_check, position);
        Cursor cursor = database.query(FinalValue.TB_COLLECT, null, null, null, null,
                null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            if(entitylist.get(position).getTitle().equals(title)){
                vh.cb_check.setChecked(true);
                vh.tv_col.setText("取消");
            }
        }

        vh.cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int position = (Integer) buttonView.getTag(R.id.cb_check);
                if (isChecked) {
                    vh.tv_col.setText("取消");
                    contentValues.put("title", entitylist.get(position).getTitle());
                    contentValues.put("detail",entitylist.get(position).getDetail());
                    database.insert(FinalValue.TB_COLLECT, null, contentValues);
                } else {
                    vh.tv_col.setText("收藏");
                    String content = entitylist.get(position).getTitle();
                    database.delete(FinalValue.TB_COLLECT, "title=?", new String[]{
                            content + ""});
                }
            }
        });
        return view;
    }


    private final class ViewHolder {

        private TextView tv_titleid;
        private CheckBox cb_check;
        private TextView tv_col;

    }

}
