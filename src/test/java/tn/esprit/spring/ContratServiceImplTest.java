package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
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
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(ContratServiceImplTest.class);

@Autowired
IEmployeService iEmployeService;

@Autowired
ContratRepository contratRepoistory;
@Autowired
private EmployeRepository employeRepository;

private static String update = "update";

private Contrat contrat;
private Employe employe1;

@Before
public void setUp() {
	employe1 = new Employe("Oueslati", "mohamed taieb", "mohamedtaieb.oueslati@gmail.com", true, Role.INGENIEUR);
	contrat = contratRepoistory.save(new Contrat(new Date(2020, 04, 10), "CDI", 2000));
	employeRepository.save(employe1);
	contrat.setEmploye(employe1);
	contratRepoistory.save(contrat);
}

@After
public void tearDown() {
	contratRepoistory.deleteAll();
	employeRepository.deleteAll();
}


@Test
public void TestajouterContrat() {
	String sDate1="02/02/2000";
	Date date1=new Date(sDate1);
	Contrat contrat = new Contrat(1, date1, "test", 1000,employe1);
	int contratadd = iEmployeService.ajouterContrat(contrat);
	assertEquals(contratadd,contrat.getReference());
}

/*
@Test
public void TestgetAllContratByEmploye() {
	Contrat contrat1 = iEmployeService.getAllContratByEmploye(employe1);
	l.info("GetContratByEmploye : "+ contrat1);
	assertThat(contrat1.getReference()).isEqualTo(contrat.getReference());
}
*/
@Test
public void TestgetAllContrats() {
List<Contrat> contrats = iEmployeService.getAllContrats();
assertThat(contrats.size()).isGreaterThan(0);
}

@Test
public void TestdeleteContratById() {

iEmployeService.deleteContratById(contrat.getReference());
 Optional<Contrat> contratdelete = contratRepoistory.findById(contrat.getReference());
 assertThat(contratdelete).isEmpty();
}




}