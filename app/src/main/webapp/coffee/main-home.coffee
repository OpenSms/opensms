app = angular.module "main-app", ["$strap.directives", "ngTable"], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/UserRegistration",
    templateUrl: "./static/app/templates/UserRegistration.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/SearchUsers",
    templateUrl: "./static/app/templates/SearchUsers.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/UpdateUser/:userId",
    templateUrl: "./static/app/templates/UpdateUser.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/UserProfile",
    templateUrl: "./static/app/templates/UserProfile.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/AddItem",
    templateUrl: "./static/app/templates/employee/AddItem.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/AllItems",
    templateUrl: "./static/app/templates/employee/ItemList.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/GrnOrder",
    templateUrl: "./static/app/templates/employee/GrnOrderWizard.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/CreateIISOrder",
    templateUrl: "./static/app/templates/employee/CreateIISOrder.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/AddUnit",
    templateUrl: "./static/app/templates/employee/AddUnit.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/AddProfit",
    templateUrl: "./static/app/templates/employee/AddProfit.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/AddCategory",
    templateUrl: "./static/app/templates/employee/AddCategory.html"
    controller: checkLogginInCtrl

  $routeProvider.when "/PreOrder",
    templateUrl: "./static/app/templates/customer/PreOrder.html"
    controller: checkLogginInCtrl

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

checkLogginInCtrl = ($http) ->
  $http.get("/isUserLoggedIn").success((data) ->
    if data is 'false'
      window.location = "/"

  ).error((data) ->
    window.location = "/"
  )
