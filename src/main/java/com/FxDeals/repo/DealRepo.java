package com.FxDeals.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FxDeals.model.Deal;

@Repository("DealRepo")
public interface DealRepo extends JpaRepository<Deal, Long> {

	// we can use a named query but writing a JPQL or native queries always gives us more control.
	//	boolean existsByDealUniqueId(String dealUniqueId);

	@Query("SELECT COUNT(id) "
			+ "FROM Deal d "
			+ "WHERE d.uuid = :dealUuid")
	Long existitingDealsCount(@Param("dealUuid") String dealUuid);

	@Query("SELECT d "
			+ "FROM Deal d "
			+ "WHERE d.uuid = :dealUuid")
	Deal getDealByUuid(@Param("dealUuid") String dealUuid);

	@Query("SELECT d FROM Deal d")
	List<Deal> getAllDeals();
}
