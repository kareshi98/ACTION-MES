<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>电子秤管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bas/electronSteelYard/">电子秤列表</a></li>
		<li><a href="${ctx}/bas/electronSteelYard/form">电子秤添加</a></li>
	</ul>
	<form:form id="searchForm" method="post" action="${ctx}/bas/electronSteelYard" modelAttribute="electronSteelYard" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>设备类型：</label>
			<form:select path="type" id="type" class="input-medium required">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>设备规格：</label>
			<form:select path="spec" id="spec" class="input-medium required">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_spec')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>编码：</label>
			<form:input path="qrCode" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>编码</th>
			<th>设备类型</th>
			<th>设备规格</th>
			<th>所属工站</th>
			<th>所属工位</th>
			<th>名称</th>
			<th>称量范围</th>
			<th>重量</th>
			<th>供应商</th>
			<th>生厂商</th>
			<th>出厂编号</th>
			<th>用途</th>
			<th>采购日期</th>
			<th>资产负责人</th>
			<th>所有权部门</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="e">
				<tr>
					<td>${e.qrCode}</td>
					<td>${e.type}</td>
					<td>${e.spec}</td>
					<td>${e.workStationInfos.stationName}</td>
					<td>${e.workCell.cellName}</td>					
					<td>${e.eSName}</td>
					<td>${e.eSRange}</td>
					<td>${e.eSWeight}</td>					
					<td>${e.supplier}</td>
					<td>${e.manufacturer}</td>
					<td>${e.factoryNumber}</td>
					<td>${e.purpose}</td>
					<td>${e.buyDate}</td>
					<td>${e.person}</td>
					<td>${e.organization.name}</td>
					<td>
						<a href="${ctx}/bas/electronSteelYard/form?id=${e.id}">修改</a>
						<a href="${ctx}/bas/electronSteelYard/delete?id=${e.id}" onclick="return confirmx('确认要删除该电子秤设备吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>