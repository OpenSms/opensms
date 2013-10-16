var ContactDetailsCtrl;

ContactDetailsCtrl = function($scope, $http, $location) {
  $scope.contactDetails = {};
  return $scope.save = function() {
    $scope.contactDetails.userId = 1;
    console.log($scope.contactDetails);
    return $http.post("/contactdetails/saveCustomer", $scope.contactDetails).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
};
