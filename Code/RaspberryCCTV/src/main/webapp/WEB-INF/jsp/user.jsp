<%@include file="includes/users-header.jsp"%>	

<div class="user">
  <div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Edit Profile</h3>
    </div>
    <div class="panel-body">
        <dl class="dl-horizontal">
            <dt>Name</dt>
            <dd><c:out value="${user.name}" /></dd>
            <dt>Email</dt>
            <dd><c:out value="${user.email}" /></dd>
            <dt>Roles</dt>
            <dd><c:out value="${user.roles}" /></dd>
        </dl>
    </div>
	<c:if test="${user.editable}">
	    <div class="panel-footer">
	
	        <a class="btn btn-link" href="/RaspberryCCTV/users/${user.id}/edit">Edit</a>
	        <a class="btn btn-link" href="/RaspberryCCTV/users/${user.id}/delete">Delete user</a>
	
	    </div>
	</c:if>
  </div>
 </div>

<%@include file="includes/footer.jsp"%>