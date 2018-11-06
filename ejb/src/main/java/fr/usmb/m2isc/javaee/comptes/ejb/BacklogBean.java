package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Remote
public class BacklogBean implements BacklogOperation {

	@PersistenceContext
	private EntityManager em;

	public BacklogBean() {
	}
	
	@Override
	public Backlog createOne(Backlog backlog) {
		em.persist(backlog);
		return backlog;
	}

	@Override
	public Backlog getOne(int id){
		return em.find(Backlog.class, id);
	}


	@Override
	public void deleteOne(int id){
		em.remove(getOne(id));
	}

	@Override
	public void updateOne(Backlog backlog){
		em.merge(backlog);
	}
	


}
