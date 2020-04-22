package hotel.web.dto;

import java.sql.Timestamp;

public class RezervacijaDTO {

	private Integer id;
	private Timestamp datumvremeUlaz;
	private String datetimeUlaz;
	private Timestamp datumvremeIzlaz;
	private String datetimeIzlaz;
	
	
	private Integer gostId;
	private String gostNaziv;
	private String gostMesto;
	private String gostJmbg;
	private String gostTelefon;

	
	private Integer sobaId;
	private String sobaNaziv;
	private Integer sobaBrojKreveta;
	private boolean sobaSlobodno;
	private String sobaSlobodnoTekst;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getDatumvremeUlaz() {
		return datumvremeUlaz;
	}
	public void setDatumvremeUlaz(Timestamp datumvremeUlaz) {
		this.datumvremeUlaz = datumvremeUlaz;
	}
	public String getDatetimeUlaz() {
		return datetimeUlaz;
	}
	public void setDatetimeUlaz(String datetimeUlaz) {
		this.datetimeUlaz = datetimeUlaz;
	}
	public Timestamp getDatumvremeIzlaz() {
		return datumvremeIzlaz;
	}
	public void setDatumvremeIzlaz(Timestamp datumvremeIzlaz) {
		this.datumvremeIzlaz = datumvremeIzlaz;
	}
	public String getDatetimeIzlaz() {
		return datetimeIzlaz;
	}
	public void setDatetimeIzlaz(String datetimeIzlaz) {
		this.datetimeIzlaz = datetimeIzlaz;
	}
	
	
	
	
	public Integer getGostId() {
		return gostId;
	}
	public void setGostId(Integer gostId) {
		this.gostId = gostId;
	}
	public String getGostNaziv() {
		return gostNaziv;
	}
	public void setGostNaziv(String gostNaziv) {
		this.gostNaziv = gostNaziv;
	}	
	public String getGostMesto() {
		return gostMesto;
	}
	public void setGostMesto(String gostMesto) {
		this.gostMesto = gostMesto;
	}
	public String getGostJmbg() {
		return gostJmbg;
	}
	public void setGostJmbg(String gostJmbg) {
		this.gostJmbg = gostJmbg;
	}
	public String getGostTelefon() {
		return gostTelefon;
	}
	public void setGostTelefon(String gostTelefon) {
		this.gostTelefon = gostTelefon;
	}
	
	
	
	
	public Integer getSobaId() {
		return sobaId;
	}
	public void setSobaId(Integer sobaId) {
		this.sobaId = sobaId;
	}
	public String getSobaNaziv() {
		return sobaNaziv;
	}
	public void setSobaNaziv(String sobaNaziv) {
		this.sobaNaziv = sobaNaziv;
	}
	public Integer getSobaBrojKreveta() {
		return sobaBrojKreveta;
	}
	public void setSobaBrojKreveta(Integer sobaBrojKreveta) {
		this.sobaBrojKreveta = sobaBrojKreveta;
	}
	public boolean isSobaSlobodno() {
		return sobaSlobodno;
	}
	public void setSobaSlobodno(boolean sobaSlobodno) {
		this.sobaSlobodno = sobaSlobodno;
	}
	public String getSobaSlobodnoTekst() {
		return sobaSlobodnoTekst;
	}
	public void setSobaSlobodnoTekst(String sobaSlobodnoTekst) {
		this.sobaSlobodnoTekst = sobaSlobodnoTekst;
	}
	
	
	
}
