package jwd.autobuska.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.autobuska.model.Prevoznik;
import jwd.autobuska.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{
	
	
	@Override
	public Prevoznik convert(PrevoznikDTO source) {
		Prevoznik prevoznik = new Prevoznik();
	
		prevoznik.setNaziv(source.getNaziv());
		prevoznik.setAdresa(source.getAdresa());
		prevoznik.setPib(source.getPib());
		
		
		return prevoznik;
	}

}
