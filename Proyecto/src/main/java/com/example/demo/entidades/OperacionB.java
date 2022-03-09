package com.example.demo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
@Table(name = "OperacionesBancarias")
public class OperacionB implements Serializable{

	
	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message="No puede estar vacio")
	@Column(name="FechaRealizacion")
	@Temporal(TemporalType.DATE)
	private Date fechaRealizacion;
	
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "CantidadDesplazada", nullable=false)
	private String cantidad;
	
	
	@Column(name = "TipoDeOperacion", nullable=false)
	private String tipoOperacion;
	
	@ManyToOne
	@JoinColumn(name="account_id", nullable = true)
	private CuentaBancaria cuentaBancaria;

}
