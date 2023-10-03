
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Navbar with Logo and Login/Signup</title>
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap.css">


</head>
<body>

<form action="generateNewPassword" method="post">
   <section class=" gradient-custom">
     <div class="container py-2">
       <div class="row d-flex justify-content-center align-items-center h-100">
         <div class="col-12 col-md-8 col-lg-6 col-xl-5">
           <div class="card bg-dark text-white" style="border-radius: 1rem;">
             <div class="card-body p-3 text-center">

               <div class=" ">

                 <h2 class="fw-bold mb-2 text-uppercase">Create New Password</h2>
                 <p class="text-white-50 mb-5">Please enter your email and Date of Birth !</p>
                <label for="password" class="pt-3"> Password:</label>
                 <input type="password" name="pass" id="password" class="form-control" required>
                   <h5 id="passcheck" style="color: red;">
                        Please Fill the password  </h5>  </div>
                             <div class="form-group">
                       <label for="conpassword" class="pb-3">  Confirm Password: </label>
                        <input type="password" name="password" id="conpassword" class="form-control" required>
                           <h5 id="conpasscheck" style="color: red;">  Password didn't match </h5>


                 <h6 class="text-center" style="color: red">
                 <% if (request.getAttribute("Done") != null) {
                   out.println(request.getAttribute("Done")); } %>
                   </h6>
  <div>
                 <p class="mb-0"> Don't have an account? <a href="index.jsp" class="text-white fw-bold">Login</a>
                 </p>
               </div>
                   <br>
                 <button class="btn btn-light " type="submit" >Generate New Password</button>

               </div>


             </div>
           </div>
         </div>
       </div>
     </div>
   </section>
</form>
<script>





//password
 document.getElementById('passcheck').style.display = 'none';
 let passwordError = true;

 document.getElementById('password').addEventListener('keyup', function () {
   validatePassword();
 });

 function validatePassword() {
   let passwordValue = document.getElementById('password').value;
   let passcheck = document.getElementById('passcheck');
   if (passwordValue.length === 0) {
     passcheck.style.display = 'block';
     passcheck.innerHTML = 'Please enter a password';
     passcheck.style.color = 'red';
     passwordError = false;
     return false;
   }
   if (passwordValue.length < 3 || passwordValue.length > 20) {
     passcheck.style.display = 'block';
     passcheck.innerHTML = 'The password length must be between 3 and 20';
     passcheck.style.color = 'red';
     passwordError = false;
     return false;
   }

   // Regular expressions
   let specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
   let digitRegex = /\d/;
   let capitalLetterRegex = /[A-Z]/;

   if (
     !specialCharRegex.test(passwordValue) ||
     !digitRegex.test(passwordValue) ||
     !capitalLetterRegex.test(passwordValue)
   ) {
     passcheck.style.display = 'block';
     passcheck.innerHTML = 'The password must include a special character, a digit, and a capital letter';
     passcheck.style.color = 'red';
     passwordError = false;
     return false;
   } else {
     passcheck.style.display = 'none';
     passwordError = true;
   }
 }
 document.getElementById('conpasscheck').style.display = 'none';
  let confirmPasswordError = true;

  document.getElementById('conpassword').addEventListener('keyup', function () {
    validateConfirmPassword();
  });

  function validateConfirmPassword() {
    let confirmPasswordValue = document.getElementById('conpassword').value;
    let passwordValue = document.getElementById('password').value;
    let conpasscheck = document.getElementById('conpasscheck');
    if (passwordValue !== confirmPasswordValue) {
      conpasscheck.style.display = 'block';
      conpasscheck.innerHTML = 'Passwords do not match';
      conpasscheck.style.color = 'red';
      confirmPasswordError = false;
      return false;
    } else {
      conpasscheck.style.display = 'none';
      confirmPasswordError = true;
    }
  }


</script>
</body>
</html>
<%@ include file="footer.jsp"%>