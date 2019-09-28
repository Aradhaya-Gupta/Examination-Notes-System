package com.example.notessystem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNotesActivity extends Activity
{
	Button btn1;
	Spinner sp1;
	EditText ed1,ed2;
	ArrayList<String> arr=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnotes);
		btn1=(Button)findViewById(R.id.button1);
		sp1=(Spinner)findViewById(R.id.spinner1);
		ed1=(EditText)findViewById(R.id.editText1);
		ed2=(EditText)findViewById(R.id.editText2);
		sp1=(Spinner)findViewById(R.id.spinner1);
    	Db_Activity obj=new Db_Activity(getApplicationContext());
		obj.open();
		arr= obj.getAllCategories();
		obj.close();
		ArrayAdapter<String> adap=new ArrayAdapter<String>(AddNotesActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
		sp1.setAdapter(adap);
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String detail,sub;
				String cat=sp1.getSelectedItem().toString();
				detail=ed1.getText().toString();
				sub=ed2.getText().toString();
				if(detail.equals("") || detail.equals(null) || sub.equals("") || sub.equals(null))
				{
					Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
				}
				else
				{
				Db_Activity obj=new Db_Activity(getApplicationContext());
				obj.open();
				Integer total = obj.getduplicatesubject(cat,sub);
				if(total==0)
				{
					obj.savedetail(cat,sub,detail);
					
					Toast.makeText(getApplicationContext(), "Detail saved", Toast.LENGTH_SHORT).show();
					ed1.setText("");
					ed2.setText("");
				}
				else {
				Toast.makeText(getApplicationContext(), "same mat chepo", Toast.LENGTH_SHORT).show();
				}
				obj.close();
				
			}
			}
		});
		
	}

}