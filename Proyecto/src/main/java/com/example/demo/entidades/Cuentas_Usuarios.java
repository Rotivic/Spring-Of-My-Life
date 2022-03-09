package com.example.demo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "Cuentas_Usuarios")
public class Cuentas_Usuarios implements Serializable{

	
	
	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuarios;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private CuentaBancaria cuentas;

	public Long getUserId() {
		return usuarios.getId();
	}
	
	public Long getAccountId() {
		return cuentas.getId();
	}
	
	public String getUserName() {
		return usuarios.getNombre();
	}
	
	@Override
	public String toString() {
		return "Cuentas_Usuarios [id=" + id + ", usuarios=" + usuarios.getId() + ", cuentas=" + cuentas.getId() + "]";
	}
	
	
	
}
