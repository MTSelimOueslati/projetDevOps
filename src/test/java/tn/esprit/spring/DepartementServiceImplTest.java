package tn.esprit.spring;




import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	private static final Logger l = LogManager.getLogger(DepartementServiceImplTest.class);
	@Autowired
	IEmployeService iEmployeService;
	@Autowired
	IEntrepriseService iEntrepriseService;
	@Autowired
	EmployeRepository employeRepository;

	@Autowired
	DepartementRepository departementRepoistory;
	
	
	@Test

	public void testaffecterEmployeADepartement() {

	boolean val= true;
	boolean res= false;
	iEmployeService.affecterEmployeADepartement (1,3);
	Optional<Employe> emp = employeRepository.findById(1);
	List<Departement> deps= emp.get().getDepartements();
	int size=deps.size();
	for (int i=0; i<size; i++){
	if (deps.get(i).getId()==3){
	res=true;
	}
	}
	assertEquals(val,res);
	}

	@Test
	public void testdesaffecterEmployeDuDepartement() {

	boolean val= true;
	boolean res= true;
	iEmployeService.desaffecterEmployeDuDepartement(1,3);
	Optional<Employe> emp = employeRepository.findById(1) ;
	List<Departement> deps= emp.get().getDepartements();
	int size=deps.size();
	for (int i=0; i<size; i++){

	if (deps.get(i).getId()==3){

	res=false;
	}

	 

	}

	assertEquals(val,res);
	}	

}
