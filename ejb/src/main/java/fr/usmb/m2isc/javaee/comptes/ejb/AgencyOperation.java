package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agency;

import java.util.List;

public interface AgencyOperation {

	Agency createOne(Agency agency);

	Agency getOne(String name);

	List<Agency> getAll();

	void deleteOne(String name);

	void updateOne(Agency agency);

}