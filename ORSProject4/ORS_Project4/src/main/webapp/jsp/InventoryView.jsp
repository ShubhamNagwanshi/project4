<%@page import="com.rays.pro4.controller.InventoryCtl"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<meta  charset="utf-8"">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2002',
		//  mindefaultDate : "01-01-1962"
		});
	});
</script>
<title>Add Inventory</title>
</head>
<body>
<jsp:useBean id="bean" class="com.rays.pro4.Bean.InventoryBean" scope="request"></jsp:useBean>
  <%@ include file="Header.jsp" %>
  
  <center>
  <form action="<%=ORSView.INVENTORY_CTL %>" method="post">
  <div align="center">
  <h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Inventory </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Inventory </font></th>
					</tr>
					<%
					}
					%>
				</h1>
    <h3> 
    <font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
    <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
    </h3>
  </div>
  
  <input type ="hidden" name="id" value="<%=bean.getId()%>">
  
  <table>
  <tr>
  <th align="left">Name<span style="color: red">*</span>
  :</th>
  <td><input type="text" name="name" placeholder="Enter Your Name" size="25" 
  value="<%=(DataUtility.getStringData(bean.getName()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td></tr>
  
  <%-- <tr>
					<th align="left">Date<span style="color: red">*</span>
						:
					</th>
					<td><input type="text" name="dob"
						placeholder="Enter Date Of Birth" size="25" readonly="readonly"
						id="udate" value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr> --%>
							<tr>
				
    <th align="left">Product<span style="color: red">*</span>
    :</th>
    <td><input type="text" name="product" placeholder="Enter your product" size="25"
    value="<%=(DataUtility.getStringData(bean.getProduct()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("product",request) %></font></td>
  </tr>
  
				<tr>
				
    <th align="left">Quality<span style="color: red">*</span>
    :</th>
    <td><input type="text" name="quality" placeholder="Enter your quality" size="25"
    value="<%=(DataUtility.getStringData(bean.getQuality()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("quality",request) %></font></td>
  </tr>
  
  			<tr>
				
    <th align="left">Address<span style="color: red">*</span>
    :</th>
    <td><input type="text" name="address" placeholder="Enter your address" size="25"
    value="<%=(DataUtility.getStringData(bean.getAddress()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage("address",request) %></font></td>
  </tr>
  
  <tr>
  <th></th>
  <%
  if(bean.getId() > 0){
  %>
  <td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=InventoryCtl.OP_UPDATE%>"> &nbsp;
						 &nbsp; <input type="submit" name="operation"
						value="<%=InventoryCtl.OP_CANCEL%>"></td>
 
					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=InventoryCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=InventoryCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
 </table>
  </form>
  
  </center>

</body>
</html>