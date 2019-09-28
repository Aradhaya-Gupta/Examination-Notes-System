package com.example.notessystem;

import java.util.ArrayList;

import com.example.notessystem.Db_Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db_Activity 
{
	static final String Key_catid="categoryid";
	static final String Key_catname="categoryname";
	static final String Database_name="notesdb1";
	static final String Database_Table="categorytable";
	static final String Key_notesid="notesid";
	static final String Table_notes="notestable";
	static final String Key_notescategory="notescategory";
	static final String Key_notessubject="notessubject";
	static final String Key_notesdetail="notesdetail";
	static final int Database_version=1;
	Dbhelper ourhelper;
	final Context ourContext;
	SQLiteDatabase ourdatabase;
	private static class Dbhelper extends SQLiteOpenHelper{

		public Dbhelper(Context context) {
			super(context, Database_name, null, Database_version);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL ( "CREATE TABLE " + Database_Table + "(" +
					Key_catid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					Key_catname + " TEXT NOT NULL);");

			arg0.execSQL( "CREATE TABLE " + Table_notes + "(" +
					Key_notesid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					Key_notescategory+" text not null, "+
					Key_notessubject+" text not null, "+
					Key_notesdetail+" TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS " + Database_Table);
			arg0.execSQL("DROP TABLE IF EXISTS " + Table_notes);
            onCreate(arg0);
		}
}
	public Db_Activity(Context c) {
		ourContext= c;
		

}

	public Db_Activity open()
	
	{
	
		ourhelper = new Dbhelper(ourContext);
		ourdatabase= ourhelper.getWritableDatabase();
		return this;
		
	}

	public void close()
	
	{
		ourhelper.close();
	}
	public long entry(String category)
	{
		ContentValues cv=new ContentValues();
		cv.put(Key_catname, category);
		return ourdatabase.insert(Database_Table, null, cv);
	}
	public String getdata() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{Key_catid,Key_catname};
		Cursor c=ourdatabase.query( Database_Table, columns, null,null,null,null,null);
		String result="";
		Integer icatid=c.getColumnIndex(Key_catid);
		Integer icatname=c.getColumnIndex(Key_catname);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			result = result + c.getString(icatid)+" "+c.getString(icatname)+"\n";
		}
		return result;
	}

	public ArrayList<String> getAllCategories()
{
	String[] columns =new String[] {Key_catname};
	Cursor c=ourdatabase.query(Database_Table, columns, null,null,null,null,null);
	ArrayList<String> arr =new ArrayList<String>();
	for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
	{
		String str=c.getString(0);
				arr.add(str);
	}
	return arr;
	
}

	public long savedetail(String cat,String sub, String detail) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(Key_notescategory,cat);
		cv.put(Key_notessubject,sub);
		cv.put(Key_notesdetail, detail);
		return ourdatabase.insert(Table_notes, null, cv);
	}

	public ArrayList<String>getSubjectList(String cat) {
		Cursor c = ourdatabase.rawQuery("select "+Key_notessubject+" from "+Table_notes+" where "+Key_notescategory+" = '"+cat+"' ", new String[]{});
		ArrayList<String> arr =new ArrayList<String>();
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			String str=c.getString(0);
					arr.add(str);
		}
		return arr;
	}
	public ArrayList<String> getSubjectDetail(String cat,String sub) {
		Cursor c = ourdatabase.rawQuery("select "+Key_notesdetail+" from "+Table_notes+" where "+Key_notessubject+" = '"+sub+"' and "+Key_notescategory+" = '"+cat+"'" , new String[]{});
		ArrayList<String> arr =new ArrayList<String>();
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			String str=c.getString(0);
					arr.add(str);
		}
		return arr;
	}
	public String getDetail(String cat,String sub) {
		Cursor c = ourdatabase.rawQuery("select "+Key_notesdetail+" from "+Table_notes+" where "+Key_notessubject+" = '"+sub+"' and "+Key_notescategory+" = '"+cat+"'" , new String[]{});
		String str = ""; 
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			str=c.getString(0);
					
		}
		return str;
	}
	public Integer isduplicate(String cat)
	{
		Cursor c= ourdatabase.rawQuery("select * from "+Database_Table+" where "+Key_catname+" ='"+cat+"'", new String[]{});
		Integer i=0;
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			i++;
		}
		return i;
	}

	public Integer getduplicatesubject(String cat, String sub) {
		// TODO Auto-generated method stub
		Cursor c= ourdatabase.rawQuery("select * from "+Table_notes+" where "+Key_notessubject+" ='"+sub+"' and "+Key_notescategory+" ='"+cat+"'", new String[]{});
		Integer i=0;
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			i++;
		}
		return i;
	}

	public void updatecategory(String ocat, String ncat) {
		// TODO Auto-generated method stub
		ourdatabase.execSQL("update "+Database_Table+" set "+Key_catname+" ='"+ncat+"' where "+Key_catname+" ='"+ocat+"'");
		
	}

	public void deletecategory(String caty) {
		// TODO Auto-generated method stub
		ourdatabase.execSQL("delete from "+Database_Table+" where "+Key_catname+" ='"+caty+"'");
	}

	public String showdetail(String subj,String cato) {
		// TODO Auto-generated method stub
		Cursor c=ourdatabase.rawQuery("select "+Key_notesdetail+" from "+Table_notes+" where "+Key_notessubject+" ='"+subj+"' and "+Key_notescategory+" ='"+cato+"'", new String[]{});
		String str="";
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			str = c.getString(0);
		}
		return str;
	}

	public void deletenotes(String subj, String cato) {
		// TODO Auto-generated method stub
		ourdatabase.execSQL("delete from "+Table_notes+" where "+Key_notessubject+" ='"+subj+"' and "+Key_notescategory+" = '"+cato+"'");
	}

	public void updatenotes(String nstr,String subj,String cato) {
		// TODO Auto-generated method stub
		ourdatabase.execSQL("update "+Table_notes+" set "+Key_notesdetail+" ='"+nstr+"' where "+Key_notescategory+" ='"+cato+"' and "+Key_notessubject+" ='"+subj+"'");
	}

}