var AddUnitCtrl;

AddUnitCtrl = function($scope, $http, $location, $routeParams) {
  $scope.unit = {};
  return $scope.save = function() {
    return $http.post("unit/save", $scope.unit).success(function(data) {
      console.log("unit saved");
      return $location.path("/");
    }).error(function(data) {
      return console.log("error in unit/save");
    });
  };
};
