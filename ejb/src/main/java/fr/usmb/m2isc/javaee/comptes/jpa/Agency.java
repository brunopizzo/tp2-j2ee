package fr.usmb.m2isc.javaee.comptes.jpa;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries ({
		@NamedQuery(name="all", query="SELECT a FROM Agency a"),
})
@Entity
public class Agency implements Serializable {

	@Id
	private String name;
	@OneToOne
	private Backlog backlog;

	public Agency() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	@Override
	public String toString() {
		return "Agency{" +
				"name='" + name + '\'' +
				", backlog=" + backlog +
				'}';
	}
}
