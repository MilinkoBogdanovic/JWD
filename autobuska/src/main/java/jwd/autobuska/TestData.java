package jwd.autobuska;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.autobuska.model.Prevoznik;
import jwd.autobuska.model.Linija;
import jwd.autobuska.service.PrevoznikService;
import jwd.autobuska.service.LinijaService;

@Component
public class TestData {
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;

	
	/*
	DROP DATABASE IF EXISTS autobuska;
	CREATE DATABASE autobuska DEFAULT CHARACTER SET utf8;

	USE autobuska;

	GRANT ALL ON autobuska.* TO 'autobuska'@'%' IDENTIFIED BY 'autobuska';

	FLUSH PRIVILEGES;
	 */

	
	
	@PostConstruct
	public void init() {
		
		Prevoznik i1 = new Prevoznik();
		i1.setNaziv("Subotica Trans");
		i1.setAdresa("Adresa1");
		i1.setPib("345356346");
		prevoznikService.save(i1);
		
		Prevoznik i2 = new Prevoznik();
		i2.setNaziv("Nis ekspres");
		i2.setAdresa("Adresa2");
		i2.setPib("3423423423");
		prevoznikService.save(i2);
		
		Prevoznik i3 = new Prevoznik();
		i3.setNaziv("Lasta");
		i3.setAdresa("Adresa3");
		i3.setPib("2353456345");
		prevoznikService.save(i3);
		
		Prevoznik i4 = new Prevoznik();
		i4.setNaziv("Farlang");
		i4.setAdresa("Adresa4");
		i4.setPib("0234234");
		prevoznikService.save(i4);
		
		
		
		
		Linija k1 = new Linija();
		k1.setBrojMesta(48);
		k1.setCena(640);
		k1.setVremePolaska("08:00");
		k1.setDestinacija("Novi Sad");
		k1.setPrevoznik(i1);
		linijaService.save(k1);
		
		Linija k2 = new Linija();
		k2.setBrojMesta(51);
		k2.setCena(1200);
		k2.setVremePolaska("09:00");
		k2.setDestinacija("Beograd");
		k2.setPrevoznik(i2);
		linijaService.save(k2);
		
		Linija k3 = new Linija();
		k3.setBrojMesta(36);
		k3.setCena(120);
		k3.setVremePolaska("10:00");
		k3.setDestinacija("Tavankut");
		k3.setPrevoznik(i3);
		linijaService.save(k3);
		
		Linija k4 = new Linija();
		k4.setBrojMesta(44);
		k4.setCena(1100);
		k4.setVremePolaska("11:00");
		k4.setDestinacija("Kraljevo");
		k4.setPrevoznik(i4);
		linijaService.save(k4);

	}
}