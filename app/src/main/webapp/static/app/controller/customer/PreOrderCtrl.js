var PreOrderCtrl;

PreOrderCtrl = function($scope, $http, $location) {
  $scope.items = [];
  $scope.selectedItem = {};
  $scope.preOrderHasItems = [];
  $scope.preOrder = {};
  $http.get("/item/all").success(function(data) {
    return $scope.items = data;
  }).error(function(data) {
    return console.log("error while retriving data '/item/all'");
  });
  $scope.addItem = function() {
    var item, preOrderHasItem, preOrderHasItemPk, preOrderItem, _i, _len, _ref;
    _ref = $scope.preOrderHasItems;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      preOrderItem = _ref[_i];
      if (preOrderItem.item1.itemId === $scope.selectedItem.itemId) {
        return;
      }
    }
    $scope.selectedItem.name = $scope.getItemName($scope.selectedItem.itemId);
    preOrderHasItemPk = {};
    preOrderHasItemPk = {
      item: $scope.selectedItem.itemId
    };
    item = {};
    item = {
      itemId: $scope.selectedItem.itemId,
      name: $scope.selectedItem.name
    };
    preOrderHasItem = {};
    preOrderHasItem = {
      preOrderHasItemPK: preOrderHasItemPk,
      quantity: $scope.selectedItem.quantity,
      item1: item
    };
    return $scope.preOrderHasItems.unshift(preOrderHasItem);
  };
  $scope.removeItem = function(index) {
    return $scope.preOrderHasItems.splice(index, 1);
  };
  $scope.getItemName = function(itemId) {
    var item, _i, _len, _ref;
    _ref = $scope.items;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      item = _ref[_i];
      if (parseInt(item.itemId) === parseInt(itemId)) {
        return item.name;
      }
    }
  };
  return $scope.submitRequest = function() {
    var preOrderModel;
    if ($scope.preOrderHasItems.length === 0) {
      return;
    }
    preOrderModel = {};
    preOrderModel = {
      preOrder: $scope.preOrder,
      preOrderHasItemList: $scope.preOrderHasItems
    };
    return $http.post("/preorder/save", preOrderModel).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log("error in /preorder/save");
    });
  };
};
