var RegistrationCtrl;

RegistrationCtrl = function($scope, $http, $location) {
  $scope.user;
  return $scope.signUp = function() {
    console.log($scope.user);
    return $http.post("/user/saveCustomer", $scope.user).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
};
