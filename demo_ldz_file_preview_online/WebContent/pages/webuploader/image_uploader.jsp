<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jslib/webuploader/image-uploader.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jslib/webuploader/webuploader.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/web_app.js"></script>
<script type="text/javascript" src="<%=basePath%>/jslib/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=basePath%>/jslib/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/jslib/layer/extend/layer.ext.js"></script>
<script type="text/javascript" src="<%=basePath%>/pages/webuploader/image_uploader.js"></script>
</head>
<body>
	<div id="container">
		<!--头部，相册选择和格式选择 -->
		<div id="uploader">
			<div class="queueList">
				<div id="dndArea" class="placeholder">
					<div id="filePicker"></div>
					<p>或将照片拖到这里，单次最多可选300张</p>
				</div>
			</div>
			<div class="statusBar" style="display: none;">
				<div class="progress">
					<span class="text">0%</span> <span class="percentage"></span>
				</div>
				<div class="info"></div>
				<div class="btns">
					<div id="filePicker2"></div>
					<div class="uploadBtn">开始上传</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>