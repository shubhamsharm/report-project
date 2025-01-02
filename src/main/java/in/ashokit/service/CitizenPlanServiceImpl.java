package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
		CitizenPlan entity = new CitizenPlan();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		     LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		
		if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		     LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanStartDate(localDate);
		}
		
		return citizenPlanRepository.findAll(Example.of(entity));
		
	}

	@Override
	public boolean exportPdf(HttpServletResponse response)throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		// Creating Font 
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);
		
		// Creating Paragraph
		Paragraph paragraph = new Paragraph("Citizen Plans",fontTitle);
		
		//Align the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		Paragraph p = new Paragraph("Citizen Plan Info");
		document.add(p);
		PdfPTable table = new PdfPTable(6);
		
		table.addCell("Id");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		
		List<CitizenPlan> citizenList = citizenPlanRepository.findAll();
		for(CitizenPlan plan : citizenList) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(String.valueOf(plan.getPlanStartDate()));
			table.addCell(String.valueOf(plan.getPlanEndDate()));
		}
		
		document.add(table);
		document.close();
		return true ;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet= workbook.createSheet("plans_data");
		Row headerRow =  sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benifit Amount");
		
		List<CitizenPlan> records = citizenPlanRepository.findAll();
		int dataRowIndex = 1;
		for(CitizenPlan plans: records) {
	    Row dataRow = 	sheet.createRow(dataRowIndex);
	    dataRow.createCell(0).setCellValue(plans.getCitizenId());
	    dataRow.createCell(1).setCellValue(plans.getCitizenName());
	    dataRow.createCell(2).setCellValue(plans.getPlanName());
	    dataRow.createCell(3).setCellValue(plans.getPlanStatus());
	    dataRow.createCell(4).setCellValue(plans.getPlanStartDate());
	    dataRow.createCell(5).setCellValue(plans.getPlanEndDate());
	    if(null != plans.getBenifitAmount()) {
	    	 dataRow.createCell(6).setCellValue(plans.getBenifitAmount());
	    }
	    else {
	    	dataRow.createCell(6).setCellValue("N/A");
	    }
	   
	    
	    dataRowIndex++;
	    
		}
		
		
		ServletOutputStream outStream = response.getOutputStream();
		workbook.write(outStream);
		workbook.close();
		return true;
	}

}
