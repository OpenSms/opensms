UserProfileCtrl = ($scope, $http, $location, $rootScope) ->
  $scope.user = $rootScope.currentUser
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
  $scope.detailsUpdated.contactDetails = false
  $scope.detailsUpdated.employeeNames = false

  $scope.userPasswordChanged = () ->
    if typeof $scope.userPassword.oldPass isnt "undefined" and typeof $scope.userPassword.newPass isnt "undefined" and typeof $scope.userPassword.confirmPass isnt "undefined"
      $scope.detailsUpdated.userPassword = true
    else
      $scope.detailsUpdated.userPassword = false

  $scope.contactDetailsChanged = () ->
    $scope.detailsUpdated.contactDetails = true

  $scope.employeeNameChanged = () ->
    $scope.detailsUpdated.employeeNames = true

  #### end of state changed signals ####

  # check userRoles for customer role or vendor role if those does not exists that means use is a employee
  $scope.isEmployee = () ->
    $scope.userType.type is 'employee'

  #### get user type ####
  $http.post("/user/usertype", Number($scope.user.userId)).success((data) ->
    $scope.userType.type = JSON.parse(data)

    $scope.getEmployeeDetails()

  ).error(() ->
    console.log("error in /user/usertype")
  )
  #### end of get user type ####

  #### populate contact details ####
  $http.get("/contactdetails?userId=" + $scope.user.userId).success((data) ->
    delete data.user
    $scope.contactDetails = data
  ).error((data) ->
    console.log("error while retriving data from '/contactdetails?userId='")
  )
  #### end of populate contact details ####

  #### get employee details ####
  $scope.getEmployeeDetails = () ->
    if $scope.isEmployee()
      $http.get("/employee?userId=" + $scope.user.userId).success((data) ->
        $scope.employee = data
      ).error((data) ->
        console.log("error while retriving data from '/employee?userId='")
      )

      # populating 'roles'
      $http.get("/role/all").success((data) ->
        $scope.roles = data
      ).error((data) ->
        console.log data
      )

      #### get assinged user roles ####
      $http.get("/userrole?userId=" + $scope.user.userId).success((data) ->
        userType = ""
        for d in data
          $scope.userRoles.unshift (
            jQuery.extend(true, {}, d)
          )

        #get user type
        for d in data
          if d.role1.description is "customer"
            userType = "customer"
            break
          else if d.role1.description is "vendor"
            userType = "vendor"
            break
          else if d.role1.description is "employee"
            userType = "employee"
            break
          else
            userType = "undefined"

        $scope.userType.type = userType
      ).error((data) ->
        console.log("error in /userrole/userId=")
      )

  $scope.getRoleDescription = (roleId) ->
    for role in $scope.roles
      if parseInt(role.roleId) is parseInt(roleId)
        return role.description


  #### update ####
  $scope.updateUserPassword = () ->
    if $scope.detailsUpdated.userPassword is true
      $scope.user.password = $scope.userPassword.oldPass
      console.log $scope.user
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

  $scope.update = () ->
    $scope.updateUserPassword()
    $scope.updateContactDetails()
    $scope.updateEmployeeNames()

    $location.path("/")
    $scope.apply()
#### end of update ####
