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
import android.widget.TextView;

public class MainActivity extends Activity {

	JSONObject jsonobject;
	JSONArray jsonarray;
	ProgressDialog progressBar;
	EnCokOkunanlarList list;
	private DatabaseHelper db;
	public TextView tv;
	String myList="";
	
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
			System.out.println(list);

        	db = new DatabaseHelper(MainActivity.this); 
        	db.deleteAllRecords();
        	 
        	long db_deger = db.haberleriKaydet(list);
        	Log.d("Dönen Long Deðer : " , String.valueOf(db_deger));
        	
        	
        	EnCokOkunanlarList dbdenGelen = new EnCokOkunanlarList();

        	dbdenGelen = db.butunHaberleriCek();

        	
        	for (int i=0; i<dbdenGelen.getLength(); i++ ) 
        	{
        		EnCokOkunanlarInfo item = new EnCokOkunanlarInfo();     
        		
				/*System.out.println(list.getItem(i).getId() + " - "
						+ list.getItem(i).getTitle() + " - "
						+ list.getItem(i).getDate() + " - "
						+ list.getItem(i).getCategory() + " - "
						+ list.getItem(i).getHit() + " - "
						+ list.getItem(i).getThumb());	*/
				
				//Listeview içerisine de basýlabilir bu liste içerisindeki degerler.
				myList += list.getItem(i).getId().toString() + " - ";
				myList += list.getItem(i).getTitle().toString() + " - ";
				myList += list.getItem(i).getDate().toString() + " - ";
				myList += list.getItem(i).getCategory().toString() + " - ";
				myList += list.getItem(i).getHit().toString() + " - ";
				myList += list.getItem(i).getThumb().toString() + "\n\n\n";
				
				
        	}
        	 
        	tv = (TextView)findViewById(R.id.jSonDonenDeger);
        	tv.setText(myList);
			progressBar.dismiss();
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
