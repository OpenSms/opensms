
angular.module("main-app", ["$strap.directives", "ngTable"], function($routeProvider, $locationProvider) {
  $routeProvider.when("/", {
    templateUrl: "./static/app/templates/index.html"
  });
  $routeProvider.when("/test", {
    templateUrl: "./static/app/templates/test.html"
  });
  $routeProvider.when("/login", {
    templateUrl: "./static/app/templates/login.html"
  });
  $routeProvider.when("/contactDetails", {
    templateUrl: "./static/app/templates/contactDetails.html"
  });
  $routeProvider.when("/registration", {
    templateUrl: "./static/app/templates/registration.html"
  });
  $routeProvider.when("/editUser", {
    templateUrl: "./static/app/templates/editUser.html"
  });
  $routeProvider.when("/customer/registration", {
    templateUrl: "./static/app/templates/customer/registration.html"
  });
  $routeProvider.when("/RegistrationWizard", {
    templateUrl: "./static/app/templates/RegistrationWizard.html"
  });
  $routeProvider.when("/SearchUsers", {
    templateUrl: "./static/app/templates/SearchUsers.html"
  });
  return $routeProvider.when("/UpdateUser", {
    templateUrl: "./static/app/templates/UpdateUser.html"
  });
});
