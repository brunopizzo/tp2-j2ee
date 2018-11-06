package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Comment;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

public interface CommentOperation {

	Comment createOne(Comment comment);

	Comment getOne(int id);


	void deleteOne(int id);

	void updateOne(Comment comment);

}