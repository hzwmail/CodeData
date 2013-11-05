<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <jsp:include page="/head.jsp"></jsp:include>
<script type="text/javascript">
	function validateForm(srcForm){
		var msg="";
		if(srcForm.userId.value==""){
			msg+="请填写用户ID\r\n";
		}

		var idPattern=/^[0-9]+$/;
		if(!idPattern.exec(srcForm.userId.value)){
			msg+="用户ID必须是数字\r\n";
		}
		
		if(srcForm.password.value==""){
			msg+="请填写密码\r\n";
		}
		
		var userTypeChecked=false;
		for(i=0;i<srcForm.userType.length;i++){
			if(srcForm.userType[i].checked){
				userTypeChecked=true;
				break;
			}
		}
		if(!userTypeChecked){
			msg+="请选择用户类型\r\n";
		}
		

		if(msg==""){
			return true;
		}else{
			alert(msg);
			return false;
		}
		
	}
</script>
</head>
<body>
<table width="800" height="600" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="20px"></td>
	</tr>
	<tr>
		<td class="black-bold-50-title" align="center">掌上新闻SMC平台客户端</td>
	</tr>
	<tr>
		<td align="center" class="red-tip"></td>
	</tr>
	<tr>
		<td valign="top">

            <s:form action="login" namespace="/user"  method="post">
				<table width="320" height="240" align="center" background="${contextPath }/images/index_bg.jpg">
					<tr>
						<td colspan="3" height="10"></td>
					</tr>
					<tr>
						<td align="right" class="black-bold-12-text">用户ID：</td>
						<td>
                            <s:textfield name="userId"></s:textfield>
                        </td>
                        <td>

                        </td>
					</tr>
					<tr>
						<td align="right" class="black-bold-12-text">密码：</td>
						<td>

                            <s:password name="supPassword"></s:password>
                        </td>
                        <td>

                        </td>
					</tr>
					<tr>
						<td align="right" class="black-bold-12-text">用户类型:</td>
						<td class="black-bold-12-text">
                              <s:radio name="roleId" list="roleIds" listKey="value" listValue="name"></s:radio>
						</td>
                        <td>

                        </td>
					</tr>
					<tr>
						<td colspan="2" align="center">
                            <s:submit></s:submit>

						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</s:form>
		</td>
	</tr>
</table>
</body>
</html>