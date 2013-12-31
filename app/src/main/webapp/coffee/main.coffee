app = angular.module "main-app",[], ($routeProvider, $locationProvider) ->

  $routeProvider.when "/login",
    templateUrl: "./static/app/templates/login.html"

  $routeProvider.otherwise
    redirectTo: "/login"

