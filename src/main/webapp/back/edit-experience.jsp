<%@ page import="java.util.Vector" %>
<%@ page import="com.jonak.model.User" %>
<%@ page import="com.jonak.controller.UserController" %>
<%@ include file="./template/_header.jsp" %>
<%
	String update = Tools.get("update");
%>


<div class="row profile">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Edit Experience
			</div>
			<div class="panel-body">

				<% if (update != null && update.equals("true")) { %>
				<p class="alert alert-success">Updated successfully!</p>
				<% } %>
				<% if (update != null && update.equals("false")) { %>
				<p class="alert alert-danger">Error Updating!</p>
				<% } %>
				<s:iterator value="dataOut">
					<form class="form-horizontal" role="form" method="post" action="processexperience">

						<div class="form-group">
							<label for="inputName" class="col-sm-2 control-label">Title</label>

							<div class="col-sm-4">
								<input type="text" class="form-control" id="inputName" placeholder="Title" name="title"
									   value="<s:property value="title"/>">
							</div>
						</div>
						<div class="form-group">
							<label for="inputAddress" class="col-sm-2 control-label">Description</label>

							<div class="col-sm-4">
								<textarea class="form-control" id="inputAddress" placeholder="Description" rows="3"
										  name="description"><s:property value="description"/></textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-4">
								<button type="submit" class="btn btn-primary">Save Changes</button>
							</div>
						</div>
					</form>
				</s:iterator>
			</div>
		</div>
	</div>
</div>

<%@include file="./template/_footer.jsp" %>