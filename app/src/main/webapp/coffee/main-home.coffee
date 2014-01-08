app = angular.module "main-app", ["$strap.directives", "ngTable", "services.breadcrumbs", "AngularGM"], ($routeProvider, $locationProvider) ->
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
    label: "Search User"

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


  $routeProvider.when "/GsrOrder",
    templateUrl: "./static/app/templates/employee/GsnOrderWizard.html"
    controller: checkLogginInCtrl
    label: "Create GSR Order"

  $routeProvider.when "/GrnPayment",
    templateUrl: "./static/app/templates/employee/GrnPayment.html"
    controller: checkLogginInCtrl
    label: "New GRN Payment"

  $routeProvider.when "/GsrPayment",
    templateUrl: "./static/app/templates/employee/GsrPayment.html"
    controller: checkLogginInCtrl
    label: "New GSR Payment"


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

  $routeProvider.when "/Reports/PreOrders",
    templateUrl: "./static/app/templates/reports/customer/PreOrders.html"
    controller: checkLogginInCtrl
    label: "Pre Orders"

  $routeProvider.when "/Reports/GsrOrders",
    templateUrl: "./static/app/templates/reports/customer/GsrOrders.html"
    controller: checkLogginInCtrl
    label: "Gsr Orders"

  $routeProvider.when "/Reports/GrnOrders",
    templateUrl: "./static/app/templates/reports/vendor/GrnOrders.html"
    controller: checkLogginInCtrl
    label: "Grn Orders"

  $routeProvider.when "/Reports/ItemDetails",
    templateUrl: "./static/app/templates/reports/employee/ItemDetails.html"
    controller: checkLogginInCtrl
    label: "Item Details"

  $routeProvider.when "/Reports/CustomerLocations",
    templateUrl: "./static/app/templates/reports/employee/CustomerLocations.html"
    controller: checkLogginInCtrl
    label: "Customer Locations"

  $routeProvider.when "/Reports/EmployeeAttendance",
    templateUrl: "./static/app/templates/reports/employee/EmployeeAttendance.html"
    controller: checkLogginInCtrl
    label: "Employee Attendance"

  $routeProvider.when "/Reports/PersonalAttendance",
    templateUrl: "./static/app/templates/reports/employee/PersonalAttendance.html"
    controller: checkLogginInCtrl
    label: "Personal Attendance"

  $routeProvider.otherwise
    redirectTo: "/"


app.run ($http, $rootScope) ->
  $rootScope.title = ""
  $http.get("/currentuser").success((data) ->
    $rootScope.currentUser = data
  ).error((data) ->
    console.log "error in /currentuser"
  )

  $http.get("/currentuserroles").success((data) ->
    $rootScope.userRoles = data
  ).error(() ->
    console.log "error in /currentuserroles"
  )

checkLogginInCtrl = ($http, $rootScope, $route) ->

  $rootScope.title =  $route.current.label

  $http.get("/isUserLoggedIn").success((data) ->
    if data is 'false'
      window.location = "/"

  ).error(() ->
    window.location = "/"
  )

app.controller "BreadcrumbCtrl", ["$scope", "breadcrumbs", ($scope, breadcrumbs) ->
  $scope.breadcrumbs = breadcrumbs
]