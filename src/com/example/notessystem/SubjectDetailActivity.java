package com.example.notessystem;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectDetailActivity extends Activity{
	TextView tv2,tv4,tv5;
	String cat,sub;
	String str="";
	TextToSpeech ts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjectdetail);
ts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int arg0) {
				// TODO Auto-generated method stub
				if(TextToSpeech.ERROR!=0)
				{
					ts.setLanguage(Locale.ENGLISH);
				}
			}
		});
		cat = getIntent().getExtras().getString("scat");
		sub=getIntent().getExtras().getString("sub");
		tv2=(TextView)findViewById(R.id.textView2);
		tv4=(TextView)findViewById(R.id.textView4);
		tv5=(TextView)findViewById(R.id.textView5);
		Db_Activity obj=new Db_Activity(getApplicationContext());
        obj.open();
		str= obj.getDetail(cat,sub);
		obj.close();
		tv2.setText(cat);
		tv4.setText(sub);
		tv5.setText(str);

		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_speech, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Integer i=item.getItemId();
		if(i==R.id.item1)
		{
			String s = tv5.getText().toString();
			ts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(ts!=null)
		{
			ts.stop();
			ts.shutdown();
		}
	}
}
