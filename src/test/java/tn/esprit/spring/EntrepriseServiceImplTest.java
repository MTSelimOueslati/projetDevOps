package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	@Autowired
	IEntrepriseService entrepriseService;


	@Test
	@Order(1)
	public void testAjouterEntreprise() {
		Entreprise ent = new Entreprise("SaidiJam Mat", "Agriculture");
		int id = entrepriseService.ajouterEntreprise(ent);
		Assert.assertNotNull(entrepriseService.getEntrepriseById(id));
	}

	@Test
	public void testAjouterDepartment() {
		Departement dep = new Departement("Agriculture");
		int id = entrepriseService.ajouterDepartement(dep);
		Assert.assertNotNull(entrepriseService.getDepartementById(id));
	}

	@Test
	public void testaffecterDepartementAEntreprise() {
		Entreprise ent = new Entreprise("Espritt", "Education");
		int idEntrep = entrepriseService.ajouterEntreprise(ent);

		Departement dep = new Departement("Web");
		int idDep = entrepriseService.ajouterDepartement(dep);

		int idEntrepDep=entrepriseService.affecterDepartementAEntreprise(idDep, idEntrep);
		Assert.assertEquals(idEntrepDep,idEntrep);

	}

	@Test
	public void testdeleteEntrepriseById() {
		
		Entreprise ent = new Entreprise("Soc", "DEV");
		int id = entrepriseService.ajouterEntreprise(ent);
		
		int value = entrepriseService.deleteEntrepriseById(id);
		Assert.assertEquals(1, value);
		
		int WrongValue = entrepriseService.deleteEntrepriseById(1812132);
		Assert.assertEquals(WrongValue, -1);

	}

	@Test
	public void testdeleteDepartementById() {
		Departement dep = new Departement("Info");
		int id = entrepriseService.ajouterDepartement(dep);
		
		int value = entrepriseService.deleteDepartementById(id);
		Assert.assertEquals(1, value);
		
		int WrongValue = entrepriseService.deleteDepartementById(1812132);
		Assert.assertEquals(WrongValue, -1);

	}

	@Test
	public void testgetEntrepriseById() {

		Entreprise ent = new Entreprise("Soc", "DEV");
		int id = entrepriseService.ajouterEntreprise(ent);
		
		Entreprise e1 = entrepriseService.getEntrepriseById(id);
		Assert.assertNotNull(e1);

		Entreprise e2 = entrepriseService.getEntrepriseById(12556);
		Assert.assertNull(e2);
	};

	@Test
	public void testgetDepartementById() {
		Departement dep = new Departement("Info");
		int id = entrepriseService.ajouterDepartement(dep);
		
		
		Departement d1 = entrepriseService.getDepartementById(id);
		Assert.assertNotNull(d1);

		Departement d2 = entrepriseService.getDepartementById(213232);
		Assert.assertNull(d2);
	};

}
