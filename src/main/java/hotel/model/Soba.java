package hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="soba")
public class Soba  {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String naziv;
	@Column
	private Integer brojKreveta;
	@Column
	private boolean slobodno;
	
	private String slobodnoTekst;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="gost")
	private Gost gost;
	
	
	public Soba() {
		this.slobodno=true;
		this.slobodnoTekst="DA";
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



	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
	}
	

	
}
