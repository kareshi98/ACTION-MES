<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="decorator" content="default">
  <title>维修报告</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/equip/repair/">${not empty equipRepair.id?'查看':'添加'}维修报告</a></li>
	</ul>
	<br/>
	<!-- 2.表单 -->
	<form:form id="inputForm" method="post" action="${ctx}/equip/repair/save" modelAttribute="equipRepair" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="mid" />
		<div class="control-group">
			<label class="control-label">故障类型：</label>
			<div class="controls">
        <form:input path="faultType" htmlEscape="false" maxlength="50" class="required"
        readonly="${not empty equipRepair.id?true:false}"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故障原因：</label>
			<div class="controls">
				<form:input path="faultReason" htmlEscape="false" maxlength="50" class="required"
                    readonly="${not empty equipRepair.id?true:false}"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故障描述：</label>
			<div class="controls">
				<form:textarea path="faultDesc" htmlEscape="false" maxlength="200" rows="5" class="required" readonly="${not empty equipRepair.id?true:false}"/>
			  <span class="help-inline"><font color="red">*</font></span>
      </div>
		</div>
		<div class="control-group">
			<label class="control-label">选择图片：</label>
			<div class="controls ">
		    <input type="file" name="file" style="display:${not empty equipRepair.id?'none':'block'}">
        <%--<img src="${not empty equipRepair.id?equipRepair.faultImgs:' '}"/>--%>
				<img src="images/${equipRepair.faultImgs}" style="display:${not empty equipRepair.id?'block':'none'}"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary layui-bg-red" type=${not empty equipRepair.id?'hidden':'submit'} value="保存"/>&nbsp;
			<input id="btnCancel" class="btn layui-bg-red" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
