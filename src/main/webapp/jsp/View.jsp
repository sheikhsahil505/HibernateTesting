

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <%@ include file="header.jsp"%>


<!DOCTYPE html>
<html>
<head>
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


.table-container {
    max-height: 190px;
    overflow-y: auto;
}


/* Modal Styles */
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 50px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.9);
}

.modal-content {
    margin: auto;
    display: block;
    max-width: 50%;
    max-height: auto;
}

.close {
    position: absolute;
    top: 20px;
    right: 30px;
    font-size: 30px;
    font-weight: bold;
    color: white;
    cursor: pointer;
}


    </style>
    <title>Registration List</title>

    <link rel="stylesheet" href="./assets/bootstrap/bootstrap.css">
  </head>

<body>
<form action ="adminView" method="get">


 <div class="container">
     <a class="btn btn-danger btn-md" type="button" href="logout" style="position: absolute; top: 0; right: 0; margin: 1rem;">Logout</a>
 </div>
 <div class="container">
                    <a class="btn btn-success btn-md" type="button" href="home" style="position: absolute; top: 0; right: 90px; margin: 1rem;">Home</a>
                </div>

<div class="profile-picture-container">
     <img src="data:image/jpeg;base64,${profile[0].base64ProfilePicture}"
        alt="Profile Picture" class="profile-picture mt-3" id="profilePicture">
</div>

<div id="profilePictureModal" class="modal">
    <span class="close" id="closeModal">&times;</span>
    <img id="profilePictureModalImage" class="modal-content">
</div>

        <div class="container ">
 <c:if test="${not empty profile}">
            <c:forEach items="${profile}" var="profiles">
            <h1 class="text-center">${profiles.first_name} ${profiles.last_name}</h1>
 <div class="text-center">
            <a class="btn btn-primary btn-sm" type="button" href="UpdateHimself?user_id=${profiles.user_id}&email=${profiles.email}">Edit Profile</a>
        </div>
                    <div class="card profile-card mb-5">
                        <h5 class="card-title profile-name"> Details</h5>
                        <p class="card-text profile-info">
                            <strong>ID:</strong> ${profiles.user_id}<br>
                            <strong>Email:</strong> ${profiles.email}<br>
                            <strong>Contact:</strong> ${profiles.contact_number}<br>
                            <strong> Role:</strong> ${profiles.role}<br>
                            <strong>Date of Birth:</strong> ${profiles.dob}<br>
                        </p>
                 <div class="container">
                     <h5>Address List</h5>
                     <div class="table-responsive table-container">
                         <table class="table table-striped table-bordered" >
                             <thead class="thead-dark">
                                 <tr>
                                     <th>S.No.</th>
                                     <th>Street</th>
                                     <th>Apartment</th>
                                     <th>City</th>
                                     <th>Pincode</th>
                                     <th>State</th>
                                     <th>Country</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <c:forEach items="${addresses}" var="address" varStatus="loop">
                                     <tr>
                                         <td>${loop.index + 1}</td>
                                         <td>${address.street}</td>
                                         <td>${address.apartment}</td>
                                         <td>${address.city}</td>
                                         <td>${address.pincode}</td>
                                         <td>${address.state}</td>
                                         <td>${address.country}</td>
                                     </tr>
                                 </c:forEach>
                             </tbody>
                         </table>
                     </div>
                 </div>


    </div>
</div>




  <div class="container mt-5">
      <div class="container text-center">

      </div>
      <div class="table-responsive" id="adminTable">
          <table class="table table-striped table-bordered">
              <thead class="thead-dark">
                  <tr>
                      <th>S.No.</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>Contact</th>
                      <th>User Role</th>
                      <th>Date of Birth</th>
                      <th>Remove</th>
                      <th>View More</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach items="${registrations}" var="registration" varStatus="loop">
                      <tr>
                          <td>${loop.index + 1}</td>
                          <td>${registration.first_name}</td>
                          <td>${registration.last_name}</td>
                          <td>${registration.email}</td>
                          <td>${registration.contact_number}</td>
                          <td>${registration.role}</td>
                          <td>${registration.dob}</td>
                          <td><a class="btn btn-danger btn-md" type="button" href="deleteUser?user_id=${registration.user_id}&email=${profiles.email}">Remove</a></td>
                          <td><a class="btn btn-primary btn-md" type="button" href="viewMoreUser?user_id=${registration.user_id}&email=${registration.email}">See More</a></td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
<script>
    // Get the value of ${profiles.role}
    var userProfileRole = "${profiles.role}";

    // Get the table element by its ID
    var adminTable = document.getElementById("adminTable");

    // Check if the user's role is 'admin'
    if (userProfileRole === "admin") {
        // If the user is an admin, display the table
        adminTable.style.display = "block";
    } else {
        // If the user is not an admin, hide the table
        adminTable.style.display = "none";
    }



    // Get the modal and image elements
    var modal = document.getElementById('profilePictureModal');
    var image = document.getElementById('profilePicture');

    // Get the close button
    var closeButton = document.getElementById('closeModal');

    // When the user clicks on the image, open the modal
    image.onclick = function () {
        modal.style.display = 'block';
        document.getElementById('profilePictureModalImage').src = this.src;
    };

    // When the user clicks on the close button, close the modal
    closeButton.onclick = function () {
        modal.style.display = 'none';
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    };



</script>
      </c:forEach>
   </c:if>
</form>
</body>
</html>
<%@ include file="footer.jsp"%>


