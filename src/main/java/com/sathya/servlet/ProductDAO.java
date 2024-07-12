package com.sathya.servlet;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
//import java.sql.Statement;

public class ProductDAO {

	// This method will create the Product_Details table in Database
	/*
	 * public int createProductTable() { int count = 0; try (Connection connection =
	 * DBConnection.createConnection(); Statement statement =
	 * connection.createStatement()) { count = statement.executeUpdate(
	 * "create table product_details(proId varchar2(30), proName varchar2(30), proPrice number, proBrand varchar2(30), proMadeIn varchar2(30), proMfgDate date, proExpDate date, proImage blob)"
	 * );
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return count; }
	 */

	// This method will save the products to Database
	public int saveProduct(Product prod) {
		int count = 0;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into product_details values(?,?,?,?,?,?,?,?,?,?)");) {
			preparedStatement.setString(1, prod.getProId());
			preparedStatement.setString(2, prod.getProName());
			preparedStatement.setDouble(3, prod.getProPrice());
			preparedStatement.setString(4, prod.getProBrand());
			preparedStatement.setString(5, prod.getProMadeIn());
			preparedStatement.setDate(6, prod.getProMfgDate());
			preparedStatement.setDate(7, prod.getProExpDate());
			preparedStatement.setBytes(8, prod.getProImage());
			preparedStatement.setBytes(9, prod.getProAudio());
			preparedStatement.setBytes(10, prod.getProVideo());

			count = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
	public List<Product> FindAll() throws ClassNotFoundException, SQLException
	{
		
		ArrayList<Product> l = new ArrayList<Product>();
		//create the connection
		Connection connection = DBConnection.createConnection();
		
		//static query 
		Statement statement = connection.createStatement();
		
		//execute the query 
		ResultSet resultSet=  statement.executeQuery("select * from product_details");
		
		while(resultSet.next())
		{
			Product product = new Product();
			product.setProId(resultSet.getString(1));
			product.setProName(resultSet.getString(2));
			product.setProPrice(resultSet.getDouble(3));
			product.setProBrand(resultSet.getString(4));
			product.setProMadeIn(resultSet.getString(5));
			product.setProMfgDate(resultSet.getDate(6));
			product.setProExpDate(resultSet.getDate(7));
			product.setProImage(resultSet.getBytes(8));
			product.setProAudio(resultSet.getBytes(9));
			product.setProVideo(resultSet.getBytes(10));
			
			l.add(product);
			
		}
		return l;
	}
	public int deleteById(String proId) 
	{
		int result=0;
		
		try {
			Connection connection = DBConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from product_details where proId=?");
			
			preparedStatement.setString(1, proId);
			
			result = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Product findById(String proId)
	{
		Product product = null;
		try
		{
			Connection connection = DBConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product_details where proId=?");
			
			preparedStatement.setString(1, proId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			product = new Product();
			if(resultSet.next())
			{
				product.setProId(resultSet.getString(1));
				product.setProName(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadeIn(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setProImage(resultSet.getBytes(8));
				product.setProAudio(resultSet.getBytes(9));
				product.setProVideo(resultSet.getBytes(10));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	public int updateById(Product prod)
	{
		int count = 0;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection
				.prepareStatement("update product_details set proName =?,  proPrice=?,proBrand = ?, proMadeIn=? , proMfgDate=?, proExpdate =?, proImage =?, proAudio =?, proVideo=? where proId = ?");) {
			
			preparedStatement.setString(1, prod.getProName());
			preparedStatement.setDouble(2, prod.getProPrice());
			preparedStatement.setString(3, prod.getProBrand());
			preparedStatement.setString(4, prod.getProMadeIn());
			preparedStatement.setDate(5, prod.getProMfgDate());
			preparedStatement.setDate(6, prod.getProExpDate());
			
			preparedStatement.setBytes(7, prod.getProImage());
			preparedStatement.setBytes(8, prod.getProAudio());
			preparedStatement.setBytes(9, prod.getProVideo());
			
			preparedStatement.setString(10, prod.getProId());
			
			count = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return count;
	}
	public List<Product> findByproName(String proname)
	{
		List<Product> p = new ArrayList<Product>();
		try
		{
			Connection connection = DBConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from product_details where proname=?");
			
			preparedStatement.setString(1, proname);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Product product = new Product();
				product.setProId(resultSet.getString(1));
				product.setProName(resultSet.getString(2));
				product.setProPrice(resultSet.getDouble(3));
				product.setProBrand(resultSet.getString(4));
				product.setProMadeIn(resultSet.getString(5));
				product.setProMfgDate(resultSet.getDate(6));
				product.setProExpDate(resultSet.getDate(7));
				product.setProImage(resultSet.getBytes(8));
				product.setProAudio(resultSet.getBytes(9));
				product.setProVideo(resultSet.getBytes(10));
				
				p.add(product);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
		return p;
		
	}
}
