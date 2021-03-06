package jwd.autobuska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.autobuska.model.Kupovina;

@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Long>{

}
