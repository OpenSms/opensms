AddUnitCtrl = ($scope, $http, $location, $routeParams) ->

  $scope.unit = {}

  $scope.save = () ->
    $http.post("unit/save", $scope.unit).success((data) ->
      console.log "unit saved"
      $location.path("/")
    ).error((data) ->
      console.log("error in unit/save")
    )