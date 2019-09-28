package com.example.notessystem;

import com.example.notessystem.Db_Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryActivity extends Activity
{
	EditText ed1;
	Button btn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcategory);
		ed1=(EditText)findViewById(R.id.editText1);
		btn1=(Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String cat;
				cat=ed1.getText().toString();
				if(cat.equals("") || cat.equals(null))
				{
					Toast.makeText(getApplicationContext(), "Category is Required", Toast.LENGTH_SHORT).show();
				}
				else
				{
				Db_Activity obj=new Db_Activity(getApplicationContext());
				obj.open();
				Integer total=obj.isduplicate(cat);
						if(total==0)
						{
								obj.entry(cat);
								ed1.setText("");
								Toast.makeText(getApplicationContext(), "Category saved", Toast.LENGTH_SHORT).show();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "ALready Exist", Toast.LENGTH_SHORT).show();
						}
						obj.close();
				}
			}
		});
	}
}
