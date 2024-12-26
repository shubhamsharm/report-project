package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {
	
	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public List<String> getPlanNames() {
		List<String> listNames =  citizenPlanRepository.getPlanNames();
		return listNames ;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> statusList  = citizenPlanRepository.getPlanStatus();
		return statusList ;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		return citizenPlanRepository.findAll();
		
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

}
