package jwd.autobuska.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.autobuska.model.Kupovina;
import jwd.autobuska.web.dto.KupovinaDTO;

@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO> {

	@Override
	public KupovinaDTO convert(Kupovina arg0) {
		
		KupovinaDTO dto = new KupovinaDTO();
		dto.setId(arg0.getId());
		dto.setKolicina(arg0.getKolicina());
		return dto;
	}

}
