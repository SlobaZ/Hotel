package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class SobaCenovnik {

	private Integer id;
	private String naziv;
	private Integer brojsoba;
	private Integer brojkreveta;
	private Double cena;
		
	
	public SobaCenovnik(Integer id, String naziv, Integer brojsoba, Integer brojkreveta, Double cena) {
		this.id = id;
		this.naziv = naziv;
		this.brojsoba = brojsoba;
		this.brojkreveta = brojkreveta;
		this.cena = cena;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getBrojsoba() {
		return brojsoba;
	}
	public void setBrojsoba(Integer brojsoba) {
		this.brojsoba = brojsoba;
	}
	public Integer getBrojkreveta() {
		return brojkreveta;
	}
	public void setBrojkreveta(Integer brojkreveta) {
		this.brojkreveta = brojkreveta;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	

	public static List<SobaCenovnik> cene(){
		
		List<SobaCenovnik> listasoba = new ArrayList<SobaCenovnik>();
		SobaCenovnik us1 = new SobaCenovnik(1, "Single", 2 , 1 , 2000.0);
		SobaCenovnik us2 =new SobaCenovnik(2, "Double" , 4 , 2 , 2500.0);
		SobaCenovnik us3 =new SobaCenovnik(3, "Triple", 2 , 3 , 3500.0);
		SobaCenovnik us4 =new SobaCenovnik(4, "Family room" , 2 , 4 , 2500.0 );		
		SobaCenovnik us5 =	new SobaCenovnik(5, "Suite", 2 , 2 , 3000.0);
		
		listasoba.add(us1);
		listasoba.add(us2);
		listasoba.add(us3);
		listasoba.add(us4);
		listasoba.add(us5);
		
		return listasoba;
		
	}

		
		
	
}
