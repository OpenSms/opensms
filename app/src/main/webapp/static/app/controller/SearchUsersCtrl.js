var SearchUsersCtrl;

SearchUsersCtrl = function($scope, $http, $location, ngTableParams) {
  $scope.users = [];
  $http.get("/user/all").success(function(data) {
    return $scope.users = data;
  }).error(function(data) {
    return console.log(data);
  });
  return $scope.tableParams = new ngTableParams({
    page: 1,
    count: 10
  }, {
    total: $scope.users.length,
    getData: function($defer, params) {
      return $defer.resolve($scope.users.slice((params.page() - 1) * params.count(), params.page() * params.count()));
    }
  });
};
