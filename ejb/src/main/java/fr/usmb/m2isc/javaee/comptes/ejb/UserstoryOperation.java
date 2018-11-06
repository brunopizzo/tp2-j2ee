package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

public interface UserstoryOperation {

	Userstory createOne(Userstory userstory);

	Userstory getOne(int id);


	void deleteOne(int id);

	void updateOne(Userstory userstory);

}