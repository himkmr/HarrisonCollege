<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Summary</title>
</head>
<body>
<h3>Order Summary</h3>
<%! public String firstname=""; %>
<%! public String lastname=""; %>
<%! public String address=""; %>
<%! public String card=""; %>
<%! public int copies; %>
<%! public double price; %>
 <% firstname = request.getParameter("firstname");%>
 <% lastname = request.getParameter("lastname");%>
 <% address = request.getParameter("address");%>
 <% card = request.getParameter("card");%>
 <% copies = Integer.parseInt(request.getParameter("copies"));%>
 <% price = 5.0 * Integer.parseInt(request.getParameter("copies"));%>
 
<ul style="list-style-type:none">
  <li>Name: <%=firstname%>  <%=lastname%></li>
  <li>Address: <%=address%></li>
  <li>Card#: <%=card%></li>
  <li>Copies: <%=copies%></li>
  <li>Price: $<%=price%></li>
</ul>  
</body>
</html>