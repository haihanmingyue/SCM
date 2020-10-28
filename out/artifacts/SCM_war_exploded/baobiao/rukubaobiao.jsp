<%--
  Created by IntelliJ IDEA.
  User: haihanmingyue
  Date: 2020/8/16
  Time: 13:26
  To change this template use File | Settings | File Templates.
  入库登记
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>仓库管理-出库登记</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/jquery.min.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/resource/script/common.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/productDiv.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/My97DatePicker/WdatePicker.js" ></script>

    <style type="text/css">
        div.product{
            position: absolute;
            top:2px;
            left: 2px;
            width:100%;
            height: 98%;
            background-color: #fffffe;
        }
        td{
            height: 30px;
            font-size: 15px;
        }
    </style>


</head>

<body>
<div id="m">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
        <tr>
            <td nowrap class="title1">您的位置：仓库管理-出库登记</td>
        </tr>
    </table>
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="50%" nowrap="" class="toolbar">&nbsp;</td>
            <td width="380px" nowrap="" class="toolbar"></td>
        </tr>
    </table>
    <br><br><br>

    <div >

        <select name="year" id="year" style="width: 100px;height: 30px" >
            <option value="">年份</option>
        </select> 年
        <select name="month" id="month" style="width: 100px;height: 30px" >
            <option value="">月份</option>
        </select> 月

        <input type="button"  style="height: 30px" onmouseover="OMO(event)"   onclick="Search()" value="查询">

        <input type="text" id="tiaoPage" style="width: 40px;height: 30px" placeholder="页数">
        <input type="button" onclick="tiaoPage()" style="height: 30px" value="跳转">
     </div>
    <div style="height: 30px;line-height: 30px;font-size: 15px">
        <a>入库单据数:<span id="itemNums">999</span></a>
        <a>|</a>
        <a>产品总数量:<span id="proNums">999</span></a>
        <a>|</a>
        <a>入库产品总金额:<span id="moneyNums">999</span></a>
    </div>
    <div id="dataList">
        <table id="ruku" width="100%" border="0" align="center" cellspacing="1" class="c">
            <tr>
                <td class="title1">采购单号</td>
                <td class="title1">入库日期</td>
                <td class="title1">产品编号</td>
                <td class="title1">产品名称</td>
                <td class="title1">产品数</td>
                <td class="title1">产品总金额</td>

            </tr>

<c:forEach items="${list}" var="pr" varStatus="no">
            <tr >
                <td class="poCode" align="center"><a href="../gztm/pomain_detail.jsp">${pr.orderCode}</a></td>
                <td align="center">${pr.stockTime}</td>
                <td align="center">${pr.productCode}</td>
                <td align="center">${pr.proName}</td>
                <td class="proNums" align="center">${pr.stockNum}</td>
                <td class="totalMoney" align="center">${pr.totalMoney}</td>
            </tr>
</c:forEach>
            <tr style="display:none"><td colspan="10" name="detail"></td></tr>

        </table>


        <div class="pageDiv" style="position: fixed;bottom: 100px">
            当前第 <span id="currentPage">1</span> 页

            <input type="button" value="首页" disabled="disabled" id="startBtn" onclick="goStart()"/>
            <input type="button" value="上一页" disabled="disabled" id="preBtn" onclick="goPre()"/>
            <input type="button" value="下一页" id="nextBtn" onclick="goNext()"/>
            <input type="button" value="末页" id="endBtn" onclick="goEnd()"/>
            <c:if test="${not empty total}">
                共<span id="totalPage">
                <c:if test="${not empty totalPage}">
                    ${totalPage}
                </c:if ></span>页
                    共 <span id="totalNum">${total}</span> 条记录
            </c:if>
            <c:if test="${empty total}">
                共  0 页 0条记录
            </c:if>
        </div>
    </div>
</div>
</body>
<script>

    var nextBtn = $("#nextBtn");
    var preBtn = $("#preBtn");
    var startBtn = $("#startBtn");
    var endBtn = $("#endBtn");
    var currentPage = $("#currentPage");
    var totalPage = $("#totalPage");
    var totalNum = $("#totalNum");
    var num = parseInt(currentPage.text());
    var total = parseInt(totalPage.text());
    var selectKey = $("#selectKey");


    <c:if test="${not empty currentPage}">
    currentPage.text("${currentPage}");
    </c:if>

    function buttonDisabled() {
        if(currentPage.text().trim() == totalPage.text().trim()){
            nextBtn.attr("disabled","disabled");
            endBtn.attr("disabled","disabled");
            if(currentPage.text().trim() != "1"){
                preBtn.attr("disabled",false);
                startBtn.attr("disabled",false);
            }
        }
        if(currentPage.text().trim() == "1"){
            preBtn.attr("disabled","disabled");
            startBtn.attr("disabled","disabled");
            if(currentPage.text().trim() != totalPage.text().trim()){
                nextBtn.attr("disabled",false);
                endBtn.attr("disabled",false);
            }
        }
    }
    buttonDisabled();
    function msgOne() {  //概括信息
        var itemNums = $("#itemNums");
        var proNums = $("#proNums");
        var moneyNums = $("#moneyNums");
        var x =$(".totalMoney");
        var y = $(".proNums");
        var monty = 0.0;
        var nums = 0.0;
        for(var j=0;j<y.length;j++){
            nums += parseFloat(y[j].innerText);
        }
        for(var i=0;i<x.length;i++){
            monty += parseFloat(x[i].innerText);
        }
        proNums.text(nums);
        moneyNums.text(monty);
        if(proNums.text()=="NaN"){
            itemNums.text("NaN");
        }else {
            var poCode = $(".poCode");
            var sum = x.length;
            for(var k=0;k<x.length-1;k++){
                for(var v=k+1;v<x.length;v++){
                    if(poCode[k].innerText == poCode[v].innerText){
                        sum -= 1;
                    }
                }
            }
            itemNums.text(sum);
        }
    }
    msgOne();

    function goStart() {
        currentPage.text(1);
        preBtn.attr("disabled","disabled");
        startBtn.attr("disabled","disabled");
        nextBtn.attr("disabled",false);
        endBtn.attr("disabled",false);
        changePageResult(1);
    }
    function goNext() {

        var num = parseInt(currentPage.text());
        var total = parseInt(totalPage.text());
        num += 1;
        currentPage.text(num);
        preBtn.attr("disabled",false);
        startBtn.attr("disabled",false);
        if(num == total){
            nextBtn.attr("disabled","disabled");
            endBtn.attr("disabled","disabled");
        }
       changePageResult(num);
    }
    function changePageResult(num){
        var table = $("#ruku");
        var url = "../stock/rkbbPage";
        var year = $("#year");
        var month = $("#month");
        var str;
        var monthstr;
        if(parseInt(month.val())<10){
            monthstr = "0"+month.val();
        }
        var flag = true;
        if(year.val() != "" && month.val() != ""){
            str = year.val()+"-"+monthstr;
        }else if(year.val() == "" && month.val() == ""){
            str = "";
        } else {
            flag = false;
            alert("如果要查询的话，年分和月份请不要为空");
        }
        if(flag){
            $.ajax({
                url: url,
                dataType: 'json',
                data:{"page":num,"yearMonth":str},
                success:function (result) {
                    var re = result;
                    $('#ruku tr:not(:first)').remove();
                    for(var i=0;i<result.length;i++){
                        table.append("<tr>"+
                            "<td class='poCode' align=\"center\"><a href=\"../gztm/pomain_detail.jsp\">"+re[i].c+"</a></td>" +
                            "<td align=\"center\">"+re[i].f+"</td>" +
                            "<td align=\"center\">"+re[i].b+"</td>" +
                            "<td align=\"center\">"+re[i].h+"</td>" +
                            "<td class=\"proNums\" align=\"center\">"+re[i].d+"</td>" +
                            "<td class=\"totalMoney\" align=\"center\">"+re[i].i+"</td>" +
                            "</tr>")
                    }
                    $("#totalPage").text(re[re.length - 1].allPage);

                    $("#totalNum").text(re[re.length - 1].totalNum);
                    currentPage.text(num);
                    msgOne();
                    buttonDisabled();
                }

            });
        }


    }
    function goPre() {
        var num = parseInt(currentPage.text());
        num -= 1;
        currentPage.text(num);
        nextBtn.attr("disabled",false);
        endBtn.attr("disabled",false);
        if(num == 1){
            preBtn.attr("disabled",true);
            startBtn.attr("disabled",true);
        }
        changePageResult(num)
    }
    function goEnd() {
        var total = parseInt(totalPage.text());
        currentPage.text(total);
        preBtn.attr("disabled",false);
        startBtn.attr("disabled",false);
        nextBtn.attr("disabled","disabled");
        endBtn.attr("disabled","disabled");
        changePageResult(total);
    }

    function Search() {
       changePageResult(1);
    }
    function ChuKu(button,num,procode,pronum) {

    }
    function changeHand(x) {
        x.style.cursor = "pointer";
    }

    //跳转页数
    function tiaoPage() {
        var tiaoPage = $("#tiaoPage").val();
        if(tiaoPage==""|| parseInt(tiaoPage.trim()) <1 || parseInt(tiaoPage.trim())> parseInt($("#tiaoPage").text().trim())){
            alert("请输入正确的页数")
        }else {
            changePageResult(tiaoPage.trim());
            currentPage.text(tiaoPage.trim());
            buttonDisabled();
        }
    }
    var obj = document.getElementById("year");
    for(var i=2000;i<2100;i++){
        obj.options.add(new Option(i.toString()),i);
    }
    var obj2 = document.getElementById("month");
    for(var j=1;j<=12;j++){
        obj2.options.add(new Option(j.toString()),j);
    }

</script>
</html>
