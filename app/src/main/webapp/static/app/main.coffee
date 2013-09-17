angular.module "main-app", [], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"

  $routeProvider.when "/test",
    templateUrl: "./static/app/templates/test.html"

  $routeProvider.when "/login",
    templateUrl: "./static/app/templates/login.html"

  $routeProvider.when "/contactDetails",
    templateUrl: "./static/app/templates/contactDetails.html"

  $routeProvider.when "/registration",
    templateUrl: "./static/app/templates/registration.html"

  $routeProvider.when "/editUser",
    templateUrl: "./static/app/templates/editUser.html"
