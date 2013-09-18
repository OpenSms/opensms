var RegistrationCtrl;

RegistrationCtrl = function($scope, $http, $location) {
  $scope.user;
  $scope.user.type = "customer";
  return $scope.signUp = function() {
    return console.log($scope.user);
  };
};
