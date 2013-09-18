RegistrationCtrl = ($scope, $http, $location) ->
  $scope.user
  $scope.user.type = "customer"
  $scope.signUp = ->
    console.log $scope.user