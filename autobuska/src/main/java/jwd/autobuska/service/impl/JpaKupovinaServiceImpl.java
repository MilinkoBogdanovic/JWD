package jwd.autobuska.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.autobuska.model.Linija;
import jwd.autobuska.model.Kupovina;
import jwd.autobuska.repository.LinijaRepository;
import jwd.autobuska.repository.KupovinaRepository;
import jwd.autobuska.service.KupovinaService;

@Service
public class JpaKupovinaServiceImpl implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository;
	@Autowired
	private LinijaRepository linijaRepository;
	
	@Override
	public Kupovina buyATicket(Long linijaId) {
		
		if(linijaId == null) {
			throw new IllegalArgumentException("Id of a linija cannot be null!");
		}
		
		Linija linija = linijaRepository.findOne(linijaId);
		if(linija == null) {
			throw new IllegalArgumentException("There is no linija with given id!");
		}
		
		if(linija.getBrojMesta() > 0) {
			
			Kupovina kupovina = new Kupovina();
			kupovina.setLinija(linija);
			
			linija.setBrojMesta(linija.getBrojMesta() - kupovina.getKolicina());
			
			kupovinaRepository.save(kupovina);
			linijaRepository.save(linija);
			
			return kupovina;
		}
		
		return null;
		
	}
}
