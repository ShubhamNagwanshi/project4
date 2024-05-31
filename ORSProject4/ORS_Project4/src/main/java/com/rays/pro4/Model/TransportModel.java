package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.TransportBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Util.JDBCDataSource;

public class TransportModel {

	public long nextPk() throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select max(id) from st_transport");

		long pk = 0;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;

	}

	public long add(TransportBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into st_transport values(?,?,?,?)");

		long pk = 0;
		pk = nextPk();

		ps.setLong(1, pk);
		ps.setString(2, bean.getName());
		ps.setString(3, bean.getLocation());
		ps.setString(4, bean.getType());

		int i = ps.executeUpdate();

		System.out.println("Data added Succesfully" + i);

		return pk;

	}

	public void update(TransportBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("update st_transport set name=?, location=?, type=? where id=?");

		ps.setString(1, bean.getName());
		ps.setString(2, bean.getLocation());
		ps.setString(3, bean.getType());
		ps.setLong(4, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data updated successfully" + i);
	}

	public void delete(TransportBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete from st_transport where id=?");

		ps.setLong(1, bean.getId());

		int i = ps.executeUpdate();

		System.out.println("Data deleted successfully" + i);

	}

	public List search(TransportBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_transport where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" and location like '" + bean.getLocation() + "%'");
			}
			if (bean.getType() != null && bean.getType().length() > 0) {
				sql.append(" and type like '" + bean.getType() + "%'");
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
			bean = new TransportBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setLocation(rs.getString(3));
			bean.setType(rs.getString(4));

			list.add(bean);

		}

		return list;

	}

	public TransportBean findByPk(long id) throws Exception {
		TransportBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from st_transport where id=?");

		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			bean = new TransportBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setLocation(rs.getString(3));
			bean.setType(rs.getString(4));

		}

		return bean;

	}

	public List list() throws Exception {
		return list(0, 0);

	}

	public List list(int pageNo, int pageSize) throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer(" select * from st_transport");
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
				TransportBean bean = new TransportBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setType(rs.getString(4));

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
