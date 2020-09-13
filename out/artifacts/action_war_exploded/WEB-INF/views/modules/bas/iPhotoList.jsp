<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>红外对射枪管理</title>
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
		<li class="active"><a href="${ctx}/bas/iPhoto/">红外对射枪列表</a></li>
		<li><a href="${ctx}/bas/iPhoto/form">红外对射枪添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/iPhoto" modelAttribute="iPhoto" class="breadcrumb form-search">
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
			<th>感应距离</th>
			<th>感应方式</th>
			<th>工作环境</th>
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
			<c:forEach items="${page.list}" var="iPhoto">
				<tr>
					<td>${iPhoto.qrCode}</td>
					<td>${iPhoto.type}</td>
					<td>${iPhoto.spec}</td>
					<td>${iPhoto.workStationInfos.stationName}</td>
					<td>${iPhoto.workCell.cellName}</td>					
					<td>${iPhoto.iphotoDistance}</td>
					<td>${iPhoto.iphotoWay}</td>
					<td>${iPhoto.workEnvironment}</td>					
					<td>${iPhoto.supplier}</td>
					<td>${iPhoto.manufacturer}</td>
					<td>${iPhoto.factoryNumber}</td>
					<td>${iPhoto.purpose}</td>
					<td>${iPhoto.buyDate}</td>
					<td>${iPhoto.person}</td>
					<td>${iPhoto.organization.name}</td>
					<td>
						<a href="${ctx}/bas/iPhoto/form?id=${iPhoto.id}">修改</a>
						<a href="${ctx}/bas/iPhoto/delete?id=${iPhoto.id}" onclick="return confirmx('确认要删除该条码打印机设备吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>