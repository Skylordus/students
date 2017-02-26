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

<body>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/script.js"></script>
	
	<header>
		<nav id="header-nav" class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a href="index.html" class="pull-left visible-md visible-lg">
						<div id="logo-img" alt="Logo"></div>
					</a>
					<div class="navbar-brand">
						<a href="index.html">
							<h1>Project Taxi</h1>
							<br>
							<a class="phone" href="tel:8-708-519-41-49">
								<span class="glyphicon glyphicon-earphone"></span>
								8-707-519-41-49
							</a>
						</a>
					</div>
				</div>

				<div class="login">
					<h2>Hi <%=request.getAttribute("login")%>!</h2>
				</div>
			</div>		
		</nav>
	</header>


	<div id="main-content" class="container">
		<%  String newOrderBlock = "hidden";
			String orderStatusBlock = "hidden";
			if (request.getAttribute("ordered")!=null) {
				orderStatusBlock = "visible";
			} else {
				newOrderBlock = "visible";
			}
		%>
		<section class="row">
			<div class="md-container col-xs-12">

				<div class="md-block">

					<div style="visibility: <%=newOrderBlock%>" class="sub-md-block">
						<h1 class="order-header">Order taxi:</h1>
						<form class="new-order-form" action="/login" method="post">
							<div class="order-labels">
								<label for="from">From:</label> <br>
								<label for="to">Where to:</label> <br>
								<label for="plan">Select a plan:</label>
							</div>
							<div class="order-inputs">
								<input class="order-address" type="text" name="from" id="from" placeholder="some city, some street, some home"> <br>
								<input class="order-address" type="text" name="to" id="to" placeholder="some city, some street, some home"> <br>
								<input type="radio" name="plan" id="plan" value="economy">Economy
								<input type="radio" name="plan" value="comfort">Comfort
								<input type="radio" name="plan" value="business">Business
							</div>
							<input class="new-order-button" type="submit" value="Order" formmethod="post">
						</form>
					</div>

					<div style="visibility: <%=orderStatusBlock%>" class="sub-md-block">
						<h1 class="order-header">Your order status:</h1>
						<div>
							<span class="order-field">id: </span>
							<span class="order-value"><%=request.getAttribute("id")%></span>
						</div>
						<div>
							<span class="order-field">from: </span>
							<span class="order-value"><%=request.getAttribute("from")%></span>
						</div>
						<div>
							<span class="order-field">to: </span>
							<span class="order-value"><%=request.getAttribute("to")%></span>
						</div>
						<div>
							<span class="order-field">driver: </span>
							<span class="order-value"><%=request.getAttribute("driver")%></span>
						</div>
						<div>
							<span class="order-field">status: </span>
							<span class="order-value"><%=request.getAttribute("status")%></span>
						</div>
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



</body>
</html>