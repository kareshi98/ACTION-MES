<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>Bom详情管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bas/bomDetail/">Bom详情列表</a></li>
		<li class="active"><a href="">Bom详情${not empty bomDetail.id?'修改':'添加' }</a></li>
	</ul>
	<br/>
	<!-- 2.表单 -->
	<form:form id="inputForm" method="post" action="${ctx}/bas/bomDetail/save" modelAttribute="bomDetail" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">所属BOM：</label>
			<div class="controls">
				<form:select path="bom.id" id="bId" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${bomList}" itemLabel="bomName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料类型：</label>
			<div class="controls">
				<form:select path="mType" id="mType" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('material_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">材料数量：</label>
			<div class="controls">
				<form:input path="mNum" htmlEscape="false" maxlength="50" class="required number"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:select path="unit" id="unit" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('unit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>