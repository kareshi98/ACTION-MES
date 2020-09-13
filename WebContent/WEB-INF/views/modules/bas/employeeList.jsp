<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>员工管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/employee/">员工信息列表</a></li>
		<li><a href="${ctx}/bas/employee/form">员工添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/employee" modelAttribute="employee" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>员工工号：</label>
			<form:input path="employeeNo" maxlength="50" class="input-medium" htmlEscape="false"/>
			<label>员工姓名：</label>
			<form:input path="employeeName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>员工号</th>
			<th>员工姓名</th>
			<th>入职日期</th>
			<th>身份证号</th>
			<th>所属部门</th>
			<th>性别</th>
			<th>年龄</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>地址</th>
			<th>操作</th>			
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employee">
				<tr>
					<td>${employee.employeeNo}</td>
					<td>${employee.employeeName}</td>
					<td>${employee.workDate}</td>
					<td>${employee.idCard}</td>
					<td>${employee.office.name}</td><!-- 注意：外键对象 -->
					<td>${employee.sex}</td>
					<td>${employee.age}</td>
					<td>${employee.telNum}</td>
					<td>${employee.address}</td>
					<td>${employee.email}</td>
					<td>
						<a href="${ctx}/bas/employee/form?id=${employee.id}">修改</a>
						<a href="${ctx}/bas/employee/delete?id=${employee.id}" onclick="return confirmx('确认要删除该员工吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>