package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Model.OrderModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;
@WebServlet(name="OrderCtl" , urlPatterns = { "/ctl/OrderCtl" })
public class OrderCtl extends BaseCtl {

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
//		if (DataValidator.isNull(request.getParameter("dob"))) {
//			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
//			pass = false;
//		} else if (!DataValidator.isName(request.getParameter("dob"))) {
//			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
//			pass = false;
//		}

		if(DataValidator.isNull(request.getParameter("orderType"))) {
			request.setAttribute("address", PropertyReader.getValue("require.error","orderType"));
			pass = false;
			System.out.println("validate5");
		}else if(!DataValidator.isName(request.getParameter("orderType"))) {
			request.setAttribute("orderType", "Address must contains alphabets");
			pass = false;
			System.out.println("validate6");
		}
		return pass;
	}
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		OrderBean bean = new OrderBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
//		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setDob(DataUtility.getString(request.getParameter("dob")));
		bean.setOrderType(DataUtility.getString(request.getParameter("orderType")));
		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderCtl do get");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("operation get kia");
		long id = DataUtility.getLong(request.getParameter("id"));
		
		System.out.println("id get kia ");
		OrderModel model = new OrderModel();
		
		if(id > 0 || op != null) {
			OrderBean bean;
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
	 OrderModel model = new OrderModel();
	 
	 if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
		 OrderBean bean = (OrderBean) populateBean(request);
				 
		 
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
	OrderBean bean = (OrderBean) populateBean(request);
	
	try {
		model.delete(bean);
		ServletUtility.redirect(getView(), request, response);
		return;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	 }else if(OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ORDER_LIST_CTL, request, response);	
			return;
		}
	 ServletUtility.forward(getView(), request, response);
	}
	@Override
	protected String getView() {
		return ORSView.ORDER_VIEW;
	}

}
