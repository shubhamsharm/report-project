package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		// cash plan data
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("John");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approvved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenifitAmount(5000.0);
		
		
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
        c2.setDenialReason("Rental Income");
        
        
        CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Smith");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setTerminatedDate(LocalDate.now());
        c3.setTerminationReason("Employed");
        c3.setBenifitAmount(5000.00);
        
        
             // Food plan data
     		CitizenPlan c4 = new CitizenPlan();
     		c4.setCitizenName("David");
     		c4.setGender("Male");
     		c4.setPlanName("Food");
     		c4.setPlanStatus("Approvved");
     		c4.setPlanStartDate(LocalDate.now());
     		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c4.setBenifitAmount(4000.0);
     		
     		
     		
     		CitizenPlan c5 = new CitizenPlan();
     		c5.setCitizenName("Robert");
     		c5.setGender("Male");
     		c5.setPlanName("Food");
     		c5.setPlanStatus("Denied");
            c5.setDenialReason("Property Income");
             
             
             CitizenPlan c6 = new CitizenPlan();
     		c6.setCitizenName("Orlen");
     		c6.setGender("Fe-Male");
     		c6.setPlanName("Food");
     		c6.setPlanStatus("Terminated");
     		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
     		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c6.setTerminatedDate(LocalDate.now());
     		c6.setBenifitAmount(5000.00);
            c6.setTerminationReason("Employed");
            
            // Medicine Record
            
            CitizenPlan c7 = new CitizenPlan();
     		c7.setCitizenName("Charles");
     		c7.setGender("Male");
     		c7.setPlanName("Medical");
     		c7.setPlanStatus("Approvved");
     		c7.setPlanStartDate(LocalDate.now());
     		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c7.setBenifitAmount(4000.0);
     		
     		
     		
     		CitizenPlan c8 = new CitizenPlan();
     		c8.setCitizenName("Butler");
     		c8.setGender("Male");
     		c8.setPlanName("Medical");
     		c8.setPlanStatus("Denied");
            c8.setDenialReason("Property Income");
             
             
             CitizenPlan c9 = new CitizenPlan();
     		c9.setCitizenName("Neel");
     		c9.setGender("Fe-Male");
     		c9.setPlanName("Medical");
     		c9.setPlanStatus("Terminated");
     		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
     		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c9.setTerminatedDate(LocalDate.now());
     		c9.setBenifitAmount(5000.00);
            c9.setTerminationReason("Govt Job");
            
            
           // Employment Record
            
            CitizenPlan c10 = new CitizenPlan();
     		c10.setCitizenName("Steve");
     		c10.setGender("Male");
     		c10.setPlanName("Employment");
     		c10.setPlanStatus("Approvved");
     		c10.setPlanStartDate(LocalDate.now());
     		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c10.setBenifitAmount(4000.0);
     		
     		
     		
     		CitizenPlan c11 = new CitizenPlan();
     		c11.setCitizenName("Moris");
     		c11.setGender("Male");
     		c11.setPlanName("Employment");
     		c11.setPlanStatus("Denied");
            c11.setDenialReason("Property Income");
             
             
            CitizenPlan c12 = new CitizenPlan();
     		c12.setCitizenName("Gibs");
     		c12.setGender("Fe-Male");
     		c12.setPlanName("Employment");
     		c12.setPlanStatus("Terminated");
     		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
     		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
     		c12.setTerminatedDate(LocalDate.now());
     		c12.setBenifitAmount(5000.00);
            c12.setTerminationReason("Govt Job");
            
            List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12); 
            
            repo.saveAll(list);
		 
	}
 
}
