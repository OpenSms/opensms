RegistrationWizardCtrl = ($scope, $http, $location) ->
  $scope.user = {}
  $scope.user.accountStatus = true # user activated by default

  $scope.userType = {}

  $scope.contactDetails = {}

  $scope.employee = {}

  $scope.userRoles = []
  $scope.userRole = {}
  $scope.userRole.active = true # active by default

  $scope.roles = []


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

    employeeRoles=[]

    for role in $scope.userRoles
      employeeRoles.push(
        roleId: role.role1
        description:$scope.getRoleDescription(role.role1)
      )

    employeeModel=
      employee:$scope.employee
      roles: employeeRoles

    $http.post("/employee/save",employeeModel ).success((data) ->
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
    )

  #### end of submit user registration ####


  #### wizard controller ####

  $scope.steps = ["one", "two", "three", "four"]
  $scope.step = 0
  $scope.wizard =
    tacos: 2
  $scope.isFirstStep = ->
    $scope.step is 0

  $scope.isLastStep = ->
    $scope.step is ($scope.steps.length - 1)

  $scope.isCurrentStep = (step) ->
    $scope.step is step


  $scope.getCurrentStep = ->
    $scope.steps[$scope.step]

  $scope.getNextLabel = ->
    (if ($scope.isLastStep()) then "Submit" else "Next")

  $scope.handlePrevious = ->
    if $scope.isFirstStep()
      $scope.step = 0
    else
      if not $scope.isEmployee() && $scope.step == 3
        $scope.step -= 2
      else
        $scope.step -= 1


  $scope.handleNext = () ->
    if $scope.isLastStep()
      $scope.save()

    else
      if not $scope.isEmployee() && $scope.step == 1
        $scope.step += 2
      else
        $scope.step += 1

  $scope.isEmployee = () ->
    $scope.userType.type is "employee"

#### end of wizard controller ####