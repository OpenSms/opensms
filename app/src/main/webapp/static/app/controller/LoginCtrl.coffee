LoginCtrl=($scope,$http)->
  $scope.user={}


  $scope.login=()->

    $http.post('/user/login',$scope.user).success((data)->


      if data.type is 'success'

        window.location='/'


    ).error((data)->

    )