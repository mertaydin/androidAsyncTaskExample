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
		
		//Burada Http request i�in HttpClient nesnesi ile client olu�turulur.
		//HttpPost ile yap�c�dan ald��� url parametresine post yap�l�r.
		//HttpResponse httpclient ile yap�lan post'tan d�nen de�eri al�r.
		//entity ile de d�nen de�er is de�i�kenine atan�r.
		//try catch i�inde oldu�undan hata oldu�u zaman logCat ekran�na basar.
	    try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(url);
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();

	    }catch(Exception e){
	            Log.e("log_tag", "ba�lan�rken hata :  "+e.toString());
	    }
	    
	    //Burada InputStremReader s�n�f� ile is entity'sinden d�nen de�er encoding de belirtilerek buffer edilir.
	    //buffer edilen de�er sat�r sat�r okunarak StringBuilder s�n�f�m�zdan t�retti�im sb de�i�kenine atan�r.
	    //sonra ba�lant� kapat�l�r ve sonu� result de�i�kenine atan�r.
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
	            Log.e("log_tag", "Sonucu �evirmede hata :  "+e.toString());
	    }

	    //burada da result de�i�kenimizin i�eri�i JSONArray tipine �evrilir.
	    //Burada JSONArray ya da JSONOBject kullanma durumu JSON olarak d�nen veriye ba�l� olarak de�i�ir.
	    //Benim �ekti�im haberler JSONArray olarak d�n�yor o y�zden b�yle yapt�m 
	    try{

            jArray = new JSONArray(result);            
	    }catch(JSONException e){
	            Log.e("log_tag", "parse ederken hata :  "+e.toString());
	    }
	    
	    //Ahanda return ettik.
	    return jArray;
	}
}