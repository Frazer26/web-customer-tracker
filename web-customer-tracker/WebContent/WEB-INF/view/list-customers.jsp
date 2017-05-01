<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>

	<title>List Customers</title>
	
	<!--reference my stlye sheet-->

	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css"/>
		<!--${pageContext.request.contextPath} use proper app name-->
		
		
	<style>
		form {
		  display: inline;
		}
		
		.inline {
		  display: inline;
		}
		
		.link-button {
		  background: none;
		  border: none;
		  color: blue;
		  text-decoration: underline;
		  cursor: pointer;
		  font-family: Tahoma,Verdana,Segoe,sans-serif;
		  font-size: 16px;
		}
		
		.link-button:focus {
		  outline: none;
		}
		.link-button:active {
		  color:red;
		}	
	</style>

</head>

<body>
	
	<div id="wrapper">
	
		<div id="header">		
		
		<h2> CRM - Customer Relationship Manager</h2>			
			
		</div>
			
	</div>
	
	
	<div id="container">
	
		<div id="content">
		
        <!--add customer button -->
        <input type="button" value="Add Customer"
        		onclick="window.location.href='showFormForAdd'; return false;"
        		class="add-button"
        		/>
        		<!--onclick:call my spring controller mapping class=css style -->
		
        <!--add our html table here -->
        
			<table>			
				<tr>
				
					<th>First name</th>					
					<th>Last name</th>					
					<th>Email</th>
					<th>Action</th>
				
				</tr>
				
				<!--loop over and print  customers -->
				
				<c:forEach var="tempCustomer" items="${customers}"> <!--relation->theModel.addAttribute("customers",theCustomers); -->
			
					
					<tr>					
						<td>${tempCustomer.firstName} </td> <!--call tempCustomer.getFirstName() -->
						<td>${tempCustomer.lastName} </td>					
						<td>${tempCustomer.email} </td>		
						
						<td>
							<form:form action="${pageContext.request.contextPath}/customer/showFormForUpdate" method="POST">
				
							  <button type="submit" name="customerId" value="${tempCustomer.id}" class="link-button">
							    Update
							  </button>

							</form:form>
							
							| 
						
							<form:form action="${pageContext.request.contextPath}/customer/delete" method="POST">
				
							  <button type="submit" name="customerId" value="${tempCustomer.id}" class="link-button"
							  		  onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">
							    Delete
							  </button>
				
							</form:form>	
						
						</td>
									
					</tr>
				
				</c:forEach>
			
			</table>		
	
		</div>
	
	</div>


</body>

</html>