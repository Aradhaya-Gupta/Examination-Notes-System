package com.example.notessystem;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class EditCategoryActivity extends Activity
{
	Spinner sp1;
	EditText ed1;
	Button btn1;
	ArrayList<String> arr= new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editcategory);
		sp1=(Spinner)findViewById(R.id.spinner1);
		ed1=(EditText)findViewById(R.id.editText1);
		btn1=(Button)findViewById(R.id.button1);
		Db_Activity obj=new Db_Activity(getApplicationContext());
		obj.open();
		arr= obj.getAllCategories();
		obj.close();
		ArrayAdapter<String> adap=new ArrayAdapter<String>(EditCategoryActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
		sp1.setAdapter(adap);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String cat=sp1.getSelectedItem().toString();
				ed1.setText(cat);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String ocat,ncat;
				ocat=sp1.getSelectedItem().toString();
				ncat=ed1.getText().toString();
				Db_Activity obj=new Db_Activity(getApplicationContext());
				obj.open();
				obj.updatecategory(ocat,ncat);
				Toast.makeText(getApplicationContext(), "Updated the category", Toast.LENGTH_SHORT).show();
				obj.close();
			}
		});
	}
}
