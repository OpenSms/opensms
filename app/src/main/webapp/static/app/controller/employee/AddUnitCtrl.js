var AddUnitCtrl;

AddUnitCtrl = function($scope, $http, $location, $routeParams) {
  $scope.unit = {};
  return $scope.save = function() {
    return $http.post("unit/save", $scope.unit).success(function(data) {
      return console.log("unit saved");
    }).error(function(data) {
      return console.log("error in unit/save");
    });
  };
};
