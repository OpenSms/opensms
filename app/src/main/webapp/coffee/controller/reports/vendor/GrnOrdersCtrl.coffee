GrnOrdersCtrl = ($scope, $http) ->

  $scope.grnOrders = []

  $http.get("/grnorder/all/current/vendor").success((data) ->
    $scope.grnOrders = data
  ).error(() ->
    console.log("error in /grnorder/all/current/vendor")
  )