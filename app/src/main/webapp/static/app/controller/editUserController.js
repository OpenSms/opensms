var EditUserCtrl;

EditUserCtrl = function($scope, $http, $location) {
  $scope.isEmployee = true;
  $scope.isVendor = true;
  $scope.isCustomer = true;
  $scope.user;
  $scope.userRoles = [];
  $scope.userRole = {};
  $scope.addRole = function() {
    console.log($scope.userRole);
    return $scope.userRoles.unshift($scope.userRole);
  };
  return $scope.save = function() {
    return console.log($scope.user);
  };
};
