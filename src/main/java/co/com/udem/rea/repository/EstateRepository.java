package co.com.udem.rea.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import co.com.udem.rea.entity.Estate;

public interface EstateRepository extends CrudRepository<Estate, Long> {
	
//	@Query("SELECT e FROM Estate e WHERE e.estateValue BETWEEN 0 AND ?1")
//    public String getPropertiesInValueRange(float initValue, float endValue);
	
	Set<Estate> findAllByEstateValueBetween(float initValue, float endValue);
}
