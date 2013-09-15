
angular.module("main-app", [], function($routeProvider, $locationProvider) {
  $routeProvider.when("/", {
    templateUrl: "./static/app/templates/index.html"
  });
  $routeProvider.when("/test", {
    templateUrl: "./static/app/templates/test.html"
  });
  return $routeProvider.when("/login", {
    templateUrl: "./static/app/templates/login.html"
  });
});
