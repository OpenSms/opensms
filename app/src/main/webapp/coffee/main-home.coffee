app = angular.module "main-app", ["$strap.directives", "ngTable"], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/home.html"

  $routeProvider.when "/RegistrationWizard",
    templateUrl: "./static/app/templates/RegistrationWizard.html"

  $routeProvider.when "/SearchUsers",
    templateUrl: "./static/app/templates/SearchUsers.html"

  $routeProvider.when "/UpdateUser/:userId",
    templateUrl: "./static/app/templates/UpdateUser.html"

  $routeProvider.when "/AddItem",
    templateUrl: "./static/app/templates/employee/AddItem.html"

  $routeProvider.when "/AllItems",
    templateUrl: "./static/app/templates/employee/ItemList.html"

  $routeProvider.when "/GrnOrder",
    templateUrl: "./static/app/templates/employee/GrnOrderWizard.html"

  $routeProvider.when "/CreateIISOrder",
    templateUrl: "./static/app/templates/employee/CreateIISOrder.html"

  $routeProvider.when "/PreOrder",
    templateUrl: "./static/app/templates/customer/PreOrder.html"

  $routeProvider.otherwise
    redirectTo: "/"



app.run ($http,$rootScope) ->

  $http.get("/currentuser").success((data) ->
    console.log "ssss"
    console.log data
    $rootScope.user = data
  ).error((data) ->
    console.log "erere"
  )

  $http.get("/currentuser").success((data) ->
    $rootScope.userRoles = data
  ).error((data) ->
  )


SideMenuCtrl = ($scope,$http,$rootScope) ->
  console.log "dfsf"
  console.log $rootScope.user
  console.log $rootScope.userRoles

  $scope.user = $rootScope.user
  $scope.userRoles = $rootScope.userRoles

app.controller('SideMenuCtrl',SideMenuCtrl)