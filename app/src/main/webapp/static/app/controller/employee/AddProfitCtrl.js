var AddProfitCtrl;

AddProfitCtrl = function($scope, $http, $location, $routeParams) {
  $scope.profit = {};
  return $scope.save = function() {
    console.log($scope.profit);
    return $http.post("profit/save", $scope.profit).success(function(data) {
      return console.log("profit saved");
    }).error(function(data) {
      return console.log("error in profit/save");
    });
  };
};
