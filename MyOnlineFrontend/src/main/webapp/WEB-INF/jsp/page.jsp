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

    <title> ${title} </title>
    
    <script>
	window.contextRoot = '${contextRoot}';
</script>

    <!-- Bootstrap Core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">
    
 	<!-- Bootstrap Datatable CSS -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${css}/shop-homepage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <%@include file="./shared/navbar.jsp" %>

    <!-- Page Content -->
    <c:if test="${userclickhome==true}">
   		 <%@include file="home.jsp" %>
    </c:if>
    
    <c:if test="${userclickaboutus==true}">
   		 <%@include file="aboutus.jsp" %>
    </c:if>
    
      <c:if test="${userclickallproducts==true || userclickcategoryproducts==true}">
   		 <%@include file="products.jsp" %>
    </c:if>
    
     <c:if test="${userclicksingleproduct==true }">
   		 <%@include file="singleproduct.jsp" %>
    </c:if>
    
    <c:if test="${userclickmanageproduct==true }">
   		 <%@include file="manageproduct.jsp" %>
    </c:if>
    <c:if test="${userclickshowcart==true }">
   		 <%@include file="cart.jsp" %>
    </c:if>
    <!-- /.container -->


        <!-- Footer -->
     <%@include file="./shared/footer.jsp" %>
     
     


    <!-- /.container -->

    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>
    
     <!-- Datatable Core  -->
    <script src="${js}/dataTables.bootstrap.js"></script>
    
    <!-- Datatable jquery JavaScript -->
    <script src="${js}/jquery.dataTables.js"></script>
    
    <!-- BootBoc jquery JavaScript -->
    <script src="${js}/bootbox.min.js"></script>
    
    
    <!-- custom JavaScript -->
    <script src="${js}/myscript.js"></script>

</body>

</html>
