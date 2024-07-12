package com.sathya.servlet;

import java.awt.Image;
import java.io.IOException; 

import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@MultipartConfig
@WebServlet("/updateProductServlet")
public class updateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 
		// Read the from data
				String proId=request.getParameter("proId");
				System.out.println(proId);
				String proName=request.getParameter("proName");
				double proPrice=Double.parseDouble(request.getParameter("proPrice"));
				String proBrand=request.getParameter("proBrand");
				String proMadeIn=request.getParameter("proMadeIn");
				

				Date proMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
				Date proExpDate=Date.valueOf(request.getParameter("proExpDate"));
				

				
				// Using above details create Product object
				Product product=new Product();
				product.setProId(proId);
				product.setProName(proName);
				product.setProPrice(proPrice);
				product.setProBrand(proBrand);
				product.setProMadeIn(proMadeIn);
				product.setProMfgDate(proMfgDate);
				product.setProExpDate(proExpDate);
				
				//readig the new image else old image
				Part part = request.getPart("newProImage");
				if(part!=null && part.getSize()>0)
				{
					InputStream inputStream = part.getInputStream();
					byte[] newproImage = IOUtils.toByteArray(inputStream);
					product.setProImage(newproImage);
				}
				else
				{
					String s = request.getParameter("existingImage");
					byte[] existingImage = Base64.getDecoder().decode(s);
					product.setProImage(existingImage);
				}
				
				Part part2 = request.getPart("newProAudio");
				if(part2!=null && part.getSize()>0)
				{
					InputStream inputStream1 = part2.getInputStream();
					byte[] newProAudio = IOUtils.toByteArray(inputStream1);
					product.setProAudio(newProAudio);		
				}
				else
				{
					String s1= request.getParameter("existingAudio");
					byte[] existingAudio = Base64.getDecoder().decode(s1);
					product.setProAudio(existingAudio);
					
				}
					
				Part part3 = request.getPart("newProVideo");
				if(part3!=null && part3.getSize()>0)
				{
					InputStream inputStream = part3.getInputStream();
					byte[] newProVideo = IOUtils.toByteArray(inputStream);
					product.setProVideo(newProVideo);
				}
				else
				{
					String s2 = request.getParameter("existingVideo");
					byte[] existingVideo= Base64.getDecoder().decode(s2);
					product.setProVideo(existingVideo);
				}
				
				
				// Giving the product object to ProductDAO layer save method to save the data into database
				ProductDAO productDAO=new ProductDAO();
				int res = productDAO.updateById(product);
				
				if(res==1)
				{
					request.setAttribute("updateresult", res);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					request.setAttribute("updateresult", res);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
					dispatcher.forward(request, response);
				}
				
	}

}
