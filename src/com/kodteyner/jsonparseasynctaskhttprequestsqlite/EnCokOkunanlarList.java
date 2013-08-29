package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import java.util.ArrayList;
import java.util.List;

public class EnCokOkunanlarList {

	//buras� ise d�nen kay�tlar�n hepsini saklayaca��m repository s�n�f�.
	//Yani burada MostlyReadNewsInfo s�n�f� 1 kay�t sakl�yordu ya
	//List<MostlyReadNewsInfo> �eklinde saklay�nca b�t�n kay�tlar� tek tek List i�inde saklam�� oluyoruz.
	
	private List<EnCokOkunanlarInfo> haberList;

	public EnCokOkunanlarList() {
	    haberList = new ArrayList<EnCokOkunanlarInfo>();
	}
	
	public void addItem(EnCokOkunanlarInfo item) {
	haberList.add(item);
	}
	
	public EnCokOkunanlarInfo getItem(int position) {
	    return haberList.get(position);
	}
	
	public List<EnCokOkunanlarInfo> getMostlyReadList() {
	    return haberList;
	}
	
	public void setMostlyReadList(List<EnCokOkunanlarInfo> mostlyReadList) {
	    this.haberList = mostlyReadList;
	}
	
	public String[] getRows() {
	    String[] result = new String[haberList.size()];
	
	    return result;
	}
	
	public int getLength()
	{
	    return haberList.size();        
	}   
    
}
