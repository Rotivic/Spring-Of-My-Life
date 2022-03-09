package com.example.demo.servicios;
import java.util.List;

import com.example.demo.entidades.OperacionB;


public interface OperacionBServiceI {

	public void addOperation(OperacionB operation);
	
	public List<OperacionB> findAllOperationsByAccount(float idAccount);
	
	public List<OperacionB> findByAccountId(float idAccount);
	
	
}
