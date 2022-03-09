package com.example.demo.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entidades.Cuentas_Usuarios;

public interface CuentasUsuariosRepository extends JpaRepository<Cuentas_Usuarios, Long>{


	@Query(value="SELECT a.* FROM cuentas_usuarios a WHERE a.account_id = ?1", nativeQuery=true)
	List<Cuentas_Usuarios> findAllTitulatesByAccountId(float accountId);
	//SELECT a.usuario_id FROM cuentas_usuarios a WHERE a.account_id = 1
	//DELETE * FROM cuentas_usuarios a WHERE a.account_id = 1 AND a.usuario_id = 1
	
	@Modifying
	@Query(value="DELETE a FROM cuentas_usuarios a WHERE a.account_id = ?1 AND a.usuario_id = ?2", nativeQuery=true)
	@Transactional
	void deleteTitularUser(int id_account,int id_user);
	
	@Modifying
	@Query(value="DELETE a FROM cuentas_usuarios a WHERE a.account_id = ?1", nativeQuery=true)
	@Transactional
	void deleteMiddleUser(int id_account);
	
	
}
