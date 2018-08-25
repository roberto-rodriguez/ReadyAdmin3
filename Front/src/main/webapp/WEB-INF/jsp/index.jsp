<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8"%>  

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en" style="height:100%">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Templates -->  
        <%@ include file="/WEB-INF/jsp/tpl/shellView.jsp" %>
        <%@ include file="/WEB-INF/jsp/tpl/col.jsp" %> 
        <%@ include file="/WEB-INF/jsp/tpl/input.jsp" %> 

        <!-- Optional --> 
        <title>Dev Tool</title>
        <!-- styles -->
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css" />"/> 
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />"/> 
        <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />"/>    
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous"/>

        <!-- libs -->
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery-2.1.1.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery-ui.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery.alerts.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery.jqGrid.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery-ui-custom.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/underscore-min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/backbone-min.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/lib/parse-1.2.2.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/lib/moment.min.js" />"></script>
        <!-- form lib -->
        <script type="text/javascript" src="<c:url value="/resources/lib/backform.js" />"></script>
       
        <!-- js -->
        <script type="text/javascript" src="<c:url value="/resources/js/app.js" />"></script> 

        <script type="text/javascript" src="<c:url value="/resources/js/util/Request.js" />"></script>    
        
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Container.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Section.js" />"></script>  
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Item.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Col.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Editor.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/TextArea.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/Icon.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/cmp/icons/CloseIcon.js" />"></script> 
          
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/models/EntityModel.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/models/FieldCollection.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/models/FieldModel.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/models/SrcCollection.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/models/SrcModel.js" />"></script>   
        
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/entity/EntityCol.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/entity/EntityEditor.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/entity/FieldEditor.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/fields/FieldsCol.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/fields/FieldItem.js" />"></script>   
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/src/SrcFilesCol.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/src/SrcCodeCol.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/source_generator/src/SrcItem.js" />"></script>  
        
        <script type="text/javascript" src="<c:url value="/resources/js/views/MenuCol.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/homeView.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/views/shellView.js" />"></script> 

    </head>



    <body class="body" style="height:100%">

        <div id="wrapper" style="height:100%"></div>

    </body>

</html>
