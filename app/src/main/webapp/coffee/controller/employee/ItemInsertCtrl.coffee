ItemCtrl = ($scope, $http, $log)->
  log = $log

  $scope.item = {}
  $scope.profit='MARGIN'

  $scope.parentCategories = []
  $scope.categories = []
  $scope.units = []
  $scope.profits = []



  $scope.$watch('profit',(newval)->
    console.log newval
    $http.get('/profit/all?type='+newval).success((data)->
      $scope.profits=data
    )
  )

  $http.get('/unit/all').success((data)->
    $scope.units = data
    $log.info(data)
  ).error((data)->

  )


  $http.get('/category/allparents?hint=').success((data)->
    log.info(data)
    $scope.parentCategories = data

  ).error((data)->

  )


  $http.get('/category/all?hint=').success((data)->
    $log.info(data)
    $scope.categories = data

  ).error((data)->

  )


  $scope.addItem=()->

    console.log $scope.item

    $http.post('/item/save',$scope.item).success((data)->
    
      console.log data


    ).error((data)->
    
    )
    


