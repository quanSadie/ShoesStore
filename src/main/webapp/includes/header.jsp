<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%if (request.getSession().getAttribute("tb")!=null){
    	String s1 = (String) request.getSession().getAttribute("tb");
    	%> <script>alert('<%=s1%>')</script>
   <% }session.removeAttribute("tb"); %> 
<div class="navbar">						

	<div id="left-side-navbar">
	<form action="search">
		<input class="search-bar" type="text" id="searchinfo" name="searchinfo" placeholder="Search">
		<button type="submit" class="search-icon" role="button"><i class="fa fa-search" aria-hidden="true"></i></button>
		</form>
	 </div>
		
		
		<div class="main-logo" onclick="location.href='home.jsp';" style="cursor: pointer;">
			<h1>Lorem Ipsum</h1>
		</div>
		
		
		<div class="right-side-nav" id="">		
		<% String att = (String)session.getAttribute("isadd");
		if (att != null && att.equals("1")){%>
			<a href="admin-page.jsp" style="text-decoration: none;"><i class="fa fa-bars" aria-hidden="true"></i> Manage</a>
		<%} 
		%>
		<a href="cart.jsp" style="text-decoration: none;"><span class="qtt">${cart_list.size()}</span><i class="fa fa-cart-plus" aria-hidden="true"></i> Cart</a>
		<%if (session.getAttribute("name")==null){
			%>
			  <a href="login.jsp" ><i class="fa fa-sign-in icon" aria-hidden="true"></i> Login</a>
		<%}else{
			%><a href="logout" style="text-decoration: none;"><i class="fa fa-sign-out icon" aria-hidden="true"></i> Logout</a>
		<%} %>
		</div>			
</div>