SearchUsersCtrl = ($scope, $http, $location, ngTableParams) ->
  $scope.users = []

  $scope.search = () ->
    $http.get("/user/search?query=" + $scope.searchString).success((data) ->
        $scope.users = data
    ).error((data) ->
      console.log data
    )

  $scope.editUser = (userId) ->
    console.log userId

  $scope.tableParams = new ngTableParams(
    page: 1 # show first page
    count: 10 # count per page
  ,
    total: $scope.users.length # length of data
    getData: ($defer, params) ->
      $defer.resolve $scope.users.slice((params.page() - 1) * params.count(), params.page() * params.count())
  )