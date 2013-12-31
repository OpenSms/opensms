UserRegistrationCtrl = ($scope, $http, $location) ->
  $scope.user = {}
  $scope.user.accountStatus = true # user activated by default

  $scope.userType = {}

  $scope.contactDetails = {}

  $scope.employee = {}

  $scope.userRoles = []
  $scope.userRole = {}
  $scope.userRole.active = true # active by default

  $scope.roles = []

  $scope.isEmployee = () ->
    $scope.userType.type is "employee"

  #### work details page ####

  # populating 'roles'
  $http.get("/role/all").success((data) ->
    $scope.roles = data
  ).error((data) ->
    console.log("error in role/all")
  )

  # employee work details page addRole function
  $scope.addRole = ()->
    for userRole in $scope.userRoles
      if userRole.role1 is $scope.userRole.role1
        return

    if $scope.getRoleDescription($scope.userRole.role1) is "vendor" or $scope.getRoleDescription($scope.userRole.role1) is "customer"
      return

    $scope.userRoles.unshift (
      role1: $scope.userRole.role1
      active: $scope.userRole.active
    )

  $scope.getRoleDescription = (roleId) ->
    for role in $scope.roles
      if parseInt(role.roleId) is parseInt(roleId)
        return role.description

  $scope.removeRole = (index)->
    $scope.userRoles.splice(index, 1)

  #### end of work details page ####


  #### submit user registration ####

  $scope.saveUser = (postMethod) ->
    $http.post("/user/save", $scope.user).success((data) ->
      $scope.user.userId = data
      postMethod()
    ).error((data) ->
      console.log("error in user/save")
    )

  $scope.saveContactDetails = () ->
    $scope.contactDetails.userId = $scope.user.userId

    $http.post("/contactdetails/save", $scope.contactDetails).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in contactdetails/save")
    )

  $scope.saveEmployee = () ->
    $scope.employee.userId = $scope.user.userId

    employeeRoles = []

    for role in $scope.userRoles
      employeeRoles.push(
        roleId: role.role1
        description: $scope.getRoleDescription(role.role1)
      )

    employeeModel =
      employee: $scope.employee
      roles: employeeRoles

    $http.post("/employee/save", employeeModel).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in employee/save")
    )

  $scope.saveCustomer = () ->
    customer =
      userId: $scope.user.userId
      name: $scope.contactDetails.name
      remark: 0

    $http.post("/customer/save", customer).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in customer/save")
    )

  $scope.saveVendor = () ->
    vendor =
      userId: $scope.user.userId
      name: $scope.contactDetails.name
      remark: 0

    $http.post("/vendor/save", vendor).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in vendor/save")
    )

  $scope.save = () ->
    $scope.saveUser(()->
      $scope.saveContactDetails()

      if $scope.userType.type is "employee"
        $scope.saveEmployee()
      else if $scope.userType.type is "customer"
        $scope.saveCustomer()
      else
        $scope.saveVendor()

      $location.path("/")
      $scope.$apply()
    )

#### end of submit user registration ####