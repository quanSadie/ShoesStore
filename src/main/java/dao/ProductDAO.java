package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbcontext.DBUtil;
import entity.Cart;
import entity.Shoes;

public class ProductDAO {

	// hien thi tat ca san pham
	public ArrayList<Shoes> allShoes() {
		ArrayList<Shoes> list = new ArrayList<Shoes>();
		DBUtil db = DBUtil.getInstance();
		String sql = "Select * from product";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new Shoes(rs.getInt("pid"), rs.getString("pname"), rs.getDouble("price"),
						rs.getString("preview")));
			}
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return list;
	}

	// lay thong tin cua 1 san pham
	public Shoes getProduct(int id) {
		String sql = "";
		DBUtil db = DBUtil.getInstance();
		PreparedStatement pst;

		ResultSet rs;
		Connection con = null;
		Shoes s1 = null;
		try {
			sql = "SELECT * from products where id=? ";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				s1 = new Shoes();
				s1.setShoesID(rs.getInt("pid"));
				s1.setShoesName(rs.getString("pname"));
				s1.setPrice(rs.getDouble("price"));
				s1.setCover(rs.getString("preview"));
			}
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return s1;
	}

	// them san pham
	public void addShoes(String pn, String pp, String pi) {
		DBUtil db = DBUtil.getInstance();
		String sql = "insert into product(pname, price, preview) values(?,?,?);";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, pn);
			statement.setString(2, pp);
			statement.setString(3, pi);
			statement.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// xoa san pham
	public void rmvShoes(String id) {
		DBUtil db = DBUtil.getInstance();
		String sql = "DELETE from product where pid=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			int id1 = Integer.parseInt(id);
			statement.setInt(1, id1);
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// cap nhap thong tin san pham
	public void updShoes(String idd, String s1, String s2) {
		DBUtil db = DBUtil.getInstance();
		String sql = "Update product SET pname=?,price=? WHERE pid=?";
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement = con.prepareStatement(sql);
			statement.setString(1, s1);
			statement.setString(2, s2);
			statement.setDouble(3, Double.parseDouble(idd));
			statement.execute();
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	// tim san pham dua theo ten
	public ArrayList<Shoes> searchShoes(String sname) {
		ArrayList<Shoes> slist = new ArrayList<Shoes>();
		String sid = "";

		DBUtil db = DBUtil.getInstance();
		String sql = "select * from product where UPPER(pname) LIKE UPPER('%" + sname + "%')";

		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				slist.add(new Shoes(rs.getInt("pid"), rs.getString("pname"), rs.getDouble("price"),
						rs.getString("preview")));
			}
		} catch (Exception e) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return slist;
	}

	// tinh tong so tien trong cart
	public double getTotalCartPrice(ArrayList<Cart> cartList) throws Exception {
		double sum = 0;
		String sql = "";
		DBUtil db = DBUtil.getInstance();
		PreparedStatement pst;

		ResultSet rs;
		Connection con = null;
		try {
			con = db.getConnection();
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					sql = "select price from product where pid=?";
					pst = con.prepareStatement(sql);
					pst.setInt(1, item.getShoesID());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return sum;
	}

	// lay thong tin cua cac item o trong cart
	public ArrayList<Cart> getCartProducts(ArrayList<Cart> cartList) throws Exception {
		ArrayList<Cart> shoeslist = new ArrayList<>();
		String sql = "";
		DBUtil db = DBUtil.getInstance();
		PreparedStatement pst;

		ResultSet rs;
		Connection con = null;
		try {
			con = db.getConnection();
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					sql = "select * from product where pid=?";
					pst = con.prepareStatement(sql);
					pst.setInt(1, item.getShoesID());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setShoesID(rs.getInt("pid"));
						row.setShoesName(rs.getString("pname"));
						row.setPrice(rs.getDouble("price"));
						row.setCover(rs.getString("preview"));
						row.setQuantity(item.getQuantity());
						shoeslist.add(row);
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (SQLException e) {
				Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return shoeslist;
	}
}
