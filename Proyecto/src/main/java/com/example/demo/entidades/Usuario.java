package com.example.demo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"id"})
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "NIF", unique=true)
	private String nif;


	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "Apellidos")
	private String apellidos;

	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 24, message = "el tamaño tiene que estar entre 4 y 24")
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	@Column(name= "Anio_nacimiento")
	private Date anioNac;
	
	@Column(name = "Direccion")
	private String direccion;
	
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false, unique = false)
	private String email;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 9, max = 18, message = "el tamaño tiene que estar entre 9 y 18")
	@Column(nullable = false)
	private String tlf;
	
	/*
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.ALL})
	@JoinTable( name = "cuentas_usuarios", 
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"))
	private Set<CuentaBancaria> cuentas;
*/



	
	
}
