<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>设备保养计划管理</title>
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
		<li><a href="${ctx}/equip/maintenance/">设备保养计划列表</a></li>
		<li class="active"><a href="">设备保养计划${not empty maintenancePlan.id?'修改':'添加' }</a></li>
	</ul>
	<br/>
	<!-- 2.表单 -->
	<form:form id="inputForm" method="post" action="${ctx}/equip/maintenance/save" modelAttribute="maintenancePlan" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">设备类型：</label>
			<div class="controls">
				<form:select path="equipType" id="equipType" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养周期：</label>
			<div class="controls">
				<form:select path="cycle" id="cycle" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('equip_cycle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警时间：</label>
			<div class="controls">
				<form:input path="warnTime" htmlEscape="false" maxlength="50" class="number"/>天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养人：</label>
			<div class="controls">
				<sys:treeselect url="/sys/office/treeData?type=3" id="user" value="${maintenancePlan.user.id}" labelName="user.name" 
				labelValue="${maintenancePlan.user.name}" title="用户" name="user.id" cssClass="input-larg" allowClear="true"
				notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保养内容：</label>
			<div class="controls">
				<form:textarea path="maintenance" htmlEscape="false" maxlength="200" rows="5" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>