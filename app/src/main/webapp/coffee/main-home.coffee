app = angular.module "main-app",
  ["$strap.directives", "ngTable", "services.breadcrumbs"], ($routeProvider, $locationProvider) ->
  $routeProvider.when "/",
    templateUrl: "./static/app/templates/index.html"
    controller: checkLogginInCtrl
    label: "Dashboard"

  $routeProvider.when "/UserRegistration",
    templateUrl: "./static/app/templates/UserRegistration.html"
    controller: checkLogginInCtrl
    label: "Register User"

  $routeProvider.when "/SearchUsers",
    templateUrl: "./static/app/templates/SearchUsers.html"
    controller: checkLogginInCtrl
    label: "Search"

  $routeProvider.when "/UpdateUser/:userId",
    templateUrl: "./static/app/templates/UpdateUser.html"
    controller: checkLogginInCtrl
    label: "Edit User"

  $routeProvider.when "/UserProfile",
    templateUrl: "./static/app/templates/UserProfile.html"
    controller: checkLogginInCtrl
    label: "Profile"

  $routeProvider.when "/AddItem",
    templateUrl: "./static/app/templates/employee/AddItem.html"
    controller: checkLogginInCtrl
    label: "Add New Item"

  $routeProvider.when "/AllItems",
    templateUrl: "./static/app/templates/employee/ItemList.html"
    controller: checkLogginInCtrl
    label: "All Item"

  $routeProvider.when "/GrnOrder",
    templateUrl: "./static/app/templates/employee/GrnOrderWizard.html"
    controller: checkLogginInCtrl
    label: "Create GRN Order"

  $routeProvider.when "/CreateIISOrder",
    templateUrl: "./static/app/templates/employee/CreateIISOrder.html"
    controller: checkLogginInCtrl
    label: "Create IIS Order"

  $routeProvider.when "/AddUnit",
    templateUrl: "./static/app/templates/employee/AddUnit.html"
    controller: checkLogginInCtrl
    label: "Add Unit"

  $routeProvider.when "/AddProfit",
    templateUrl: "./static/app/templates/employee/AddProfit.html"
    controller: checkLogginInCtrl
    label: "Add Profit"

  $routeProvider.when "/AddCategory",
    templateUrl: "./static/app/templates/employee/AddCategory.html"
    controller: checkLogginInCtrl
    label: "Add Category"

  $routeProvider.when "/PreOrder",
    templateUrl: "./static/app/templates/customer/PreOrder.html"
    controller: checkLogginInCtrl
    label: "Pre Order"

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

BreadcrumbCtrl = ($scope, breadcrumbs) ->
  $scope.breadcrumbs = breadcrumbs
