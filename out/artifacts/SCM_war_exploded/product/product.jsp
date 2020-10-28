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
    <title>产品分类-产品管理</title>

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
            height: 30px;
            outline:none;
        }
        button,input[type=button]:hover{
            cursor: pointer;
        }
        #oneDiv input{
            margin-top:60px ;
        }
        #threeDiv input{
            margin-top:30px ;
            width: 175px;
        }
        #twoDiv select{
            margin-top: 30px;
            height: 30px;
            width: 175px;

        }
        select option{
            font-size: 18px;
        }

        #acSecondDiv{
        font-size: 15px;
        }
        .tip{
            color: red;
            width: 75px;
            border: none;
            background-color: white;
        }
        .\*{
            color: red;
        }
    </style>


</head>

<body>

<%-----------------------------------------------添加分类弹框------------------------------------------------------------------%>
<div id="acSecondDiv"  style="box-shadow: gray 1px 1px;position: absolute;background-color: white;display: none;z-index: 3;" align="center">
    <div id="oneDiv">
      产品编号：<input  id="proID" type="text" maxlength="20" placeholder="4~20字符,字母和数字"><span class="*">*</span>
        <input type="text" class="tip">
      产品名称 ：<input id="proName" type="text" id="product" maxlength="100" placeholder="100字符内"><span class="*">*</span>
        <input type="text" class="tip">
        <br>
    </div>
    <div id="twoDiv">
      产品分类：<select style="border:#A7E2E7 1px solid" id="category">
    <option value="">分类</option>
        <c:forEach items="${classno}" var="cat">
            <option value="${cat.categoryID}">${cat.name}</option>
        </c:forEach>
               </select><span class="*">*</span>
        <input type="text" class="tip">
    产品售价：<input type="text" id="price" value="0" placeholder="售价"><span class="*">*</span>
        <input type="text" class="tip">
    <br>
    </div>
    <div id="threeDiv">
        数量单位：<input type="text" id="unitName"><span class="*">*</span>
        <input type="text" class="tip" style="width: 75px">
        添加日期：<input type="text" id="date" disabled="disabled" style="border: gray 1px solid" placeholder="无需填写，自动填充"><span class="*">&nbsp;</span>
        <input type="text" class="tip" style="width: 75px">
        <br>
    </div>
        <span style="position: relative;top: -135px">描述/备注：</span><textarea id="remark" style=" margin-top: 30px; height: 150px;width: 600px;"  ></textarea><br>
        <br>
        <input type="button" onclick="resert()" value="重置" style="margin-left: 520px;width: 80px;"> <input type="button" style="width: 80px;background-color: #0099CC;color: white" value="确定" onclick="submitBtn()">

    <button id="closeAddBtn" style="position: absolute;top: 0;right: 0" onclick="closeAddDiv()" >x</button>
</div>
<div id="addClassDiv" style="position: absolute;background-color: black;opacity: 0.5;display: none;z-index: 2" align="center">
</div>
<%----------------------------------------------------------------------------------------------------------------------------------%>
<div id="m">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
        <tr>
            <td nowrap class="title1">您的位置：产品分类-产品管理</td>
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
            <option value="">分类</option>
            <c:forEach items="${classno}" var="cat">
                <option value="${cat.categoryID}">${cat.name}</option>
            </c:forEach>
        </select>
        <input type="month" id="addDate">
        <input type="text" id="productName" style="width: 200px;height: 30px" placeholder="产品编号/产品名">
        <input type="button"  style="height: 30px" onmouseover="OMO(event)"   onclick="Search()" value="查询">

        <input type="text" id="tiaoPage" style="width: 40px;height: 30px" placeholder="页数">
        <input type="button" onclick="tiaoPage()" style="height: 30px" value="跳转">

        <div style="float: right">
            <input type="button" onclick="addClass()" style="height: 30px" onmouseover="OMO(event)" value="产品增加">
        </div>
     </div>
    <div style="float: left;margin-top: 10px">
        <button onclick="batchDelete()">删除选中</button>
    </div>
    <div id="dataList">
        <table id="pClass" width="100%" border="0" align="center" cellspacing="1" class="c">
            <br><br><br>
            <tr>
                <td class="title1" ><input type="checkbox" id="checkAll" onclick="checkAll()"></td>
                <td class="title1" >产品编号</td>
                <td class="title1" >产品名称</td>
                <td class="title1" >数量单位</td>
                <td class="title1" >分类名称</td>
                <td class="title1" >添加日期</td>
                <td class="title1" >销售价</td>
                <td class="title1" >描述</td>
                <td class="title1">操作</td>
            </tr>
<c:forEach items="${list}" var="pr" varStatus="no">
            <tr >
                <td align="center" style="width: 5%"><input class="checkBox" type="checkbox" onclick="checkOne()"></td>
                <td align="center" style="width: 10%"><a href="pomain_detail.html" class="proID">${pr.productCode}</a></td>
                <td align="center" style="width: 10%">${pr.name}</td>
                <td align="center" style="width: 5%" >${pr.unitName}</td>
                <td id="${pr.categoryID}" align="center" style="width: 10%" >${pr.categoryName}</td>
                <td align="center" style="width: 10%">${pr.createDate}</td>
                <td align="center" style="width: 10%">${pr.price}</td>
                <td align="center">${pr.remark}</td>
                <td align="center" style="width: 10%">
                    <a onclick="changeMsg('${pr.productCode}','${pr.name}','${pr.unitName}','${pr.categoryID}','${pr.createDate}','${pr.price}','${pr.remark}')" onmouseover="changeHand(this)" >修改</a>
                    <a onclick="deleteClass('${pr.productCode}','${pr.name}')" onmouseover="changeHand(this)" >删除</a>
                </td>
            </tr>
</c:forEach>
            <tr style="display:none"><td colspan="10" name="detail"></td></tr>
        </table>
        <div class="pageDiv" style="position: relative;bottom: 0">
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
        var url = "../stock/productPage";
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
                    var proCode = "'"+Class[i].a+"'"
                    var proName = "'"+Class[i].c+"'";
                    var unitName  = "'"+Class[i].d+"'";
                    var createDate = "'"+Class[i].f+"'";
                    var category = "'"+Class[i].b+"'";
                    var msg = "'"+Class[i].g+"'";
                    table.append("<tr>"+
                        '<td align="center" style="width: 5%"><input class="checkBox" type="checkbox" onclick="checkOne()"/></td>'+
                        '<td align="center" style="width: 10%"><a href="pomain_detail.jsp" class="proID">'+Class[i].a+'</a></td>'+
                        '<td align="center" style="width: 10%">'+Class[i].c+'</td>'+
                        '<td align="center" style="width: 5%" >'+Class[i].d+'</td>'+
                        '<td id='+category+' align="center" style="width: 10%" >'+Class[i].k+'</td>'+
                        '<td align="center" style="width: 10%">'+Class[i].f+'</td>'+
                        '<td align="center" style="width: 10%">'+Class[i].e+'</td>'+
                        '<td align="center">'+Class[i].g+'</td>'+
                        "<td align=\"center\" style=\"width:10%\">" +
                        '<a href=\"#\" onclick="changeMsg('+proCode+','+proName+','+unitName+','+category+','+createDate+','+Class[i].e+','+msg+')" onmouseover="changeHand(this)">修改</a>' + "&nbsp"+
                        '<a href=\"#\" onclick="deleteClass('+proCode+','+proName+')" onmouseover="changeHand(this)">删除</a>' +
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
        var date = $("#addDate");
        var key1 = classNo.val()+date.val()+productName.val();
        if(key1 == ""){
            window.location.href = "${pageContext.request.contextPath}/stock/productIndex";
        }else {
            var x1,x2,x3;
            if(classNo.val()==""){
                x1 = "null"
            }else {
                x1  = classNo.val();
            }
            if(date.val()==""){
                x2 = "null";
            }else {
                x2 = date.val();
            }
            if(productName.val()==""){
                x3 = "null"
            }else {
                x3  = productName.val();
            }
            var key2 = x1+" "+x2+" "+x3;
            window.location.href = "${pageContext.request.contextPath}/stock/productSearch?searchKey="+key2;
        }


    }

    function changeHand(x) {
        x.style.cursor = "pointer";
    }

    function deleteClass(proid,proname) {
       var flag = confirm("您将要删除  编号:"+proid+" 名为："+proname+ "   的产品,是否确定？");
        if(flag){
            $.ajax({
                url: "${pageContext.request.contextPath}/stock/productdelete",
                dataType: 'text',
                data: {"proid":proid},
                success: function (result) {
                    if(result == "false"){
                        alert("选择的产品中，存在正在采购/售卖中的产品，将停止删除操作");
                    }else if(result == "succeed"){
                        alert("删除成功！");
                        var num = parseInt(currentPage.text());
                        window.location.href = "../stock/productPage?page="+num+"&flag=2";
                    }else {
                        alert("失败!可能已被其他人员删除，请刷新页面");
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
        var tip = $(".tip");
        for(var i= 0;i<tip.length;i++){
            tip[i].disabled = true;
        }
        $("#addClassDiv").css({"width":"100%","height": "100%","display":"block"});
        $("#acSecondDiv").css({"min-height":"500px","min-width":"800px","width":"50%","height":"40%","display":"block","background-color":"white","opacity":"1.0"
        ,"top":"25%","left":"25%"});
    }
    //关闭 添加弹框
    function closeAddDiv(){
        $("#addClassDiv").css("display","none");
        $("#acSecondDiv").css("display","none");
        var num = parseInt(currentPage.text());
        window.location.href = "${pageContext.request.contextPath}/stock/productPage?page="+num+"&flag=2";
    }
    //重置
    function resert(){
        $("#acSecondDiv input[type='text']").val("");
        $("#acSecondDiv textarea").val("");
        $("#acSecondDiv select").val("");//通过value值，设置对应的选中项
    }

    var cid ;
    //提交信息
    function submitBtn() {
        var f1,f2,f3,f4 = true;
        var proid = $("#proID").val();//产品编号
        var proName = $("#proName").val();
        var category = $("#category").val();
        var price = $("#price").val();
        var unitName = $("#unitName").val();//数量单位
        var date = $("#date");
        var remark = $("#remark").val();
        var tips = $(".tip");

        for(var i=0;i<tips.length;i++){
            tips[i].value = ""; //每次点击按钮初始化所有tips的提示
        }
        var reg = /[A-Za-z0-9]/;
        reg.test(proid);
        if(proid == ""){
           tips[0].value = "必填";
            f1 = false;
        }else if(proid.length<4){
            tips[0].value = "4~20字符";
            f1 = false;
        }else if(!reg.test(proid)){
            tips[0].value = "字母/数字";
            f1 = false;
        } else {
            tips[0].value = "";
            f1 = true;
        }
        var reg2 = /[A-Za-z0-9\u4e00-\u9fa5]/;
        if(proName == ""){
            tips[1].value = "必填";
            f2 = false;
        }else if(!reg2.test(proName)){
            tips[1].value = "中英文数字";
            f2 = false;
        } else {
            tips[1].value = "";
            f2 = true;
        }
        if(category == ""){
            tips[2].value = "必填";
            flag = false;
        }else {
            tips[2].value = "";
            flag = true;
        }
        var reg3 = /[0-9]/;
        if(price == ""){
            tips[3].value = "必填";
            f3 = false;
        }else if(!reg3.test(price)){
            tips[3].value = "数字";
            f3 = false;
        }
        else {
            tips[3].value = "";
            f3 = true;
        }
        var reg4 = /[A-Za-z\u4e00-\u9fa5]/;
        if(unitName == ""){
            tips[4].value = "必填";
            f4 = false;
        }else if (!reg4.test(unitName)){
            tips[4].value = "中/英文";
            f4 = false;
        }
        else {
            tips[4].value = "";
            f4 = true;
        }
        if(f1 && f2 && f3 && f4 ){
            if(changeType==1){
                var d = new Date();
                date.val(d.getUTCFullYear()+"-"+(d.getUTCMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());
                $.ajax({
                    url: "${pageContext.request.contextPath}/stock/addproduct",
                    dataType:'text',
                    data:{"proid":proid,"proName":proName,"price":price,"unitName":unitName,"remark":remark,"category":category,"date":date.val()},
                    success:function (result) {
                        if(result == "alreadyID") {
                            tips[0].value = "编号已存在";
                        }
                        else if(result == "alreadyName"){
                            tips[1].value = "产品名已有";
                        } else if(result == "succeed"){
                            alert("添加成功");
                            var num = parseInt(currentPage.text());
                            changePageResult(num);
                        }else {
                            alert("error!");
                        }
                    },
                    error:function() {
                        alert("error");
                    }
                });
            }else {
                var flag2 = confirm("确定修改吗？");
                if(flag2){
                    var num2 = parseInt(currentPage.text());
                    $.ajax({
                        url: "${pageContext.request.contextPath}/stock/proupdate",
                        dataType:'text',
                        data:{"proid":proid,"proName":proName,"price":price,"unitName":unitName,"remark":remark,"category":category},
                        success:function (result) {
                            if(result == "already"){
                                alert("该产品的销售待发数/采购在途数不为零，不可修改产品名、分类和数量单位！");
                            }else if(result == "succeed"){
                                changePageResult(num2);
                                alert("修改成功!");
                                $("#addClassDiv").css("display","none");
                                $("#acSecondDiv").css("display","none");
                            }else if(result == "no"){
                                alert("不存在此产品，可能已被其他人员删除，将刷新页面!");
                                var num = parseInt(currentPage.text());
                                window.location.href = "../stock/productPage?page="+num+"&flag=2";
                            }
                            else {
                                alert("修改失败!");
                                $("#addClassDiv").css("display","none");
                                $("#acSecondDiv").css("display","none");
                            }
                        },
                        error:function () {
                            alert("error");
                        }
                    });
                }
            }
        }
    }
    //修改信息
    function changeMsg(procode,proname,produnitName,categoryid,createdate,price,remark){
        var tip = $(".tip");
        for(var i= 0;i<tip.length;i++){
            tip[i].disabled = true;
        }
        $("#addClassDiv").css({"width":"100%","height": "100%","display":"block"});
        $("#acSecondDiv").css({"min-height":"500px","min-width":"800px","width":"50%","height":"40%","display":"block","background-color":"white","opacity":"1.0"
            ,"top":"25%","left":"25%"});
        $("#proID").val(procode).attr("disabled",true);
        $("#proName").val(proname);
        console.log(categoryid);
        $("#acSecondDiv select").val(categoryid);
        $("#price").val(price);
        $("#unitName").val(produnitName);
        $("#date").val(createdate);
        $("#remark").val(remark);
        changeType = 2;

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

    //全选checkedbox事件
    function checkAll() {
        var rows = $(".checkBox");
        var checkAll = $("#checkAll");
        if (checkAll.prop("checked")) {
            for (var k = 0; k < rows.length; k++) {
                rows[k].checked = true;
            }
        } else {
            for (var j = 0; j < rows.length; j++) {
                rows[j].checked = false;
            }
        }
    }
    function checkOne() {
        var rows = $(".checkBox");
        var flag = true;
        for(var j=0;j<rows.length;j++){
            if(rows[j].checked == false){
                flag = false;
                break;
            }
        }
        console.log(flag);
        if(flag){
            $("#checkAll").prop("checked", true);
        }else {
            $("#checkAll").prop("checked", false);
        }
    }

    function batchDelete(){  //批量删除
        var rows = $(".checkBox");
        var proCode = [];
        var proid = $(".proID");
        for(var j=0;j<rows.length;j++){
            if(rows[j].checked == true){
                proCode.push(proid[j].innerText);
            }
        }
        if(proCode.length>0){
            var flag = confirm("确定删除吗？");
            if(flag){
                $.ajax({
                    url: "${pageContext.request.contextPath}/stock/batchdelete",
                    type:'post',
                    dataType:'text',
                    data:{"productCode":JSON.stringify(proCode)},
                    success:function (result) {
                        if(result == "already"){
                            alert("选择的产品中，存在正在采购/售卖中的产品，将停止删除操作");
                        }else if(result == "succeed"){
                            var num2 = parseInt(currentPage.text());
                            alert("删除成功");
                            $("#addClassDiv").css("display","none");
                            $("#acSecondDiv").css("display","none");
                            changePageResult(num2);
                            window.location.href = "../stock/productPage?page="+num2+"&flag=2";
                        }else {
                            alert("删除失败，请刷新重试");
                            var num = parseInt(currentPage.text());
                            window.location.href = "../stock/productPage?page="+num+"&flag=2";
                        }
                    },
                    error:function () {
                        alert("error");
                    }
                });
            }
        }else {
            alert("没有选择删除的产品");
        }


    }
</script>
</html>
