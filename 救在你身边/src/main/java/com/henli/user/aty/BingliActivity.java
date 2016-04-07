package com.henli.user.aty;

import java.util.Calendar;
import java.util.Locale;

import com.example.save.R;
import com.henli.user.aty.PersonalActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BingliActivity extends Activity implements OnClickListener {
	
	    private EditText et_nameid;
	    private RadioGroup rg_sex;
	    private RadioButton  rb_male;
	    private RadioButton rb_female;
	    private TextView tv_birth;
	    private EditText et_addressid;
	    private String sex;
	    private Button btn_save;
	    private Button btn_cancle;
	    private Spinner sp_aidid;
	    private Spinner sp_speakid;
	    private Spinner sp_confirmid;
	    private  EditText et_nowid;
	    private String guo;
	    private String con;
	    private String spe;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_aid);
	        init();

	    }

	    private void init() {
	        et_nameid = ((EditText) findViewById(R.id.et_nameid));
	        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
	        tv_birth = ((TextView) findViewById(R.id.tv_birthid));
	        et_addressid = ((EditText) findViewById(R.id.et_addressid));
	        rb_male=(RadioButton) findViewById(R.id.rb_male);
	        rb_female=(RadioButton) findViewById(R.id.rb_female);
	        btn_save=(Button) findViewById(R.id.btn_save);
	        btn_cancle=(Button) findViewById(R.id.btn_cancle);
	        sp_aidid=(Spinner)findViewById(R.id.sp_aidid);
	        sp_speakid=(Spinner)findViewById(R.id.sp_speakid);
	        sp_confirmid=(Spinner)findViewById(R.id.sp_confirmid);
	        et_nowid=(EditText) findViewById(R.id.et_nowid);
	        String[]  guomin= getResources().getStringArray(R.array.guomin);
	        String[]  confirm= getResources().getStringArray(R.array.confirm);
	        String[]  speak= getResources().getStringArray(R.array.speak);
	        ArrayAdapter<String> guominadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, guomin);
	        guominadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp_aidid .setAdapter(guominadapter);
	        sp_aidid.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					 String[] guomin = getResources().getStringArray(R.array.guomin);
		                guo = guomin[position];
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
	       
	        ArrayAdapter<String> confirmadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, confirm);
	        confirmadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp_confirmid .setAdapter(confirmadapter);
	        sp_confirmid.setOnItemSelectedListener(new OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	                String[] confirm = getResources().getStringArray(R.array.confirm);
	                con = confirm[position];
	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> parent) {

	            }
	        });
	        ArrayAdapter<String> speakadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, speak);
	        speakadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp_speakid .setAdapter(speakadapter);
	        sp_speakid.setOnItemSelectedListener(new OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	                String[] speak = getResources().getStringArray(R.array.speak);
	                spe = speak[position];
	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> parent) {

	            }
	        });
	        tv_birth.setOnClickListener(this);
	        et_addressid.setOnClickListener(this);
	        btn_save.setOnClickListener(this);
	        btn_cancle.setOnClickListener(this);
	        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	            @Override
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	                if(checkedId==R.id.rb_male) {
	                    sex= rb_male.getText().toString();
	                }
	                else  if(checkedId==R.id.rb_female)
	                    sex= rb_female.getText().toString();
	            }
	        });

	    }

	  

		@Override
		public void onClick(View v) {
			 switch (v.getId()){

	            case R.id.tv_birthid:
	                final Calendar calendar = Calendar.getInstance(Locale.CHINA);
	                int year = calendar.get(Calendar.YEAR);
	                final int month = calendar.get(Calendar.MONTH);
	                final int day = calendar.get(Calendar.DAY_OF_MONTH);
	                DatePickerDialog dialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
	                    @Override
	                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	                        calendar.set(year,monthOfYear,dayOfMonth);
	                        tv_birth.setText(year + "-" + (int) ((int) monthOfYear + 1) + "-" + dayOfMonth);
	                    }
	                },year,month,day);
	                dialog.show();
	                break;

	            case R.id.btn_save:
	                String name=et_nameid.getText().toString();//sex
	                String birth=tv_birth.getText().toString();
	                String address=et_addressid.getText().toString();
	                String now=et_nowid.getText().toString();//guo,con,spe
	                Toast.makeText(BingliActivity.this,name+ "|"+birth+"|"+sex+"|"+address+"|"+now+guo+con+spe+"±£´æ³É¹¦", Toast.LENGTH_SHORT).show();
	                break;
	            case R.id.btn_cancle:
	            	BingliActivity.this.finish();
	                break;

	        }
			
		}
}
