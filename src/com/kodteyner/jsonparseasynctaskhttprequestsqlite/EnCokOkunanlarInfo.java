package com.kodteyner.jsonparseasynctaskhttprequestsqlite;

public class EnCokOkunanlarInfo {

	//Burasý dönecek olan tek bir kayýda karþýlýk modelleme yapýlan sýnýf.
	//Dönen 1 kayýtýn parametrelerini bunlar karþýlýyor yani.
	private String id;
    private String title;
    private String date;
    private String category;
    private String hit;
    private String thumb;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	
    
    
	
}
