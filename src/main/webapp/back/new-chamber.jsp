<%@ page import="java.util.Vector" %>
<%@ page import="com.jonak.model.User" %>
<%@ page import="com.jonak.controller.UserController" %>
<%@ include file="./template/_header.jsp" %>
<%
    String  update = Tools.get("update");
%>


<div class="row profile">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Add New Chamber
            </div>
            <div class="panel-body">

                <form class="form-horizontal" role="form" method="post" action="saveprofile">
                    <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control" id="inputName" placeholder="Email" name="" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputAddress" class="col-sm-2 control-label">Address</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" id="inputAddress" placeholder="Address" rows="3" name=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTelephone" class="col-sm-2 control-label">Telephone</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputTelephone" placeholder="Telephone" name="" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputVisitHour" class="col-sm-2 control-label">Visiting Hour</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputVisitHour" placeholder="12pm-3pm" name="" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputVisitDays" class="col-sm-2 control-label">Visiting Days</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputVisitDays" placeholder="Monday, Friday" name="" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFees" class="col-sm-2 control-label">Fees</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputFees" placeholder="500/=" name="" >
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="./template/_footer.jsp" %>