PreOrdersCtrl = ($scope, $http) ->

  $scope.preOrders = []

  $http.get("/preorder/all/current/customer").success((data) ->
    $scope.preOrders = data
  ).error(() ->
    console.log("error in /preorder/all/current/customer")
  )