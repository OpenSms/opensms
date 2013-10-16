EditUserCtrl = ($scope, $http, $location)->
  $scope.isEmployee=true
  $scope.isVendor=true
  $scope.isCustomer=true
  $scope.user
  $scope.userRoles = []
  $scope.userRole = {}
  $scope.addRole = ()->
    console.log 'print'
    console.log  $scope.userRole
    $scope.userRoles.unshift (
      type:$scope.userRole.type
      state:$scope.userRole.state
    )
  $scope.save = ()->
    console.log $scope.user