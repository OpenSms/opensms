var ItemCtrl;

ItemCtrl = function($scope, $http) {
  $scope.item = {};
  $scope.parentCategories = [];
  $scope.categories = [];
  $http.get('/category/allparents?hint=Too').success(function(data) {
    console.log(data);
    return $scope.parentCategories = data;
  }).error(function(data) {});
  return $http.get('/category/all?hint=Too').success(function(data) {
    console.log(data);
    return $scope.categories = data;
  }).error(function(data) {});
};
