package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

import java.util.List;

public interface BacklogOperation {

	Backlog createOne(Backlog backlog);

	Backlog getOne(int id);


	void deleteOne(int id);

	void updateOne(Backlog backlog);

}