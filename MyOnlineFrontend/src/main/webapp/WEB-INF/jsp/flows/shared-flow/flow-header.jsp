<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url var="images" value="/rs/images" />
<spring:url var="css" value="/rs/css" />
<spring:url var="js" value="/rs/js" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>${title}</title>

<script>
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Datatable CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="${css}/shop-homepage.css" rel="stylesheet">
</head>
<body>
	<div class="wrapper">

		<!-- Navigation -->

		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${flowExecutionUrl}&_eventId_home">HOME</a>
				</div>
			</div>
		</nav>
		<br/>