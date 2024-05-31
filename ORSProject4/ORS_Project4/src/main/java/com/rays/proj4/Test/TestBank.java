package com.rays.proj4.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Model.BankModel;

public class TestBank {
	public static void main(String[] args) throws Exception {
//	testAdd();
//	testUpdate();
//	 testDelete();
//	 testSearch();
//		testFindByPk();

	}

	private static void testFindByPk() throws Exception {
		
			BankBean bean = new BankBean();
			BankModel model = new BankModel();
			// int accountNo = 124;
			bean = model.findByPk(1);

			
			System.out.print(bean.getId()+"->");
			System.out.print(" " +bean.getAccountNo());
			System.out.print(" " + bean.getName());
			System.out.print(" " + bean.getBankName());
			System.out.print(" " + bean.getBranch());
			System.out.println(" " + bean.getAccountType());
	}

	private static void testSearch() throws Exception {
		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		List list = new ArrayList();

//	bean.setAccountNo(123);

		list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (BankBean) it.next();

			System.out.print(bean.getId()+"->");
			System.out.print(" " +bean.getAccountNo());
			System.out.print(" " + bean.getName());
			System.out.print(" " + bean.getBankName());
			System.out.print(" " + bean.getBranch());
			System.out.println(" " + bean.getAccountType());
//		System.out.println("123");
		}

	}

	private static void testDelete() throws Exception {
		BankBean bean = new BankBean();
		BankModel model = new BankModel();
		bean.setId(1);

		model.delete(bean);

	}

	private static void testUpdate() throws Exception {

		BankBean bean = new BankBean();
		BankModel model = new BankModel();

		bean.setAccountNo("125");
		bean.setName("Gopal");
		bean.setBankName("Yes Bank");
		bean.setBranch("Dehli");
		bean.setAccountType("Saving");
		bean.setId(1);
		

		model.update(bean);
	}

	private static void testAdd() throws Exception {

		BankBean bean = new BankBean();
		BankModel model = new BankModel();

		bean.setAccountNo("125");
		bean.setName("Gopal");
		bean.setBankName("Axis Bank");
		bean.setBranch("Indore");
		bean.setAccountType("Saving");

		model.add(bean);
	}
}
