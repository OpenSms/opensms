GsrPaymentCtrl = ($scope, $http, $location) ->
  $scope.gsrOrder = {}
  $scope.paymentMethod = {}
  $scope.paymentMethods = []
  $scope.previousGsrPayments = []
  $scope.gsrPayment = {}

  $http.get("/paymentmethod/all").success((data) ->
    $scope.paymentMethods = data
    $scope.paymentMethod = $scope.paymentMethods[0]
  ).error(()->
    console.log("error in /paymentmethod/all")
  )

  $scope.getGsrOrder = () ->
    console.log 'wok'
    if $scope.gsrOrder.gsrOrderId is undefined
      return

    $http.get("/gsrorder/get?orderId=" + $scope.gsrOrder.gsrOrderId).success((data) ->
      console.log data
      $scope.gsrOrder = data
      $scope.getAllPreviousPayments()
    ).error(() ->
      console.log("error in /grnorder?grnorderid")
    )

  $scope.getAllPreviousPayments = () ->
    $http.get("/gsrpayment/gsrorder/?gsrorderid=" + $scope.gsrOrder.gsrOrderId).success((data) ->
      $scope.previousGrnPayments = data
    ).error(()->
      console.log("error in /grnorder?grnorderid")
    )


  $scope.$watch('gsrOrder.gsrOrderId', () ->
    console.log 'working'
    $scope.getGsrOrder()
  )

  $scope.getTotalGsrOrderAmount = () ->
    if $scope.gsrOrder.iisOrderBatchHasGsrOrderList is undefined
      return

    totalAmount = 0.0
    for batch in $scope.gsrOrder.iisOrderBatchHasGsrOrderList
      console.log batch
      profit = batch.iisOrderHasBatch.batch1.profit
      if not profit
        profit = batch.iisOrderHasBatch.batch1.item.defaultProfit

      totalAmount += batch.quantity * batch.iisOrderHasBatch.batch1.buyingUnitPrice

      if profit.type is 'MARGIN'
        totalAmount += profit.value
      else
        totalAmount += totalAmount * profit.value / 100

    return totalAmount

  $scope.save = () ->
    if $scope.gsrOrder is undefined
      return


    $scope.gsrPayment.gsrOrder = $scope.gsrOrder.gsrOrderId + ''
    $scope.gsrPayment.paymentmethod = $scope.paymentMethod.paymentMethodId + ''
    $scope.gsrPayment.amount = $scope.gsrPayment.amount + ''

    console.log $scope.gsrPayment


    $http({
      method: 'POST',
      url: '/gsrpayment/saved',
      data: $scope.gsrPayment,
      headers: {'Content-Type': 'aapplication/json'}
    }).success((data)->
      console.log data
    ).error((data)->
      console.log data
    );

#    $http.post("/gsrpayment/saved", JSON.stringify ($scope.gsrPayemnt) ).success((data) ->
#      console.log data
#      console.log "grn payment saved."
#      $location.path("/")
#    ).error((data) ->
#      console.log data
#      console.log "error in /grnpayment/save"
#    )


  $scope.totalPreviousPayments = () ->
    totalAmount = 0.0;
    for p in $scope.previousGsrPayments
      totalAmount += p.amount

    return totalAmount