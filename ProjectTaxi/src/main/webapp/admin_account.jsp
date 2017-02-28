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

				<div class="login">
					<h2>Hi <%=request.getAttribute("login")%>!</h2>
				</div>
			</div>		
		</nav>
	</header>


	<div id="main-content" class="container">

		<section class="row">
			<div class="md-container col-xs-12">

				<div class="md-block">

					<div style="visibility: visible" class="sub-md-block">
						<h1 class="order-header">Admin  :</h1>

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