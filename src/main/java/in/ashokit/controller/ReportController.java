package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.CitizenPlanService;

@Controller
public class ReportController {
	
	@Autowired
	private CitizenPlanService citizenPlanService;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", citizenPlanService.getPlanNames());
		model.addAttribute("status", citizenPlanService.getPlanStatus());
	}
	
	@PostMapping("/search")
	public String handleSearch(SearchRequest request, Model model) {
		init(model);
		System.out.println(request);
		List<CitizenPlan> citizenList = citizenPlanService.search(request);
		model.addAttribute("plans", citizenList);
		request.getPlanName();
		request.getPlanStatus();
		request.getGender();
		request.getStartDate();
		request.getEndDate();	
		
		return "index";
	}
	

}
