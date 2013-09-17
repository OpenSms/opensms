var RegistrationCtrl;

RegistrationCtrl = function($scope, $http, $location) {
  $scope.user;
  return $scope.test = function() {
    return console.log($scope.user);
  };
};
