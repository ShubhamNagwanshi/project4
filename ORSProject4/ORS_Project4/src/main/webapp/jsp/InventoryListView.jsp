

<%@page import="com.rays.pro4.controller.InventoryListCtl"%>
<%@page import="com.rays.pro4.Bean.InventoryBean"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Bean.OrderBean"%>
<%@page import="com.rays.pro4.controller.OrderListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<meta charset="utf-8"">
<title>Order List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>
<script>
	$(function() {
		$("#udate").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2024',
		//  mindefaultDate : "01-01-1962"
		});
	});
</script>
</head>
<body>
	<jsp:useBean id="bean" class="com.rays.pro4.Bean.InventoryBean"
		scope="request"></jsp:useBean>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.INVENTORY_LIST_CTL%>" method="post">
		<center>
			<div align="center">
				<h1>Inventory List</h1>

				
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>

			

			</div>
			<%
			List olist = (List) request.getAttribute("olist");
			%>
			<%
				List list = ServletUtility.getList(request);
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);

				int index = (pageNo - 1) * pageSize + 1;

				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<table width="100%" align="center">
				<tr>
					<th></th>

					<td align="center"><label>Name</font> :
					</label> <input type="text" name="name" placeholder="Enter your Name"
						value="<%=ServletUtility.getParameter("name", request)%>">


						<label>Product</font> :
					</label> <input type="text" name="product" placeholder="Enter Product Name"
						value="<%=ServletUtility.getParameter("product", request)%>">

						<label>Quality</font> :
					</label> <input type="text" name="quality" placeholder="Enter Quality"
						value="<%=ServletUtility.getParameter("quality", request)%>">
	<label>Address</font> :
					</label> <input type="text" name="address" placeholder="Enter Address"
						value="<%=ServletUtility.getParameter("address", request)%>">

						<%-- <label>OrderType</font> :
					</label> <input type="text" name="orderType" placeholder="Enter OrderType"
						value="<%=ServletUtility.getParameter("orderType", request)%>"> --%> <%-- &emsp; <label>Date</font> :
					</label> <%=HTMLUtility.getList("dob", String.valueOf(bean.getDob()), olist)%>
 --%> &emsp;  <%-- <label>OrderType</font> :
					</label> <%=HTMLUtility.getList("orderType", String.valueOf(bean.getOrderType()), olist)%>
 --%>
						<input type="submit" name="operation"
						value="<%=InventoryListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=OrderListCtl.OP_RESET%>">

					</td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: PALEGREEN">
					<th><input type="checkbox" id="select_all" name="select">Select
						All</th>

					<th>S.No.</th>
					<th>Name</th>
					<th>Product</th>
					<th>Quality</th>
					<th>Address</th>
					<th>Edit</th>
					<%
						while (it.hasNext()) {
								bean = (InventoryBean) it.next();
					%>
				
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getProduct()%></td>
					<td><%=bean.getQuality()%></td>
					<td><%=bean.getAddress()%></td>
					<td><a href="InventoryCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<tr>
				<th></th>
				<%
					if (pageNo == 1) {
				%>
				<td><input type="submit" name="operation" disabled="disabled"
					value="<%=InventoryListCtl.OP_PREVIOUS%>"></td>
				<%
					} else {
				%>
				<td><input type="submit" name="operation"
					value="<%=InventoryListCtl.OP_PREVIOUS%>"></td>
				<%
					}
				%>
				<td><input type="submit" name="operation"
					value="<%=InventoryListCtl.OP_DELETE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=InventoryListCtl.OP_NEW%>"></td>

				<td align="right"><input type="submit" name="operation"
					value="<%=InventoryListCtl.OP_NEXT%>"
					<%=(list.size() < pageSize) ? "disabled" : ""%>></td>



			</tr>

			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=InventoryListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

		</center>

	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	<%@include file="Footer.jsp"%>



</body>
</html>