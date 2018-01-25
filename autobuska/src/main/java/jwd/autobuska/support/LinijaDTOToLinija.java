package jwd.autobuska.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.autobuska.model.Linija;
import jwd.autobuska.service.PrevoznikService;
import jwd.autobuska.service.LinijaService;
import jwd.autobuska.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija 
	implements Converter<LinijaDTO, Linija>{
	
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikService prevoznikService;
	
	
	@Override
	public Linija convert(LinijaDTO source) {
		Linija linija;
		if(source.getId()==null){
			linija = new Linija();
			linija.setPrevoznik(
					prevoznikService.findOne(
							source.getPrevoznikId()));
		}else{
			linija = linijaService.findOne(source.getId());
		}
		linija.setBrojMesta(source.getBrojMesta());
		linija.setCena(source.getCena());
		linija.setVremePolaska(source.getVremePolaska());
		linija.setDestinacija(source.getDestinacija());
		linija.setPrevoznik(prevoznikService.findOne(source.getPrevoznikId()));
		return linija;
	}

}
