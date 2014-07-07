package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ProgressDialog progressBar;
	EnCokOkunanlarList list;
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new DownloadJSON().execute();	
		
	}

	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar = new ProgressDialog(MainActivity.this);
			progressBar.setTitle("Çek 1.5 JSON");
			progressBar.setMessage("Jsondan veriler çekiliyor...");
			progressBar.setIndeterminate(false);
			progressBar.show();
		} 

		@Override
		protected Void doInBackground(Void... params) {
			jsonarray = JSONfunctions.getJSONfromURL("http://kodteyner.com/jsonParse/haberler.json"); 
			
			list = new EnCokOkunanlarList();
			
			try {
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject row;

                                row = jsonarray.getJSONObject(i);

                                EnCokOkunanlarInfo item = new EnCokOkunanlarInfo(); 
                                
                                item.setId(row.optString(ServiceConstant.id));
                                item.setTitle(row.optString(ServiceConstant.title));
                                item.setDate(row.optString(ServiceConstant.date));
                                item.setCategory(row.optString(ServiceConstant.category));
                                item.setHit(row.optString(ServiceConstant.hit));
                                item.setThumb(row.optString(ServiceConstant.thumb));                                    
                        
                                list.addItem(item);
				                }
				    }
				    catch (JSONException e) {}								 
			return null;
		}

		@Override
		protected void onPostExecute(Void args) { 

        	db = new DatabaseHelper(MainActivity.this); 
        	db.deleteAllRecords();
        	 
        	long db_deger = db.haberleriKaydet(list);
        	Log.d("Dönen Long Deðer : " , String.valueOf(db_deger));
        	
        	
        	EnCokOkunanlarList dbdenGelen = new EnCokOkunanlarList();

        	dbdenGelen = db.butunHaberleriCek();

        	        	 
        	//populate listview...
        	ListView sonucList = (ListView) findViewById(R.id.myList);

        	MyListAdapter adapter = new MyListAdapter(MainActivity.this, dbdenGelen);
     		sonucList.setAdapter(adapter);
     		
			progressBar.dismiss();
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
