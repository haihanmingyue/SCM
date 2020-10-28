<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>采购单详情</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link href="../resource/css/style.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="../resource/script/common.js" ></script>
	<script type="text/javascript" src="../resource/script/productDiv.js" ></script>
	<script type="text/javascript" src="../resource/script/My97DatePicker/WdatePicker.js" ></script>
</head>
<style>
	td{
		height: 30px;
		font-size: 15px;
	}
</style>
<body>
<div id="m">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
		<button style="position: absolute;left: 5px;cursor:pointer;" onclick="javascript:window.history.back();">返回</button>
		<tr>
			<td nowrap class="title1">采购单详情</td>
		</tr>
	</table>
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="30px" nowrap class="toolbar">&nbsp;</td>
			<td nowrap class="toolbar">&nbsp;</td>
		</tr>
	</table>

	<table id="headTable" width="100%"  border="0" align="center" class="a1">
		<tr align="justify">
			<c:if test="${not empty pomain}">
			<td>采购单编号</td>
			<td>${pomain.POID}</td>
			<td>创建时间</td>
			<td>${pomain.createTime}</td>
			<td>供应商的名称</td>
			<td>${pomain.venderName}</td>
			<td>创建用户</td>
			<td>${pomain.account}</td>
		</tr>
		<tr align="justify">
			<td>附加费用</td>
			<td>${pomain.tipFee}</td>
			<td>采购产品总价</td>
			<td>${pomain.productTotal}</td>
			<td>付款方式</td>
			<td>${pomain.payName}</td>
			<td>最低预付款金额</td>
			<td>${pomain.prePayFee}</td>
		</tr>
		<tr align="justify">
			<td colspan="8">&nbsp;</td>
		</tr>
		<tr align="justify">
			<td>处理状态</td>
			<td>
					${pomain.staName}
			</td>
			<td>入库登记时间</td>
			<td>${pomain.stockTime}</td>
			<td>入库登记用户</td>
			<td>${pomain.stockUser}</td>
			<td>付款登记时间</td>
			<td>${pomain.payTime}</td>
		</tr>
		<tr align="justify">
			<td>付款登记用户</td>
			<td>${pomain.payUser}</td>
			<td>预付登记时间</td>
			<td>${pomain.prePayTime}</td>
			<td>预付登记用户</td>
			<td>${pomain.prePayUser}</td>
			<td>了结时间</td>
			<td>${pomain.endTime}</td>
		</tr>
		<tr align="justify">
			<td>了结用户</td>
			<td>${pomain.endUser}</td>
			<td>备注</td>
			<td colspan="5">${pomain.remark}</td>
		</tr>
		<tr>
			<td class="title2"></td>
		</tr>
		</c:if>
	</table>

	<table width="100%"  border="0" align="center" cellspacing="1" id="detailTable">
		<tr>
			<td class="toolbar">序号 </td>
			<td class="toolbar">产品编号 </td>
			<td class="toolbar">产品名称 </td>
			<td class="toolbar">数量单位 </td>
			<td class="toolbar">产品数量 </td>
			<td class="toolbar">采购单价</td>
			<td class="toolbar">明细总价</td>
		</tr>

		<c:forEach items="${poitemList}" var="po" varStatus="no">
			<tr align="center">
				<td>${no.count}</td>
				<td>${po.productCode}</td>
				<td>${po.productName}</td>
				<td>${po.unitName}</td>
				<td>${po.num}</td>
				<td>${po.unitPrice}</td>
				<td>${po.itemPrice}</td>
			</tr>
		</c:forEach>
	</table>
	<table width="100%"  border="0" align="center" cellspacing="1">
		<tr>
			<td class="title2"></td>
		</tr>
	</table>





</div>


</body>
</html>
