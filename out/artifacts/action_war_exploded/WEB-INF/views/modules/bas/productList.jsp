<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>产品管理</title>
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
		<li class="active"><a href="${ctx}/bas/product/">产品列表</a></li>
		<li><a href="${ctx}/bas/product/form">产品添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/bas/product/" modelAttribute="product" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		
		<label>产品名称：</label>
		<form:input path="productName" maxlength="50" class="input-medium" htmlEscape="false"/>
		<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>产品名称</th>
			<th>产品简称</th>
			<th>产品简述</th>
			<th>是否需要首件检查</th>
			<th>条码</th>
			<th>管理方式</th>
			<th>批次</th>
			<th>状态</th>
			<th>产品计量单位</th>
			<th>属性</th>
			<th>生产流程</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="product">
				<tr>
					<td>${product.productName}</td>
					<td>${product.productAbbr}</td>
					<td>${product.productDesc}</td>
					<td>${fns:getDictLabel(product.firstCheck, 'yes_no', '')}</td>
					<td>${product.qrCode}</td>
					<td>${product.manageWay}</td>
					<td>${product.lotNumber}</td>
					<td>${product.state}</td>
					<td>${product.productUnit}</td>
					<td>${product.productProp}</td>
					<td>${product.flow.flowName}</td>
					<td>
						<a href="${ctx}/bas/product/form?id=${product.id}">修改</a>
						<a href="${ctx}/bas/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该产品吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>