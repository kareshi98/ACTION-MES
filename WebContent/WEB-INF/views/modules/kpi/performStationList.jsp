<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>绩效参数与工站关系</title>
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
		<li class="active"><a href="${ctx}/kpi/performStation">绩效参数与工站关系列表</a></li>
		<li><a href="${ctx}/kpi/performStation/form">绩效参数与工站关系添加</a></li>
	</ul>
	<!-- 2.查询部分 -->
	<form:form id="searchForm" method="post" action="${ctx}/kpi/performStation" modelAttribute="performStation" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>绩效参数名称：</label>
			<form:input path="performType.performTypeName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>绩效参数名称</th>
			<th>工站名称</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="performStation">
				<tr>
					<td>${performStation.performType.performTypeName}</td>
					<td>${performStation.workStationInfos.stationName}</td>
					<td>
						<a href="${ctx}/kpi/performStation/form?id=${performStation.id}">修改</a>
						<a href="${ctx}/kpi/performStation/delete?id=${performStation.id}" onclick="return confirmx('确认要删除该绩效参数与工站关系信息吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>