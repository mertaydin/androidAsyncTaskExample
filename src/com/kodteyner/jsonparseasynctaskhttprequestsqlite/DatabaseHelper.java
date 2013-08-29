package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; 

    private static final String DATABASE_NAME = "HABER_DB";

    private static final String TABLE_NAME = "HABERLER";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_NAME
				+ " (id TEXT PRIMARY KEY, " + "title TEXT, "
				+ "date TEXT, " + "category TEXT, "
				+ "hit TEXT, " + "thumb TEXT)";
		db.execSQL(CREATE_LOCATION_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		onCreate(db);	
	}
	 
    public long haberleriKaydet(EnCokOkunanlarList list) {
	SQLiteDatabase db = this.getWritableDatabase();
	long result = 0;
	
	for (int i = 0; i < list.getLength(); i++) {
		ContentValues values = new ContentValues();
		values.put(ServiceConstant.id, list.getItem(i).getId());
		values.put(ServiceConstant.title, list.getItem(i).getTitle());
		values.put(ServiceConstant.date, list.getItem(i).getDate());
		values.put(ServiceConstant.category, list.getItem(i).getCategory());
		values.put(ServiceConstant.hit, list.getItem(i).getHit());
		values.put(ServiceConstant.thumb, list.getItem(i).getThumb());

		result = db.insert(TABLE_NAME, null, values);		
	}

	db.close();

	return result;
    }
    
    
    public EnCokOkunanlarList butunHaberleriCek() { 
    	EnCokOkunanlarList list = new EnCokOkunanlarList();

		String selectQuery = "SELECT  * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				 EnCokOkunanlarInfo item = new EnCokOkunanlarInfo();

				 item.setId(cursor.getString(0));
				 item.setTitle(cursor.getString(1));
				 item.setDate(cursor.getString(2));
				 item.setCategory(cursor.getString(3));
				 item.setHit(cursor.getString(4));
				 item.setThumb(cursor.getString(5));
				 
				 list.addItem(item);

			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return list;
    }
    
    public void deleteAllRecords()
    {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_NAME,null,null);
    	db.close();  
    	
    }
 
}
