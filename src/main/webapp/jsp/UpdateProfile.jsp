
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp"%>


<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap.css">
    <style>
        /* Custom CSS for additional styling */
        .profile-picture-container {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .profile-picture {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            border: 2px solid #007bff;
        }

        .profile-card {
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            margin-top: 20px;
        }

        .profile-name {
            font-size: 1.25rem;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .profile-info {
            font-size: 0.9rem;
            color: #777;
        }



         .card {
                margin-bottom: 30px;
            }



            h1 {
                text-align: center;
                margin-top: 40px;
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"], input[type="email"] {

                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            button[type="button"] {


                border: none;
                padding: 8px 16px;
                margin-bottom: 10px;
                border-radius: 4px;
                cursor: pointer;
            }

            .address {
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 4px;
            }

            .address label {
                margin-bottom: 0;
            }

            .address input[type="text"] {
                margin-bottom: 5px;
            }

            .address:last-child {
                margin-bottom: 0;
            }

    </style>
</head>

<body>
    <form action="UpdateHimself" method="post" enctype="multipart/form-data">
     <div class="container">
         <a class="btn btn-danger btn-md" type="button" href="logout" style="position: absolute; top: 0; right: 0; margin: 1rem;">Logout</a>
     </div>
      <div class="container">
                    <a class="btn btn-success btn-md" type="button" href="home" style="position: absolute; top: 0; right: 90px; margin: 1rem;">Home</a>
                </div>
        <div class="profile-picture-container">
            <img src="data:image/jpeg;base64,${profiles[0].base64ProfilePicture}"
                alt="Profile Picture" class="profile-picture mt-3">
                </br>

        </div>

        <div class="container mt-4">

            <c:if test="${not empty profiles}">
                <c:forEach items="${profiles}" var="profile">
                 <input type="hidden" name="user_id" value="${profile.user_id}" />
                 <div class="text-center">
                             <input type="file" id="profilePicture" name="profilePicture" accept="image/*" >
                              </div>
                    <h1 class="text-center">${profile.first_name} ${profile.last_name}</h1>
                    <div class="card profile-card mb-5">
                        <h5 class="card-title profile-name">Edit Your Details</h5>
                        <div class="form-group">
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="first_name" value="${profile.first_name}">
                      <div class="text-danger" id="first-name-err"></div>
                        </div>
                        <div class="form-group">
                            <label for="last_name">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" name="last_name" value="${profile.last_name}">
                       <div class="text-danger" id="last-name-err"></div>
                        </div>


                        <div class="form-group">
                            <label for="contact_number">Contact:</label>
                            <input type="text" class="form-control" id="contactNumber" name="contact_number" value="${profile.contact_number}"  onkeyup="ValidateContact();">
                       <span id="contactNoError" style="color: red"></span>
                        </div>


                             <div class="form-group">
                                 <label for="email">Email</label>
                                 <input type="email" class="form-control" name="email" value="${profile.email}" readonly id="emailField"/>
                            <span id="emailError" style="color: red"></span>
                             </div>

                             <div class="form-group">
                                 <label for="role">Role</label>
                                 <input type="text" class="form-control" name="role" value="${profile.role}" readonly id="roleField"/>
                             </div>

                        <div class="form-group pb-3">
                            <label for="dob">Date of Birth:</label>
                            <input type="date" class="form-control" id="dob" name="dob" value="${profile.dob}">
                        <h5 id="dob1" style="color: red; display: none;"></h5>
                        </div>

             <h5 class="text-center" style="color: red"><% if (request.getAttribute("error") != null) {
                          out.println(request.getAttribute("error")); } %></h5>


                      <div class="container mt-5">
                          <div class="row">
                              <div class="col-lg-12">
                                  <h2> Address List</h2>
                                  <div class="table-responsive">
                                      <table class="table table-striped table-bordered">
                                          <thead class="thead-dark">
                                              <tr>
                                                  <th>Id</th>
                                                  <th>Street</th>
                                                  <th>Apartment</th>
                                                  <th>city</th>
                                                  <th>pincode</th>
                                                  <th>State</th>
                                                  <th>Country</th>
                                                  <th>Delete</th>
                                              </tr>
                                          </thead>
                                          <tbody>
                                              <c:forEach items="${addresses}" var="address" varStatus="loop">
                                                  <tr>
                                                      <td><input value="${address.address_id}"  name ="address_id1"style="width: 50px; height: 20px;"readonly></td>
                                                      <td><input value="${address.street}"name ="street1" style="width: 120px;"></td>
                                                      <td><input value="${address.apartment}"name ="apartment1" style="width: 120px;"></td>
                                                      <td><input value="${address.city}"name ="city1" style="width: 120px;"></td>
                                                      <td><input value="${address.pincode}"name ="pincode1" style="width: 120px;"></td>
                                                      <td><input value="${address.state}"name ="state1" style="width: 120px;"></td>
                                                      <td><input value="${address.country}"name ="country1" style="width: 120px;"></td>
                                                   <td><a class="btn btn-danger btn-md remove-address" data-address-id="${address.address_id}" href="#">Remove Address</a></td>

                                                  </tr>
                                              </c:forEach>
                                          </tbody>
                                      </table>
                                  </div>
                              </div>
                          </div>
                      </div>
                                 <input type="hidden" id="removedAddressIds" name="removedAddressIds" value="">

                      <div id="address">
                        <h3>Address </h3>
                           <div class="address">
                           </div>
                           </div>
                         <div class="row">
                          <div class="col-lg-6">
                                 <button type="button" class="btn btn-success" onclick="addAddress()">Add Address</button>
                             </div>
                         </div>
                      <br><br>

                        <button type="submit" class="btn btn-primary bg-primary" style="">Update</button>
                    </div>



        </div>
    </form>
 <%
    int adminId = 0;
    String LoginRole = null;

    if (session.getAttribute("admin_id") != null) {
        adminId = (int) session.getAttribute("admin_id");
    }
    if (session.getAttribute("role") != null) {
        LoginRole = (String) session.getAttribute("role");
    }
 %>
<script>
    var adminId = <%= adminId %>;
    var user_id = ${profile.user_id};
    var loginRole = "<%= LoginRole %>";

    var emailField = document.getElementById("emailField");
    var roleField = document.getElementById("roleField");

    if (adminId !== user_id && loginRole === "admin") {
        emailField.removeAttribute("readonly");
        roleField.removeAttribute("readonly");
    } else {
        emailField.setAttribute("readonly", "readonly");
        roleField.setAttribute("readonly", "readonly");
    }




     var removedAddressIds = [];

     document.addEventListener("DOMContentLoaded", function () {
         var removeButtons = document.querySelectorAll(".remove-address");

         removeButtons.forEach(function (button) {
             button.addEventListener("click", function (event) {
                 event.preventDefault();

                 var addressId = this.getAttribute("data-address-id");

                 var tableRow = this.closest("tr");

                 // Check if it's the last row before removing
                 if (tableRow.parentNode.rows.length > 1) {
                     removedAddressIds.push(addressId);
                     document.getElementById("removedAddressIds").value = removedAddressIds.join(",");
                     tableRow.parentNode.removeChild(tableRow);
                 }
             });
         });
     });

</script>
   </c:forEach>
   </c:if>
    <script>


  var addressIndex = 1;

  function addAddress() {
      var addressDiv = document.createElement('div');
      addressDiv.className = 'address';
      addressDiv.innerHTML =
          '<div class="row">' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="street">Street:</label><br>' +
          '<input type="text" id="street' + addressIndex + '" name="streets" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="apartment">Apartment:</label><br>' +
          '<input type="text" id="apartment' + addressIndex + '" name="apartments" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="city">City:</label><br>' +
          '<input type="text" id="city' + addressIndex + '" name="cities" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="pincode' + addressIndex + '">PinCode:</label><br>' +
          '<input type="text" id="pincode' + addressIndex + '" name="pincodes" required onblur="ValidatePincode(' + addressIndex + ');"><br>' +
          '<span id="pincodeError' + addressIndex + '" style="color: red"></span>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="state">State:</label><br>' +
          '<input type="text" id="state' + addressIndex + '" name="states" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="country">Country:</label><br>' +
          '<input type="text" id="country' + addressIndex + '" name="countries" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3 " style="padding-left: 205px; padding-top: 25px;">' +
          '<button type="button" class="btn btn-danger" onclick="removeAddress()">Remove</button>' +
          '</div>' +
          '</div>';

      var addressesDiv = document.getElementById('address');
      addressesDiv.appendChild(addressDiv);

      addressIndex++;
  }

            function removeAddress() {
                var addressDiv = document.getElementById('address');
                var addresses = addressDiv.getElementsByClassName('address');

                // Check if there is more than one address
                if (addresses.length > 1) {
                    // Remove the last address
                    addressDiv.removeChild(addresses[addresses.length - 1]);
                }
            }


                 function ValidatePincode(addressIndex) {
                var pincodeInput = document.getElementById("pincode" + addressIndex);
                var pincode = pincodeInput.value.trim();
                var pincodeError = document.getElementById("pincodeError" + addressIndex);

                // Regular expression to match exactly 6 digits
                var pincodePattern = /^\d{6}$/;

                if (!pincodePattern.test(pincode)) {
                    pincodeError.innerHTML = "Invalid PIN code. Please enter a 6-digit number.";
                    pincodeError.style.color = "red";
                    pincodeInput.focus();
                } else {
                    pincodeError.innerHTML = ""; // Clear the error message
                }
            }




 var firstName= document.getElementById("firstName");
    var firstNameValidation=function(){
       firstNameValue=firstName.value.trim();
       validFirstName=/^[A-Za-z]+$/;
       firstNameErr=document.getElementById('first-name-err');
       if(firstNameValue=="")
       {
        firstNameErr.innerHTML="First Name is required";
       }else if(!validFirstName.test(firstNameValue)){
         firstNameErr.innerHTML="First Name must be only string without white spaces";
       }else{
         firstNameErr.innerHTML="";
         return true;

       }
    }
    firstName.oninput=function(){

       firstNameValidation();
    }





     var lastName= document.getElementById("lastName");
        var lastNameValidation=function(){
           lastNameValue=lastName.value.trim();
           validlastName=/^[A-Za-z]+$/;
           lastNameErr=document.getElementById('last-name-err');
           if(lastNameValue=="")
           {

           lastNameErr.innerHTML="Last Name is required";
           }else if(!validlastName.test(lastNameValue)){
             lastNameErr.innerHTML="Last Name must be only string without white spaces";
           }else{
             lastNameErr.innerHTML="";
             return true;

           }
        }
        lastName.oninput=function(){

           lastNameValidation();
        }

            function ValidateContact() {

                var contactNumber = document.getElementById("contactNumber").value;
                var contactNoError = document.getElementById("contactNoError");
                contactNoError.innerHTML = "";

                var maxLength = 10; // Maximum length of contact number

                if (contactNumber.length > maxLength) {
                    contactNoError.innerHTML = "Contact number cannot exceed the maximum length of " + maxLength + " digits.";
                    // Truncate the contact number to the maximum length
                    contactNumber = contactNumber.slice(0, maxLength);
                    // Update the input field with the truncated contact number
                    document.getElementById("contactNumber").value = contactNumber;
                }

                var expr =  /^(0|91)?[6-9][0-9]{9}$/;
                if (!expr.test(contactNumber)) {
                    contactNoError.innerHTML = "Invalid Contact Number!";
                } else {
                return true;
                    // Clear the error message if the contact number is valid
                    contactNoError.innerHTML = "";
                }
            }




           function validateDOB() {
               var dobInput = document.getElementById('dob');
               var inputDate = new Date(dobInput.value);
               var currentDate = new Date();
               currentDate.setHours(0, 0, 0, 0); // Set current date to midnight for accurate comparison

               var dobError = document.getElementById('dob1');

               if (inputDate >= currentDate) {
                   dobError.style.display = 'block';
                   dobError.textContent = 'Birthday date should be before today';
                   dobError.style.color = 'red';
                   return false;
               } else {
                   dobError.style.display = 'none';
                   return true;
               }
           }
           document.getElementById('dob').addEventListener('change', validateDOB);



  function ValidateEmail() {
            var email = document.getElementById("emailField").value;
            var emailError = document.getElementById("emailError");
            emailError.innerHTML = "";

            var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
            var maxLength = 30; // Maximum length of email

            if (!expr.test(email)) {
                emailError.innerHTML = "Invalid email address.";
                 return false;
            } else if (email.length > maxLength) {
                emailError.innerHTML = "Email address cannot exceed the maximum length of " + maxLength + " characters.";
                // Truncate the email to the maximum length
                email = email.slice(0, maxLength);
                // Update the input field with the truncated email
                document.getElementById("emailField").value = email;
                 return false;
            }else{
                    emailError.innerHTML = "";
                      return true;
        }
}



            function validateForm(event) {

                     if (!firstNameValidation()) {
                         alert("Please enter a valid first name.");
                         event.preventDefault();
                         return false;
                     }

                     if (!lastNameValidation()) {
                         alert("Please enter a valid last name.");
                         event.preventDefault();
                         return false;
                     }



                     if (!ValidateContact()) {
                         alert("Please enter a valid contact number.");
                         event.preventDefault();
                         return false;
                     }


                       if (!validateDOB()) {
                         alert("Birthday date should be less then today.");
                         event.preventDefault();
                         return false;
                         }

                                   if (!ValidateEmail()) {
                                       alert("Please enter a valid email.");
                                       event.preventDefault();
                                       return false;
                                   }


                          for (var i = 0; i < addressIndex; i++) {
                              if (!validatePincode(i)) {
                                  alert("Pin code is not correct for address " + (i + 1));
                                  event.preventDefault();
                                  return false;
                              }
                          }


                     return true;
                 }

                 document.querySelector("form").addEventListener("submit", validateForm);


    </script>

</body>
</html>

<%@ include file="footer.jsp"%>
