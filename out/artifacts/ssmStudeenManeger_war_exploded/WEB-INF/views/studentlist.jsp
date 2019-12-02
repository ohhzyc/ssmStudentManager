<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/8
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

	<meta charset="UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
        var clazzList = ${clazzListJson};
        $(function() {
            var table;

            //datagrid初始化
            $('#dataList').datagrid({
                title:'学生列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"getStudentList?t="+new Date().getTime(),
                idField:'id',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'id',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'id',title:'ID',width:50, sortable: true},
                    {field:'photo',title:'头像',width:100,
                        formatter:function(value,index,row){
                            return '<img src='+value+' width="100px" />';
                        }
                    },
                    {field:'username',title:'姓名',width:150, sortable: true},
                    {field:'sn',title:'学号',width:150, sortable: true},
                    {field:'sex',title:'性别',width:150, sortable: true},
                    {field:'clazzId',title:'所属班级',width:150, sortable: true,
                        formatter:function(value,index,row){
                            for(var i=0;i<clazzList.length;i++){
                                if(clazzList[i].id == value){
                                    return clazzList[i].name;
                                }
                            }
                            return value;
                        }
                    },
                    {field:'password',title:'密码',width:150},
                    {field:'remark',title:'备注',width:200},
                ]],
                toolbar: "#toolbar"
            })
	</script>
</head>
<body>
<div id="toolbar">
	<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
	<div style="float: left;" class="datagrid-btn-separator"></div>
	<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
	<div style="float: left;" class="datagrid-btn-separator"></div>
	<div>
		<a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
		用户名：<input id="search-username" class="easyui-textbox" />
		<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
	</div>
</div>

</body>
</html>
