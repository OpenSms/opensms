IisOrdersCtrl = ($scope, $http) ->

  $scope.iisOrderList = []
  console.log "fsfsdf"

  $http.get("/iisorder/all/today").success((data) ->

    $scope.iisOrderList = data
    console.log data

  ).error(() ->
    console.log("error in /iisorder/all/today")
  )
