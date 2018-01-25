package jwd.autobuska.web.dto;

public class PrevoznikDTO {

	private Long id;
	
	private String naziv;
	
	private String adresa;
	
	private String pib;	
	
	private Long linijaId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getPib() {
		return pib;
	}
	public void setPib(String pib) {
		this.pib = pib;
	}
	public Long getLinijaId() {
		return linijaId;
	}
	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}

}
