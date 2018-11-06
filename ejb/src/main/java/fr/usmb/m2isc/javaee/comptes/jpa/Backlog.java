package fr.usmb.m2isc.javaee.comptes.jpa;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Backlog implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int priority;
	private int estimate;
	private String description;
	@OneToMany(
			targetEntity=Userstory.class,
			cascade=ALL,
			mappedBy="backlog",
			orphanRemoval=true,
			fetch = FetchType.EAGER)
	private List<Userstory> userstories = new ArrayList();


	
	public Backlog() {
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Userstory> getUserstories() {
		return userstories;
	}

	public void setUserstories(List<Userstory> userstories) {
		this.userstories = userstories;
	}


	@Override
	public String toString() {
		return "Backlog{" +
				"id=" + id +
				", date=" + date +
				", priority=" + priority +
				", estimate=" + estimate +
				", description='" + description + '\'' +
				", userstories=" + userstories +
				'}';
	}
}
