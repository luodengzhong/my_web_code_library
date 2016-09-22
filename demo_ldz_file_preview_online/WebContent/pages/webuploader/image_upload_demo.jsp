<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>测试</title>
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/jslib/webuploader/image-uploader.css" />
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/jslib/webuploader/webuploader.css">
<!--引入JS-->	
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/web_app.js"></script>
	<script type="text/javascript" src="<%=basePath%>/jslib/webuploader/webuploader.js"></script>
   <script type="text/javascript" src="<%=basePath%>/jslib/layer/layer.js"></script>
   <script type="text/javascript" src="<%=basePath%>/jslib/layer/extend/layer.ext.js"></script> 
   <style type="text/css">
   .webuploader-pick:hover {
	background: #00a2d4;
   }
   </style>
</head>
<body>
  <input type="hidden" id="id" value="123456">
  <input type="button" value="上传图片" id="question" class="webuploader-pick">
    <script type="text/javascript" >
    //document.write("<script language=\"javascript\" src=\"../resources/js/upload.js\" > <\/script>"); 
    $("#question").click(function(){
    	layer.open({
    	    type: 2,
    	    area: ['800px', '500px'],
    	    shade: false,
    	    title: false,
    	    content: '<%=basePath%>/pages/webuploader/image_uploader.jsp'
        	    //$('#uploader')
    	}
    	);
    });
  </script>
 <!--  
  <iframe id="uploader" style="display: none;" src="touploader" frameborder="0" scrolling="yes" height="500px" width="800px" ></iframe>
  -->
</body>
</html>