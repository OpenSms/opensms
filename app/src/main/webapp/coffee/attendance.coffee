app = angular.module "attendance-app", () ->


AttendanceCtrl = ($scope, $http) ->
  $scope.user = {}
  $scope.signed = false

  $scope.signin = () ->
    $http.post("/employee/attendance/signin", $scope.user).success((data) ->
      $scope.signed = data.type is "success"

      if $scope.signed
        alert "Signed In"
        $scope.user = {}
      else
        alert "Sign In Faild"
        $scope.user = {}

    ).error(() ->
      $scope.signed = false
      alert "Sign In Faild"
      $scope.user = {}
    )

  $scope.leave = () ->
    $http.post("/employee/attendance/leave", $scope.user).success((data) ->
      $scope.leaved = data.type is "success"

      if $scope.leaved
        alert "Leaved"
        $scope.user = {}
      else
        alert "Leave Faild"
        $scope.user = {}

    ).error(() ->
      $scope.leaved = false
      alert "Leave Faild"
      $scope.user = {}
    )




app.controller "AttendanceCtrl" , AttendanceCtrl