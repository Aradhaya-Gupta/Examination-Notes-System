package com.example.notessystem;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowSubjectActivity extends Activity{

	ListView lv1;
	String cat;
	ArrayList<String> arr=new ArrayList<String>();
	TextToSpeech ts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showsubject);
ts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int arg0) {
				// TODO Auto-generated method stub
				if(TextToSpeech.ERROR!=0)
				{
					ts.setLanguage(Locale.CANADA);
				}
			}
		});
		cat = getIntent().getExtras().getString("scat");
		lv1 = (ListView)findViewById(R.id.listView1);
		Db_Activity obj=new Db_Activity(getApplicationContext());
        obj.open();
		arr= obj.getSubjectList(cat);
		obj.close();
		ArrayAdapter<String> adap=new ArrayAdapter<String>(ShowSubjectActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
		lv1.setAdapter(adap);
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
					String sub = lv1.getItemAtPosition(arg2).toString();
					ts.speak(sub, TextToSpeech.QUEUE_FLUSH, null);
					while(ts.isSpeaking())
					{
						
					}
					Intent i = new Intent(getApplicationContext(),SubjectDetailActivity.class);
					i.putExtra("scat", cat);
					i.putExtra("sub", sub);
					startActivity(i);
					
			}
		});


		
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
