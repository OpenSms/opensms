var ItemCtrl;

ItemCtrl = function($scope, $http, $log) {
  var log;
  log = $log;
  $scope.item = {};
  $scope.profit = 'MARGIN';
  $scope.parentCategories = [];
  $scope.categories = [];
  $scope.units = [];
  $scope.profits = [];
  $scope.$watch('profit', function(newval) {
    console.log(newval);
    return $http.get('/profit/all?type=' + newval).success(function(data) {
      return $scope.profits = data;
    });
  });
  $http.get('/unit/all').success(function(data) {
    $scope.units = data;
    return $log.info(data);
  }).error(function(data) {});
  $http.get('/category/allparents?hint=Too').success(function(data) {
    log.info(data);
    return $scope.parentCategories = data;
  }).error(function(data) {});
  $http.get('/category/all?hint=Too').success(function(data) {
    $log.info(data);
    return $scope.categories = data;
  }).error(function(data) {});
  return $scope.addItem = function() {
    console.log($scope.item);
    return $http.post('/item/save', $scope.item).succsess(function(data) {
      return console.log(data);
    }).error(function(data) {});
  };
};
