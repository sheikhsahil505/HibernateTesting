
<%@ include file="header.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar with Logo and Login/Signup</title>
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap.css">

<style>

</style
</head>
<body>
<form action="/JspServlet/forgetPassword" method="post">
   <section class=" gradient-custom">
     <div class="container py-2">
       <div class="row d-flex justify-content-center align-items-center h-100">
         <div class="col-12 col-md-8 col-lg-6 col-xl-5">
           <div class="card bg-dark text-white" style="border-radius: 1rem;">
             <div class="card-body p-3 text-center">

               <div class=" ">

                 <h2 class="fw-bold mb-2 text-uppercase">Forget Password</h2>
                 <p class="text-white-50 mb-5">Please enter your email and Date of Birth !</p>

                 <div class="form-outline form-white mb-4">
                   <input type="email" id="typeEmailX" name="email" class="form-control form-control-lg" required/>
                   <label class="form-label" for="typeEmailX">Email</label>
                 </div>

                 <div class="form-outline form-white mb-4">
                   <input type="date" id="date" name="dob" class="form-control form-control-lg" required/>
                   <label class="form-label" for="typePasswordX">Date Of Birth</label>
                 </div>

                <h6 class="text-center" style="color: red">  <%
                 if(request.getAttribute("error")!=null){
                             out.println(request.getAttribute("error"));
                             }
                             %></h6>

 <p class="mb-0"> Don't have an account? <a href="/jsp/index.jsp" class="text-white fw-bold">Login</a>
                 </p>

                 <button class="btn btn-outline-light btn-lg px-5" type="submit" >Generate New Password</button>


               </div>


             </div>
           </div>
         </div>
       </div>
     </div>
   </section>
</form>
</body>
</html>
<%@ include file="footer.jsp"%>