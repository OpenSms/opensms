ItemListCtrl=($scope,$http,ngTableParams)->
  $scope.items=[]

  $scope.searchString=''

  $scope.search=()->
    loadItems($scope.searchString)





  loadItems=(hint)->
    $http.get('/item/all?hint='+hint).success((data)->
      $scope.items=data

    ).error((data)->

    )


  loadItems('')


  $scope.tableParams = new ngTableParams(
    page: 1 # show first page
    count: 10 # count per page
  ,
    total: $scope.items.length # length of data
    getData: ($defer, params) ->
      $defer.resolve $scope.items.slice((params.page() - 1) * params.count(), params.page() * params.count())
  )