package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class UserstoryBean implements UserstoryOperation {

	@PersistenceContext
	private EntityManager em;

	public UserstoryBean() {
	}
	
	@Override
	public Userstory createOne(Userstory userstory) {
		em.persist(userstory);
		return userstory;
	}

	@Override
	public Userstory getOne(int id){
		return em.find(Userstory.class, id);
	}


	@Override
	public void deleteOne(int id){
		em.remove(getOne(id));
	}

	@Override
	public void updateOne(Userstory userstory){
		em.merge(userstory);
	}
	


}
