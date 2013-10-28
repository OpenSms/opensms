var LoginCtrl;

LoginCtrl = function($scope, $http) {
  $scope.user = {};
  return $scope.login = function() {
    return $http.post('/user/login', $scope.user).success(function(data) {
      if (data.type === 'success') {
        return window.location = '/';
      }
    }).error(function(data) {});
  };
};

app.controller('LoginCtrl', LoginCtrl);
