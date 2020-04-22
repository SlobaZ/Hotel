package hotel.web.dto;

public class SobaDTO {
	

	private Integer id;
	private String naziv;
	private Integer brojKreveta;
	private boolean slobodno;
	private String slobodnoTekst;
	

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
	public Integer getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(Integer brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	public boolean isSlobodno() {
		return slobodno;
	}
	public void setSlobodno(boolean slobodno) {
		this.slobodno = slobodno;
	}
	public String getSlobodnoTekst() {
		return slobodnoTekst;
	}
	public void setSlobodnoTekst(String slobodnoTekst) {
		this.slobodnoTekst = slobodnoTekst;
	}
	
	

}
