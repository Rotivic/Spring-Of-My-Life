package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Cuentas_Usuarios;
import com.example.demo.repositorios.CuentasUsuariosRepository;

@Service
public class CuentasUsuariosImpl implements CuentasUsuariosServiceI{

	@Autowired
	private CuentasUsuariosRepository cuenta_usu;
	
	@Override
	public List<Cuentas_Usuarios> findAllTitulatesByAccountId(float accountId) {
		
		return cuenta_usu.findAllTitulatesByAccountId(accountId);
	}

	@Override
	public void deleteTitularUser(int id_account, int id_user) {
		
		cuenta_usu.deleteTitularUser(id_account, id_user);
		
	}

	@Override
	public void deleteMiddleUser(int id_account) {
		
		cuenta_usu.deleteMiddleUser(id_account);
		
	}

}
