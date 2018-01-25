package jwd.autobuska.service;

import java.util.List;

import jwd.autobuska.model.Prevoznik;


public interface PrevoznikService {
	List<Prevoznik> findAll();
	Prevoznik findOne(Long id);
	void save(Prevoznik prevoznik);
	void remove(Long id);

}
