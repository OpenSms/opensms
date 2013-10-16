ContactDetailsCtrl = ($scope, $http, $location) ->
  $scope.contactDetails = {}
  $scope.save = ()->
    $scope.contactDetails.userId = 1

    console.log $scope.contactDetails
    $http.post("/contactdetails/saveCustomer", $scope.contactDetails).success((data) ->
      console.log data
    ).error((data) ->
      console.log data
    )