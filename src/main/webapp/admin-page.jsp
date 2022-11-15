    <%@ page import="java.util.ArrayList, entity.*" %>
    <%@ page import="dao.*" %>
 <%
ProductDAO dao = new ProductDAO();
ArrayList<Shoes> list = new ArrayList<Shoes>();
list = dao.allShoes();
 %>

 <%
 UserDAO ud = new UserDAO();
 ArrayList<User> ulist = ud.displayUsers();
 %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin-page.css">
<style>
.button-63 {
  align-items: center;
  background-image: linear-gradient(144deg,#AF40FF, #5B42F3 50%,#00DDEB);
  border: 0;
  border-radius: 8px;
  box-shadow: rgba(151, 65, 252, 0.2) 0 15px 30px -5px;
  box-sizing: border-box;
  color: #FFFFFF;
  display: flex;
  font-family: Phantomsans, sans-serif;
  justify-content: center;
  line-height: 1em;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  cursor: pointer;
}

.button-63:active,
.button-63:hover {
  outline: 0;
}
.fomrgroup {
    width: 50%;
    margin: auto;
    display: block;
    height: 50px;
}
.fomrgroup input{
    float: right;
     width: 70%;
    height: 25px;
    margin-right: 20px;
    line-height: 50px;
}
/* The Modal (background) */
.modal {
    display: none; /* mặc định được ẩn đi */
    position: fixed; /* vị trí cố định */
    z-index: 1; /* Ưu tiên hiển thị trên cùng */
    padding-top: 100px; 
    left: 0;
    top: 0;
    width: 100%;
    height: 100%; 
    overflow: auto;
    background-color: rgb(0,0,0); 
    background-color: rgba(0,0,0,0.4); 
}

/* Modal Content */
.modal-content {
    background-color: rgba(1,1,1,0.1);
    color: whitesmoke;
    width: 60%;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
.mdf[type="text"] {
	 background-color : rgba(0,0,0,0); 
	 border: 1px solid black;
	 font-weight: bold;
	 color: whitesmoke;
}

.mdf[type="text"]:focus {
	 outline: linear-gradient(45deg, #49a09d, #5f2c82);
}
.navMe
</style>
</head>
<body>
<%
    	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
  		response.setHeader("Pragma","no-cache");
  		response.setHeader("Expires","0");
    %>
<div>
			<nav class="navMenu">
				<input id="display1" type="button" class="button-63" value="User"/>
				<input id="display2" type="button" class="button-63" value="Product"/>
				<form action="home.jsp">
				<input type="submit" class="button-63" value="Home"/>
				</form>
				<button id="myBtn" class="button-63" class="">Add a product</button>
				<form action="logout">
				<input type="submit" class="button-63" value="Logout"/>
				</form>
			</nav>
		</div>
		<br><br><hr>
		
			<table id="user-table" style="display:none" cellpadding="1" cellspacing="0" border="1px" width="100%">
				<thead>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Username</th>
					<th>Password</th>
					<th>Admin</th>
				</thead>
				<tbody>
				<%for(User u: ulist){%>
					<tr>
						<form action="RmvUpdUser">
							<td><input type="text" id="updID" name="updID" value="<%= u.getUserID()%>" readonly></td>
							<td><input type="text" id="updName" name="updName" value="<%= u.getName()%>"></td>
							<td><input type="text" id="updEmail" name="updEmail" value="<%= u.getEmail()%>"></td>
							<td><input type="text" id="updUn" name="updUn" value="<%= u.getUsername()%>" readonly></td>
							<td><input type="text" id="updPass" name="updPass" value="<%= u.getPassword()%>"></td>
							<td>
								<select name="updAd" id="updAd">
								<%if (u.getIsAdmin().equals("1")) {
								%>
  								   <option value="1">Yes</option>
   								   <option value="0">No</option>
   								   <%} else{ %>
   								   <option value="0">No</option>
   								   <option value="1">Yes</option>
   								   <%} %>
  								</select>
							 </td>
							<input type="hidden" id="action" name="action" value="Update">
							<td><input type="submit" class="button-63" value="Update"> </td>
							</form>
							<%if (!u.getIsAdmin().equals("1")){
								%><td><a href="RmvUpdUser?action=remove&uid=<%=u.getUserID()%>">Remove</a></td>
							<%} %>
								
					</tr>
				<%} %>		
				</tbody>
			</table>
			
			<table id="product-table" style="display:none" cellpadding="1" cellspacing="0" border="3px">
				<thead>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
				</thead>
				<tbody>
					<%for(Shoes s: list){%>
					<tr>
					<form action="RmvUpdProduct">
						<td><input type="text" id="prid" name="prid" value="<%= s.getShoesID()%>" readonly></td>
						<td><input type="text" id="prn" name="prn" value="<%= s.getShoesName()%>"></td>
						<td><input type="text" id="prp" name="prp" value="$<%= s.getPrice()%>"></td>
						<input type="hidden" id="action2" name="action2" value="Update">
							<td><input class="button-63" type="submit" value="Update"> </td>
					</form>
						<td><a href="RmvUpdProduct?action2=remove&pid=<%=s.getShoesID()%>">Remove</a></td>	
					</tr>
				<%} %>		
				</tbody>
			</table>
			
			
			
	
		   <div id="myModal" class="modal">
        <!-- Nội dung form đăng nhập -->
        <div class="modal-content">
            <form action="AddProduct">
                <span class="close">&times;</span>
                <h2>Add a product</h2>
                <div class="fomrgroup">
                    <input class="mdf" type="text" name="prdname" placeholder="Enter product name">
                </div>
                <div class="fomrgroup">
                    <input class="mdf" type="text" name="prdprice" placeholder="Enter price">
                </div>
                <div class="fomrgroup">
                    <input class="mdf" type="text" name="prdimg" placeholder="Preview image">
                </div>
                <div class="fomrgroup">
                    <button class="">Confirm</button>
                </div>
            </form>
       	 </div>
    		</div>
		
		
		
		
		
		<script>
    // lấy phần Modal
    var modal = document.getElementById('myModal');
 
    // Lấy phần button mở Modal
    var btn = document.getElementById("myBtn");
 
    // Lấy phần span đóng Modal
    var span = document.getElementsByClassName("close")[0];
 
    // Khi button được click thi mở Modal
    btn.onclick = function() {
        modal.style.display = "block";
    }
 
    // Khi span được click thì đóng Modal
    span.onclick = function() {
        modal.style.display = "none";
    }
 
    // Khi click ngoài Modal thì đóng Modal
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
		<!--  -->
		<!--  -->
		<!-- put this js file in js folder -->
		<script type="text/javascript">
			
			document.getElementById("display1").addEventListener("click", function(button) {
		if (document.getElementById("user-table").style.display === "none") {
					document.getElementById("user-table").style.display = "block";
					document.getElementById("product-table").style.display = "none";
		}
		else
			document.getElementById("user-table").style.display = "none";
		});
		</script>
		<script type="text/javascript">
			
			document.getElementById("display2").addEventListener("click", function(button) {
		if (document.getElementById("product-table").style.display === "none") {
					document.getElementById("product-table").style.display = "block";
					document.getElementById("user-table").style.display = "none";
		}
		else document.getElementById("product-table").style.display = "none";
		});
		</script>
	</body>
 
</body>
</html>