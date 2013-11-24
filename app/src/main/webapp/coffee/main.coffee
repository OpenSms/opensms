app = angular.module "main-app",[], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"

  $routeProvider.when "/login",
    templateUrl: "./static/app/templates/login.html"

  $routeProvider.otherwise
    redirectTo: "/"