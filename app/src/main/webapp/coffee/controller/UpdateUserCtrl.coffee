UpdateUserCtrl = ($scope, $http, $location, $routeParams) ->
  $scope.steps = ["one", "two", "three"]
  $scope.step = 0
  $scope.wizard =
    tacos: 2

  $scope.user = {}
  $scope.userType = {}
  $scope.contactDetails = {}
  $scope.employee = {}
  $scope.userPassword = {}

  $scope.userRoles = []
  $scope.userRole = {}

  $scope.roles = []

  #### state changed signals ####
  # these signals emited when property change is happens

  # whether user update any thing
  # e.g. if user update contact details $scope.detailsUpdated.contactDetails becomes true
  $scope.detailsUpdated = {}
  $scope.detailsUpdated.userPassword = false
  $scope.detailsUpdated.userAccountState = false
  $scope.detailsUpdated.contactDetails = false
  $scope.detailsUpdated.employeeNames = false
  $scope.detailsUpdated.employeeRoles = false

  $scope.userPasswordChanged = () ->
    if typeof $scope.userPassword.oldPass isnt "undefined" and typeof $scope.userPassword.newPass isnt "undefined" and typeof $scope.userPassword.confirmPass isnt "undefined"
      $scope.detailsUpdated.userPassword = true
    else
      $scope.detailsUpdated.userPassword = false

  $scope.userAccountStateChanged = () ->
    $scope.detailsUpdated.userAccountState = true

  $scope.contactDetailsChanged = () ->
    $scope.detailsUpdated.contactDetails = true

  $scope.employeeNameChanged = () ->
    $scope.detailsUpdated.employeeNames = true

  $scope.employeeRoleChanged = () ->
    $scope.detailsUpdated.employeeRoles = true

  #### end of state changed signals ####

  #### populate user data ####

  $http.get("/user?userId=" + $routeParams.userId).success((data) ->
    delete data.id
    $scope.user = data
  ).error((data) ->
    console.log("error while retriving data '/user?userId='")
  )

  #### end of populate user data ####


  #### populate contact details ####

  $http.get("/contactdetails?userId=" + $routeParams.userId).success((data) ->
    delete data.user
    $scope.contactDetails = data
  ).error((data) ->
    console.log("error while retriving data from '/contactdetails?userId='")
  )

  #### end of populate contact details ####

  #### get employee ####

  # populate employee
  $http.get("/employee?userId=" + $routeParams.userId).success((data) ->
    $scope.employee = data
  ).error((data) ->
    console.log("error while retriving data from '/employee?userId='")
  )

  # check userRoles for customer role or vendor role if those does not exists that means use is a employee
  $scope.isEmployee = () ->
    $scope.userType.type is "employee"

  #### end of get employee ####


  #### work details page ####

  # populating 'roles'
  $http.get("/role/all").success((data) ->
    $scope.roles = data
  ).error((data) ->
    console.log data
  )

  # employee work details page addRole function
  $scope.addRole = () ->
    for userRole in $scope.userRoles
      if Number(userRole.role1.roleId) is Number($scope.userRole.role1.roleId) and userRole.resignDate is null
        return

    $scope.userRole.role1.description = $scope.getRoleDescription($scope.userRole.role1.roleId)
    $scope.userRole.active = true

    if $scope.userRole.role1.description is "vendor" or $scope.userRole.role1.description is "customer"
      return

      # create UserRolePK object
    uRolePk = {}
    uRolePk =
      assignDate: new Date().getTime()
      role: $scope.userRole.role1.roleId
      user: $routeParams.userId

    # UserRole object
    $scope.userRole["resignDate"] = null
    $scope.userRole["userRolePK"] = uRolePk

    $scope.userRoles.unshift (
      jQuery.extend(true, {}, $scope.userRole)
    )
    $scope.employeeRoleChanged()


  $scope.getRoleDescription = (roleId) ->
    for role in $scope.roles
      if parseInt(role.roleId) is parseInt(roleId)
        return role.description

  $scope.activateRole = (index) ->
    if $scope.userRoles[index].active
      $scope.userRoles[index].resignDate = new Date().getTime()
      $scope.userRoles[index].active = false
    else
      $scope.userRoles[index].resignDate = null
      $scope.userRoles[index].active = true

    $scope.employeeRoleChanged()

  #### end of work details page ####


  #### get assinged user roles ####

  $http.get("/userrole?userId=" + $routeParams.userId).success((data) ->
    userType = ""
    for d in data
      $scope.userRoles.unshift (
        jQuery.extend(true, {}, d)
      )

      if d.role1.description is "customer"
        userType = "customer"
      else if d.role1.description is "vendor"
        userType = "vendor"
      else if d.role1.description is "employee"
        userType = "employee"
      else
        userType = "undefined"

    $scope.userType.type = userType
    $scope.detailsUpdated.employeeRoles = false
  ).error((data) ->
    console.log("error in /userrole/userId=")
  )

  #### end of get assinged user roles ####


  #### wizard controller ####

  $scope.isFirstStep = ->
    $scope.step is 0

  $scope.isLastStep = ->
    $scope.step is ($scope.steps.length - 1)

  $scope.isCurrentStep = (step) ->
    $scope.step is step

  $scope.setCurrentStep = (step) ->
    $scope.step = step

  $scope.getCurrentStep = ->
    $scope.steps[$scope.step]

  $scope.handlePrevious = ->
    if $scope.isFirstStep()
      $scope.step -= 0
    else
      if not $scope.isEmployee() && $scope.step == 2
        $scope.step -= 2
      else
        $scope.step -= 1

  $scope.handleNext = () ->
    if $scope.isLastStep()
      $scope.step += 0
    else
      if not $scope.isEmployee() && $scope.step == 0
        $scope.step += 2
      else
        $scope.step += 1

  #### end of wizard controller ####


  #### update ####

  $scope.updateUserPassword = () ->
    if $scope.detailsUpdated.userPassword is true
      $scope.user.password = $scope.userPassword.oldPass
      $http.post("/user/validatepassword", $scope.user).success((data) ->
        if data is "true"
          $scope.user.password = $scope.userPassword.newPass
          $http.post("/user/changepassword", $scope.user).success((data) ->
            console.log data
          ).error((data) ->
            console.log("error in /user/changepassword")
          )
          console.log "password changed"
        else
          console.log "invalid password"
      ).error((data) ->
        console.log("error in /user/validatepassword")
      )

  $scope.updateUserAccountState = () ->
    if $scope.detailsUpdated.userAccountState is true
      $http.post("/user/updatestate", $scope.user).success((data) ->
        console.log data
      ).error((data) ->
        console.log("error in /user/updatestate")
      )

  $scope.updateContactDetails = () ->
    if $scope.detailsUpdated.contactDetails is true
      $scope.contactDetails.userId = $scope.user.userId
      console.log $scope.contactDetails
      $http.post("/contactdetails/update", $scope.contactDetails).success((data) ->
        console.log data
      ).error((data) ->
        console.log("error in /contactdetails/update")
      )

  $scope.updateEmployeeNames = () ->
    if $scope.detailsUpdated.employeeNames is true
      $http.post("/employee/updatenames", $scope.employee).success((data) ->
        console.log data
      ).error((data) ->
        console.log("error in /employee/updatenames")
      )

  $scope.updateEmployeeRoles = () ->
    if $scope.detailsUpdated.employeeRoles is false
      return

    employeeModel=
      employee: $scope.employee
      userRoles: $scope.userRoles

    console.log employeeModel

    $http.post("/employee/updateroles", $scope.userRoles).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in /employee/updateroles")
    )

  $scope.update = () ->
    $scope.updateUserPassword()
    $scope.updateUserAccountState()
    $scope.updateContactDetails()
    $scope.updateEmployeeNames()
    $scope.updateEmployeeRoles()

#### end of update ####
