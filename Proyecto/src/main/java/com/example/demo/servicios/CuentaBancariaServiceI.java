package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.Cuentas_Usuarios;


public interface CuentaBancariaServiceI {

	public List<CuentaBancaria> findAllAccounts();
	
	public CuentaBancaria findAccountById(long IdAccount);
	
	//public CuentaBancaria findAccountByUser();
	
	public void removeAccountById(long IdAccount);
	
	//public void removeAccountsByUser();
	
	public void addAccount(CuentaBancaria cB);
	
	public void updateAccount(CuentaBancaria cB);	
	
	public List<CuentaBancaria> findAllAccountsByUser(float userId);
	
	public void insertNewAccountUser(int id_user, CuentaBancaria cB);
	
	
}
