ContactDetailsCtrl = ($scope, $http, $location) ->
  $scope.contractDetails
  $scope.save = ()->
    console.log $scope.contactDetails