<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工位管理</title>
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
		<li class="active"><a href="${ctx}/bas/workCell/">工位列表</a></li>
		<li><a href="${ctx}/bas/workCell/form">工位添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/workCell" modelAttribute="workCell" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<label>工位名称：</label>
		<form:input path="cellName" maxlength="50" class="input-medium" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>工位名称</th>
			<th>所属工站</th>
			<th>工位编码</th>
			<th>工位负责人</th>
			<th>工位描述</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="workCell">
				<tr>
					<td>${workCell.cellName}</td>
					<td>${workCell.workStationInfos.stationName}</td><!-- 注意：外键对象 -->
					<td>${workCell.cellNumber}</td>
					<td>${workCell.cellMaster}</td>
					<td>${workCell.cellDescription}</td>
					<td>
						<a href="${ctx}/bas/workCell/form?id=${workCell.id}">修改</a>
						<a href="${ctx}/bas/workCell/delete?id=${workCell.id}" onclick="return confirmx('确认要删除该工位吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>