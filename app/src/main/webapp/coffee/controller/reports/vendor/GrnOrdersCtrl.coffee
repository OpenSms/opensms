GrnOrdersCtrl = ($scope, $http) ->

  $scope.grnOrders = []

  $http.get("/grnorder/all/current/vendor").success((data) ->
    $scope.grnOrders = data
  ).error(() ->
    console.log("error in /grnorder/all/current/vendor")
  )

  $scope.grnOrderTotalAmount = ($index) ->
    totalAmount = 0.0
    for batch in $scope.grnOrders[$index].batchList
      totalAmount += batch.quantity * batch.buyingUnitPrice

    return totalAmount