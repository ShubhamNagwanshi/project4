package com.rays.proj4.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Model.OrderModel;

public class TestOrder {
	public static void main(String[] args) throws Exception {
//	 testAdd();
//		testUpdate();
//		testSearch();
		testFindByPk();
//		testDelete();
	}

	private static void testDelete()throws Exception {
		
		OrderBean bean = new OrderBean();
		OrderModel model = new OrderModel();
		bean.setId(7);
		
		model.delete(bean);
		
		
	}

	private static void testFindByPk()throws Exception {
		OrderBean bean = new OrderBean();
		OrderModel model = new OrderModel();
		
	   bean  =	model.findByPk(2);
		
		System.out.println(bean.getId());
		System.out.print(" "+bean.getName());
		System.out.print(" "+bean.getDob());
		System.out.println(" "+bean.getOrderType());
	}

	private static void testSearch() throws Exception {
		
		OrderBean bean = new OrderBean();
		OrderModel model = new OrderModel();
		
		List list = new ArrayList();
		
		
	list =	model.search(bean, 1, 10);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			bean = (OrderBean) it.next();
			
			System.out.print(" "+bean.getId()+"-->");
			System.out.print(" "+bean.getName());
			System.out.print(" "+bean.getDob());
			System.out.println(" "+bean.getOrderType());
		}
		
		
	}

	private static void testUpdate() throws Exception {

		OrderBean bean = new OrderBean();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		bean.setId(4);
		bean.setName("Dipesh");
//		bean.setDob(sdf.parse("05-07-1997"));
		bean.setDob("15-03-2023");
		bean.setOrderType("Medium");
		
		OrderModel model = new OrderModel();
		model.update(bean);
		
		System.out.println("qwerty");

	}

	private static void testAdd() throws Exception {

		OrderBean bean = new OrderBean();
		OrderModel model = new OrderModel();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		bean.setName("Hardeep");
//		bean.setDob(sdf.parse("01-03-2023"));
		bean.setDob("01-03-2023");
		bean.setOrderType("Large");

		model.add(bean);

	}

}
