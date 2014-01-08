CloseIISOrder=($http,$scope, $rootScope)->

  $scope.userRoles = $rootScope.userRoles
  $scope.iisOrderList = []

  $http.get("/iisorder/all/today").success((data) ->

    $scope.iisOrderList = data
    console.log data

  ).error(() ->
    console.log("error in /iisorder/all/today")
  )

  $scope.closeOrder=(id)->
    $http.post("/iisorder/close?orderid="+id).success((data) ->
    ).error(() ->
      console.log("error in /iisorder/all/today")
    )

  $scope.show = () ->
    for role in $scope.userRoles
      if role.role1.description is 'customer' or role.role1.description is 'vendor'
        return false

    return true