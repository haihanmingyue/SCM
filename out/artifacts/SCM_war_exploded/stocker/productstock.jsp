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
    <title>库管理-库存查询</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="${pageContext.request.contextPath}/resource/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/script/common.js" ></script>
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
        a:hover{
            cursor: pointer;
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

    产品编号：<span id="stoProCode"></span> | 产品名：<span id="stoProName"></span></label>
    <button id="closeAddBtn" style="position: absolute;top: 0;right: 0" onclick="closeAddDiv()" >x</button>


    <div style="margin-top: 50px" >
        <div style="float: left">
            <input type="date" id="stockDate">

            <select id="stockType" style="height: 30px">
                <option value="">类型</option>
            </select>

            <input type="text" id="stockKey">

            <button onclick="stockSearch()">搜索</button>


            <input type="text" id="tiaoPage" placeholder="页数" style="width:40px;margin-left:260px" >
            <button onclick="tiaoPage()" >跳转</button>

        </div>
    <table id="stockRecord" width="100%" border="0"  cellspacing="1" class="c" style="text-align: center">
        <tr>
            <td class="title1" >时间</td>
            <td class="title1" >相关单号</td>
            <td class="title1" >经手人</td>
            <td class="title1" >数量</td>
            <td class="title1" >类型</td>
        </tr>
        <tr>
            <td >无信息</td>
            <td >无信息</td>
            <td >无信息</td>
            <td >无信息</td>
            <td >无信息</td>
        </tr>
    </table>
    </div>

    <div  style="position: absolute;bottom:100px;width: 100%;text-align: center" >
        当前第 <span id="stoCurrPage">1</span> 页

        <input type="button" id="recordFirstBtn" value="首页" onclick="recordFirstPage()" />
        <input type="button" id="recordPreBtn" value="上一页" onclick="recordPrePage()" />
        <input type="button" id="recordNextBtn" value="下一页" onclick="recordNextPage()" />
        <input type="button" id="recordLastBtn" value="末页" onclick="recordLastPage()" />

            共<span id="stoTotal"></span>页 共 <span id="stoRecoder"></span> 条记录
    </div>
</div>
<div id="addClassDiv" style="position: absolute;background-color: black;opacity: 0.5;display: none;z-index: 2" align="center">
</div>
<%----------------------------------------------------------------------------------------------------------------------------------%>
<div id="m">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
        <tr>
            <td nowrap class="title1">您的位置：出库管理-库存查询</td>
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

        <input type="text" id="searchkey" style="width: 200px;height: 30px" placeholder="产品编号/产品名">
        库存数量范围：<input type="text" id="startNum" value="0" style="width: 50px;text-align: center">
        -
        <input type="text"  id="endNum" style="width: 50px;text-align: center">
        <input type="button"  style="height: 30px" onmouseover="OMO(event)"   onclick="Search()" value="查询">
        <input type="text" id="proTiaoPage" placeholder="页数" style="width:40px;" >
        <button onclick="proTiaoPage()" >跳转</button>
     </div>
    <div id="dataList">
        <table  id="pClass" width="100%" border="0" cellspacing="1" class="c" style="text-align: center">
            <br><br><br>
            <tr>
                <td class="title1" >产品编号</td>
                <td class="title1" >产品名称</td>
                <td class="title1" >库存数量</td>
                <td class="title1" >采购在途数</td>
                <td class="title1" >预销售数</td>
                <td class="title1">查看</td>
            </tr>
<c:forEach items="${list}" var="pr" varStatus="no">
            <tr >
                <td  style="width: 10%">${pr.productCode}</td>
                <td  style="width: 10%">${pr.name}</td>
                <td  style="width: 5%" >${pr.num}</td>
                <td  style="width: 10%" >${pr.pONum}</td>
                <td  style="width: 10%">${pr.sONum}</td>
                <td  style="width: 10%">
                    <a onclick="lookUp('${pr.productCode}','${pr.name}')" onmouseover="changeHand(this)" >库存变化记录</a>
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
        var url = "${pageContext.request.contextPath}/stock/kucunpage";
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

                    var proCode = "'"+Class[i].a+"'";
                    var proName = "'"+Class[i].c+"'";
                    table.append("<tr>"+
                        '<td  style="width: 10%">'+Class[i].a+'</td>'+
                        '<td  style="width: 10%">'+Class[i].c+'</td>'+
                        '<td  style="width: 10%" >'+Class[i].h+'</td>'+
                        '<td  style="width: 10%" >'+Class[i].i+'</td>'+
                        '<td  style="width: 10%">'+Class[i].j+'</td>'+
                        "<td  style=\"width:10%\">" +
                        '<a href=\"#\" onclick="lookUp('+proCode+',\''+Class[i].c+'\')" onmouseover="changeHand(this)">库存变化记录</a>' +
                        "</td>" +
                        "</tr>")
                }
            },
            error:function () {
              alert("error")
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
        var searchkey = $("#searchkey");
        var startNum = $("#startNum");
        var endNum = $("#endNum");
        var reg = /[0-9]/;
        if(startNum.val() == ""){
            startNum.val(0) ;
        }
        if(reg.test(startNum.val()) && (reg.test(endNum.val()) || endNum.val()=="")){
            var key1 = searchkey.val()+startNum.val()+endNum.val();
            console.log(key1);
            if(key1 == "0") {
                window.location.href = "../stock/kucunindex";
            }else {
                window.location.href = "../stock/kucunsearch?searchkey="+searchkey.val()+"&start="+startNum.val()+"&end="+endNum.val();
            }
        }else {
            alert("库存数量范围只允许输入数字")
        }

    }

    function changeHand(x) {
        x.style.cursor = "pointer";
    }
    
  function recordNextPage() {
        var stoCurrPage =  $("#stoCurrPage");
        var currpage = parseInt(stoCurrPage.text().trim());
        currpage = currpage+1;
        stoCurrPage.text(currpage);
        if(stoCurrPage.text().trim() == $("#stoTotal").text().trim()){
           $("#recordLastBtn").attr("disabled",true);
           $("#recordNextBtn").attr("disabled",true);
        }
        $("#recordPreBtn").attr("disabled",false);
        $("#recordFirstBtn").attr("disabled",false);
        changeRecordPage(stoCurrPage.text(),$("#stoProCode").text());
  }
    function recordPrePage() {
        var stoCurrPage =  $("#stoCurrPage");
        var currpage = parseInt(stoCurrPage.text().trim());
        currpage = currpage-1;
        stoCurrPage.text(currpage);
        if(currpage == "1"){
            $("#recordPreBtn").attr("disabled",true);
            $("#recordFirstBtn").attr("disabled",true);
        }
        $("#recordLastBtn").attr("disabled",false);
        $("#recordNextBtn").attr("disabled",false);
        changeRecordPage(stoCurrPage.text(),$("#stoProCode").text());
    }
    function recordFirstPage() {
        var stoCurrPage =  $("#stoCurrPage").text(1);
        $("#recordPreBtn").attr("disabled",true);
        $("#recordFirstBtn").attr("disabled",true);
        $("#recordLastBtn").attr("disabled",false);
        $("#recordNextBtn").attr("disabled",false);

        changeRecordPage(stoCurrPage.text(),$("#stoProCode").text());
    }
    function recordLastPage() {
        var stoCurrPage =  $("#stoCurrPage").text($("#stoTotal").text().trim());
        $("#recordPreBtn").attr("disabled",false);
        $("#recordFirstBtn").attr("disabled",false);
        $("#recordLastBtn").attr("disabled",true);
        $("#recordNextBtn").attr("disabled",true);
        changeRecordPage(stoCurrPage.text(),$("#stoProCode").text());
    }

    //库存变化记录 翻页
    function changeRecordPage(num,proCode){
        var stoCurrPage =  $("#stoCurrPage")
        var date = $("#stockDate").val();
        var type = $("#stockType").val();
        var key  = $("#stockKey").val();
        var url = "${pageContext.request.contextPath}/stock/recordpage";
        var table = $("#stockRecord");
        console.log(num);
        $.ajax({
            url: url,
            dataType:'json',
            data:{"page":num,"productCode":proCode,"date":date,"type":type,"key":key},
            success:function (result) {
                console.log(result);
                var record = result;
                $('#stockRecord tr:not(:first)').remove();
                for (var i = 0; i < result.length; i++) {
                    // js中onclick中文参数传输方式
                    // 添加单引号或双引号即可，例
                    table.append("<tr>" +
                        '<td  style="width: 10%">'+record[i].f+'</td>' +
                        '<td  style="width: 10%"><a onclick="gotoDetail('+record[i].c+')">'+record[i].c+'</a></td>' +
                        '<td  style="width: 10%" >' + record[i].g + '</td>' +
                        '<td  style="width: 10%" >' + record[i].d + '</td>' +
                        '<td  style="width: 10%"><span id="ttype">'+record[i].e+'</span></td>' +
                        "</tr>")
                }
                //额外拼接在json末尾的数据，在前台获取后，处于数据末尾的那个数据里
                $("#stoTotal").text(record[record.length - 1].allPage);
                $("#stoRecoder").text(record[record.length - 1].totalNum);

                if (stoCurrPage.text().trim() == "1") {
                    $("#recordPreBtn").attr("disabled", true);
                    $("#recordFirstBtn").attr("disabled", true);
                    $("#recordLastBtn").attr("disabled", false);
                    $("#recordNextBtn").attr("disabled", false);
                }
                if (stoCurrPage.text().trim() == $("#stoTotal").text().trim()) {
                    $("#recordLastBtn").attr("disabled", true);
                    $("#recordNextBtn").attr("disabled", true);
                }

            },
            error:function () {
                alert("error")
            }
        });
    }

    //弹出
    function lookUp(proCode,proName) {
        $("#stoCurrPage").text(1);

        $("#stockType").append("<option value='1'>采购入库</option>" +
            "<option value='3'>盘点入库</option><option value='2'>销售出库</option><option value='4'>盘点出库</option>");
        $("#stoProCode").text(proCode);
        $("#stoProName").text(proName);
        $("#addClassDiv").css({"width":"100%","height": "100%","display":"block"});
        $("#acSecondDiv").css({"min-height":"600px","min-width":"800px","width":"50%","height":"40%","display":"block","background-color":"white","opacity":"1.0"
        ,"top":"15%","left":"25%"});

        var url = "${pageContext.request.contextPath}/stock/rukurecord";
        var table = $("#stockRecord");
        $.ajax({
            url: url,
            dataType: 'json',
            data:{"productCode":proCode},
            success:function (result) {
                console.log(result);
                    var record = result;
                    $('#stockRecord tr:not(:first)').remove();
                    for (var i = 0; i < result.length; i++) {
                        // js中onclick中文参数传输方式
                        // 添加单引号或双引号即可，例
                        var str = "'"+record[i].e+"'";
                        table.append("<tr>" +
                            '<td  style="width: 10%">' + record[i].f + '</td>' +
                            '<td  style="width: 10%"><a onclick="gotoDetail('+record[i].c+','+str+')">'+record[i].c+'</a></td>' +
                            '<td  style="width: 10%" >' + record[i].g + '</td>' +
                            '<td  style="width: 10%" >' + record[i].d + '</td>' +
                            '<td  style="width: 10%"><span id="ttype">'+record[i].e+'</span></td>' +
                            "</tr>")
                    }
                    //额外拼接在json末尾的数据，在前台获取后，处于数据末尾的那个数据里
                    $("#stoTotal").text(record[record.length - 1].allPage);

                    $("#stoRecoder").text(record[record.length - 1].totalNum);

                    var stoCurrPage = $("#stoCurrPage");

                    if (stoCurrPage.text().trim() == "1") {
                        $("#recordPreBtn").attr("disabled", true);
                        $("#recordFirstBtn").attr("disabled", true);
                        $("#recordLastBtn").attr("disabled", false);
                        $("#recordNextBtn").attr("disabled", false);
                    }
                    if (stoCurrPage.text().trim() == $("#stoTotal").text().trim()) {
                        $("#recordLastBtn").attr("disabled", true);
                        $("#recordNextBtn").attr("disabled", true);
                    }

            },
            error:function () {
                alert("error")
            }
        });

    }

    function stockSearch() {
        var stoProCode = $("#stoProCode").text().trim();
        console.log(stoProCode);
        var date = $("#stockDate").val();
        var type = $("#stockType").val();
        var key  = $("#stockKey").val();
        console.log(date+type+key);
        //如果查询为空时
        var table = $("#stockRecord");
        var  stoTotal =  $("#stoTotal");
        var stoRecoder = $("#stoRecoder");
        if(date+type+key == ""){
            var url = "${pageContext.request.contextPath}/stock/rukurecord";

            $.ajax({
                url: url,
                dataType: 'json',
                data:{"productCode":stoProCode},
                success:function (result) {
                    console.log(result);
                    var record = result;
                    $('#stockRecord tr:not(:first)').remove();
                    for (var i = 0; i < result.length; i++) {
                        // js中onclick中文参数传输方式
                        // 添加单引号或双引号即可，例
                        var str = "'"+record[i].e+"'";
                        table.append("<tr>" +
                            '<td  style="width: 10%">' + record[i].f + '</td>' +
                            '<td  style="width: 10%"><a onclick="gotoDetail('+record[i].c+','+str+')">'+record[i].c+'</a></td>' +
                            '<td  style="width: 10%" >' + record[i].g + '</td>' +
                            '<td  style="width: 10%" >' + record[i].d + '</td>' +
                            '<td  style="width: 10%">'+record[i].e+'</td>' +
                            "</tr>")
                    }
                    //额外拼接在json末尾的数据，在前台获取后，处于数据末尾的那个数据里
                    stoTotal.text(record[record.length - 1].allPage);
                    stoRecoder.text(record[record.length - 1].totalNum);
                    var stoCurrPage = $("#stoCurrPage").text(1);
                    if (stoCurrPage.text().trim() == "1") {
                        $("#recordPreBtn").attr("disabled", true);
                        $("#recordFirstBtn").attr("disabled", true);
                        $("#recordLastBtn").attr("disabled", false);
                        $("#recordNextBtn").attr("disabled", false);
                    }
                    if (stoCurrPage.text().trim() == $("#stoTotal").text().trim()) {
                        $("#recordLastBtn").attr("disabled", true);
                        $("#recordNextBtn").attr("disabled", true);
                    }

                },
                error:function () {
                    alert("error")
                }
            });
        }else {
            var url2 = "${pageContext.request.contextPath}/stock/recordsearch";
            $.ajax({
                url: url2,
                dataType: 'json',
                data:{"productCode":stoProCode,"date":date,"type":type,"key":key},
                success:function (result) {
                    console.log(result);
                    var record = result;
                    $('#stockRecord tr:not(:first)').remove();
                    for (var i = 0; i < result.length; i++) {
                        // js中onclick中文参数传输方式
                        // 添加单引号或双引号即可，例
                        var str = "'"+record[i].e+"'";
                        table.append("<tr>"+
                            '<td  style="width: 10%">'+record[i].f +'</td>'+
                            '<td  style="width: 10%"><a onclick="gotoDetail('+record[i].c+','+str+')">'+record[i].c +'</a></td>'+
                            '<td  style="width: 10%" >'+record[i].g+'</td>'+
                            '<td  style="width: 10%" >'+record[i].d+'</td>'+
                            '<td  style="width: 10%">'+record[i].e+'</td>'+
                            "</tr>")
                    }
                    //额外拼接在json末尾的数据，在前台获取后，处于数据末尾的那个数据里
                    stoTotal.text(record[record.length - 1].allPage);
                    stoRecoder.text(record[record.length - 1].totalNum);
                    var stoCurrPage = $("#stoCurrPage").text(1);
                    if (stoCurrPage.text().trim() == "1") {
                        $("#recordPreBtn").attr("disabled", true);
                        $("#recordFirstBtn").attr("disabled", true);
                        $("#recordLastBtn").attr("disabled", false);
                        $("#recordNextBtn").attr("disabled", false);
                    }
                    if (stoCurrPage.text().trim() == $("#stoTotal").text().trim()) {
                        $("#recordLastBtn").attr("disabled", true);
                        $("#recordNextBtn").attr("disabled", true);
                    }

                },
                error:function () {
                    alert("error")
                }
            });

        }

    }
    //关闭 添加弹框
    function closeAddDiv(){
        $("#addClassDiv").css("display","none");
        $("#acSecondDiv").css("display","none");
        $("#tiaoPage").val("");
        $("#stockType option:not(:first)").remove();
        $('#stockRecord tr:not(:first)').remove();
        var table = $("#stockRecord");
        table.append("<tr>" +
            '<td  style="width: 10%">无信息</td>' +
            '<td  style="width: 10%" >无信息</td>' +
            '<td  style="width: 10%" >无信息</td>' +
            '<td  style="width: 10%" >无信息</td>' +
            '<td  style="width: 10%">无信息</td>' +
            "</tr>")
    }

    //库存变化记录页面跳转
    function tiaoPage() {
        var tiaoPage = $("#tiaoPage").val();
        if( tiaoPage.trim()=="" ||parseInt(tiaoPage.trim()) <1 || parseInt(tiaoPage.trim())> parseInt($("#stoTotal").text().trim())){
            alert("请输入正确的页数")
        }else {
            changeRecordPage(tiaoPage,$("#stoProCode").text())
        }
    }

    //库存查询跳转
    function proTiaoPage() {
        var proTiaoPage = $("#proTiaoPage").val();
        if(proTiaoPage==""|| parseInt(proTiaoPage.trim()) <1 || parseInt(proTiaoPage.trim())> parseInt($("#totalPage").text().trim())){
            alert("请输入正确的页数")
        }else {
            changePageResult(proTiaoPage.trim());
            currentPage.text(proTiaoPage.trim());
            buttonDisabled();
        }
    }
    function gotoDetail(poid,str) {
       console.log(poid+":"+str);
       console.log(str.indexOf("入库"));
        if(str.indexOf("入库")>0){
            window.location.href ="${pageContext.request.contextPath}/poamin/poamindetail?poid="+poid;
        }else {
            window.location.href ="${pageContext.request.contextPath}/soamin/soamindetail?poid="+poid;
        }


    }
</script>
</html>
