package com.example.demo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "CuentasBancarias")
public class CuentaBancaria implements Serializable{

	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "NumeroCuenta", unique=true)
	private String numAccount;
	
	
	@Column(name="FechaAlta")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	
	@Column(name="Saldo", nullable=false)
	private double balance = 0;
	/*
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "cuentas")
	private Set<Usuario> clientes;
*/	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "account_id")
	private List<OperacionB> operations;

	@Override
	public String toString() {
		return "CuentaBancaria [id=" + id + ", numAccount=" + numAccount + ", createAt=" + createAt + ", balance="
				+ balance + "]";
	}
	
	
	
}
