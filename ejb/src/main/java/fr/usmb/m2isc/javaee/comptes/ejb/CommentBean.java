package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.usmb.m2isc.javaee.comptes.jpa.Comment;

@Stateless
@Remote
public class CommentBean implements CommentOperation {

	@PersistenceContext
	private EntityManager em;

	public CommentBean() {
	}
	
	@Override
	public Comment createOne(Comment comment) {
		em.persist(comment);
		return comment;
	}

	@Override
	public Comment getOne(int id){
		return em.find(Comment.class, id);
	}


	@Override
	public void deleteOne(int id){
		em.remove(getOne(id));
	}

	@Override
	public void updateOne(Comment comment){
		em.merge(comment);
	}
	


}
