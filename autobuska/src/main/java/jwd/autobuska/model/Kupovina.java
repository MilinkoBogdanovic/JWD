package jwd.autobuska.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Kupovina {
	
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private Integer kolicina;

	@ManyToOne(fetch=FetchType.EAGER)
	private Linija linija;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Linija getLinija() {
		return linija;
	}
	
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public void setLinija(Linija linija) {
		this.linija = linija;
		if(linija!=null && !linija.getKupovine().contains(this)){
			linija.getKupovine().add(this);
		}
	}
}
