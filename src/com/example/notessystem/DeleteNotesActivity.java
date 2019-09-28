package com.example.notessystem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteNotesActivity extends Activity
{
	Spinner sp1,sp2;
	Button btn1;
	TextView tv1;
	ArrayList<String> arr= new ArrayList<String>();
	ArrayList<String> arr1=new ArrayList<String>();	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deletenotes);
		sp1=(Spinner)findViewById(R.id.spinner1);
		sp2=(Spinner)findViewById(R.id.spinner2);
		btn1=(Button)findViewById(R.id.button1);
		tv1=(TextView)findViewById(R.id.textView3);
		String cato,subj;
		Db_Activity obj=new Db_Activity(getApplicationContext());
		obj.open();
		arr=obj.getAllCategories();
		ArrayAdapter<String> adap= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,arr);
		sp1.setAdapter(adap);
		obj.close();
		cato=sp1.getSelectedItem().toString();
		Db_Activity obj1=new Db_Activity(getApplicationContext());
		obj1.open();
		arr1=obj1.getSubjectList(cato);
		ArrayAdapter<String> adap1=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,arr1);
		sp2.setAdapter(adap1);
		subj=sp2.getSelectedItem().toString();
		obj1.close();
		subj=sp2.getSelectedItem().toString();
		tv1.setText(subj);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String subj=tv1.getText().toString();
				String cato=sp1.getSelectedItem().toString();
				Db_Activity obj2=new Db_Activity(getApplicationContext());
				obj2.open();
				if(obj2==null)
				{Toast.makeText(getApplicationContext(), "notes empty", Toast.LENGTH_SHORT).show();}
				else{
				obj2.deletenotes(subj,cato);
				Toast.makeText(getApplicationContext(), "notes deleted", Toast.LENGTH_SHORT).show();
				obj2.close();}
			}
		});
	}
}
