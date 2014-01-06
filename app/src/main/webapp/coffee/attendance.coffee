app = angular.module "attendance-app",() ->

app.controller "AttendanceCtrl", ["$scope", "$http",($scope, $http) ->
  $scope.user = {}

  $scope.signin = () ->
    console.log "$scope.user"

    $http.post("/employee/attendance/signin", $scope.user).success((data) ->
      console.log "data"
    ).error(()->
      console.log("error in /employee/attendence/signin")
    )

  $scope.leave = () ->
    console.log $scope.user
]