RegistrationCtrl = ($scope, $http, $location) ->
  $scope.user
  $scope.signUp = ->
    console.log $scope.user
    $http.post("/user/saveCustomer", $scope.user).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )

    $scope.contactDetails.userId = 1

    console.log $scope.contactDetails
    $http.post("/contactdetails/saveCustomer", $scope.contactDetails).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )
