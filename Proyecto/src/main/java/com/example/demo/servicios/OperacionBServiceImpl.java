package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.OperacionB;
import com.example.demo.repositorios.OperacionBRepository;

@Service
public class OperacionBServiceImpl implements OperacionBServiceI{

	@Autowired
	private OperacionBRepository operacionBRepository;	
	
	@Override
	public List<OperacionB> findAllOperationsByAccount(float idAccoutn) {
		// TODO Auto-generated method stub
		
		return operacionBRepository.findByAccountId(idAccoutn);
	}

	@Override
	public List<OperacionB> findByAccountId(float idAccount) {
		
		return operacionBRepository.findByAccountId(idAccount);
	}

	@Override
	public void addOperation(OperacionB operation) {
		
		operacionBRepository.save(operation);
		
	}

}
