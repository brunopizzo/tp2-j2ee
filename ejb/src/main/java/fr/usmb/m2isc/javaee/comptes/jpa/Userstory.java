package fr.usmb.m2isc.javaee.comptes.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Userstory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String title;

	@ManyToOne
	@JoinColumn(nullable=false)
	private Backlog backlog;

	@OneToMany(
			targetEntity=Comment.class,
			cascade=ALL,
			mappedBy="userstory",
			orphanRemoval=true,
			fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList();


	public Userstory() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Userstory{" +
				"id=" + id +
				", date=" + date +
				", title='" + title + '\'' +
				", comments=" + comments +
				'}';
	}
}
