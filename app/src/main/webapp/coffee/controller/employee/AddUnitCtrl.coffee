AddUnitCtrl = ($scope, $http, $location, $routeParams) ->

  $scope.unit = {}

  console.log "AddUnitCtrl"

  $scope.save = () ->
    $http.post("unit/save", $scope.unit).success((data) ->
      console.log "unit saved"
    ).error((data) ->
      console.log("error in unit/save")
    )