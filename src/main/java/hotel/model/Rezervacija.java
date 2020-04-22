package hotel.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rezervacija")
public class Rezervacija {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column(nullable=false)
	private Timestamp datumvremeUlaz;
	
	private String datetimeUlaz;
	
	@Column(nullable=false)
	private Timestamp datumvremeIzlaz;
	
	private String datetimeIzlaz;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="gost")
	private Gost gost;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="soba")
	private Soba soba;

	

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

	
	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
		}
	

	
	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
		
	}
	
	
	
	
	
	
	
}
