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

public class UpdateNotesActivity extends Activity
{
	Spinner sp1,sp2;
	Button btn1;
	EditText ed1;
	ArrayList<String> arr= new ArrayList<String>();
	ArrayList<String> arr1=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updatenotes);
		sp1=(Spinner)findViewById(R.id.spinner1);
		sp2=(Spinner)findViewById(R.id.spinner2);
		btn1=(Button)findViewById(R.id.button1);
		ed1=(EditText)findViewById(R.id.editText1);
		Db_Activity obj=new Db_Activity(getApplicationContext());
		obj.open();
		arr=obj.getAllCategories();
		ArrayAdapter<String> adap= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,arr);
		sp1.setAdapter(adap);
		obj.close();
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String cato=sp1.getSelectedItem().toString();;
				Db_Activity obj1=new Db_Activity(getApplicationContext());
				obj1.open();
				arr1=obj1.getSubjectList(cato);
				ArrayAdapter<String> adap1= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,arr1);
				sp2.setAdapter(adap1);
				obj1.close();
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String cato = sp1.getSelectedItem().toString();
				String subj=sp2.getSelectedItem().toString();
				Db_Activity obj2=new Db_Activity(getApplicationContext());
				obj2.open();
				String str;
				str=obj2.showdetail(subj,cato).toString();
				ed1.setText(str);
				obj2.close();
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
				String nstr,subj,cato;
			    cato=sp1.getSelectedItem().toString();
				subj=sp2.getSelectedItem().toString();
				nstr=ed1.getText().toString();
				if (nstr.equals("") || nstr.equals(null))
				{
					Toast.makeText(getApplicationContext(), "Updated Detail Required", Toast.LENGTH_SHORT).show();
				}
				else
				{
				Db_Activity obj3=new Db_Activity(getApplicationContext());
				obj3.open();
				obj3.updatenotes(nstr,subj,cato);
				obj3.close();
			}
			}
		});
		
		
		
		
		
		
	}
}
