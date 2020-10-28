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
    <title>产品分类-产品分类管理</title>

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
        body{
            margin: 0;
        }
        #closeAddBtn{
            border: 0;
            width: 35px;
            height: 25px;
        }
        #closeAddBtn:hover{
            background-color: #E81123;
            color: white;
        }
        button,input{
            outline:none;
        }
        button,input[type=button]:hover{
            cursor: pointer;
        }
    </style>


</head>

<body>

<%-----------------------------------------------添加分类弹框------------------------------------------------------------------%>
<div id="acSecondDiv"  style="box-shadow: gray 1px 1px;position: absolute;background-color: white;display: none;z-index: 3;" align="center">

       产品名称 ：<input type="text" id="productClass" style="margin-top: 60px;width: 200px;height: 20px"><br>
        <span style="position: relative;top: -90px">描述/备注：</span><textarea id="remark" style="margin-top: 30px; height: 100px;width: 200px;" ></textarea><br>
        <br>
        <input type="button" onclick="resert()" value="重置" style="margin-left: 180px"> <input type="button" value="确定" onclick="submitBtn()">

    <button id="closeAddBtn" style="position: absolute;top: 0;right: 0" onclick="closeAddDiv()" >x</button>
</div>
<div id="addClassDiv" style="position: absolute;background-color: black;opacity: 0.5;display: none;z-index: 2" align="center">
</div>
<%----------------------------------------------------------------------------------------------------------------------------------%>
<div id="m">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
        <tr>
            <td nowrap class="title1">您的位置：产品分类-产品分类管理</td>
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
        <div style="font-size: 15px">
            您当前的搜索：<span id="sekey"></span>
        </div>
        <select style="height: 30px" id="classNo">
            <option value="">分类序列号</option>
            <c:forEach items="${classNoList}" var="classNoList" varStatus="no">
                <option value="${classNoList}">${classNoList}</option>
            </c:forEach>
        </select>
        <input type="text" id="productName" style="width: 200px;height: 30px" placeholder="分类序列号/产品名">
        <input type="button"  style="height: 30px" onmouseover="OMO(event)"   onclick="Search()" value="查询">

        <input type="text" id="tiaoPage" style="width: 40px;height: 30px" placeholder="页数">
        <input type="button" onclick="tiaoPage()" style="height: 30px" value="跳转">

        <div style="float: right">
            <input type="button" onclick="addClass()" style="height: 30px" onmouseover="OMO(event)" value="产品分类增加">
        </div>
     </div>
    <div id="dataList">
        <table id="pClass" width="100%" border="0" align="center" cellspacing="1" class="c">
            <br><br><br>
            <tr>
                <td class="title1" style="width: 20%">产品分类序列号</td>
                <td class="title1" style="width: 30%">名称</td>
                <td class="title1" style="width: 30%;">描述</td>
                <td class="title1">操作</td>
            </tr>
<c:forEach items="${list}" var="pr" varStatus="no">
            <tr >
                <td align="center"><a href="pomain_detail.html">${pr.categoryID}</a></td>
                <td align="center">${pr.name}</td>
                <td align="center">${pr.remark}</td>
                <td align="center">
                    <a onclick="changeMsg(${pr.categoryID},'${pr.name}','${pr.remark}')" onmouseover="changeHand(this)" >修改</a>
                    <a onclick="deleteClass(this,${pr.categoryID},'${pr.name}')" onmouseover="changeHand(this)" >删除</a>
                </td>
            </tr>
</c:forEach>
            <tr style="display:none"><td colspan="10" name="detail"></td></tr>

        </table>


        <div class="pageDiv" style="position: relative;bottom: 0px">
            当前第 <span id="currentPage">1</span> 页

            <input type="button" value="首页"  id="startBtn" onclick="goStart()"/>
            <input type="button" value="上一页"  id="preBtn" onclick="goPre()"/>
            <input type="button" value="下一页" id="nextBtn" onclick="goNext()"/>
            <input type="button" value="末页" id="endBtn" onclick="goEnd()"/>
            <c:if test="${not empty total}">
                共<span id="totalPage">
                <c:if test="${not empty totalPage}">
                    ${totalPage}
                </c:if >
                </span>页
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

    var sekey = $("#sekey");

    <c:if test="${not empty currentPage}">
    currentPage.text("${currentPage}");
    </c:if>

    <c:if test="${not empty key}">
    sekey.text("${key}");
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



    //首页
    function goStart() {
        currentPage.text(1);
        preBtn.attr("disabled","disabled");
        startBtn.attr("disabled","disabled");
        nextBtn.attr("disabled",false);
        endBtn.attr("disabled",false);
        changePageResult(1);
    }
    //下一页
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

    //改变页面数据的function
    function changePageResult(num){
        var table = $("#pClass");
        var url = "../stock/classCP";
        $.ajax({
            url: url,
            dataType: 'json',
            data:{"page":num,"flag":1},
            success:function (result) {
                var Class = result;
                $('#pClass tr:not(:first)').remove();
                for(var i=0;i<result.length;i++){

                    // js中onclick中文参数传输方式
                    // 添加单引号或双引号即可，例
                    var classname = "'"+Class[i].b+"'";
                    var msg  = "'"+Class[i].c+"'";
                    table.append("<tr>"+
                        "<td align=\"center\"><a >"+Class[i].a+"</a></td>" +
                        "<td align=\"center\">"+Class[i].b+"</td>" +
                        "<td align=\"center\">"+Class[i].c+"</td>" +
                        "<td align=\"center\">" +
                        '<a href=\"#\" onclick="changeMsg('+Class[i].a+','+classname+','+msg+')" onmouseover="changeHand(this)">修改</a>' + "&nbsp"+
                        '<a href=\"#\" onclick="deleteClass(this,'+Class[i].a+','+classname+')" onmouseover="changeHand(this)">删除</a>' +
                        "</td>" +
                        "</tr>")
                }
            }
        });

    }
    //上一页
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
        changePageResult(num);
    }
    //末页
    function goEnd() {
        var total = parseInt(totalPage.text());
        currentPage.text(total);
        preBtn.attr("disabled",false);
        startBtn.attr("disabled",false);
        nextBtn.attr("disabled","disabled");
        endBtn.attr("disabled","disabled");
        changePageResult(total);
    }
    //搜索
    function Search() {
        var classNo = $("#classNo");
        var productName = $("#productName");
        console.log(classNo.val());
        console.log(productName.val());
        var key1 = classNo.val()+productName.val();
        if(key1 == ""){
            window.location.href = "../stock/pClass";
        }else {
            var x1,x2;
            if(classNo.val()==""){
                x1 = "null"
            }else {
                x1  = classNo.val();
            }
            if(productName.val()==""){
                x2 = "null";
            }else {
                x2 = productName.val();
            }
            var key2 = x1+" "+x2;
            window.location.href = "../stock/classSearch?searchKey="+key2;
        }


    }

    function changeHand(x) {
        x.style.cursor = "pointer";
    }

    function deleteClass(button,id,className) {
       var flag = confirm("您将要删除  ID:"+id+" 名为："+className+ "   的产品分类,是否确定？");
        if(flag){
            $.ajax({
                url: "../stock/deleteclass",
                dataType: 'text',
                data: {"categoryid":id},
                success: function (result) {
                    if(result == "false"){
                        alert("该产品分类已有正在售卖中的产品，无法删除！");
                    }else if(result == "succeed"){
                        alert("删除成功！");
                        var num = parseInt(currentPage.text());
                        console.log("传输"+num);
                        window.location.href = "../stock/classCP?page="+num+"&flag=2";
                    }else {
                        alert("失败!可能已被其他人员删除，请刷新重试");
                    }
                },
                error:function () {
                    alert("error");
                }
            });
        }
    }

    var changeType = 1;//changeType 1 = 添加  2 = 修改
    console.log("changetype:"+changeType);
    //添加框弹出
    function addClass() {
        changeType = 1;
        $("#addClassDiv").css({"width":"100%","height": "100%","display":"block"});
        $("#acSecondDiv").css({"min-height":"300px","min-width":"500px","width":"34%","height":"34%","display":"block","background-color":"white","opacity":"1.0"
        ,"top":"25%","left":"33%"});
    }
    //关闭 添加弹框
    function closeAddDiv(){
        $("#addClassDiv").css("display","none");
        $("#acSecondDiv").css("display","none");
        // var num = parseInt(currentPage.text());
        // window.location.href = "../product/classCP?page="+num+"&flag=2";
    }
    //重置
    function resert(){
         $("#productClass").val("");
         $("#remark").val("");
    }

    var cid ;
    //提交信息
    function submitBtn() {
        console.log(changeType);
        var productClass = $("#productClass");

        if(productClass.val() ==""){
            alert("请填写产品名");
        }else {
            var reg =/[A-Za-z0-9\u4e00-\u9fa5]/
            if(!reg.test(productClass.val())){
                alert("产品名为中英文数字");
            }else {
                if (changeType == "1") {
                    var flag1 = confirm("确定添加吗？");
                    if (flag1) {
                        var classname = $("#productClass").val();
                        var msg = $("#remark").val();
                        var num1 = parseInt(currentPage.text());
                        $.ajax({
                            url: "../stock/addclass",
                            dataType: 'text',
                            data: {"flag": 2, "name": classname, "msg": msg},
                            success: function (result) {
                                if (result == "already") {
                                    alert("已经存在该分类!");
                                } else if (result == "succeed") {
                                    alert("添加成功!");
                                    changePageResult(num1);
                                } else {
                                    alert("添加失败!");
                                }
                            },
                            error: function () {
                                alert("error");
                            }
                        });
                    }
                } else {
                    var flag2 = confirm("确定修改吗？");
                    if (flag2) {
                        var num2 = parseInt(currentPage.text());
                        var classname2 = $("#productClass").val();
                        var msg2 = $("#remark").val();
                        $.ajax({
                            url: "../stock/updateclass",
                            dataType: 'text',
                            data: {"flag": 2, "name": classname2, "msg": msg2, "cid": cid},
                            success: function (result) {
                                if (result == "already") {
                                    alert("该分类有产品存在，无法修改产品名,只能修改备注！");

                                } else if (result == "succeed") {
                                    changePageResult(num2);
                                    alert("修改成功!");
                                    $("#addClassDiv").css("display", "none");
                                    $("#acSecondDiv").css("display", "none");
                                } else {
                                    alert("修改失败!");
                                    $("#addClassDiv").css("display", "none");
                                    $("#acSecondDiv").css("display", "none");
                                }
                            },
                            error: function () {
                                alert("error");
                            }
                        });
                    }
                }
            }
        }
    }
    //修改信息
    function changeMsg(id,name,msg){
        $("#addClassDiv").css({"width":"100%","height": "100%","display":"block"});
        $("#acSecondDiv").css({"min-height":"300px","min-width":"500px","width":"34%","height":"34%","display":"block","background-color":"white","opacity":"1.0"
            ,"top":"25%","left":"33%"});
        $("#productClass").val(name);
        $("#remark").val(msg);
        changeType = 2;
        cid  = id;
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
</script>
</html>
