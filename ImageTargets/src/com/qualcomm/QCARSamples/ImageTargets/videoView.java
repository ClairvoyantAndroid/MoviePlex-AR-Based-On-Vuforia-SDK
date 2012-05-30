package com.qualcomm.QCARSamples.ImageTargets;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/** Our Activity which play Video based on the Code returned Form Bundle*/
public class videoView extends Activity {
	
	String videos[]={"http://182.71.230.252/download/RealSteel.mp4", "http://182.71.230.252/download/Tangled%20-%20Official%20Trailer%20%5bHD%5d.mp4","http://182.71.230.252/download/Journey%20to%20the%20Center%20of%20the%20Earth%20-%20Official%20Trailer%20(HD).mp4",
			"http://182.71.230.252/download/transformer.mp4"};
	
	String code=null;
	VideoView videoview=null;
	ProgressDialog progressDialog=null;
	ImageView detailImage=null;
	TextView detailText=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.videoview);
		Bundle extras=getIntent().getExtras();
		code=extras.getString("code");
		progressDialog=ProgressDialog.show(this, "",  "Initializing...");
		videoview=(VideoView)findViewById(R.id.videoview);
		detailImage=(ImageView)findViewById(R.id.detail_image);
		detailText=(TextView)findViewById(R.id.detail_text);
		detailText.setText(code);
		setImageRunnable();
		
		videoview.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				
				progressDialog.cancel();
				
			}
		});
		
		if(code.equals("Real_Steel"))
		{
			videoview.setVideoURI(Uri.parse(videos[0]));
		}
		else if(code.equals("Tangled"))
		{
			videoview.setVideoURI(Uri.parse(videos[1]));
		}
		else if(code.equals("Journey2TheCenterOfEarth"))
		{
			videoview.setVideoURI(Uri.parse(videos[2]));
		}
		else
		{
			videoview.setVideoURI(Uri.parse(videos[3]));
		}
		
		videoview.setMediaController(new MediaController(this));
		videoview.start();
		
	}

	private void setImageRunnable() {
		 	Thread bitmapFetcher=new Thread(new Runnable() {
				
				@Override
				public void run() {
				
					final Bitmap bmp=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+File.separator+"test.png");
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							
							detailImage.setImageBitmap(bmp);
							
						}
					});
				}
			});bitmapFetcher.start();
	
	}

	@Override
	public void onBackPressed() {
   
		finish();
		Intent intent =new Intent(this,ImageTargets.class);
		startActivity(intent);
		
	}
}
