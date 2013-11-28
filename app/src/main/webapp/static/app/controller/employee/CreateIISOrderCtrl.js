var CreateIISOrderCtrl;

CreateIISOrderCtrl = function($scope, $http, $location, $routeParams) {
  $scope.iisOrder = {};
  $scope.salesArea = {};
  $scope.requiredItems = [];
  $scope.salesEmployee = {};
  $http.get("preorder/all/open").success(function(data) {
    return $scope.preOrderModels = data;
  }).error(function(data) {
    return console.log("error in '/preorder/all/open'");
  });
  $scope.search = {};
  $scope.searchSalesPerson = function() {
    console.log($scope.search.query);
    return $http.get("user/search?query=" + $scope.search.query + "&type=Sales Rep").success(function(data) {
      return $scope.users = data;
    }).error(function(data) {
      return console.log("error in 'user/search?query=_x_?type=Sales Rep");
    });
  };
  $scope.selectSalesPerson = function(salesPerson) {
    $scope.salesEmployee = salesPerson;
    $scope.handleNext();
    return console.log($scope.salesEmployee);
  };
  $scope.getAllRequiredItems = function() {
    var itemAlreadyExists, preOrderHasItem, preOrderModel, requiredItem, _i, _len, _ref, _results;
    $scope.requiredItems = [];
    _ref = $scope.preOrderModels;
    _results = [];
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      preOrderModel = _ref[_i];
      if (!preOrderModel.selected) {
        continue;
      }
      _results.push((function() {
        var _j, _k, _len1, _len2, _ref1, _ref2, _results1;
        _ref1 = preOrderModel.preOrderHasItemList;
        _results1 = [];
        for (_j = 0, _len1 = _ref1.length; _j < _len1; _j++) {
          preOrderHasItem = _ref1[_j];
          itemAlreadyExists = false;
          _ref2 = $scope.requiredItems;
          for (_k = 0, _len2 = _ref2.length; _k < _len2; _k++) {
            requiredItem = _ref2[_k];
            if (requiredItem.item.itemId === preOrderHasItem.item1.itemId) {
              itemAlreadyExists = true;
            }
          }
          if (itemAlreadyExists) {
            _results1.push((function() {
              var _l, _len3, _ref3, _results2;
              _ref3 = $scope.requiredItems;
              _results2 = [];
              for (_l = 0, _len3 = _ref3.length; _l < _len3; _l++) {
                requiredItem = _ref3[_l];
                if (requiredItem.item.itemId === preOrderHasItem.item1.itemId) {
                  requiredItem.quantity += preOrderHasItem.quantity;
                  break;
                } else {
                  _results2.push(void 0);
                }
              }
              return _results2;
            })());
          } else {
            requiredItem = {
              item: preOrderHasItem.item1,
              quantity: preOrderHasItem.quantity
            };
            _results1.push($scope.requiredItems.unshift(requiredItem));
          }
        }
        return _results1;
      })());
    }
    return _results;
  };
  $scope.issueItems = function() {
    var iisOrderModel, preOrderList, preOrderModel, _i, _len, _ref;
    preOrderList = [];
    _ref = $scope.preOrderModels;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      preOrderModel = _ref[_i];
      if (preOrderModel.selected === true) {
        preOrderList.unshift(preOrderModel.preOrder);
      }
    }
    iisOrderModel = {
      iisOrder: {
        salesEmployee: $scope.salesEmployee.userId
      },
      itemModelList: $scope.requiredItems,
      preOrderList: preOrderList
    };
    console.log(iisOrderModel);
    return $http.post("iisorder/save", iisOrderModel).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log("error in iisorder/save");
    });
  };
  $scope.steps = ["one", "two", "three"];
  $scope.step = 0;
  $scope.wizard = {
    tacos: 2
  };
  $scope.isFirstStep = function() {
    return $scope.step === 0;
  };
  $scope.isLastStep = function() {
    return $scope.step === ($scope.steps.length - 1);
  };
  $scope.isCurrentStep = function(step) {
    return $scope.step === step;
  };
  $scope.setCurrentStep = function(step) {
    return $scope.step = step;
  };
  $scope.getCurrentStep = function() {
    return $scope.steps[$scope.step];
  };
  $scope.getNextLabel = function() {
    if ($scope.isLastStep()) {
      return "Submit";
    } else {
      return "Next";
    }
  };
  $scope.handlePrevious = function() {
    if ($scope.isFirstStep()) {
      return $scope.step = 0;
    } else {
      return $scope.step -= 1;
    }
  };
  return $scope.handleNext = function() {
    if ($scope.isLastStep()) {
      $scope.issueItems();
      return $scope.step += 0;
    } else {
      $scope.step += 1;
      if ($scope.step === 2) {
        return $scope.getAllRequiredItems();
      }
    }
  };
};
