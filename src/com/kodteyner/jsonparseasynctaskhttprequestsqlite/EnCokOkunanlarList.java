package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

import java.util.ArrayList;
import java.util.List;

public class EnCokOkunanlarList {

	//burasý ise dönen kayýtlarýn hepsini saklayacaðým repository sýnýfý.
	//Yani burada MostlyReadNewsInfo sýnýfý 1 kayýt saklýyordu ya
	//List<MostlyReadNewsInfo> þeklinde saklayýnca bütün kayýtlarý tek tek List içinde saklamýþ oluyoruz.
	
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
