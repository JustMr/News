<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教育智慧党建网管理平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style-dangjian.css">
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		function editsubmit() {
			var id = $("#num_ar").text().trim();
			var tit = $("#tit_ar").val().trim();
			var main = $(window.frames["fulltextedit"].document).find("#textarea").val();	
			
			$.ajax({
				url: "article/EditArticle.do",
				data: {
					id:id,
					title: tit,
					main: main,
				},
				type: "post",
				dataType: "json",
				success: function(data) {	
					if(data=="1") {
						alert("修改成功!");
					}else {
						alert("修改失败!");
					}					
				},
				error: function() {
					alert("something wrong!");
				}
			});
		}
		function editShow(int) {
			var tr = $(".listArticle tr").eq(int);
			var id = tr.find("td:eq(0)").text();
			var title = tr.find("td:eq(1)").text();
			var main = tr.find("td:eq(2)").html();
			
			$("#num_ar").text(id);
			$("#tit_ar").val(title);
			var url = "fulltext.jsp?text="+main;
			$("#fulltextedit").attr("src",url);		
			
			$("#aredit").show();
			$("#aradd").hide();
			$("#arlist").hide();
		}
		function editArticle() {
			$.ajax({
				url:"article/EditArticle.do",
				type:"post",
				data: {id:id},
				dataType:"json",
				success:function(data) {
					if(data=="1") {
						alert("删除成功!");
					}else {
						alert("删除失败!");
					}
					show1();
				},
				error:function() {
					alert("删除失败!");
				}
			});
		}
		function deleteArticle(id) {
			$.ajax({
				url:"article/DeleteArticle.do",
				type:"post",
				data: {id:id},
				dataType:"json",
				success:function(data) {
					if(data=="1") {
						alert("删除成功!");
					}else {
						alert("删除失败!");
					}
					show1();
				},
				error:function() {
					alert("删除失败!");
				}
			});
		}
		function show1() {
			$("#aredit").hide();
			$("#aradd").hide();
			$("#arlist").show();
			$.ajax({
				url:"article/ListArticle.do",
				type:"post",
				dataType:"json",
				success:function(data) {
					$(".listArticle").empty();
					console.log(data);
					for ( var int = 0; int < data.length; int++) {
						var $tr = "<tr><td>"+data[int].id+"</td>"
						+"<td>"+data[int].title+"</td>"
						+"<td>"+data[int].main+"</td>"
						+"<td>"+data[int].uid+"</td>"
						+"<td><a href='javascript:void(0);' onclick='editShow("+int+")'>编辑</a><a href='javascript:void(0);' onclick='deleteArticle("+data[int].id+")'>删除</a></td></tr>";
						$(".listArticle").append($tr);
					}
				},
				error:function() {
					alert("获取列表失败!");
				}
			});
		}
		function show2() {
			$("#aredit").hide();
			$("#aradd").show();
			$("#arlist").hide();
		}
		$(function(){
			$("#listit").on("click",show1);
			$("#addtit").on("click",show2);	
			$("#pubtn").click(function(){
				var arTitle = $("#arTitle").val();
				var mainText = $(window.frames["fulltext"].document).find("#textarea").val();
				
				var flag=0;
				if (arTitle==""||arTitle==null||mainText==""||mainText==null) {
					flag=1;
					alert("请填写标题或正文保持文章完整!");
				}
				
				if (flag==0) {
					$.ajax({
						url: "addarticle",
						data: {
							arTitle: arTitle,
							arMain: mainText,
						},
						type: "post",
						dataType: "json",
						success: function(data) {	
							if(data=="1") {
								alert("发表成功！");
							}else {
								alert("发表失败");
							}					
						},
						error: function() {
							alert("something wrong!");
						}
					});
				}
			});
			$("#editbtn").on("click",editsubmit);
		});
	</script>

  </head>
  
  <body>
    <div class="continer">
    	<h1>教育智慧党建网管理平台</h1>
    	<div class="mhleft">
    		<ul>
    			<li id="listit">文章列表</li>
    			<li id="addtit">文章添加</li>
    		</ul>
    	</div>
    	<div class="mhright">
    		<div class='aright' id="aredit">
    			<table>
    				<caption>文章修改</caption>	
    				<tbody>
    					<tr>
    						<td>编号：</td>
    						<td id="num_ar"></td>
    					</tr>
    					<tr>
    						<td>标题：</td>
    						<td>
    							<input id="tit_ar" name="titar" type="text" />
    						</td>
    					</tr>
    					<tr>
    						<td valign="top">正文：</td>
    						<td id="main_ar">
    							<iframe id="fulltextedit" name="fulltextedit" scrolling="no" src="fulltext.jsp"></iframe>
    						</td>
    					</tr>
    					<tr>
    						<td colspan="2"><button id="editbtn">修改</button> </td>
    					</tr>
    				</tbody>
    			</table>
    		</div>
    		<div class="aright" id="arlist">
    			<table>
    				<caption>文章列表</caption>
					<thead>
						<tr>
							<th>编号</th>
							<th>标题</th>
							<th>正文</th>
							<th>作者</th>
							<th>编辑</th>
						</tr>
					</thead>
					<tbody class="listArticle">
					</tbody>
    			</table>
    		</div>
    		<div class="aright" id="aradd">
    			<form action="addarticle" method="post">
    				<div class="firstline">
		    			<label class="firTil">标题:</label>
		    			<input id="arTitle" name="arTitle" type="text" autocomplete="off" />
		    			<a id="pubtn" class="upBrBtn pubArcBtn" href="javascript:void(0);">发表</a>
		    		</div>
		    		<div class="mainBody">
		    			<iframe id="fulltext" name="fulltext" scrolling="no" src="fulltext.jsp"></iframe>
		    		</div>
    			</form>
    		</div>
    	</div>
    </div>
  </body>
</html>
