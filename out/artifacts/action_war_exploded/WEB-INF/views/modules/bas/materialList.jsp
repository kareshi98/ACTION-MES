<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>原料库存列表</title>
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
		<li class="active"><a href="${ctx}/bas/material/">原料库存列表</a></li>
	</ul>
	<!-- 2.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>批号</th>
			<th>材料类型</th>
			<th>材料名称</th>
			<th>供应商名称</th>
			<th>采购日期</th>
			<th>库存</th>
			<th>单位</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="m">
				<tr>
					<td>${m.batchNum}</td>
					<td>${fns:getDictLabel(m.mType, 'material_type', '')}</td>
					<td>${m.mName}</td>
					<td>${m.supplierName}</td>
					<td>${m.purchaseDate}</td>
					<td>${m.quantity}</td>
					<td>${fns:getDictLabel(m.unit,'unit','')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 3.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>