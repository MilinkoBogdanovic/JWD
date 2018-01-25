package jwd.autobuska.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Linija {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private Integer brojMesta;
	@Column
	private Integer cena;
	@Column
	private String vremePolaska;
	@Column
	private String destinacija;

	
	@ManyToOne(fetch=FetchType.EAGER)
	private Prevoznik prevoznik;
	
	@OneToMany(mappedBy="linija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Kupovina> kupovine = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Prevoznik getPrevoznik() {
		return prevoznik;
	}
	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(prevoznik!=null && !prevoznik.getLinije().contains(this)){
			prevoznik.getLinije().add(this);
		}
	}
	
	public Integer getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(Integer brojMesta) {
		this.brojMesta = brojMesta;
	}
	public Integer getCena() {
		return cena;
	}
	public void setCena(Integer cena) {
		this.cena = cena;
	}
	public String getVremePolaska() {
		return vremePolaska;
	}
	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}
	public String getDestinacija() {
		return destinacija;
	}
	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}
	
	public List<Kupovina> getKupovine() {
		return kupovine;
	}
	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	public void addKupovina(Kupovina kupovina){
		this.kupovine.add(kupovina);
		
		if(!this.equals(kupovina.getLinija())){
			kupovina.setLinija(this);
		}
	}
}
