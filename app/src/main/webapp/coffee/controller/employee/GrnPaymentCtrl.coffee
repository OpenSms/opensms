GrnPaymentCtrl = ($scope, $http) ->

  $scope.grnOrder = {}
  $scope.paymentMethod = {}
  $scope.paymentMethods = []
  $scope.previousGrnPayments = []

  $http.get("/paymentmethod/all").success((data) ->
    $scope.paymentMethods = data
    $scope.paymentMethod = $scope.paymentMethods[0]
  ).error(()->
    console.log("error in /paymentmethod/all")
  )

  $scope.getGrnOrder = () ->
    if $scope.grnOrder.grnOrderId is undefined
      return

    $http.get("/grnorder?grnorderid=" + $scope.grnOrder.grnOrderId).success((data) ->
      $scope.grnOrder = data
      $scope.getAllPreviousPayments()
    ).error(() ->
      console.log("error in /grnorder?grnorderid")
    )

  $scope.getAllPreviousPayments = () ->
    $http.get("/grnpayment?grnorderid=" + $scope.grnOrder.grnOrderId).success((data) ->
      $scope.previousGrnPayments = data
    ).error(()->
      console.log("error in /grnorder?grnorderid")
    )


  $scope.$watch('grnOrder.grnOrderId', () ->
    $scope.getGrnOrder()
  )

  $scope.getTotalGrnOrderAmount = () ->
    if $scope.grnOrder.batchList is undefined
      return

    totalAmount = 0.0
    for batch in $scope.grnOrder.batchList
      totalAmount += batch.quantity * batch.buyingUnitPrice

    return totalAmount

  $scope.save = () ->
    if $scope.grnOrder.grnOrderId is undefined
      return

    $scope.grnPayment.grnOrder = $scope.grnOrder
    $scope.grnPayment.paymentMethod = $scope.paymentMethod

    $http.post("/grnpayment/save", $scope.grnPayment).success(() ->
      console.log "grn payment saved."
      $location.path("/")
    ).error(() ->
      console.log "error in /grnpayment/save"
    )

    
  $scope.totalPreviousPayments = () ->
    totalAmount = 0.0;
    for p in $scope.previousGrnPayments
      totalAmount += p.amount

    return totalAmount