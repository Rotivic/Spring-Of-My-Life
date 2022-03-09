package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.CuentaBancaria;
import com.example.demo.entidades.Cuentas_Usuarios;
import com.example.demo.repositorios.CuentaBancariaRepository;

@Service
public class CuentaBancariaImpl implements CuentaBancariaServiceI{

	@Autowired
	private CuentaBancariaRepository accountRepository;
	
	//Genericas
	
	@Override
	public List<CuentaBancaria> findAllAccounts() {
		
		return accountRepository.findAll();
	}

	@Override
	public CuentaBancaria findAccountById(long IdAccount) {
		
		return accountRepository.findById(IdAccount).get();
	}

	@Override
	public void removeAccountById(long IdAccount) {
		
		accountRepository.deleteById(IdAccount);
		
	}

	@Override
	public void addAccount(CuentaBancaria cB) {
		
		accountRepository.save(cB);
		
	}

	@Override
	public void updateAccount(CuentaBancaria cB) {
		// TODO Auto-generated method stub
		accountRepository.save(cB);
	}

	@Override
	public List<CuentaBancaria> findAllAccountsByUser(float userId) {
		// TODO Auto-generated method stub
		return accountRepository.findAllAccountsByUser(userId);
	}

	@Override
	public void insertNewAccountUser(int id_user, CuentaBancaria cB) {
		accountRepository.save(cB);
		System.out.print(id_user);
		accountRepository.insertNewAccountUser(id_user, Integer.parseInt(cB.getId().toString()));
		
	}

	







	
	
	
}
