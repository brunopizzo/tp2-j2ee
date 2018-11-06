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
public class AgencyBean implements AgencyOperation {

	@PersistenceContext
	private EntityManager em;

	public AgencyBean() {
	}
	
	@Override
	public Agency createOne(Agency agency) {
		em.persist(agency);
		return agency;
	}

	@Override
	public Agency getOne(String name){
		return em.find(Agency.class, name);
	}

	@Override
	public List<Agency> getAll() {
		Query req = em.createNamedQuery("all");
		return req.getResultList();
	}

	@Override
	public void deleteOne(String name){
		em.remove(getOne(name));
	}

	@Override
	public void updateOne(Agency agency){
		em.merge(agency);
	}
	


}
