package com.koko.koko;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
 
public class SendToWebApp extends Activity {
 
  ShareExternalServer appUtil;
  String regId;
  AsyncTask<Void, Void, String> shareRegidTask;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sent_to_web);
    appUtil = new ShareExternalServer();
 
    regId = getIntent().getStringExtra("regId");
    Log.d("MainActivity", "regId: " + regId);
 
    final Context context = this;
    shareRegidTask = new AsyncTask<Void, Void, String>() {
      @Override
      protected String doInBackground(Void... params) {
        String result = appUtil.shareRegIdWithAppServer(context, regId);
        return result;
      }
 
      @Override
      protected void onPostExecute(String result) {
        shareRegidTask = null;
        Toast.makeText(getApplicationContext(), result,
            Toast.LENGTH_LONG).show();
      }
 
    };
    shareRegidTask.execute(null, null, null);
  }
 
}