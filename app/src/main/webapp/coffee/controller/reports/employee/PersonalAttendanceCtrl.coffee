PersonalAttendanceCtrl = ($scope, $http) ->

  $scope.attendanceList = []

  $http.get("/employee/attendance/currentuser").success((data) ->

    $scope.attendanceList = data

  ).error(() ->
    console.log("error in /employee/attendance/currentuser")
  )