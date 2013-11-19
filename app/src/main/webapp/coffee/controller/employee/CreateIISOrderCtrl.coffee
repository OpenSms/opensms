CreateIISOrderCtrl = ($scope, $http, $location, $routeParams) ->

  $scope.iisOrder = {}
  $scope.salesArea = {}
  $scope.requiredItems = []
  $scope.salesEmployee = {}

#  $scope.preOrderModels = []

#  $scope.getPreOrdersAt = () ->
#    $http.get("preorder/at?location=" + $scope.salesArea.name).success((data) ->
#      $scope.preOrderModels = data
#    ).error((data) ->
#      console.log("error in '/preorder/at?location'")
#    )

  $http.get("preorder/all/open").success((data) ->
    $scope.preOrderModels = data
  ).error((data) ->
    console.log("error in '/preorder/all/open'")
  )


  $scope.search = {}

  $scope.searchSalesPerson = () ->
    console.log $scope.search.query
    $http.get("user/search?query=" + $scope.search.query + "&type=Sales Rep").success((data) ->
      $scope.users = data
    ).error((data) ->
      console.log("error in 'user/search?query=_x_?type=Sales Rep")
    )

  $scope.selectSalesPerson=(salesPerson)->
    $scope.salesEmployee = salesPerson
    $scope.handleNext()
    console.log $scope.salesEmployee

  $scope.getAllRequiredItems = () ->
    $scope.requiredItems = []

    for preOrderModel in $scope.preOrderModels
      if !preOrderModel.selected
        continue

      for preOrderHasItem in preOrderModel.preOrderHasItemList
        itemAlreadyExists = false
        for requiredItem in $scope.requiredItems
          if requiredItem.item.itemId is preOrderHasItem.item1.itemId
            itemAlreadyExists = true

        if itemAlreadyExists
          for requiredItem in $scope.requiredItems
            if requiredItem.item.itemId is preOrderHasItem.item1.itemId
              requiredItem.quantity += preOrderHasItem.quantity
              break

        else
          requiredItem =
            item: preOrderHasItem.item1
            quantity: preOrderHasItem.quantity

          $scope.requiredItems.unshift(
            requiredItem
          )


  $scope.issueItems = () ->

    preOrderList = []
    for preOrderModel in $scope.preOrderModels
      if preOrderModel.selected is true
        preOrderList.unshift(
          preOrderModel.preOrder
        )

    iisOrderModel =
      iisOrder:
        salesEmployee:
          $scope.salesEmployee.userId
      itemModelList: $scope.requiredItems
      preOrderList: preOrderList

    console.log iisOrderModel

    $http.post("iisorder/save", iisOrderModel).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in iisorder/save")
    )



  #### wizard controller ####
  # wizard vars
  $scope.steps = ["one", "two", "three"]
  $scope.step = 0
  $scope.wizard =
    tacos: 2

  $scope.isFirstStep = ->
    $scope.step is 0

  $scope.isLastStep = ->
    $scope.step is ($scope.steps.length - 1)

  $scope.isCurrentStep = (step) ->
    $scope.step is step

  $scope.setCurrentStep = (step) ->
    $scope.step = step

  $scope.getCurrentStep = ->
    $scope.steps[$scope.step]

  $scope.getNextLabel = ->
    (if ($scope.isLastStep()) then "Submit" else "Next")

  $scope.handlePrevious = ->
    if $scope.isFirstStep()
      $scope.step = 0
    else
        $scope.step -= 1

  $scope.handleNext = () ->
    if $scope.isLastStep()
      $scope.issueItems()
      $scope.step += 0
    else
      $scope.step += 1
      if ($scope.step is 2)
        $scope.getAllRequiredItems();

#### end of wizard controller ####