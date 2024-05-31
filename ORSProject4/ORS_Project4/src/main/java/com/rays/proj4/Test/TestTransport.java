package com.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.TransportBean;
import com.rays.pro4.Model.TransportModel;

public class TestTransport {
	public static void main(String[] args) throws Exception {

//	testAdd();
//	testUpdate();
//	testDelete();
//		testSearch();
		testFindByPk();
	}

	private static void testFindByPk() throws Exception {

		TransportBean bean = new TransportBean();
		TransportModel model = new TransportModel();

		bean = model.findByPk(2);

		System.out.print(" " + bean.getId() + "-->");
		System.out.print(" " + bean.getName());
		System.out.print(" " + bean.getLocation());
		System.out.println(" " + bean.getType());

	}

	private static void testSearch() throws Exception {

		TransportBean bean = new TransportBean();
		TransportModel model = new TransportModel();

		List list = new ArrayList();
		list = model.search(bean, 1, 10);
		Iterator it = list.iterator();
		while (it.hasNext()) {

			bean = (TransportBean) it.next();

			System.out.print(" " + bean.getId() + "-->");
			System.out.print(" " + bean.getName());
			System.out.print(" " + bean.getLocation());
			System.out.println(" " + bean.getType());
		}
	}

	private static void testDelete() throws Exception {

		TransportBean bean = new TransportBean();
		TransportModel model = new TransportModel();

		bean.setId(4);
		model.delete(bean);
	}

	private static void testUpdate() throws Exception {
		TransportBean bean = new TransportBean();
		TransportModel model = new TransportModel();

		bean.setName("Nitesh");
		bean.setLocation("Bhopal");
		bean.setType("Medium");
		bean.setId(2);

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		TransportBean bean = new TransportBean();
		TransportModel model = new TransportModel();

		bean.setName("Saras");
		bean.setLocation("Nagpur");
		bean.setType("Large");

		model.add(bean);
	}
}
