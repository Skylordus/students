<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yberdaliyev.models.pojos.User" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Project Taxi</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="css/welcome.css">
	<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">

</head>

<body style="background-size: cover">

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<%
		User user = (User) session.getAttribute("user_object");
	%>

	<header>
		<nav id="header-nav" class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a href="index.jsp" class="pull-left visible-md visible-lg">
						<div id="logo-img" alt="Logo"></div>
					</a>
					<div class="navbar-brand">
						<a href="index.jsp">
							<h1>Project Taxi</h1>
							<br>
							<a class="phone" href="tel:8-708-519-41-49">
								<span class="glyphicon glyphicon-earphone"></span>
								8-707-519-41-49
							</a>
						</a>
					</div>
				</div>

				<div style="float: right; margin-top: 20px">
					<p><%=user.getFirstname()+" "+user.getLastname()%></p>
					<p style="color:#f18500;font-weight: bold">Administrator</p>
					<form action="/logout" method="get">
						<input class="reg-button" style="position: inherit" type="submit" value="Log out" formmethod="get">
					</form>
				</div>
			</div>		
		</nav>
	</header>


	<div id="main-content" class="container">
        <section class="row">
            <div class="md-container col-xs-12">
                <div class="admin-md-block">
                    <table class="table">
                        <thead>
                        <tr>
                            <th id="orders-menu" class="table-buttons">Orders</th>
                            <th id="clients-menu" class="table-buttons">Clients</th>
                            <th id="drivers-menu" class="table-buttons">Drivers</th>
                            <th id="admins-menu" class="table-buttons">Admins</th>
                        </tr>
                        </thead>
                    </table>

                    <div id="table-orders" class="sub-md-block">

                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Price/km</th>
                                <th>Client</th>
                                <th>Driver</th>
                                <th>Status</th>
                                <th>Pickup time</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orders}" var="order">
                            <tr>
                                <th scope="row">${order.getId()}</th>
                                <form id="order_form${order.getId()}" action="/admin_account" method="post"></form>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getFrom()}</div>
                                        <input class="form${order.getId()} order_lid${order.getId()}" type="text" name="from">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getTo()}</div>
                                        <input class="form${order.getId()} order_lid${order.getId()}" type="text" name="to">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getPrice_per_km()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="price">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getClient()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="client_id">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getDriver()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="driver_id">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getStatus()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="status">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getPickup_time().toString()}</div>
                                        <input style="width: 100px" class="form${order.getId()} order_lid${order.getId()}" type="time" name="pickup_time">
                                    </td>
                                    <input type="hidden" class="form${order.getId()} order_lid${order.getId()}" name="type" value="edit">
                                    <input type="hidden" class="form${order.getId()} order_lid${order.getId()}" name="id" value="${order.getId()}">
                                <td>
                                    <div class="order_lid${order.getId()}">
                                        <button onClick="submitForm('order_form${order.getId()}','form${order.getId()}')">Submit</button>
                                    </div>
                                    <div class="order_fid${order.getId()}">
                                        <button id="edit-button" onClick="createEditForm('order_fid${order.getId()}','order_lid${order.getId()}')">
                                            Edit
                                        </button>
                                        <form style="display: inline" action="/admin_account" method="post">
                                            <input type="hidden" name="type" value="delete">
                                            <input type="hidden" name="id" value="${order.getId()}">
                                            <input type="submit" value="delete" formmethod="post">
                                        </form>
                                    </div>
                                </td>

                                <style type="text/css">
                                    .order_fid${order.getId()} {
                                        display: block;
                                    }
                                    .order_lid${order.getId()} {
                                        display: none;
                                        color: black;
                                    }
                                </style>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-clients" class="sub-md-block">

                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Client1</td>
                                <td>Clietn2</td>
                                <td>Client3</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Larry</td>
                                <td>the Bird</td>
                                <td>@twitter</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-drivers" class="sub-md-block">

                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Driver1</td>
                                <td>Driver2</td>
                                <td>Driver3</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Larry</td>
                                <td>the Bird</td>
                                <td>@twitter</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-admins" class="sub-md-block">

                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Admin1</td>
                                <td>Admin2</td>
                                <td>Admin3</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Larry</td>
                                <td>the Bird</td>
                                <td>@twitter</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </section>
    </div> <!-- End of #main-content -->
	
	<footer class="panel-footer">
		<div class="container">
			<div class="row">
				<section id="hours" class="col-sm-4">
					<span>Hours:</span><br>
					Sunday-Thursday: 11:15am - 10:00pm <br>
					Friday: 11:15am - 2:30pm <br>
					Saturday Closed
					<hr class="visible-xs">										
				</section>
				<section id="address" class="col-sm-4">
					<span>Address:</span><br>
					7105 Reisterstown Road <br>
					Baltimore, MD 21215
					<p>*Delivery area within 3-4 miles, with  minimum order of $20 plus $3 charge for all deliveries.</p>
					<hr class="visible-xs">										
				</section>
				<section id="testimonials" class="col-sm-4">
					<p>"The best Chinese restaurant I've been to! And that's saying a lot, since I've been to many!"</p>
					<p>"Amazing food! Great service! Couldn't ask for more! I'll be back again and again!"</p>					 										
				</section>				
			</div>
			<div class="text-center">&copy; Copyright Yerlan's Bistro</div>
		</div>
	</footer>

    <script src="js/script.js"></script>

</body>
</html>