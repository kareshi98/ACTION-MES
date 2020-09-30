<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>设备保养计划管理</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>

	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
		}
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

						window.location.href = "${ctx}/equip/maintenance/delmore?idAr="+idAr;
					}
				}
			}
		}
	</script>
</head>
<body>
	<!-- 1.tab头部 -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/equip/maintenance">设备保养计划列表</a></li>
		<li><a href="${ctx}/equip/maintenance/form">设备保养计划添加</a></li>
	</ul>
	<!-- 2.查询 -->
	<form:form id="searchForm" method="post" action="${ctx}/equip/maintenance" modelAttribute="maintenancePlan" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=${page.pageNo}/>
		<input id="pageSize" name="pageSize" type="hidden" value=${page.pageSize}/>
		<div class="controls">
			<label>设备类型：</label>
			<form:select path="equipType" id="equipType" class="input-medium required">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('equip_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<input id="btnSubmit" type="submit" value="查询" class="btn btn-primary"/>
			<input id="PutExcel" type="submit" value="导出EXCEL" class="btn btn-primary" onclick="tableToExcel('contentTable','flowList')"/>
			<button type="button" class="btn btn-primary" onclick="delmore()" id="delSelected">删除选中</button>
		</div>
	</form:form>
	<sys:message content="${message}"></sys:message>
	<!-- 3.列表 -->
	<table id="contentTable" class="layui-table">
		<thead>
		<th><input type="checkbox" name="selall" id="selall" onclick="Select()"/>全选</th>
			<th>设备类型</th>
			<th>维护周期</th>
			<th>预警时间</th>
			<th>保养内容</th>
			<th>保养人</th>
			<th>操作</th>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="plan">
				<tr>
					<td><input type="checkbox" name="ids" id="id" value="${plan.id}"/></td>
					<td>${fns:getDictLabel(plan.equipType, 'equip_type', '')}</td>
					<td>${fns:getDictLabel(plan.cycle, 'equip_cycle', '')}</td>
					<td>${plan.warnTime}</td>
					<td>${plan.maintenance}</td>
					<td>${plan.user.name}</td>
					<td>
						<a href="${ctx}/equip/maintenance/form?id=${plan.id}">修改</a>
						<a href="${ctx}/equip/maintenance/delete?id=${plan.id}" onclick="return confirmx('确认要删除该设备保养计划信息吗？',this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 4.分页 -->
	<div class="pagination">${page}</div>
</body>
</html>