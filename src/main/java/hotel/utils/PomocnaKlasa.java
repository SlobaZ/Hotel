package hotel.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PomocnaKlasa {
	
	
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	

	// Upisi sadasnji Sql datum i vreme 
	public static java.sql.Timestamp  UpisiSadasnjiDatumIVremeSql() {
		Date date = new Date();  
        Timestamp datumIvremeSada=new Timestamp(date.getTime());     
	  return datumIvremeSada;
	}
	
	
	// Konvertuj Sql datum i vreme u String
	public static String  PrikaziTekstualnoDatumIVreme(Timestamp datumIvreme) {
		String tekst = DATE_TIME_FORMAT.format(datumIvreme);
		// String tekst = datumIvreme.toString(); 
		return tekst;
	}
	
	
	// Konvertuj String u Sql datum i vreme
	public static java.sql.Timestamp  KonvertujStringUSqlDatumIVreme(String tekst){
		java.util.Date dateTime = null;
		try {
			dateTime = DATE_TIME_FORMAT.parse(tekst);
		} catch (Exception ex) {
			System.out.println("GRESKA");
		}
		Timestamp datumIvreme = new Timestamp(dateTime.getTime());  // Timestamp.valueOf(tekst);
		return datumIvreme;
	}
	
	
	// Racunanje broja dana
	public static double BrojDana(String ulaz, String izlaz) {
		Date Datum1 = null;
		Date Datum2 = null;
		try {
			Datum1 = DATE_TIME_FORMAT.parse(ulaz);
			Datum2 = DATE_TIME_FORMAT.parse(izlaz);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		long pocetak = Datum1.getTime();
		long kraj = Datum2.getTime();
		long razlika = kraj - pocetak;
		int brojaDana = (int)(razlika/(1000 * 60 * 60 * 24));
		
		return (double)(brojaDana );
    
  }
	
	// Racunanje cene
	public static double cena(double brojDana, String nazivSobe) {
		double cenaPoDanu=0;
		if(nazivSobe.equals("Single 01") || nazivSobe.equals("Single 02") ) {
			cenaPoDanu = 2000.0 ;
		}
		else if(nazivSobe.equals("Double 01") || nazivSobe.equals("Double 02") || nazivSobe.equals("Double 03") || nazivSobe.equals("Double 04") ) {
			cenaPoDanu = 2500.0 ;
		}
		else if(nazivSobe.equals("Triple 01") || nazivSobe.equals("Triple 02")  ) {
			cenaPoDanu = 3500.0 ;
		}
		else if(nazivSobe.equals("Family room 01") || nazivSobe.equals("Family room 02")  ) {
			cenaPoDanu = 2500.0 ;
		}
		else if(nazivSobe.equals("Suite 01") || nazivSobe.equals("Suite 02")  ) {
			cenaPoDanu = 3000.0 ;
		}
		double ukupnaCena = brojDana * cenaPoDanu ;
		return ukupnaCena;	
	}
	
	// Provera opsega Ulaz/Izlaz datuma
	public static boolean odgovaraOpesegu(String ulaznaGranica, String izlaznaGranica) {
		
		boolean pripada = true;
		Date ulazDatum = null;
		Date izlazDatum = null;
		Date danas = null;
		String danasnjiDatum = null;

		
		Date datumDanas = new Date();
		danasnjiDatum = DATE_TIME_FORMAT.format(datumDanas);
		
		try {
			ulazDatum  = DATE_TIME_FORMAT.parse(ulaznaGranica);
			izlazDatum = DATE_TIME_FORMAT.parse(izlaznaGranica);
			danas = DATE_TIME_FORMAT.parse(danasnjiDatum);
			
			if(ulazDatum.before(izlazDatum) && !ulazDatum.before(danas)){
	            pripada = true;
	        }
			else if(ulazDatum.before(danas)){
				pripada=false;
				
			}
			else {
				pripada=false;
				}
			
		} 
		catch (ParseException e) {	
		}
	
		return pripada;
	}

	
}
