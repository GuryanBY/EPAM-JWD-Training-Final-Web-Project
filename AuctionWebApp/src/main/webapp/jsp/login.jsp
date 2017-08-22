<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../WEB-INF/jspf/locale.jspf" %>
<!DOCTYPE html>

<!-- [if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${index_title }</title>

<meta name="description"
	content="Онлайн площадка для продажи и покупки товаров. Аукционы со ставками или продажа по фиксированной цене.">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="keywords"
	content="интернет аукцион, электронный аукцион, купить на аукционе, продажа с аукциона, выставить на аукцион" />

<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/skeleton.css">
<link rel="stylesheet" href="css/screen.css">
<link rel="shortcut icon" href="images/favicon.png">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
</head>
<body>

	<!--Content Part -->
	<div id="header">
		<div class="container">
			<!-- Header | Logo, Menu ================================================== -->
			<div class="logo">
				<a href="index.jsp"><img src="images/logo.png" alt="Logo" /></a>
			</div>
			<div class="mainmenu">
				<div id="mainmenu">
					<ul class="sf-menu">
						<li><a href="index.jsp" id="visited"> ${index_header_main}</a></li>
						<li><a href="about.html"> ${index_header_auctions} </a></li>
						<li><a href="result.html">${index_header_results} </a>
							<ul>
								<li><a href="1.html">${index_header_type1 }</a></li>
								<li><a href="2.html">${index_header_type2 }</a></li>
								<li><a href="3.html">${index_header_type3 }</a></li>
							</ul></li>
						<li><a href="gallery.html">${index_header_gallery }</a>
							<ul>
								<li><a href="1.html">${index_header_gallery_news1 }</a></li>
								<li><a href="2.html">${index_header_gallery_news2 }</a></li>
								<li><a href="3.html">${index_header_gallery_news3 }</a></li>
							</ul></li>
						<li><a href="contacts.html">${index_header_contacts }</a></li>
						<li><a href="login-form.html">${index_header_maps }</a></li>
					</ul>
				</div>
				<!-- mainmenu ends here -->
				
			</div>
			<!-- mainmenu ends here -->
		</div>
		<!-- container ends here -->
	</div>
	<!-- header ends here -->

	<form action="Controller" method="POST">
		<input type="text" name="login" value="" placeholder="${index_login_placeholder_login}"/> 
		<input type="password" name="password" value="" placeholder="${index_login_placeholder_password}" /> 
		<input type="submit" value="${index_login_submit}" /><br />
		<input type="hidden" name="command" value="log_in" />
	</form>
	
	<div class="badLoginOrPass">
		<c:set var="message" scope="request" value="${invalidLogOrPass }" />
		<c:if test="${not empty message }">
			<div>${message }</div>
		</c:if>

	</div>
	<a href="jsp/registration.jsp">${index_register}</a>
	
	<form action="Controller" method="GET">
			<button class="lang" type="submit" name="locale" value="en"><img alt="ENG" src="images/eng.png" width="30" height="15"></button>
			<button class="lang" type="submit" name="locale" value="ru"><img alt="RUS" src="images/rus.png" width="30" height="15"></button>
			<input type="hidden" name="command" value="locale">
	</form>

	<!-- info Box ================================================== -->
	<div class="infobox">
		<div class="container info">
			<header>
				<h1>${content_mainttitle }</h1>
				<p class="infop">
					&#8212; ${content_phrase } <a
						href="http://www.ebay.com/" rel="nofollow"><strong>Jack Goldman</strong></a> &#8212;
				</p>
			</header>
			<hr class="separator">
		</div>
		<!-- container ends here -->
	</div>
	<!-- infobox ends here -->
	<!--Latest Lots ================================================== -->
	<div class="container latest">
		<div class="one_third">
			<figure class="shadow">
				<a href="#" class="thumb"><img src="images/lots/a.jpg"
					alt="lot" title="${content_fig }" /></a>
				<figcaption>
					<a href="#">
						<h3 class="heading">${content_lot1_title }</h3>
					</a>
					<p class="bioquote">${content_lot1_content }</p>
				</figcaption>
			</figure>
		</div>
		<!-- one_third ends here -->
		<div class="one_third">
			<figure class="shadow">
				<a href="#" class="thumb"><img src="images/lots/b.jpg"
					alt="lot" title="${content_fig }" /></a>
				<figcaption>
					<a href="#">
						<h3 class="heading">${content_lot2_title }</h3>
					</a>
					<p class="bioquote">${content_lot2_content }</p>
				</figcaption>
			</figure>
		</div>
		<!-- one_third ends here -->
		<div class="one_third lastcolumn">
			<figure class="shadow">
				<a href="#" class="thumb"><img src="images/lots/c.jpg"
					alt="lot" title="${content_fig }" /></a>
				<figcaption>
					<a href="#">
						<h3 class="heading">${content_lot3_title }</h3>
					</a>
					<p class="bioquote">${content_lot3_content }</p>
				</figcaption>
			</figure>
		</div>
		<!-- one_third ends here -->
	</div>
	<!-- end container -->
	<!--Heading Box ==================================================-->
	<div class="headingblock">
		<div class="container heading">
			<header>
				<h2>${content_title2 }</h2>
				<p>
					- ${content_phrase2 } -
				</p>
			</header>
		</div>
		<!-- container ends here -->
		<hr class="separator1">
	</div>
	<!-- headingblock ends here -->
	<!-- Latest News & Faq ================================================== -->
	<div class="container latest">
		<div class="two_third">
			<div class="accordion-trigger">
				<h3>${content_qwestion1 }</h3>
			</div>
			<div class="accordion-container">
				<div class="one_third">
					<img class="shadow" src="images/lots/a1.jpg" alt="lot"
						title="${content_fig }" />
				</div>
				<!--end one_third-->
				<div class="one_third">
					<img class="shadow" src="images/lots/b1.jpg" alt="lot"
						title="${content_fig }" />
				</div>
				<!--end one_third-->
				<div class="one_third lastcolumn">
					<img class="shadow" src="images/lots/c1.jpg" alt="lot"
						title="${content_fig }" />
				</div>
				<!--end one_third-->
				<p>${content_answ1 }</p>
				<hr class="separator1">
			</div>
			<div class="accordion-trigger">
				<h3>${content_question2 }</h3>
			</div>
			<div class="accordion-container">
				<div class="one_half">
					<div class="video-holder">
						<div class="video-container">
							<iframe title="YouTube video player" class="youtube-player"
								width="640" height="360"
								src="https://www.youtube.com/embed/Y8lHaUersf0" allowfullscreen></iframe>
						</div>
						<!--video-container ends here-->
					</div>
					<!--video-holder ends here-->
				</div>
				<!--end one_half-->
				<div class="one_half lastcolumn">
					<div class="video-holder">
						<div class="video-container">
							<iframe title="YouTube video player" class="youtube-player"
								width="640" height="360"
								src="https://www.youtube.com/embed/ji1jn6fhRJs" allowfullscreen></iframe>
						</div>
						<!--video-container ends here-->
					</div>
					<!--video-holder ends here-->
				</div>
				<!--end one_half-->
				<p>${content_answ2 }</p>
				<hr class="separator1">
			</div>
			<div class="accordion-trigger">
				<h3>${content_question3 }</h3>
			</div>
			<div class="accordion-container">
				<p>${content_answ3 }</p>
				<hr class="separator1">
			</div>
			<div class="accordion-trigger">
				<h3>${content_question4 }</h3>
			</div>
			<div class="accordion-container">
				<p>${content_answ4 }</p>
				<hr class="separator1">
			</div>
			<div class="accordion-trigger">
				<h3>${content_question5 }</h3>
			</div>
			<div class="accordion-container">
				<p>${content_answ5 }</p>
				<hr class="separator1">
			</div>
		</div>
		<!-- two_third ends here -->
		<div class="one_third lastcolumn">
			<h3>${content_news_title }</h3>
			<article>
				<h6>${content_news_q1 }</h6>
				<p class="quote">${content_news_a1 }</p>
				<a href="#" title="">&rarr; ${content_news_l1 }</a>
			</article>
			<hr class="separator1">
			<article>
				<h6>${content_news_q2 }</h6>
				<p class="quote">${content_news_a2 }</p>
				<a href="#" title="">&rarr; ${content_news_l2 }</a>
			</article>
			<hr class="separator1">
			<article>
				<h6>${content_news_q3 }</h6>
				<p class="quote">${content_news_a3 }</p>
				<a href="#" title="">&rarr;${content_news_l3 }</a>
			</article>
		</div>
		<!-- one_third ends here -->
	</div>
	<!-- end container -->
	<!-- Socialize ================================================== -->
	<hr class="separator2">
	<div class="socialsblock">
		<div class="container socialize">
			<h3> ${index_socialise }</h3>
			<section class="socials">
				<ul class="socials">
					<li><a href="#"><img src="images/socials/twitter.png"
							alt="" /></a></li>
					<li><a href="#"><img src="images/socials/facebook.png"
							alt="" /></a></li>
					<li><a href="#"><img src="images/socials/dribbble.png"
							alt="" /></a></li>
					<li><a href="#"><img src="images/socials/google+.png"
							alt="" /></a></li>
					<li><a href="#"><img src="images/socials/linkedin.png"
							alt="" /></a></li>
					<li><a href="#"><img src="images/socials/youtube.png"
							alt="" /></a></li>
				</ul>
			</section>
		</div>
		<!-- container ends here -->
	</div>
	<!-- socialsblock ends here -->
	<!-- Footer ================================================== -->
	<div class="footer">
		<div class="container">
			<div class="one_fourth">
				<h3> ${index_footer_contactinfo }</h3>
				<p>
					<span class="orange"><strong> ${index_footer_address }:</strong></span> <br>
					${index_footer_addressfull }
				</p>
				<p>
					<span class="orange"><strong> ${index_footer_telephone }:</strong></span>
					<br> +375 (17) 236 2587<br>
				</p>
				<p>
					<span class="orange"><strong>E-mail:</strong></span> <br>
					info@a-auction.com<br>
				</p>
			</div>
			<!-- four columns ends here -->
			<div class="one_fourth">
				<h3> ${index_footer_departments }</h3>
				<ul>
					<li><a href="#" title=""> ${index_footer_sale }</a></li>
					<li><a href="#" class=""> ${index_footer_buy }</a></li>
					<li><a href="#" class=""> ${index_footer_ads }</a></li>
					<li><a href="#" class=""> ${index_footer_marketing }</a></li>
					<li><a href="#" class=""> ${index_footer_investment }</a></li>
				</ul>
			</div>
			<!-- four columns ends here -->
			<div class="one_fourth">
				<h3> ${index_footer_archive }</h3>
				<ul>
					<li><a href="#" class=""> ${index_footer_apr }</a></li>
					<li><a href="#" class=""> ${index_footer_march }</a></li>
					<li><a href="#" class="">${index_footer_feb }</a></li>
					<li><a href="#" class="">${index_footer_jan }</a></li>

				</ul>
			</div>
			<!-- four columns ends here -->
			<div class="one_fourth lastcolumn">
				<h3> ${index_footer_name } 1967—2017</h3>
				<p> ${index_footer_desc }</p>
				<p>
					<a href="http://anarieldesign.com/" rel="nofollow"> ${index_footer_descinfo }</a>
				</p>
			</div>
			<!-- four columns ends here -->
		</div>
		<!-- container ends here -->
	</div>
	<!-- footer ends here -->
	<!-- Copyright================================================= -->
	<div id="copyright">
		<div class="container">
			<p class="copyright">
				&copy; Copyright 2017. ${index_footer_dev } <a
					href="http://www.facebook.com/user/" rel="nofollow">Клещёв Гурьян</a>.
			</p>
		</div>
		<!-- container ends here -->
	</div>
	<!-- copyright ends here -->
	<!-- End Document================================================== -->

</body>
</html>