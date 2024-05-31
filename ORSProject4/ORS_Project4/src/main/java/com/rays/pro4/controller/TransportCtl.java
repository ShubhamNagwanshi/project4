package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Bean.TransportBean;
import com.rays.pro4.Model.OrderModel;
import com.rays.pro4.Model.TransportModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

public class TransportCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		TransportModel model = new TransportModel();

		try {
			List nlist = model.list();
			request.setAttribute("nlist", nlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

		super.preload(request);
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Name should contain Alphabhets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", PropertyReader.getValue("error.require", "location"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("location", "Name should contain Alphabhets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader.getValue("error.require", "type"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("type"))) {
			request.setAttribute("type", "Name should contain Alphabhets only");
			pass = false;
		}
		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		TransportBean bean = new TransportBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setName(DataUtility.getString(request.getParameter("location")));
		bean.setName(DataUtility.getString(request.getParameter("type")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("OrderCtl do get");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("operation get kia");
		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println("id get kia ");
		TransportModel model = new TransportModel();

		if (id > 0 || op != null) {
			TransportBean bean;
			try {
				System.out.println("do get me check kia");
				bean = model.findByPk(id);

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("do post OrderCtl");
		 String op = DataUtility.getString(request.getParameter("operation"));
		 System.out.println("operation");
		 long id = DataUtility.getLong(request.getParameter("id"));
		 System.out.println("id get ki");
		 TransportModel model = new TransportModel();
		 
		 if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			 TransportBean bean = (TransportBean) populateBean(request);
					 
			 
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
			 TransportBean bean = (TransportBean) populateBean(request);
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
