package com.example.demo.entidades;


import java.io.Serializable;
import java.sql.Date;
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
public class UsuarioModelo implements Serializable{
	
	
	
	private Long id;


	private String nif;



	private String apellidos;

	

	private String nombre;
	


	private String anioNac;
	

	private String direccion;
	

	private String email;
	

	private String tlf;
	

	private Set<CuentaBancaria> cuentas;




	
	
}

