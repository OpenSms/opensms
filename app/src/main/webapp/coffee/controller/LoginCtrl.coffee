LoginCtrl=($scope,$http)->
  $scope.user={}
  $scope.errorMessage = ""
  $scope.isLoginError = false

  $scope.login=()->

    $http.post('/user/login',$scope.user).success((data)->

      if data.type is 'success'
        window.location='/'
      else
        $scope.isLoginError = true
        $scope.errorMessage = "Invalid username or password."

    ).error((data)->

      $scope.isLoginError = true
      $scope.errorMessage = "Server error."

    )

app.controller('LoginCtrl',LoginCtrl)