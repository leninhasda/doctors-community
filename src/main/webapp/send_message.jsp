<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Doctor's Community</title>
</head>
<body>
<form action="send_msg" method="post">

  Send message to:<select name="to_user_id">
  <s:iterator value="messages">
    <option value=<s:property value="to_user_id"/>><s:property value="user_name"/></option>
  </s:iterator>
</select><br />
  Message <input type="text" name="msg"><br>

  <input type="submit" value="Submit">

</form>

</body>
</html>