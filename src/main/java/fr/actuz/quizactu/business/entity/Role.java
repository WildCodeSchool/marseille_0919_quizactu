package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Role() {
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}