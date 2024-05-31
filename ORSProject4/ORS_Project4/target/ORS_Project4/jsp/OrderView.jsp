<%@page import="com.rays.pro4.controller.OrderCtl"%>

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
<title>Add Order</title>
</head>
<body>
<jsp:useBean id="bean" class="com.rays.pro4.Bean.OrderBean" scope="request"></jsp:useBean>
  <%@ include file="Header.jsp" %>
  
  <center>
  <form action="<%=ORSView.ORDER_CTL %>" method="post">
  <div align="center">
  <h1>

					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<tr>
						<th><font size="5px"> Update Order </font></th>
					</tr>
					<%
						} else {
					%>
					<tr>
						<th><font size="5px"> Add Order </font></th>
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
				
    <th align="left">Date<span style="color: red">*</span>
    :</th>
    <td><input type="text" name="dob" placeholder="Enter your Date" size="25"
    value="<%=(DataUtility.getStringData(bean.getDob()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage(request) %></font></td>
  </tr>
  
				<tr>
				
    <th align="left">OrderType<span style="color: red">*</span>
    :</th>
    <td><input type="text" name="orderType" placeholder="Enter your OrderType" size="25"
    value="<%=(DataUtility.getStringData(bean.getOrderType()))%>"></td>
  <td style="position: fixed"><font color="red"><%=ServletUtility.getErrorMessage(request) %></font></td>
  </tr>
  
  <tr>
  <th></th>
  <%
  if(bean.getId() > 0){
  %>
  <td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=OrderCtl.OP_UPDATE%>"> &nbsp;
						 &nbsp; <input type="submit" name="operation"
						value="<%=OrderCtl.OP_CANCEL%>"></td>
 
					<%
						} else {
					%>

					<td colspan="2">&nbsp; &emsp; <input type="submit"
						name="operation" value="<%=OrderCtl.OP_SAVE%>"> &nbsp;
						&nbsp; <input type="submit" name="operation"
						value="<%=OrderCtl.OP_RESET%>"></td>

					<%
						}
					%>
				</tr>
 </table>
  </form>
  
  </center>

</body>
</html>