<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
	
<!-- Bootstrap CDN(Content Delivery Network) link to get support of BootStrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<!-- This is JavaScript validation code location -->
<script src="formValidation.js"></script>
<title>Edit form</title>
</head>
<body>
<div class="container mt-5 text-center">
		<h2 class="text-center font-italic mb-1">Save Product Details...</h2>
		<form method="post"  action="updateProductServlet" enctype="multipart/form-data" onsubmit="return validateForm()">

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proId">Product
					Id:</label> <input type="text" class="form-control-sm" name="proId"
					id="proId" value="${existingProduct.proId}" required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proName">Product
					Name:</label> <input type="text" class="form-control-sm" name="proName"
					id="proName" value="${existingProduct.proName}" required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proPrice">Product
					Price:</label> <input type="text" class="form-control-sm" name="proPrice"
					id="proPrice" value="${existingProduct.proPrice}" required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proBrand">Product
					Brand:</label> <input type="text" class="form-control-sm" name="proBrand"
					id="proBrand" value="${existingProduct.proBrand}"required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proMadeIn">Product
					Made In:</label> <input type="text" class="form-control-sm" name="proMadeIn"
					id="proMadeIn" value="${existingProduct.proMadeIn}" required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proMfgDate">MFG.
					Date:</label> <input type="date" class="form-control-sm" name="proMfgDate"
					id="proMfgDate" value="${existingProduct.proMfgDate}" required>
			</div>

			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proExpDate">Exp.
					Date:</label> <input type="date" class="form-control-sm" name="proExpDate"
					id="proExpDate" value="${existingProduct.proExpDate}" required>
			</div>
			
			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proImage">Current Product Image:</label>
				<img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(existingProduct.proImage)}" Style="max-width:100px; max-height:100px;" alt="current product image">
				<input type="hidden" id="existingImage" name="existingImage" value="${Base64.getEncoder().encodeToString(existingProduct.proImage)}">
				
			</div>
			
			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proImage">New Product Image:</label>
				<input type="file" id="newProImage" name="newProImage" accept="image/*">
				
			</div>
			
			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proAudio">Current Product Audio:</label>
				<audio controls>
                        <source src="data:audio/mpeg;base64,${Base64.getEncoder().encodeToString(existingProduct.proAudio)}" type="audio/mpeg">
                 </audio>
				<input type="hidden" id="existingAudio" name="existingAudio" value="${Base64.getEncoder().encodeToString(existingProduct.proAudio)}">
				
			</div>
			 
			<div class="form-group">
				<label class="font-italic font-weight-bold" for="newProAudio">New Product Audio:</label>
				<input type="file" id="newProAudio" name="newProAudio" accept="audio/*">
				
			</div>
			 
			<div class="form-group">
				<label class="font-italic font-weight-bold" for="proVideo">Current Product Video: </label>
				<video controls width="360" height="240">
					<source src="data:video/mp4;base64,${Base64.getEncoder().encodeToString(existingProduct.proVideo)}" type="video/mp4">
				</video>
				<input type="hidden" id="existingVideo" name="existingVideo" value="${Base64.getEncoder().encodeToString(existingProduct.proVideo)}">
			
			</div>
			<div class="form-group">
  			  <label class="font-italic font-weight-bold" for="newProVideo">Current product Video</label>
    		<input type="file" id="newProVideo" name="newProVideo" accept="video/*">
			</div>

			
			
		
			<div class="form-group">
				<input type="submit" class="btn btn-success" value="update product"/>
			</div>
		</form>
	</div>

</body>
</html>