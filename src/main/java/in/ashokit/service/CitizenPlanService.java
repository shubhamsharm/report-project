package in.ashokit.service;

import java.util.List;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface CitizenPlanService {
	
	 
	
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> search(SearchRequest request);
	public boolean exportPdf(HttpServletResponse response)throws Exception;
	public boolean exportExcel(HttpServletResponse response) throws Exception;

}
