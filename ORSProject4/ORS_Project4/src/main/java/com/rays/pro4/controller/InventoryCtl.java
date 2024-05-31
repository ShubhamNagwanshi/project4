package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Model.InventoryModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;
@WebServlet(name = "InventoryCtl", urlPatterns = { "/ctl/InventoryCtl" })
public class InventoryCtl extends BaseCtl{

	

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("validate method OrderCtl");
		boolean pass = true;
		if(DataValidator.isNull(request.getParameter("name"))){
			request.setAttribute("name", PropertyReader.getValue("error.require","name"));
			pass = false;
			System.out.println("validate1");
		}else if(!DataValidator.isName(request.getParameter("name"))){
				request.setAttribute("name", "Name must contains Alphabets");
				pass = false;
				System.out.println("validate2");
			}

		if(DataValidator.isNull(request.getParameter("product"))){
			request.setAttribute("product", PropertyReader.getValue("error.require","product"));
			pass = false;
			System.out.println("validate1");
		}else if(!DataValidator.isName(request.getParameter("product"))){
				request.setAttribute("product", "product must contains Alphabets");
				pass = false;
				System.out.println("validate2");
			}
		if(DataValidator.isNull(request.getParameter("quality"))){
			request.setAttribute("quality", PropertyReader.getValue("error.require","quality"));
			pass = false;
			System.out.println("validate1");
		}else if(!DataValidator.isName(request.getParameter("quality"))){
				request.setAttribute("quality", "quality must contains Alphabets");
				pass = false;
				System.out.println("validate2");
			}

		if(DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require","address"));
			pass = false;
			System.out.println("validate5");
		}else if(!DataValidator.isName(request.getParameter("address"))) {
			request.setAttribute("address", "Address must contains alphabets");
			pass = false;
			System.out.println("validate6");
		}
		return pass;
	}
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		InventoryBean bean = new InventoryBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
//		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		bean.setQuality(DataUtility.getString(request.getParameter("quality")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderCtl do get");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("operation get kia");
		long id = DataUtility.getLong(request.getParameter("id"));
		
		System.out.println("id get kia ");
		InventoryModel model = new InventoryModel();
		
		if(id > 0 || op != null) {
			InventoryBean bean;
			try {
				System.out.println("do get me check kia");
			bean =	model.findByPk(id);
			
			ServletUtility.setBean(bean, request);
			System.out.println("findbypk and setbean");
			System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post OrderCtl");
	 String op = DataUtility.getString(request.getParameter("operation"));
	 System.out.println("operation");
	 long id = DataUtility.getLong(request.getParameter("id"));
	 System.out.println("id get ki");
	 InventoryModel model = new InventoryModel();
	 
	 if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
		 InventoryBean bean = (InventoryBean) populateBean(request);
				 
		 
		 try {
			 if(id > 0) {
				 System.out.println("condition check id ");
			model.update(bean);
			System.out.println("operation update mila");
			ServletUtility.setBean(bean, request);
			ServletUtility.setSuccessMessage("Data updated successfully", request);
			 }else {
				 System.out.println("else part before add method ");
			long pk =	 model.add(bean);
			System.out.println("after add method");
			ServletUtility.setSuccessMessage("Data added successfully", request);
			bean.setId(pk);
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }else if(OP_DELETE.equalsIgnoreCase(op)) {
	InventoryBean bean = (InventoryBean) populateBean(request);
	
	try {
		model.delete(bean);
		ServletUtility.redirect(getView(), request, response);
		return;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	 }else if(OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.INVENTORY_LIST_CTL, request, response);	
			return;
		}
	 ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.INVENTORY_VIEW;
	}

}
