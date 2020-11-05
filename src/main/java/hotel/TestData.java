package hotel;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hotel.model.Gost;
import hotel.model.Rezervacija;
import hotel.model.Soba;
import hotel.service.GostService;
import hotel.service.RezervacijaService;
import hotel.service.SobaService;


@Component
public class TestData { 
	
	@Autowired
	private GostService gostService;
	
	@Autowired
	private SobaService sobaService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
		
	
	@PostConstruct
	public void init() {
		
		
		//  GOSTI
		
		Gost gost1 = new Gost();
		gost1.setNaziv("Pera Peric");
		gost1.setMesto("Novi Sad");
		gost1.setJmbg("1106970775533"); 
		gost1.setTelefon("0641122333");
		gost1 = gostService.save(gost1);
		
		
		Gost gost2 = new Gost();
		gost2.setNaziv("Jova Jovic");
		gost2.setMesto("Beograd");
		gost2.setJmbg("2208960335577"); 
		gost2.setTelefon("0634455666");
		gost2 = gostService.save(gost2);
		
		Gost gost3 = new Gost();
		gost3.setNaziv("Vasa Vasic");
		gost3.setMesto("Subotica");
		gost3.setJmbg("0710950775533"); 
		gost3.setTelefon("0657788999");
		gost3 = gostService.save(gost3);
	
		
		// SOBE
		
		Soba soba1 = new Soba();
		soba1.setNaziv("Single 01");
		soba1.setBrojKreveta(1);
		soba1 = sobaService.save(soba1);

		Soba soba2 = new Soba();
		soba2.setNaziv("Single 02");
		soba2.setBrojKreveta(1);
		soba2.setGost(gost1);
		soba2 = sobaService.save(soba2);

		Soba soba3 = new Soba();
		soba3.setNaziv("Double 01");
		soba3.setBrojKreveta(2);
		soba3.setGost(gost2);
		soba3 = sobaService.save(soba3);

		Soba soba4 = new Soba();
		soba4.setNaziv("Double 02");
		soba4.setBrojKreveta(2);
		soba4 = sobaService.save(soba4);

		Soba soba5 = new Soba();
		soba5.setNaziv("Double 03");
		soba5.setBrojKreveta(2);
		soba5 = sobaService.save(soba5);

		Soba soba6 = new Soba();
		soba6.setNaziv("Double 04");
		soba6.setBrojKreveta(2);
		soba6 = sobaService.save(soba6);

		Soba soba7 = new Soba();
		soba7.setNaziv("Triple 01");
		soba7.setBrojKreveta(3);
		soba7 = sobaService.save(soba7);

		Soba soba8 = new Soba();
		soba8.setNaziv("Triple 02");
		soba8.setBrojKreveta(3);
		soba8 = sobaService.save(soba8);

		Soba soba9 = new Soba();
		soba9.setNaziv("Family room 01");
		soba9.setBrojKreveta(4);
		soba9 = sobaService.save(soba9);

		Soba soba10 = new Soba();
		soba10.setNaziv("Family room 02");
		soba10.setBrojKreveta(4);
		soba10.setGost(gost3);
		soba10 = sobaService.save(soba10);

		Soba soba11 = new Soba();
		soba11.setNaziv("Suite 01");
		soba11.setBrojKreveta(2);
		soba11 = sobaService.save(soba11);

		Soba soba12 = new Soba();
		soba12.setNaziv("Suite 02");
		soba12.setBrojKreveta(2);
		soba12 = sobaService.save(soba12);
	
		
		// REZERVACIJE
		
		Rezervacija rezervacija1 = new Rezervacija();
		rezervacija1.setDatumvremeUlaz(java.sql.Timestamp.valueOf("2020-04-11 10:10:10"));
		rezervacija1.setDatetimeUlaz("11.04.2020. 10:10");
		rezervacija1.setDatumvremeIzlaz(java.sql.Timestamp.valueOf("2020-04-23 10:10:10"));
		rezervacija1.setDatetimeIzlaz("23.04.2020. 10:10");
		rezervacija1.setGost(gost1);
		rezervacija1.setSoba(soba2);
		rezervacija1 = rezervacijaService.save(rezervacija1);
		
		Rezervacija rezervacija2 = new Rezervacija();
		rezervacija2.setDatumvremeUlaz(java.sql.Timestamp.valueOf("2020-03-06 14:30:10"));
		rezervacija2.setDatetimeUlaz("06.03.2020. 14:30");
		rezervacija2.setDatumvremeIzlaz(java.sql.Timestamp.valueOf("2020-03-19 14:30:10"));
		rezervacija2.setDatetimeIzlaz("19.03.2020. 14:30");
		rezervacija2.setGost(gost2);
		rezervacija2.setSoba(soba3);
		rezervacija2 = rezervacijaService.save(rezervacija2);
		
		Rezervacija rezervacija3 = new Rezervacija();
		rezervacija3.setDatumvremeUlaz(java.sql.Timestamp.valueOf("2020-04-01 18:15:10"));
		rezervacija3.setDatetimeUlaz("01.04.2020. 18:15");
		rezervacija3.setDatumvremeIzlaz(java.sql.Timestamp.valueOf("2020-04-15 18:15:10"));
		rezervacija3.setDatetimeIzlaz("15.04.2020. 18:15");
		rezervacija3.setGost(gost3);
		rezervacija3.setSoba(soba10);
		rezervacija3 = rezervacijaService.save(rezervacija3);
	

		
	}

}
