package com.example.notessystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryActivity extends Activity{
	ImageButton btn1,btn2,btn3,btn4;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		btn1=(ImageButton)findViewById(R.id.imageButton1);
		btn2=(ImageButton)findViewById(R.id.imageButton2);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(CategoryActivity.this,AddCategoryActivity.class);
				startActivity(i);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent a=new Intent(CategoryActivity.this,ShowCategoryActivity.class);
				startActivity(a);
			}
		});
		}
}
