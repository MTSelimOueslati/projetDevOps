package tn.esprit.spring.services;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	public static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	String errorText = "Erreur:";

	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public int affecterDepartementAEntreprise(int depId, int entrepriseId) {
		logger.info("START affecterDepartementAEntreprise ");
		Departement dep = new Departement();
		try {
			logger.debug("init dep" + dep);

			Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);
			Optional<Departement> d = deptRepoistory.findById(depId);
			logger.trace("Début Test : verifier l'existence du l'entreprise et du Departement");

			if (e.isPresent() && d.isPresent()) {

				logger.trace("début: récuperer entrep by ID");
				Entreprise entrepriseManagedEntity = e.get();
				logger.trace("fin: récuperer entrep by ID");

				logger.trace("début: récuperer dep by ID");
				Departement depManagedEntity = d.get();
				logger.trace("début: récuperer dep by ID");

				logger.trace("début: affectation d'entrep -> departement");
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				logger.trace("fin: affectation d'entrep -> departement");

				dep = depManagedEntity;

				logger.debug("new Dep" + dep);

				logger.debug("Entrep: " + depManagedEntity.getEntreprise().getId() + "-"
						+ depManagedEntity.getEntreprise().getName() + "affecté au department:"
						+ depManagedEntity.getName() + "-" + depManagedEntity.getId());
				logger.trace("FIN Test : verifier l'existence du l'entreprise et du Departement");

			}
		 else {
			logger.trace("Entrep ou Dep n'exitse pas");
			logger.trace("FIN Test : verifier l'existence du l'entreprise et du Departement");
		}

		} catch (Exception e) {
			logger.error(errorText + e);
		}

		logger.info("END affecterDepartementAEntreprise ");
		return dep.getEntreprise().getId();
	}

	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public int deleteEntrepriseById(int entrepriseId) {
		logger.info("START deleteEntrepriseById ");
		Optional<Entreprise> e = entrepriseRepoistory.findById(entrepriseId);

		try {

			logger.trace("Début Test : verifier l'existence du l'entreprise");
			if (e.isPresent()) {
				
				logger.debug("Entrep exitse:" + e.get().getId());

				logger.trace("débbut suppression");
				entrepriseRepoistory.delete(e.get());
				logger.trace("fin suppression");
				logger.trace("FIN Test : verifier l'existence du l'entreprise");
				
				return 1;
			} else {
				logger.trace("Entrep n'exitse pas");
				logger.trace("FIN Test : verifier l'existence du l'entrep");
				return -1;
			}

		} catch (Exception err) {
			logger.error(errorText + err);

		}
		if(e.isPresent()) {
			logger.debug("Entrep supprimée:" + e.get().getId());
		}
	
		logger.info("END deleteEntrepriseById ");

		return 0;

	}

	@Transactional
	public int deleteDepartementById(int depId) {
		logger.info("START deleteDepartementById ");
		Optional<Departement> d = deptRepoistory.findById(depId);

		try {
			logger.trace("Début Test : verifier l'existence du lDepartement");

			if (d.isPresent()) {
				logger.debug("Departement exitse:" + d.get().getId());

				logger.trace("débbut suppression");
				deptRepoistory.delete(d.get());
				logger.trace("fin suppression");
				logger.trace("FIN Test : verifier l'existence du Departement");
				return 1;
			} else {

				logger.trace("Departement n'exitse pas");
				logger.trace("FIN Test : verifier l'existence du Departement");
				return -1;
			}

		} catch (Exception e) {
			logger.error(errorText+ e);

		}
		if(d.isPresent()) {
			logger.debug("Departement suprimée:" + d.get().getId());
		}
		logger.info("END deleteDepartementById ");
		return 0;

	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();	
	}
	
	public Departement getDepartementById(int departementId) {
		logger.info("START getDepartementById ");
		try {
			Optional<Departement> d = deptRepoistory.findById(departementId);

			logger.trace("Début Test : verifier l'existence du departement");
			if (d.isPresent()) {

				logger.debug("Entreprise exitse:" + d.get().getId());

				logger.trace("débbut Get");
				return d.get();
			}
			logger.trace("fin Get");
			logger.trace("FIN Test : verifier l'existence du departement");
		} catch (Exception e) {
			logger.error(errorText+ e);

		}
		logger.info("END getDepartementById ");

		return null;
	}
	
	
}
