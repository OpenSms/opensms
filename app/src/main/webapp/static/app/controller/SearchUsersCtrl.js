var SearchUsersCtrl;

SearchUsersCtrl = function($scope, $http, $location, ngTableParams) {
  $scope.users = [];
  $scope.search = function() {
    return $http.get("/user/search?query=" + $scope.searchString).success(function(data) {
      return $scope.users = data;
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.editUser = function(userId) {
    return console.log(userId);
  };
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
