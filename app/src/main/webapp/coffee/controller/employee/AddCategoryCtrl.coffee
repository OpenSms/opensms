AddCategoryCtrl = ($scope, $http, $location, $routeParams) ->

  $scope.category = {}
  $scope.parentCategoryName = ""


  $scope.getParentTypeahead = (query, callback) ->
    $http.get("/category/all?hint=" + query).success (stations) ->

      arr = []
      for s in stations
        arr.unshift(
          s.category
        )
      callback arr



  $scope.save = () ->
    $http.get("category/allparents?hint=" + $scope.parentCategoryName).success((data)->
      $scope.category.parentCategory = data[0]
    ).error((data) ->
      console.log("error in 'category/allparents?hint='")
    )

    $http.post("category/save", $scope.category).success((data) ->
      console.log "category saved"
    ).error((data) ->
      console.log "error in category/save"
    )