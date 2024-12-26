package in.ashokit.service;

import java.util.List;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;


public interface CitizenPlanService {
	
	 
	
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> search(SearchRequest request);
	public boolean exportPdf();
	public boolean exportExcel();

}
