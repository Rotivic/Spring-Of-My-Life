package com.example.demo.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.Cuentas_Usuarios;



public interface CuentaBancariaRepository  extends JpaRepository<CuentaBancaria, Long>{

	CuentaBancaria getByNumAccount(String numAccount);
	
	List<CuentaBancaria> findByNumAccountLike(String numAccount);
	
	@Query(value="SELECT a.* FROM cuentas_bancarias a INNER JOIN cuentas_usuarios b ON a.id=b.account_id WHERE b.usuario_id = ?1", nativeQuery=true)
	List<CuentaBancaria> findAllAccountsByUser(float userId);
	
	@Modifying
	@Query(value="INSERT INTO cuentas_usuarios (usuario_id, account_id) VALUES (?1,?2)", nativeQuery=true)
	@Transactional
	void insertNewAccountUser(int id_user,int id_account);

	

	
}
