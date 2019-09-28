package com.example.notessystem;

import com.example.notessystem.HomeActivity;
import com.example.notessystem.HomeActivity;
import com.example.notessystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends Activity
{
	ImageButton btn1,btn2,btn3,btn4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		btn1=(ImageButton)findViewById(R.id.imageButton1);
		btn2=(ImageButton)findViewById(R.id.imageButton2);
		btn3=(ImageButton)findViewById(R.id.imageButton3);
		btn4=(ImageButton)findViewById(R.id.imageButton4);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent q=new Intent(HomeActivity.this,AddNotesActivity.class);
				startActivity(q);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent d=new Intent(HomeActivity.this,NotesCategoryActivity.class);
				startActivity(d);
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent e=new Intent(HomeActivity.this,UpdateNotesActivity.class);
				startActivity(e);
			}
		});
		btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent f=new Intent(HomeActivity.this,DeleteNotesActivity.class);
				startActivity(f);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Integer i=item.getItemId();
		if(i==R.id.item1)
		{
			Intent a=new Intent(HomeActivity.this,CategoryActivity.class);
			startActivity(a);
		}
		return super.onOptionsItemSelected(item);
	}
}
