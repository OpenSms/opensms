var LoginCtrl;

LoginCtrl = function($scope, $http, $location) {
  $scope.user;
  return $scope.login = function() {
    return console.log($scope.user);
  };
};
