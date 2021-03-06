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
				Edit Board
			</div>
			<div class="panel-body">

				<% if (update != null && update.equals("true")) { %>
				<p class="alert alert-success">Updated successfully!</p>
				<% } %>
				<% if (update != null && update.equals("false")) { %>
				<p class="alert alert-danger">Error Updating!</p>
				<% } %>
				<s:iterator value="dataOut">
					<form class="form-horizontal" role="form" method="post"
						  action="processboard">

						<input type="hidden" name="id" value="<% out.print( Tools.toInt(Tools.get("id")) ); %>">
						<input type="hidden" name="user_id" value="<% out.print( SessionLib.getUserID() ); %>">
						<input type="hidden" name="type" value="1">
						<input type="hidden" name="parent_id" value="0">

						<div class="form-group">
							<label for="inputCategory" class="col-sm-2 control-label">Category</label>
							<select class="form-control" id="inputCategory" placeholder="Category" name="categoryId">
								<option value=<s:property value="categoryId"/>><s:property
										value="category_name"/></option>
								<s:iterator value="output">
									<option value=<s:property value="id"/>><s:property value="name"/></option>
								</s:iterator>
							</select>
						</div>
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
							<label for="inputTelephone" class="col-sm-2 control-label">Publish Board</label>

							<div class="col-sm-4">
								<select class="form-control" id="inputTelephone" placeholder="Privacy" name="privacy">
									<option value="1">Private</option>
									<option value="2">Public</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="inputComment" class="col-sm-2 control-label">Allow Comments?</label>

							<div class="col-sm-4">
								<select class="form-control" id="inputComment" placeholder="Privacy" name="allowComment">
									<option value="1">Yes</option>
									<option value="0">No</option>
								</select>
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