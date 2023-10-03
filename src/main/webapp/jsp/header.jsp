<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar with Logo and Login/Signup</title>
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap.css">

    <style>

        .navbar-logo {
          height: auto;
            height: 50px;
        }


        .navbar-nav .btn {
            padding: 10px 20px;
            font-size: 18px;
            border-radius: 25px;
        }


    .navbar-dark {
        background-color: #007bff; /* Blue background color */
    }

    .navbar-nav .nav-link {
        color: #ffffff; /* White text color */
    }



        @media (max-width: 576px) {
            .navbar-logo {
             height:auto;
                max-width: 60%;
            }
        }
 @media (max-width: 476px) {
            .navbar-logo {
            height:auto;
                max-width: 40%;
            }
        }

    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <!-- Change bg-light to bg-dark here -->
    <div class="container">
        <!-- Logo -->
        <a class="navbar-brand" href="#">
            <img src="/JspServlet/jsp/images/inx-white-logos.png" alt="Logo" class="navbar-logo">
        </a>



        </div>
    </div>
</nav>
