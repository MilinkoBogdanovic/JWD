package jwd.autobuska.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.autobuska.model.Prevoznik;
import jwd.autobuska.model.Linija;
import jwd.autobuska.service.PrevoznikService;
import jwd.autobuska.service.LinijaService;
import jwd.autobuska.support.PrevoznikToPrevoznikDTO;
import jwd.autobuska.support.LinijaToLinijaDTO;
import jwd.autobuska.support.PrevoznikDTOToPrevoznik;
import jwd.autobuska.web.dto.PrevoznikDTO;
import jwd.autobuska.web.dto.LinijaDTO;

@RestController
@RequestMapping(value="/api/prevoznici")
public class ApiPrevoznikController {
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikToPrevoznikDTO toDTO;
	@Autowired
	private PrevoznikDTOToPrevoznik toPrevoznik;
	@Autowired
	private LinijaToLinijaDTO toLinijaDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PrevoznikDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(prevoznikService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PrevoznikDTO> get(
			@PathVariable Long id){
		
		Prevoznik prevoznik = prevoznikService.findOne(id);
		
		if(prevoznik == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(prevoznik),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PrevoznikDTO> add(
			@RequestBody PrevoznikDTO noviPrevoznik){
	
		
		Prevoznik prevoznik = toPrevoznik.convert(noviPrevoznik); 
		prevoznikService.save(prevoznik);
		
		return new ResponseEntity<>(toDTO.convert(prevoznik),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{prevoznikId}/linije")
	public ResponseEntity<List<LinijaDTO>> linijePrevoznika(
			@PathVariable Long prevoznikId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Linija> linije = linijaService.findByPrevoznikId(pageNum, prevoznikId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		return  new ResponseEntity<>(
				toLinijaDTO.convert(linije.getContent()),
				headers,
				HttpStatus.OK);
	}
}
