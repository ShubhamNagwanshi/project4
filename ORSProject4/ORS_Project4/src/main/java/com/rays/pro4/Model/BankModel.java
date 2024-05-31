package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Util.JDBCDataSource;

public class BankModel {
	public long nextPK() throws Exception {

		

		String sql = "SELECT MAX(ID) FROM ST_BANK";
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			throw new Exception("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;

	}


	public long add(BankBean bean) throws Exception {
		long pk=0; 
		Connection conn = JDBCDataSource.getConnection();
		pk = nextPK();
		PreparedStatement pstm = conn.prepareStatement("insert into st_bank values(?,?,?,?,?,?)");

		pstm.setLong(1, pk);
		pstm.setString(2, bean.getAccountNo());
		pstm.setString(3, bean.getName());
		pstm.setString(4, bean.getBankName());
		pstm.setString(5, bean.getBranch());
		pstm.setString(6, bean.getAccountType());
		

		int i = pstm.executeUpdate();

		System.out.println("Data added " + i);
		return pk;
		
		

	}

	public void update(BankBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement(
				"update st_bank set accountNo = ?, name = ?, bankName = ?, branch = ? , accountType = ?  where id = ?");

		pstm.setString(1, bean.getAccountNo());
		pstm.setString(2, bean.getName());
		pstm.setString(3, bean.getBankName());
		pstm.setString(4, bean.getBranch());
		pstm.setString(5, bean.getAccountType());
		pstm.setLong(6, bean.getId());
		

		int i = pstm.executeUpdate();

		System.out.println("data updated" + i);

	}

	public void delete(BankBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement("delete from st_bank where id = ?");

		pstm.setLong(1, bean.getId());

		int i = pstm.executeUpdate();

		System.out.println("data deleted" + i);

	}

	public List search(BankBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_bank where 1=1");

		if (bean != null) {
			if (bean.getAccountNo() != null && bean.getAccountNo().length()>0) {
				sql.append(" and accountNo like '" + bean.getAccountNo() + "%'");

			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getBankName() != null && bean.getBankName().length() > 0) {
				sql.append(" and bankName like '" + bean.getBankName() + "%'");
			}
			if (bean.getBranch() != null && bean.getBranch().length() > 0) {
				sql.append(" and branch like '" + bean.getBranch() + "%'");
			}
			if (bean.getAccountType() != null && bean.getAccountType().length() > 0) {
				sql.append(" and accountType like '" + bean.getAccountType() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		List list = new ArrayList();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			bean = new BankBean();
			bean.setId(rs.getLong(1));
			bean.setAccountNo(rs.getString(2));
			bean.setName(rs.getString(3));
			bean.setBankName(rs.getString(4));
			bean.setBranch(rs.getString(5));
			bean.setAccountType(rs.getString(6));

			list.add(bean);
		}

		return list;

	}

	public BankBean findByPk(long id) throws Exception {

		BankBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement("select * from st_bank where id = ?");

		pstm.setLong(1, id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new BankBean();
			bean.setId(rs.getLong(1));
			bean.setAccountNo(rs.getString(2));
			bean.setName(rs.getString(3));
			bean.setBankName(rs.getString(4));
			bean.setBranch(rs.getString(5));
			bean.setAccountType(rs.getString(6));

		}
		return bean;

	}
}
