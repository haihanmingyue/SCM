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
    <title>仓库管理-入库登记</title>

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
        #tabBtn button{
            height: 30px;
            background-color: white;
            border: 1px solid;
        }
        #tabBtn button:hover{
           cursor: pointer;
        }
        a:hover{
            cursor: pointer;
        }
    </style>


</head>

<body>
<div id="m">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
        <tr>
            <td nowrap class="title1">您的位置：仓库管理-入库登记</td>
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

        <div style="float: right" id="tabBtn">
            <input type="text" id="tiaoPage" style="width: 40px;height: 30px" placeholder="页数">
            <input type="button" onclick="tiaoPage()" style="height: 30px" value="跳转">

            <button value="0" name="0">查看全部</button>
            <button value="1" name="1">货到付款</button>
            <button value="2" name="2">款到发货</button>
            <button value="3" name="3">预付款到发货</button>

        </div>

     </div>
    <div id="dataList">
        <table id="ruku" width="100%" border="0" align="center" cellspacing="1" class="c">
            <br><br><br>
            <tr>
                <td class="title1">采购单编号</td>
                <td class="title1">创建时间</td>
                <td class="title1">供应商名称</td>
                <td class="title1">创建用户</td>
                <td class="title1">附加费用</td>
                <td class="title1">采购产品总价</td>
                <td class="title1">采购单总价格</td>
                <td class="title1">付款方式</td>
                <td class="title1">最低预付款金额</td>
                <td class="title1">处理状态</td>

                <td class="title1">操作</td>
            </tr>

<c:forEach items="${list}" var="pr" varStatus="no">
            <tr >
                <td align="center"><a onclick="gotoDetail('${pr.POID}')">${pr.POID}</a></td>
                <td align="center">${pr.createTime}</td>
                <td align="center">${pr.venderName}</td>
                <td align="center">${pr.account}</td>
                <td align="center">${pr.tipFee}</td>
                <td align="center">${pr.productTotal}</td>
                <td align="center">${pr.pOTotal}</td>
                <td align="center">${pr.payName}</td>
                <td align="center">${pr.prePayFee}</td>
                <td align="center">${pr.staName}</td>

                <td align="center">
                    <a onclick="RuKu('${pr.POID}')" onmouseover="changeHand(this)" >入库</a>
                </td>
            </tr>
</c:forEach>
            <tr style="display:none"><td colspan="10" name="detail"></td></tr>

        </table>


        <div class="pageDiv" style="position: fixed;bottom: 100px">
            当前第 <span id="currentPage">1</span> 页

            <input type="button" value="首页"  id="startBtn" onclick="goStart()"/>
            <input type="button" value="上一页" id="preBtn" onclick="goPre()"/>
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
    var selectKey = $("#selectKey");
    var sekey = $("#sekey");
    var tempFlag = 1;//判断查询的状态
    <c:if test="${not empty currentPage}">
    currentPage.text("${currentPage}");
    </c:if>


    function gotoDetail(poid) {
        window.location.href ="${pageContext.request.contextPath}/poamin/poamindetail?poid="+poid;
    }

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

    function goStart() {
        currentPage.text(1);
        preBtn.attr("disabled","disabled");
        startBtn.attr("disabled","disabled");
        nextBtn.attr("disabled",false);
        endBtn.attr("disabled",false);
        changePageResult(1,tempFlag);
    }
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
       changePageResult(num,tempFlag);
    }
    function changePageResult(num,flag){

        var table = $("#ruku");
        var url = "../stock/changePage";
        $.ajax({
            url: url,
            dataType: 'json',
            data:{"page":num,"flag":flag},
            success:function (result) {
                var re = result;
                $('#ruku tr:not(:first)').remove();
                for(var i=0;i<result.length;i++){
                    table.append("<tr>"+
                        "<td align=\"center\"><a onclick='gotoDetail("+re[i].a+")'>"+re[i].a+"</a></td>" +
                        "<td align=\"center\">"+re[i].b+"</td>" +
                        "<td align=\"center\">"+re[i].c+"</td>" +
                        "<td align=\"center\">"+re[i].d+"</td>" +
                        "<td align=\"center\">"+re[i].e+"</td>" +
                        "<td align=\"center\">"+re[i].f+"</td>" +
                        "<td align=\"center\">"+re[i].g+"</td>" +
                        "<td align=\"center\">"+re[i].h+"</td>" +
                        "<td align=\"center\">"+re[i].i+"</td>" +
                        "<td align=\"center\">"+re[i].j+"</td>" +
                        "<td align=\"center\">" +
                        "<a href=\"#\" onclick='RuKu("+re[i].a+")' onmouseover='changeHand(this)'>入库</a>" +
                        "</td>" +
                        "</tr>")
                }
                $("#totalPage").text(re[re.length - 1].allPage);

                $("#totalNum").text(re[re.length - 1].totalNum);
                currentPage.text(num);
                buttonDisabled();
            }
        });

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
        changePageResult(num,tempFlag)
    }
    function goEnd() {
        var total = parseInt(totalPage.text());
        currentPage.text(total);
        preBtn.attr("disabled",false);
        startBtn.attr("disabled",false);
        nextBtn.attr("disabled","disabled");
        endBtn.attr("disabled","disabled");
        changePageResult(total,tempFlag);
    }

    function RuKu(poid) {
        var flag = confirm("确定入库吗？");
        if (flag){
            var url = "../stock/rukuservice";
            $.ajax({
                url: url,
                dataType: 'text',
                data:{"poid":poid},
                success:function (result) {
                    if(result == "succeed"){
                        changePageResult(currentPage.text().trim(),tempFlag);
                        alert("入库成功");
                    }else {
                        alert("入库出现错误，可能已被其他人执行，请刷新重试");
                    }
                },
                error:function () {
                    alert("程序出现错误，请刷新重试，或联系人员修复")
                }
            });
        }

    }
    function changeHand(x) {
        x.style.cursor = "pointer";
    }

    //跳转页数
    function tiaoPage() {

        var tiaoPage = $("#tiaoPage").val();
        if(tiaoPage=="" || parseInt(tiaoPage.trim()) <1 || parseInt(tiaoPage.trim())> parseInt($("#totalNum").text().trim())){
            alert("请输入正确的页数")
        }else {
            changePageResult(tiaoPage.trim(),tempFlag);
            currentPage.text(tiaoPage.trim());
            buttonDisabled();
        }
    }
    $("#tabBtn button[name='0']").css({"background-color":"#2B579A","color":"white"});

    $("#tabBtn button[name='0']").click(function () {
        tempFlag = 1;
        $("#tabBtn button[name='0']").css({"background-color":"#2B579A","color":"white"});
        $("#tabBtn button[name='1']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='2']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='3']").css({"background-color":"#ffffff","color":"black"});
        changePageResult(1,tempFlag);
    });
    $("#tabBtn button[name='1']").click(function () {
        tempFlag = 2;
        $("#tabBtn button[name='1']").css({"background-color":"#2B579A","color":"white"});
        $("#tabBtn button[name='0']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='2']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='3']").css({"background-color":"#ffffff","color":"black"});
        changePageResult(1,tempFlag);
    });
    $("#tabBtn button[name='2']").click(function () {
        tempFlag = 3;
        $("#tabBtn button[name='2']").css({"background-color":"#2B579A","color":"white"});
        $("#tabBtn button[name='0']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='1']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='3']").css({"background-color":"#ffffff","color":"black"});
        changePageResult(1,tempFlag);
    });
    $("#tabBtn button[name='3']").click(function () {
        tempFlag = 4;
        $("#tabBtn button[name='3']").css({"background-color":"#2B579A","color":"white"});
        $("#tabBtn button[name='0']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='1']").css({"background-color":"#ffffff","color":"black"});
        $("#tabBtn button[name='2']").css({"background-color":"#ffffff","color":"black"});
        changePageResult(1,tempFlag);
    });
</script>
</html>
