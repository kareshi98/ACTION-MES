<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>违规记录管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
	<script type="text/javascript">
		function base64 (content) {
			return window.btoa(unescape(encodeURIComponent(content)));
		}
		/*
        *@tableId: table的Id
        *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
        */
		function tableToExcel(tableID,fileName){
			var table = document.getElementById(tableID);
			var excelContent = table.innerHTML;
			var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
			excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
			excelFile += "<body><table>";
			excelFile += excelContent;
			excelFile += "</table></body>";
			excelFile += "</html>";
			var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
			var a = document.createElement("a");
			a.download = fileName+".xls";
			a.href = link;
			a.click();
		}
	</script>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qc/violaction">违规记录列表</a></li>
		<li><a href="${ctx}/qc/violaction/form">违规记录添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/qc/violaction" modelAttribute="violaction" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label>工站名称：</label>
			<form:input path="workStationInfos.stationName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
			<input id="PutExcel" type="submit" value="导出EXCEL" class="btn btn-primary" onclick="tableToExcel('contentTable','violationList')"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th>工序编码</th>
			<th>工站名称</th>
			<th>违规人员</th>
			<th>违规描述</th>
			<th>违规时间</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="violaction">
				<tr>
					<td>${violaction.process.proCode}</td>
					<td>${violaction.workStationInfos.stationName}</td>
					<td>${violaction.userName}</td>
					<td>${violaction.violationDes}</td>
					<td>${violaction.violationTime}</td>
					<td>
						<a href="${ctx}/qc/violaction/form?id=${violaction.id}">修改</a>
						<a href="${ctx}/qc/violaction/delete?id=${violaction.id}" onclick="return confirmx('确认要删除该违规记录信息吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>