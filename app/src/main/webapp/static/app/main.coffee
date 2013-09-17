angular.module "main-app", [], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"

  $routeProvider.when "/test",
    templateUrl: "./static/app/templates/test.html"

  $routeProvider.when "/login",
    templateUrl: "./static/app/templates/login.html"



