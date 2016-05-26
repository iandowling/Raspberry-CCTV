<%@include file="includes/users-header.jsp"%>

<div class="user-edit">
 <div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Edit Profile</h3>
    </div>
    <div class="panel-body">
    
          <form:form modelAttribute="userEditForm" class="form-horizontal" role="form">

           <div class="form-group">
			<form:label path="name" class="col-lg-2 control-label">Name</form:label>
			<div class="col-lg-10">
				<form:input path="name" class="form-control" placeholder="Edit your profile name." />
				<form:errors cssClass="error" path="name" />
				<p class="help-block">Edit your profile.</p>
			</div>
			
			<form:label path="email" class="col-lg-2 control-label">Email</form:label>
			<div class="col-lg-10">
				<form:input path="email" class="form-control" placeholder="Edit your email." />
				<form:errors cssClass="error" path="email" />
				<p class="help-block">Edit your email.</p>
			</div>
			
			<form:label path="password" class="col-lg-2 control-label">Password</form:label>
			<div class="col-lg-10">
				<form:input path="password" type="password" class="form-control" placeholder="Update your password." />
				<form:errors cssClass="error" path="password" />
				<p class="help-block">Update your password.</p>
			</div>
			
		   </div>
            
           <div class="form-group">
               <div class="col-lg-offset-2 col-lg-10">
                   <button type="submit" class="btn btn-default">Update</button>
               </div>
           </div>
            
       </form:form>
    </div>
  </div>
</div>
<%@include file="includes/footer.jsp"%>
	