package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
	

    private static Context context;
    private final EnCokOkunanlarList list;


  public MyListAdapter(Context context, EnCokOkunanlarList list) {


		super(context, R.layout.mylist_row, list.getRows());
		this.context = context;
		this.list = list;
  }



  @Override

  public View getView(int position, View convertView, ViewGroup parent) {


      if(convertView==null){
          LayoutInflater inflater = ((Activity) context).getLayoutInflater();
          convertView = inflater.inflate(R.layout.mylist_row, parent, false);
      }

      
      TextView title = (TextView) convertView.findViewById(R.id.row_title);
      TextView cat = (TextView) convertView.findViewById(R.id.row_cat);

      final EnCokOkunanlarInfo item = list.getItem(position);
      
      title.setText(item.getTitle().toString());
      cat.setText(item.getCategory().toString());
      
      return convertView;
      

      /*ObjectItem objectItem = data[position];



 

      TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);

      textViewItem.setText(objectItem.itemName);

      textViewItem.setTag(objectItem.itemId);



      return convertView;*/



  }



	}
