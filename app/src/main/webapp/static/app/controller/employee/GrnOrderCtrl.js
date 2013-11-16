var GrnOrderCtrl, tableParams, wizardController;

GrnOrderCtrl = function($scope, $http, ngTableParams) {
  var loadItems, ok;
  $scope.vendor = {};
  $scope.batchList = [];
  $scope.curruntBatch = {};
  $scope.profits = [];
  wizardController($scope);
  $scope.finishOrder = function() {
    var grnOrder;
    grnOrder = {
      vendor: $scope.vendor,
      batchList: $scope.batchList
    };
    console.log(grnOrder);
    return $http.post('/grnorder/save', grnOrder).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.tbItemListParam = tableParams($scope.batchList, ngTableParams);
  $scope.$watch('curruntBatch', function(newval) {
    console.log(newval);
    if (newval.item) {
      return $http.get('/profit/all?type=' + newval.item.defaultProfit.type).success(function(data) {
        return $scope.profits = data;
      });
    }
  });
  $scope.addNextItem = function() {
    var batch, canAdd, _i, _len, _ref;
    canAdd = true;
    _ref = $scope.batchList;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      batch = _ref[_i];
      if (batch.item.itemId === $scope.curruntBatch.item.itemId) {
        batch = $scope.curruntBatch;
        canAdd = false;
        break;
      }
    }
    if (canAdd) {
      $scope.batchList.push($scope.curruntBatch);
    }
    console.log($scope.batchList);
    $scope.curruntBatch = {};
    return $scope.handlePrevious();
  };
  /*Item Select Controller
  */

  $scope.items = [];
  $scope.itemSearchString = '';
  loadItems = function(hint) {
    console.log(hint);
    return $http.get('/item/all?hint=' + hint).success(function(data) {
      return $scope.items = data;
    }).error(function(data) {});
  };
  $scope.itemSearch = function() {
    return loadItems($scope.itemSearchString);
  };
  $scope.itemTableParams = tableParams($scope.items, ngTableParams);
  $scope.selectItem = function(item) {
    var batch, canAddNew, _i, _len, _ref;
    canAddNew = true;
    _ref = $scope.batchList;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      batch = _ref[_i];
      if (batch.item.itemId === item.itemId) {
        $scope.curruntBatch = batch;
        canAddNew = false;
        break;
      }
    }
    if (canAddNew) {
      $scope.curruntBatch = {
        item: item
      };
    }
    return $scope.handleNext();
  };
  /*User Controller for select vendor
  */

  $scope.users = [];
  $scope.vendorsearchString = '';
  $scope.searchVendor = function() {
    console.log($scope.vendorsearchString);
    return $http.get("/user/search?query=" + $scope.vendorsearchString + "&type=vendor").success(function(data) {
      $scope.users = data;
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.editUser = function(userId) {
    return console.log(userId);
  };
  $scope.vendorTableParams = tableParams($scope.users, ngTableParams);
  $scope.selectVendor = function(vendor) {
    $scope.vendor = vendor;
    $scope.handleNext();
    return console.log(vendor);
  };
  return ok = '';
};

tableParams = function(rows, ngTableParams) {
  return new ngTableParams({
    page: 1,
    count: 10
  }, {
    total: rows.length,
    getData: function($defer, params) {
      return $defer.resolve(rows.slice((params.page() - 1) * params.count(), params.page() * params.count()));
    }
  });
};

wizardController = function($scope) {
  $scope.steps = ["one", "two", "three", "four"];
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
      return $scope.save();
    } else {
      return $scope.step += 1;
    }
  };
};
