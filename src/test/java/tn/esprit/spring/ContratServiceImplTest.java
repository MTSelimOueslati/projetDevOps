package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.exceptions.ResourceNotFoundException;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.IEmployeService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);

@Autowired
IEmployeService iEmployeService;

@Autowired
ContratRepository contratRepoistory;



@Test

	public void testAddContrat()  {
		Employe e = new Employe (1,"mohamed taieb selim","oueslati","mohamedtaiebselim.oueslati@esprit.tn",true,Role.INGENIEUR);
		String sDate1="02/02/2000";
		Date date1=new Date(sDate1);
		Contrat c = new Contrat(1, date1, "test", 1000,e);
		int contratAdded = iEmployeService.ajouterContrat(c);
		assertEquals(contratAdded,c.getReference(),iEmployeService.ajouterContrat(c));
		l.info("contrat ajouté: ", c);
}


@Test
@Rollback(false)
public void testDeleteContrat(int contratId) {
	
	    Contrat contrat = contratRepoistory.findById(contratId).orElseThrow(() -> new ResourceNotFoundException("contrat not found with this id : " + contratId));
	    
	     
	    contratRepoistory.deleteById(contrat.getReference());
	     
     
	    assertThat(contrat).isNull();       
     
}

@Test
public void testdeleteAllContratJPQL() {
	l.info("Start testdeleteAllContratJPQL method test");


	iEmployeService.deleteAllContratJPQL();
	assertNull(contratRepoistory.findAll());
	l.info("End deleteAllEntrepriseJPQL method test");

}


}