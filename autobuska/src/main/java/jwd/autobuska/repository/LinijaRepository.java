package jwd.autobuska.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.autobuska.model.Linija;

@Repository
public interface LinijaRepository 
	extends JpaRepository<Linija, Long> {

	Page<Linija> findByPrevoznikId(Long prevoznikId, Pageable pageRequest);

	@Query("SELECT k FROM Linija k WHERE "
			+ "(:destinacija IS NULL or k.destinacija like :destinacija ) AND "
			+ "(:prevoznikId IS NULL or k.prevoznik.id = :prevoznikId ) AND "
			+ "(:maxCena IS NULL OR k.cena <= :maxCena)"
			)
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija, 
			@Param("prevoznikId") Long prevoznikId, 
			@Param("maxCena") Integer maxCena,
			Pageable pageRequest);

}
