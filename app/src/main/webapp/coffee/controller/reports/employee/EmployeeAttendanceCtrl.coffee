EmployeeAttendanceCtrl = ($scope, $http) ->

  $scope.attendanceList = []

  $http.get("/employee/attendance/all").success((data) ->

    $scope.attendanceList = data

  ).error(() ->
    console.log("error in /employee/attendance/all")
  )