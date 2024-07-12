<%@page import="com.sathya.servlet.ProductDAO" import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<title>Product List..</title>
<!-- Bootstrap CDN(Content Delivery Network) link to get support of BootStrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div>
        <h1 class="text-center font-italic mb-2 text-success">List of Available Products...</h1>
    </div>
    
    <div style="text-align:center">
        <a class="btn btn-success text-left" href="addProduct.html">save Product</a>
    </div>
    
    <div>
        <input type="text" id="searchInput" placeholder="Search" onkeyup="searchProducts()">
    </div>
        <script>
        function searchProducts() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementsByTagName("table")[0];
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1]; // Index 1 represents the column for product name, adjust as needed
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    
    
    <div>
        <s:if test="${result==1}">
            <h2 class="text-center text-success">Data inserted successfully....</h2>
        </s:if>
    </div>
    
    <div>
        <s:if test="${deleteres==1}">
            <h2 class="text-center text-success">Data deleted successfully....</h2>
        </s:if>
    </div>
    <div>
        <s:if test="${deleteres==0}">
            <h2 class="text-center text-success">Data deleted failed....</h2>
        </s:if>
    </div>
    
    <div>
        <s:if test="${updateresult==1}">
            <h2 class="text-center text-success">Data updated Suceessfully....</h2>
        </s:if>
    </div>
    <div>
        <s:if test="${updateresult==0}">
            <h2 class="text-center text-success">Data update failed....</h2>
        </s:if>
    </div>
    <table class="table table-bordered table-striped">
    <thead class="thead-dark">
        <tr>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Product cost</th>
            <th>Brand</th>
            <th>Made In</th>
            <th>Manufacture</th>
            <th>Expiry Date</th>
            <th>Images</th>
            <th>Audio</th>
            <th>Video</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <s:forEach var="product" items="<%=new ProductDAO().FindAll()%>" >
            <tr>
                <td>${product.proId}</td>
                <td>${product.proName}</td>
                <td>${product.proPrice}</td>
                <td>${product.proBrand}</td>
                <td>${product.proMadeIn}</td>
                <td>${product.proMfgDate}</td>
                <td>${product.proExpDate}</td>
                
                <!-- Display image using img tag -->
                <td><img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}" Style="max-width:100px; max-height:100px;"></td>
                
                <!-- Display audio using audio tag -->
                <td>
                    <audio controls>
                        <source src="data:audio/mpeg;base64,${Base64.getEncoder().encodeToString(product.proAudio)}" type="audio/mpeg">
                    </audio>
                </td>
                
                <td>
                    <video controls width="320" height="240">
                        <source src="data:video/mp4;base64,${Base64.getEncoder().encodeToString(product.proVideo)}" type="video/mp4">
                    </video>
                </td>
                <td colspan="2">
				    <div class="btn-group">
				        <a href="./DeleteProductServlet?proId=${product.proId}" class="btn btn-primary">Delete</a>
				        <a href="./EditProductServlet?proId=${product.proId}" class="btn btn-primary">Edit</a>
				    </div>
				</td>
            </tr>
        </s:forEach>
    </tbody>
    </table>
</body>
</html>
