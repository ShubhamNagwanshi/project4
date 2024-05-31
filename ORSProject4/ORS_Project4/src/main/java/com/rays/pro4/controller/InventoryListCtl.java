package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Model.InventoryModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;
@WebServlet(name = "InventoryListCtl", urlPatterns = { "/ctl/InventoryListCtl" })
public class InventoryListCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		InventoryModel model = new InventoryModel();

		try {
			List olist = model.list(0, 0);

			request.setAttribute("olist", olist);

		} catch (Exception e) {
			e.printStackTrace();
		}

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		InventoryBean bean = (InventoryBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		InventoryModel model = new InventoryModel();

		try {
			list = model.search(bean, pageNo, pageSize);

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.setBean(bean, request);

			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		InventoryBean bean = (InventoryBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		InventoryModel model = new InventoryModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			System.out.println("inner search");
			pageNo = 1;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.INVENTORY_CTL, request, response);
			return;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.INVENTORY_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				InventoryBean deletebean = new InventoryBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));

					try {
						model.delete(deletebean);
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
					ServletUtility.setSuccessMessage("Data is Deleted Successfully", request);
					System.out.println("success msg");
				}

			} else {

				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}
		try {
			System.out.println("search");
			// ServletUtility.forward(getView(), request, response);
			list = model.search(bean, pageNo, pageSize);
			System.out.println("qwerty1");

		} catch (Exception e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			System.out.println("qwerty");
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.setBean(bean, request);

		ServletUtility.forward(getView(), request, response);
	}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.INVENTORY_LIST_VIEW;
	}

}
