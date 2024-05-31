package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.UserBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.BankModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;
@WebServlet(name = "BankCtl", urlPatterns = { "/ctl/BankCtl" })
public class BankCtl extends BaseCtl {

	
	 @Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require","name"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("bankName"))) {
			request.setAttribute("name"," Name must contains alphabet only");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("bankName"))) {
			request.setAttribute("bankName", PropertyReader.getValue("error.require","bankName"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("bankName"))) {
			request.setAttribute("bankName","Bank name must contains alphabet only");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("branch"))) {
			request.setAttribute("branch", PropertyReader.getValue("error.require","branch"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("branch"))) {
			request.setAttribute("branch","Bank name must contains alphabet only");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("accountType"))) {
			request.setAttribute("accountType", PropertyReader.getValue("error.require","accountType"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("accountType"))) {
			request.setAttribute("accountType","Bank name must contains alphabet only");
			pass = false;
		}
		 return pass;
	}
	 
	 @Override
	protected BaseBean populateBean(HttpServletRequest request) {
		BankBean bean = new BankBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setAccountNo(DataUtility.getStringData(request.getParameter("accountNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setBankName(DataUtility.getString(request.getParameter("bankName")));
		bean.setBranch(DataUtility.getString(request.getParameter("branch")));
		bean.setAccountType(DataUtility.getString(request.getParameter("accountType")));
		 return bean;
	}
	 
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String op = DataUtility.getString(request.getParameter("operation"));
		 BankModel model = new BankModel();
		 
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id > 0 || op != null) {
			BankBean bean;
			try {
			bean = model.findByPk(id);
			System.out.println(bean);
			
			ServletUtility.setBean(bean, request);
			}catch (Exception e){
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		 ServletUtility.forward(getView(), request, response);
	}
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String op = DataUtility.getString(request.getParameter("operation"));
		 long id = DataUtility.getLong(request.getParameter("id"));
			
			BankModel model = new BankModel();
			
			if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
				BankBean bean = (BankBean) populateBean(request);
				try {
					if (id > 0) {
	 
						model.update(bean);
						ServletUtility.setBean(bean, request);
						ServletUtility.setSuccessMessage("Account is successfully Updated", request);

					} else {
					long pk =	model.add(bean);

						ServletUtility.setSuccessMessage("Account is successfully Added", request);
						bean.setId(pk);
					}

				} catch (ApplicationException e) {
					
					ServletUtility.handleException(e, request, response);
					return;
				} catch (DuplicateRecordException e) {
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Account number already exists", request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (OP_DELETE.equalsIgnoreCase(op)) {

				BankBean bean = (BankBean) populateBean(request);
				try {
					model.delete(bean);
					ServletUtility.redirect(ORSView.BANK_CTL, request, response);
					return;
				} catch ( Exception e) {
					
					ServletUtility.handleException(e, request, response);
					return;
				}

			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.BANK_LIST_CTL, request, response);
				return;
			}
		


		
		 ServletUtility.forward(getView(), request, response);
	}
	@Override
	protected String getView() {

		return ORSView.BANK_VIEW;
	}

}
