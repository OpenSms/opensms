ItemCtrl = ($scope, $http)->
  $scope.item = {}

  $scope.parentCategories=[]
  $scope.categories=[]

  $http.get('/category/allparents?hint=Too').success((data)->

    console.log data
    $scope.parentCategories=data

  ).error((data)->

  )


  $http.get('/category/all?hint=Too').success((data)->

    console.log data
    $scope.categories=data

  ).error((data)->

  )

