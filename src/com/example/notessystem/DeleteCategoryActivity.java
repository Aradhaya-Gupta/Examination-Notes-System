package com.example.notessystem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteCategoryActivity extends Activity
{
	Spinner sp1;
	Button btn1;
	ArrayList<String> arr=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deletecategory);
		sp1=(Spinner)findViewById(R.id.spinner1);
		btn1=(Button)findViewById(R.id.button1);
		Db_Activity obj=new Db_Activity(getApplicationContext());
		obj.open();
		arr=obj.getAllCategories();
		obj.close();
		ArrayAdapter<String> adap=new ArrayAdapter<String>(DeleteCategoryActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
		sp1.setAdapter(adap);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String caty;
				caty=sp1.getSelectedItem().toString();
				Db_Activity obj=new Db_Activity(getApplicationContext());
				obj.open();			
				obj.deletecategory(caty);
				obj.close();
				Toast.makeText(getApplicationContext(), "Category Deleted", Toast.LENGTH_SHORT).show();
				Intent q=new Intent(DeleteCategoryActivity.this,ShowCategoryActivity.class);
				startActivity(q);
				finish();
			}
		});
	}
}
