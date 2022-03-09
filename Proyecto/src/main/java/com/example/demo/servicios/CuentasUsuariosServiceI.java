package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Cuentas_Usuarios;

public interface CuentasUsuariosServiceI {

	public List<Cuentas_Usuarios> findAllTitulatesByAccountId(float accountId);
	
	public void deleteTitularUser(int id_account,int id_user);
	
	public void deleteMiddleUser(int id_account);
}
