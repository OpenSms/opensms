var ItemListCtrl;

ItemListCtrl = function($scope, $http, ngTableParams) {
  var loadItems;
  $scope.items = [];
  $scope.searchString = '';
  $scope.search = function() {
    return loadItems($scope.searchString);
  };
  loadItems = function(hint) {
    return $http.get('/item/all?hint=' + hint).success(function(data) {
      return $scope.items = data;
    }).error(function(data) {});
  };
  loadItems('');
  return $scope.tableParams = new ngTableParams({
    page: 1,
    count: 10
  }, {
    total: $scope.items.length,
    getData: function($defer, params) {
      return $defer.resolve($scope.items.slice((params.page() - 1) * params.count(), params.page() * params.count()));
    }
  });
};
