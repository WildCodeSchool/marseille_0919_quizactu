package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "ROLE")
public class Role implements Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	public Role() {
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
    
    

}