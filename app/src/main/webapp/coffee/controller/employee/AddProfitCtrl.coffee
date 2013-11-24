AddProfitCtrl = ($scope, $http, $location, $routeParams) ->

  $scope.profit = {}

  $scope.save = () ->
    console.log $scope.profit

    $http.post("profit/save", $scope.profit).success((data) ->
      console.log "profit saved"
    ).error((data) ->
      console.log "error in profit/save"
    )