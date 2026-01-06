package com.example.app.rls.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "rls", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Role {
    
    @Id
	@Column(name= "\"id\"", precision=19)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq_gen")
	@SequenceGenerator(name = "roles_seq_gen", sequenceName= "roles_seq", schema = "rls", allocationSize =1)
	private Long id; 

	@Column(name="rolename")
	private String rolename;

}