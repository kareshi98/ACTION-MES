<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工艺流程与工序关系管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
	</script>
	<script type="text/javascript">
		function Select() {
			var name = document.getElementsByName("ids");
			var sel = document.getElementById("selall");
			for (var i = 0; i < name.length; i++) {
				name[i].checked=true;
			}
			if(!sel.checked){
				for (var i = 0; i < name.length; i++) {
					name[i].checked=false;
				}
			}
		}

		/*添加删除选中栏*/
		function delmore(){
			//给删除选中按钮添加单击事件
			document.getElementById("delSelected").onclick = function(){
				if(confirm("您确定要删除选中条目吗？")){
					var flag=false;
					//判断是否有选中条目,不选中任何条目删除会报空指针异常错误
					var idAr=new Array();
					var id='';
					var name = document.getElementsByName("ids");
					for (var i = 0; i < name.length; i++) {
						if(name[i].checked){
							//有一个条目选中了
							flag=true;
							break;
						}
					}
					if(flag==false)
						alert("当前未选中任何项目，请检查");
					else
					{
						for(var i=0;i<name.length;i++){
							id=name[i].value;
							if(name[i].checked){
								//window.location.href = "${ctx}/tec/flow/delete?id="+id;
								idAr[i]=id;

							}
						}
						//上面会拼接出一个名为idAr的数组

						window.location.href = "${ctx}/tec/flowProcess/delmore?idAr="+idAr;

					}
				}
			}
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
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tec/flowProcess">工艺流程与工序关系列表</a></li>
		<li><a href="${ctx}/tec/flowProcess/form">工艺流程与工序关系添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/tec/flowProcess" modelAttribute="flowProcess" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			<label>所属流程：</label>
			<form:input path="flow.flowName" maxlength="50" class="input-medium" htmlEscape="false"/>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
			<input id="PutExcel" type="submit" value="导出EXCEL" class="btn btn-primary" onclick="tableToExcel('contentTable','flowProcessList')"/>
			<button type="button" class="btn btn-primary" onclick="delmore()" id="delSelected">删除选中</button>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<!-- 3.列表 -->
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<th><input type="checkbox" name="selall" id="selall" onclick="Select()"/>全选</th>
			<th>工序名称</th>
			<th>所属工艺流程</th>
			<th>排序号</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="flowProcess">
				<tr>
					<td><input type="checkbox" name="ids" id="id" value="${flowProcess.id}"/></td>
					<td>${flowProcess.process.proName}</td>
					<td>${flowProcess.flow.flowName}</td>
					<td>${flowProcess.sort}</td>
					<td>
						<a href="${ctx}/tec/flowProcess/form?id=${flowProcess.id}">修改</a>
						<a href="${ctx}/tec/flowProcess/delete?id=${flowProcess.id}" onclick="return confirmx('确认要删除该工艺流程与工序关系吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>