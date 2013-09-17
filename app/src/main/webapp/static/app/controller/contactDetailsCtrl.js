var ContactDetailsCtrl;

ContactDetailsCtrl = function($scope, $http, $location) {
  $scope.contactDetails;
  return $scope.test = function() {
    return console.log($scope.contactDetails);
  };
};
