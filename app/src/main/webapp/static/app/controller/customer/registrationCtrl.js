var RegistrationCtrl;

RegistrationCtrl = function($scope, $http, $location) {
  $scope.user;
  return $scope.signUp = function() {
    console.log($scope.user);
    $http.post("/user/saveCustomer", $scope.user).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
    $scope.contactDetails.userId = 1;
    console.log($scope.contactDetails);
    return $http.post("/contactdetails/saveCustomer", $scope.contactDetails).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
};
