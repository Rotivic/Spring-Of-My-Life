package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entidades.OperacionB;


public interface OperacionBRepository extends JpaRepository<OperacionB, Long>{

	//List<OperacionB> findByAccountIdLike(float accountId);
	
	@Query(value="Select u.* FROM operaciones_bancarias u WHERE u.account_id  = ?1", nativeQuery=true)
	List<OperacionB> findByAccountId(float idCuenta);
	
}
