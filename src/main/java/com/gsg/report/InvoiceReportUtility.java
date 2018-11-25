package com.gsg.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gsg.mongo.model.AppUser;
import com.gsg.mongo.model.AppUser.AddressBook;
import com.gsg.mongo.model.OfficeDetails;
import com.gsg.mongo.model.Order;
import com.gsg.mongo.model.OrderDetails;
import com.gsg.mongo.model.ServiceRequest;
import com.gsg.mongo.model.UserVehicle;
import com.gsg.mongo.model.master.SchemeData;
import com.gsg.mongo.model.master.Services;
import com.gsg.services.AppUserService;
import com.gsg.services.OfficeDetailsService;
import com.gsg.services.OrderService;
import com.gsg.utilities.GSGCommon;

@Component
public class InvoiceReportUtility extends ReportUtility {

	@Autowired
	OrderService orderService;

	@Autowired
	AppUserService userService;
	
	@Autowired
	OfficeDetailsService officeService;

	@Autowired
	Invoice invoice;
	
	@Autowired
	ApplicationContext context;

	@Override
	public void reportConstructor(Object... inData) throws Exception {

		//officeService.getByState(state)
		
		
		
		AppUser user = (AppUser) inData[0];//userService.getByUserID(order.getUserId());//user
		Order order = (Order) inData[1];
		
		//AppUser svcEngr = userService.getByUserID(order.getAssignedToUserId());//service engineer
		
		// get ofc address
		//OfficeDetails ofc = officeService.getByState(svcEngr.getServiceArea().getState());
		OfficeDetails ofc = officeService.getOfficeDetails().get(0);

		invoice.setOrderId(order.getOrderId());
		invoice.setInvoiceId(order.getInvoiceNbr());
		invoice.setCompanyName(ofc.getCompanyName());
		invoice.setCompanyAddress(ofc.getAddress().getAddressFull());
		invoice.setOfcStateCd(ofc.getAddress().getStateCd());
		invoice.setGstin(ofc.getGstin());
		invoice.setPlaceOfSupply(ofc.getAddress().getState().toUpperCase());
		invoice.setBank(ofc.getBankdetails());

		
		
		//invoice.setAppUser(user);

		String userFirstName = (user.getFirstName() == null) ? "" : (user.getFirstName());
		String userMiddleName = (user.getMiddleName() == null) ? "" : (user.getMiddleName());
		String userLastName = (user.getLastName() == null) ? "" : (user.getLastName());
		
		invoice.setCustomerName(userFirstName + " " + userMiddleName + " " + userLastName);
		invoice.setCustomerId(user.getUserId());
		
		AddressBook address = null;
		List<AddressBook> addressList = user.getAddress();
		if (!addressList.isEmpty())
			address = addressList.get(0);
		
		if(address == null)	{
			invoice.setCustomerAddress("No address found");
		}	else	{
			invoice.setCustomerAddress(address.getAddressFull());
			invoice.setCustomerState(address.getState());
		}
		

		invoice.setProductType(order.getProdutType());
		if(!order.getOrderDtls().isEmpty()){
			
			if(order.getProdutType().equals(GSGCommon.TYPE_SCHEME)){
				SchemeData sd = (SchemeData) order.getOrderDtls().get(0).getProduct();
				List<SchemeData> sdList = new ArrayList<SchemeData>();
				sdList.add(sd);
				invoice.setSchemeList(sdList);
			}else{ //service
				UserVehicle userVehicle = ((ServiceRequest)order.getOrderDtls().get(0).getProduct()).getUsrVehicle();
				invoice.setVehicleType(userVehicle.getVehicle().getMake()+" - "+userVehicle.getVehicle().getModels());
				invoice.setVehicleRegdNbr(userVehicle.getRegistrationNumber());
				
				List<Services> tempList = new ArrayList<Services>();
				
				for (OrderDetails odtl : order.getOrderDtls()) {
					tempList.addAll(((ServiceRequest) odtl.getProduct()).getServices());
					//invoice.setTotalDiscount(invoice.getTotalDiscount() + odtl.getDiscount());
				}
				
				invoice.setServiceList(tempList);
			}
		}
		
		invoice.setCreationDate(order.getCreationDate());
		
		
		// Create the data source
		List<Object> listDataSource = new ArrayList<Object>();
		listDataSource.add(invoice);
		dataSource = new JRBeanCollectionDataSource(listDataSource);

	}

	@Override
	public void reportMetadata() throws JRException, IOException {
		
		//java.awt.Image image_c = Toolkit.getDefaultToolkit().createImage(JRLoader.loadBytesFromResource("chk_c.jpg"));
		
		jasperReport = JasperCompileManager.compileReport("/root/report/invoice.jrxml");
		JasperReport serviceReport = JasperCompileManager.compileReport("/root/report/subInvoiceService.jrxml");
		JasperReport schemeReport = JasperCompileManager.compileReport("/root/report/subInvoiceScheme.jrxml");

		reportParam.put("CONTEXT",context.getResource("/").getURI().getPath());
		reportParam.put("serviceReport", serviceReport);
		reportParam.put("schemeReport", schemeReport);
	}
	
	@Override
	public void fillReport() throws JRException {
		jasperPrint = JasperFillManager.fillReport(jasperReport, reportParam, dataSource);
	}

	@Override
	public byte[] generateReportByte(String orderId) throws JRException {
		
		//testing
		generateReport(orderId);
		////////
		
		byte[] inputByte = JasperExportManager.exportReportToPdf(jasperPrint);
		logger.info("InvoiceUtility.generatePDF() -- end");
		return inputByte;

	}
	
	@Override
	public void generateReport(String reportName) throws JRException {
		JasperExportManager.exportReportToPdfFile(jasperPrint, "/root/" + reportName + "_"+ System.currentTimeMillis() + ".pdf");
	}



}
