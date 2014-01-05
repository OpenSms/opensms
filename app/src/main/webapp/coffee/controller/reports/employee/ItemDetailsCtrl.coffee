ItemDetailsCtrl = ($scope, $http) ->

  $scope.item = {}
  $scope.batchList = []

  $scope.$watch('item.itemId', () ->
    $http.get("/item/get?itemid=" + $scope.item.itemId).success((data) ->

      $scope.item = data

      $http.get("/batch/all?itemid=" + $scope.item.itemId).success((data) ->
        $scope.batchList = data
      ).error(() ->
        console.log("error in /batch/all?itemid=")
      )

    ).error(() ->
      console.log("error in /item/get?itemid=")
    )
  )


