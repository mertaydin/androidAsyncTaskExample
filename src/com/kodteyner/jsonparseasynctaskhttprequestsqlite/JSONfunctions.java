package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONfunctions {

	public static JSONArray getJSONfromURL(String url){
		InputStream is = null;
		String result = "";
		JSONArray jArray = null;
		
		//Burada Http request için HttpClient nesnesi ile client oluþturulur.
		//HttpPost ile yapýcýdan aldýðý url parametresine post yapýlýr.
		//HttpResponse httpclient ile yapýlan post'tan dönen deðeri alýr.
		//entity ile de dönen deðer is deðiþkenine atanýr.
		//try catch içinde olduðundan hata olduðu zaman logCat ekranýna basar.
	    try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(url);
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();

	    }catch(Exception e){
	            Log.e("log_tag", "baðlanýrken hata :  "+e.toString());
	    }
	    
	    //Burada InputStremReader sýnýfý ile is entity'sinden dönen deðer encoding de belirtilerek buffer edilir.
	    //buffer edilen deðer satýr satýr okunarak StringBuilder sýnýfýmýzdan türettiðim sb deðiþkenine atanýr.
	    //sonra baðlantý kapatýlýr ve sonuç result deðiþkenine atanýr.
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is.close();
	            result=sb.toString();
	    }catch(Exception e){
	            Log.e("log_tag", "Sonucu çevirmede hata :  "+e.toString());
	    }

	    //burada da result deðiþkenimizin içeriði JSONArray tipine çevrilir.
	    //Burada JSONArray ya da JSONOBject kullanma durumu JSON olarak dönen veriye baðlý olarak deðiþir.
	    //Benim çektiðim haberler JSONArray olarak dönüyor o yüzden böyle yaptým 
	    try{

            jArray = new JSONArray(result);            
	    }catch(JSONException e){
	            Log.e("log_tag", "parse ederken hata :  "+e.toString());
	    }
	    
	    //Ahanda return ettik.
	    return jArray;
	}
}