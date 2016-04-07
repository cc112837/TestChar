package com.henli.user.aty;

import android.os.Bundle;

import com.example.save.R;
import com.henli.util.ExitManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;




public class RadioButtonActivity extends TabActivity{
    
	TabHost tabHost;
	private RadioButton rb_home, rb_knowledge,
	                    rb_family,rb_personal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 initTab();
	     init();
	     ExitManager.getInstance().addActivity(this);
	}

	public void init(){
    	rb_home=(RadioButton) findViewById(R.id.rb_home);
    	
		rb_knowledge = (RadioButton) findViewById(R.id.rb_knowledge);
		rb_family = (RadioButton) findViewById(R.id.rb_family);
		rb_personal = (RadioButton) findViewById(R.id.rb_personal);
		
		rb_home.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("home");

			}
		});

		
		rb_knowledge.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("knowledge");

			}
		});
		rb_family.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("family");

			}
		});
		rb_personal.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("personal");

			}
		});
    }
    
    public void initTab(){
    	tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
				.setContent(new Intent(this, HomeActivity.class)));
 		tabHost.addTab(tabHost.newTabSpec("knowledge").setIndicator("knowledge")
				.setContent(new Intent(this, KnowledgeActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("family").setIndicator("family")
				.setContent(new Intent(this,FamilyActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("personal").setIndicator("personal")
				.setContent(new Intent(this,PersonalActivity.class)));
    }
    
    public boolean dispatchKeyEvent( KeyEvent event) {
		int keyCode=event.getKeyCode();
	      if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount() == 0) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						RadioButtonActivity.this);
				alertDialog.setTitle(RadioButtonActivity.this
						.getString(R.string.app_close));
				alertDialog.setPositiveButton(RadioButtonActivity.this
						.getString(R.string.btn_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								ExitManager.getInstance().exit();
							}
						});
				alertDialog.setNegativeButton(RadioButtonActivity.this
						.getString(R.string.btn_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						});
				alertDialog.show();
			}
		}
		return super.dispatchKeyEvent(event);
	}
}
