package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.InventoryBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Util.JDBCDataSource;

public class InventoryModel {
	public long nextPk() throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select max(id) from st_inventory");

		long pk = 0;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;

	}

	public long add(InventoryBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into st_inventory values(?, ?, ?, ? ,?)");

		long pk = 0;
		pk = nextPk();

		ps.setLong(1, pk);
		ps.setString(2, bean.getName());
//		ps.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(3, bean.getProduct());
		ps.setString(4, bean.getQuality());
		ps.setString(5, bean.getAddress());

		int i = ps.executeUpdate();
		System.out.println("Data  Successfully" + i);
		return pk;

	}

	public void update(InventoryBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("update st_inventory set name=?, product=?, quality=?, address=? where id=?");

		ps.setString(1, bean.getName());
//		ps.setDate(2, new java.sql.Date(bean.getDob().getTime()));
		ps.setString(2, bean.getProduct());
		ps.setString(3, bean.getQuality());
		ps.setString(4, bean.getAddress());
		ps.setLong(5, bean.getId());

		int i = ps.executeUpdate();
		return;

	}

	public List search(InventoryBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_inventory where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
//			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
//				Date d = new java.sql.Date(bean.getDob().getTime());
//				sql.append(" AND DOB like '" + d + "%'");
//				System.out.println("date");
//			}
			if (bean.getProduct() != null && bean.getProduct().length() > 0) {
//				Date d = new java.sql.Date(bean.getDob().getTime());
				sql.append(" AND product like '" + bean.getProduct() + "%'");
				System.out.println("date");
			}

			System.out.println("dob run");
			if (bean.getQuality() != null && bean.getQuality().length() > 0) {
				sql.append(" and quality like '" + bean.getQuality() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			bean = new InventoryBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
//			bean.setDob(rs.getDate(3));
			bean.setProduct(rs.getString(3));
			bean.setQuality(rs.getString(4));
			bean.setAddress(rs.getString(5));

			list.add(bean);
		}
		return list;
	}

	public InventoryBean findByPk(long id) throws Exception {
		InventoryBean bean = null;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from st_inventory where id = ?");

		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			bean = new InventoryBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setProduct(rs.getString(3));
			bean.setQuality(rs.getString(4));
			bean.setAddress(rs.getString(5));
		}

		return bean;

	}

	public void delete(InventoryBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from st_inventory where id = ?");

		ps.setLong(1, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data deleted successfully" + i);

	}

	public List list() throws Exception {
		return list(0, 0);

	}

	public List list(int pageNo, int pageSize) throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer(" select * from st_inventory");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("preload........" + sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				InventoryBean bean = new InventoryBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setProduct(rs.getString(3));
				bean.setQuality(rs.getString(4));
				bean.setAddress(rs.getString(5));

				list.add(bean);

			}
			conn.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

}
