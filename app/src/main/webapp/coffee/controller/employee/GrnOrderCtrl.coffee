GrnOrderCtrl = ($scope, $http,ngTableParams)->
  $scope.vendor={}
  $scope.batchList=[]
  $scope.curruntBatch={}
  $scope.profits = []


  wizardController($scope)



  $scope.finishOrder=()->

    grnOrder=
      vendor:$scope.vendor
      batchList:$scope.batchList

    console.log grnOrder

    $http.post('/grnorder/save',grnOrder).success((data)->

      console.log data

    ).error((data)->
      console.log data
    )


  $scope.tbItemListParam=tableParams($scope.batchList,ngTableParams)


  $scope.$watch('curruntBatch',(newval)->
    console.log newval
    if newval.item
      $http.get('/profit/all?type='+newval.item.defaultProfit.type).success((data)->
        $scope.profits=data
      )
  )

  $scope.addNextItem=()->

    canAdd=true

    for batch in $scope.batchList
      if batch.item.itemId is $scope.curruntBatch.item.itemId
        batch=$scope.curruntBatch
        canAdd=false
        break


    $scope.batchList.push $scope.curruntBatch if canAdd


    console.log  $scope.batchList
    $scope.curruntBatch={}
    $scope.handlePrevious()


  ###Item Select Controller ###
  $scope.items=[]
  $scope.itemSearchString=''

  loadItems=(hint)->
    console.log hint
    $http.get('/item/all?hint='+hint).success((data)->
      $scope.items=data

    ).error((data)->

    )

  $scope.itemSearch=()->
    loadItems($scope.itemSearchString)
  ##loadItems('')##
  $scope.itemTableParams = tableParams($scope.items,ngTableParams)

  $scope.selectItem=(item)->


    canAddNew=true

    for batch in $scope.batchList
      if batch.item.itemId is item.itemId
        $scope.curruntBatch=batch
        canAddNew=false
        break


    if canAddNew
      $scope.curruntBatch=
        item:item

    $scope.handleNext()

  ###User Controller for select vendor    ###

  $scope.users = []
  $scope.vendorsearchString=''

  $scope.searchVendor = () ->
    console.log $scope.vendorsearchString
    $http.get("/user/search?query=" + $scope.vendorsearchString+"&type=vendor").success((data) ->

      $scope.users = data
      console.log data
    ).error((data) ->
      console.log data
    )

  $scope.editUser = (userId) ->
    console.log userId

  $scope.vendorTableParams = tableParams($scope.users,ngTableParams)

  $scope.selectVendor=(vendor)->
    $scope.vendor=vendor
    $scope.handleNext()
    console.log vendor

  ok=''


tableParams=(rows,ngTableParams)->
  new ngTableParams(
    page: 1 # show first page
    count: 10 # count per page
  ,
    total: rows.length # length of data
    getData: ($defer, params) ->
      $defer.resolve rows.slice((params.page() - 1) * params.count(), params.page() * params.count())
  )



wizardController=($scope)->
  #### wizard controller ####
   $scope.steps = ["one", "two", "three", "four"]
   $scope.step = 0
   $scope.wizard =
     tacos: 2
   $scope.isFirstStep = ->
     $scope.step is 0

   $scope.isLastStep = ->
     $scope.step is ($scope.steps.length - 1)

   $scope.isCurrentStep = (step) ->
     $scope.step is step


   $scope.getCurrentStep = ->
     $scope.steps[$scope.step]

   $scope.getNextLabel = ->
     (if ($scope.isLastStep()) then "Submit" else "Next")

   $scope.handlePrevious = ->
     #console.log $scope.step
     if $scope.isFirstStep()
       $scope.step = 0
     else
       $scope.step -= 1

   $scope.handleNext = () ->
     #console.log $scope.step
     if $scope.isLastStep()
       $scope.save()
     else
       $scope.step += 1
