GsrOrdersCtrl = ($scope, $http) ->

  $scope.gsrOrders = []

  $http.get("/gsrorder/all/current/customer").success((data) ->
    $scope.gsrOrders = data
  ).error(() ->
    console.log("error in /gsrorder/all/current/customer")
  )