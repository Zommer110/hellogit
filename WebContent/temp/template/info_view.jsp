<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td>제품번호</td> <td>XXXX</td>
</tr>
<tr> <td>가격</td> <td>10,000원</td> </tr>
</table>
<jsp:include page="infoSub.jsp" flush="false">
	<jsp:param value="B" name="type"/>
</jsp:include>