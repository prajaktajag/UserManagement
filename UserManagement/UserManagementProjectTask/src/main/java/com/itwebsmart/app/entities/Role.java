package com.itwebsmart.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Role( String name) {
			super();
			
			this.name = name;
		}

		public Role() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Role [id=" + id + ", name=" + name + "]";
		}
	    
	    
}
