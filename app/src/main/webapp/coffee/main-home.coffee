app = angular.module "main-app", ["$strap.directives", "ngTable"], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"

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

  $routeProvider.when "/AddUnit",
    templateUrl: "./static/app/templates/employee/AddUnit.html"

  $routeProvider.when "/AddProfit",
    templateUrl: "./static/app/templates/employee/AddProfit.html"

  $routeProvider.when "/AddCategory",
    templateUrl: "./static/app/templates/employee/AddCategory.html"

  $routeProvider.when "/PreOrder",
    templateUrl: "./static/app/templates/customer/PreOrder.html"

  $routeProvider.otherwise
    redirectTo: "/"


app.run ($http, $rootScope) ->
  $http.get("/currentuser").success((data) ->
    $rootScope.currentUser = data
  ).error((data) ->
    console.log "error in /currentuser"
  )

  $http.get("/currentuserroles").success((data) ->
    $rootScope.userRoles = data
  ).error((data) ->
    console.log "error in /currentuserroles"
  )
