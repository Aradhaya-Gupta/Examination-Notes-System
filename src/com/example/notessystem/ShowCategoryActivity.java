package com.example.notessystem;
import java.util.ArrayList;
import com.example.notessystem.Db_Activity;
import com.example.notessystem.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowCategoryActivity extends Activity
{

		ListView lv1;
	    Integer id;
		ArrayList<String> arr=new ArrayList<String>();
	     @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	// TODO Auto-generated method stub
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_showcategory);
	    	lv1=(ListView)findViewById(R.id.listView1);
	    	Db_Activity obj=new Db_Activity(getApplicationContext());
			obj.open();
			arr= obj.getAllCategories();
			obj.close();
			ArrayAdapter<String> adap=new ArrayAdapter<String>(ShowCategoryActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
			lv1.setAdapter(adap);
			lv1.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					AlertDialog.Builder ad=new AlertDialog.Builder(ShowCategoryActivity.this);
					ad.setTitle("Choose your Option");
					ad.setPositiveButton("Delete", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent j=new Intent(getApplicationContext(),DeleteCategoryActivity.class);
							startActivity(j);
						}
					});
					ad.setNegativeButton("Edit", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							Intent k=new Intent(getApplicationContext(),EditCategoryActivity.class);
							startActivity(k);
						}
					});
					ad.setNeutralButton("Cancel", null);
					ad.show();
				}
			});
			
			

	}
}
