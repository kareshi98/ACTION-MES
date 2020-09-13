<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>读卡器管理</title>
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
		<li class="active"><a href="${ctx}/bas/cardReader/">读卡器列表</a></li>
		<li><a href="${ctx}/bas/cardReader/form">读卡器添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/cardReader" modelAttribute="cardReader" class="breadcrumb form-search">
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
			<th>读取速度</th>
			<th>打卡间隔</th>
			<th>感应距离</th>
			<th>读卡类型</th>
			<th>读卡频率</th>
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
			<c:forEach items="${page.list}" var="reader">
				<tr>
					<td>${reader.qrCode}</td>
					<td>${reader.type}</td>
					<td>${reader.spec}</td>
					<td>${reader.workStationInfos.stationName}</td>
					<td>${reader.workCell.cellName}</td>					
					<td>${reader.readerSpeed}</td>
					<td>${reader.timeInterval}</td>
					<td>${reader.distance}</td>	
					<td>${reader.readerType}</td>
					<td>${reader.frequency}</td>					
					<td>${reader.supplier}</td>
					<td>${reader.manufacturer}</td>
					<td>${reader.factoryNumber}</td>
					<td>${reader.purpose}</td>
					<td>${reader.buyDate}</td>
					<td>${reader.person}</td>
					<td>${reader.organization.name}</td>
					<td>
						<a href="${ctx}/bas/cardReader/form?id=${reader.id}">修改</a>
						<a href="${ctx}/bas/cardReader/delete?id=${reader.id}" onclick="return confirmx('确认要删除该读卡器设备吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>