RegistrationWizardCtrl = ($scope, $http, $location) ->
  $scope.user = {}

  $scope.userType = {}

  $scope.contactDetails = {}

  $scope.employee = {}

  $scope.userRoles = []
  $scope.userRole = {}

  $scope.roles = []


  #### work details page ####

  # populating 'roles'
  $http.get("/role/all").success((data) ->
    $scope.roles = data
  ).error((data) ->
    console.log data
  )

  # employee work details page addRole function
  $scope.addRole = ()->
    console.log $scope.userRole

    for userRole in $scope.userRoles
      if userRole.role1 is $scope.userRole.role1
        return

    $scope.userRoles.unshift (
      role1: $scope.userRole.role1
      active: $scope.userRole.active
    )

  $scope.getRoleDescription = (roleId) ->
    console.log roleId
    for role in $scope.roles
      console.log role.roleId
      if parseInt(role.roleId) is parseInt(roleId)
        return role.description

  $scope.removeRole = (index)->
    $scope.userRoles.splice(index, 1)

  #### end of work details page ####


  #### submit user registration ####

  $scope.saveUser = (postMethod) ->
    $http.post("/user/save", $scope.user).success((data) ->
      console.log data
      $scope.user.userId = data
      postMethod()
    ).error((data) ->
      console.log data
    )

  $scope.saveUserWithRoles = (postMethod) ->
    $http.post("/user/save_with_user_roles", $scope.user).success((data) ->
      console.log data
      $scope.user.userId = data
      postMethod()
    ).error((data) ->
      console.log data
    )

  $scope.saveContactDetails = () ->
    $scope.contactDetails.userId = $scope.user.userId

    $http.post("/contactdetails/save", $scope.contactDetails).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )

  $scope.saveEmployee = () ->
    $scope.employee.userId = $scope.user.userId

    $http.post("/employee/save", $scope.employee).success((data) ->
      console.log data
      saveEmployeeRoles()
    ).error((data) ->
      console.log data
    )

  $scope.saveCustomer = () ->
    customer =
      userId: $scope.user.userId
      name: $scope.contactDetails.name
      remark: 0

    $http.post("/customer/save", customer).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )

  $scope.saveVendor = () ->
    vendor =
      userId: $scope.user.userId
      name: $scope.contactDetails.name
      remark: 0

    $http.post("/vendor/save", vendor).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )

  $scope.saveEmployeeRoles = () ->
    console.log "save employee roles"

  $scope.save = () ->
    console.log "save()"
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
    #console.log $scope.step
    if $scope.isFirstStep()
      $scope.step = 0
    else
      if not $scope.isEmployee() && $scope.step == 3
        $scope.step -= 2
      else
        $scope.step -= 1


  $scope.handleNext = () ->
    #console.log $scope.step
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