package fr.usmb.m2isc.javaee.comptes.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String description;
	private String owner;

	@ManyToOne
	@JoinColumn(nullable=false)
	private Userstory userstory;


	public Comment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setUserstory(Userstory userstory) {
		this.userstory = userstory;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", date=" + date +
				", description='" + description + '\'' +
				", owner='" + owner + '\'' +
				'}';
	}
}
