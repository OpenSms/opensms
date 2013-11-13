PreOrderCtrl = ($scope, $http, $location) ->

  # items retrived from /item/all
  $scope.items = []

  # item selected from item list
  $scope.selectedItem = {}

  # items that user order
  $scope.preOrderHasItems = []


  # preodrder object, stores preorder priority
  $scope.preOrder = {}



  $http.get("/item/all").success((data) ->
    $scope.items = data
    #console.log data
  ).error((data) ->
    console.log("error while retriving data '/item/all'")
  )

  $scope.addItem = () ->
    # grr.. need to check whether user adding already inserted item
    for preOrderItem in $scope.preOrderHasItems
      if preOrderItem.item1.itemId is $scope.selectedItem.itemId
        return

    $scope.selectedItem.name = $scope.getItemName($scope.selectedItem.itemId)

    # first we need to create PreOrderHasItemPK object
    preOrderHasItemPk = {}
    preOrderHasItemPk =
      item: $scope.selectedItem.itemId
      #preOrder: right now we don't have an id

    # uh it needs an item object too. :(
    item = {}
    item =
      itemId: $scope.selectedItem.itemId
      name: $scope.selectedItem.name # using this to show name in selected item table :D

    # create IreOrderHasItem object with above id
    preOrderHasItem = {}
    preOrderHasItem =
      preOrderHasItemPK: preOrderHasItemPk
      quantity: $scope.selectedItem.quantity
      item1: item

    $scope.preOrderHasItems.unshift (
      preOrderHasItem
    )

  # if there is a add method then we also need a remove method.
  $scope.removeItem = (index)->
    $scope.preOrderHasItems.splice(index, 1)

  # use to get setected item's name
  $scope.getItemName = (itemId) ->
    for item in $scope.items
      if parseInt(item.itemId) is parseInt(itemId)
        return item.name


  $scope.submitRequest = () ->
    if $scope.preOrderHasItems.length is 0
      return

    # creating PreOrderModel object
    preOrderModel = {}
    preOrderModel =
      preOrder: $scope.preOrder
      preOrderHasItemList: $scope.preOrderHasItems

    # ...and save it yay!!!
    $http.post("/preorder/save", preOrderModel).success((data) ->
      console.log data
    ).error((data) ->
      console.log("error in /preorder/save")
    )

# p.s.: i'm suffering from a headache :(