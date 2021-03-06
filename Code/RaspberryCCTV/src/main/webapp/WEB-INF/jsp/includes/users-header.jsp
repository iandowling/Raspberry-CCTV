<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
  	<meta name="description" content="An application with enables a user to view a live webcam feed."/>
  	<meta name="author" content="Ian Dowling"/>
  	<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/{userId}" />
	<link href="./public/lib/bootstrap-3.1.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
	<link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
   	<link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.min.css" rel="stylesheet" />
   	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" />
   	<link type="text/css" rel="stylesheet" href="./public/css/style.css"/>
	<link rel="shortcut icon" type="image/png" sizes="16x16" href="./public/images/favicon/logo.png"/>
	<link type="application/javascript" href="./public/js/home.js"/>
	
	<title>Raspberry CCTV</title>

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
    
	<nav class="navbar navbar-custom navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
		            <i class="fa fa-bars"></i>
          		</button>
	      		<a class="navbar-brand" href="/RaspberryCCTV"><i class="fa fa-eye"></i> Raspberry CCTV</a>
	   		</div>
	
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
		      <ul class="nav navbar-nav">
		        <li>
		        	<a class="page-scroll" href="/RaspberryCCTV/#about">About</a>
		        </li>
		        <li>
		        	<a class="page-scroll" href="/RaspberryCCTV/#download">Download</a>
		        </li>
		        <li>
		        	<a class="page-scroll" href="/RaspberryCCTV/#contact">Contact</a>
		        </li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
		      <sec:authorize access="isAnonymous()">
		      
		        <li><a href="<c:url value='signup' />"><span class="glyphicon glyphicon-list-alt"></span> Sign up</a></li>
		        <li>
	                  <a href="login">Sign in <span class="glyphicon glyphicon-log-in"></span></a>
	            </li>
	         </sec:authorize>
	         
	          <sec:authorize access="isAuthenticated()">
	          	  <li><a href="/RaspberryCCTV/webcam">Webcam Feed</a></li>
	              <li class="dropdown">
	                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                      <span class="glyphicon glyphicon-user"></span>
	                      <sec:authentication property="principal.user.name" /> <b class="caret"></b>
	                  </a>
	                  <ul class="dropdown-menu">
	                     <li><a href="/RaspberryCCTV/users/<sec:authentication property='principal.user.id' />"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
	                     <li>
		                    <c:url var="logoutUrl" value="/logout" />
			               	<form:form	id="logoutForm" action="${logoutUrl}" method="post">
						    </form:form>
						    <a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
	                     </li>
	                  </ul>
	              </li>
	            </sec:authorize>
	   		  </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

	<sec:authorize access="hasRole('ROLE_UNVERIFIED')">
		<div class="alert alert-warning alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  Your email address has not been registered. <a href="/RaspberryCCTV/users/resend-verification-mail">Click here</a> to get the verification mail again.
		</div>
    </sec:authorize>

	<c:if test="${not empty flashMessage}">
		<div class="alert alert-${flashKind} alert-dismissable">
		  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		  ${flashMessage}
		</div>
	</c:if>
	