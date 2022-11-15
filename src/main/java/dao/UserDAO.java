package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbcontext.DBUtil;
import entity.User;

public class UserDAO {

	// dang nhap nguoi dung
	public boolean login(String username, String password) {
		DBUtil db = DBUtil.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
					return true;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return false;
	}

	// dang ky nguoi dung
	public boolean signUpUser(String fn, String em, String un, String pw) {

		DBUtil db = DBUtil.getInstance();
		String sql = "insert into users(fullname,email,username,password, isAdmin) values(?,?,?,?,?);";
		Connection con = null;
		if (checkUsername(un) == true) {
			try {
				con = db.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, fn);
				statement.setString(2, em);
				statement.setString(3, un);
				statement.setString(4, pw);
				statement.setInt(5, 0);
				statement.executeUpdate();
				return true;
			} catch (Exception e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			} finally {
				try {
					DBUtil.closeConnection(con);
				} catch (SQLException e) {
					Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		return false;

	}

	// hien thi thong tin nguoi dung
	public ArrayList<User> displayUsers() {
		DBUtil db = DBUtil.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		ArrayList<User> uList = new ArrayList<User>();
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				uList.add(
						new User(Integer.parseInt(rs.getString("id")), rs.getString("fullname"), rs.getString("email"),
								rs.getString("username"), rs.getString("password"), rs.getString("isAdmin")));
			}
		} catch (Exception e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return uList;
	}

	// ham dung de check xem co trung ten dang nhap khi dang ky hay khong
	public boolean checkUsername(String un) {
		DBUtil db = DBUtil.getInstance();
		String sql = "Select * from users";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(un)) {
					return false;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return true;
	}

	// xoa nguoi dung
	public void rmvUser(String id) {
		DBUtil db = DBUtil.getInstance();
		String sql = "DELETE from users where id=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			int id1 = Integer.parseInt(id);
			statement.setInt(1, id1);
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// cap nhat thong tin cua nguoi dung
	public void updUser(String idd, String s1, String s2, String s3, String s4) {
		DBUtil db = DBUtil.getInstance();
		String sql = "Update users SET fullname=?,email=?,password=?,isAdmin=? WHERE id=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement = con.prepareStatement(sql);
			statement.setString(1, s1);
			statement.setString(2, s2);
			statement.setString(3, s3);
			statement.setString(4, s4);
			statement.setInt(5, Integer.parseInt(idd));
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
