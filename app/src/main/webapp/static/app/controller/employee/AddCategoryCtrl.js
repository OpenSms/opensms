var AddCategoryCtrl;

AddCategoryCtrl = function($scope, $http, $location, $routeParams) {
  $scope.category = {};
  $scope.parentCategoryName = "";
  $scope.getParentTypeahead = function(query, callback) {
    return $http.get("/category/all?hint=" + query).success(function(stations) {
      var arr, s, _i, _len;
      arr = [];
      for (_i = 0, _len = stations.length; _i < _len; _i++) {
        s = stations[_i];
        arr.unshift(s.category);
      }
      return callback(arr);
    });
  };
  return $scope.save = function() {
    $http.get("category/allparents?hint=" + $scope.parentCategoryName).success(function(data) {
      return $scope.category.parentCategory = data[0];
    }).error(function(data) {
      return console.log("error in 'category/allparents?hint='");
    });
    return $http.post("category/save", $scope.category).success(function(data) {
      console.log("category saved");
      return $location.path("/");
    }).error(function(data) {
      return console.log("error in category/save");
    });
  };
};
