app = angular.module "main-app", ["$strap.directives", "ngTable"], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"

  $routeProvider.when "/login",
    templateUrl: "./static/app/templates/login.html"


  $routeProvider.when "/customer/registration",
    templateUrl: "./static/app/templates/customer/registration.html"

  $routeProvider.when "/RegistrationWizard",
    templateUrl: "./static/app/templates/RegistrationWizard.html"

  $routeProvider.when "/SearchUsers",
    templateUrl: "./static/app/templates/SearchUsers.html"

  $routeProvider.when "/UpdateUser/:userId",
    templateUrl: "./static/app/templates/UpdateUser.html"


  $routeProvider.when "/AddItem",
    templateUrl: "./static/app/templates/employee/AddItem.html"


test='name'
console.log test