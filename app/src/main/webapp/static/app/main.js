
angular.module("main-app", ["$strap.directives", "ngTable"], function($routeProvider, $locationProvider) {
  $routeProvider.when("/", {
    templateUrl: "./static/app/templates/index.html"
  });
  $routeProvider.when("/login", {
    templateUrl: "./static/app/templates/login.html"
  });
  $routeProvider.when("/RegistrationWizard", {
    templateUrl: "./static/app/templates/RegistrationWizard.html"
  });
  $routeProvider.when("/SearchUsers", {
    templateUrl: "./static/app/templates/SearchUsers.html"
  });
  return $routeProvider.when("/UpdateUser/:userId", {
    templateUrl: "./static/app/templates/UpdateUser.html"
  });
});
