package com.example.notessystem;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;

public class SplashActivity extends Activity 
{
	ProgressBar pb;
	Handler h =new Handler();
	Integer i=0;
	Thread t;
	MediaPlayer mp1;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splash);
	//mp1=MediaPlayer.create(getApplicationContext(), R.raw.a);
	pb=(ProgressBar)findViewById(R.id.progressBar1);
	t=new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//try {
				//mp1.prepare();
			 //catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			// catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			
			//mp1.start();
			while(i<10)
			{
				i=i+1;
				h.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						pb.setProgress(i);
						
					}
				});
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//mp1.pause();
			Intent i=new Intent(SplashActivity.this,HomeActivity.class);
			startActivity(i);
			finish();
		}
	});
	t.start();
}
	

}
