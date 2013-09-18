EditUserCtrl = ($scope, $http, $location)->
  $scope.isEmployee=true
  $scope.isVendor=true
  $scope.isCustomer=true
  $scope.user
  $scope.userRoles = []
  $scope.userRole = {}
  $scope.addRole = ()->
    console.log $scope.userRole
    $scope.userRoles.unshift $scope.userRole
  $scope.save = ()->
    console.log $scope.user