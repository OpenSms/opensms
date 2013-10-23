var RegistrationWizardCtrl;

RegistrationWizardCtrl = function($scope, $http, $location) {
  $scope.user = {};
  $scope.userType = {};
  $scope.contactDetails = {};
  $scope.employee = {};
  $scope.userRoles = [];
  $scope.userRole = {};
  $scope.roles = [];
  $http.get("/role/all").success(function(data) {
    return $scope.roles = data;
  }).error(function(data) {
    return console.log(data);
  });
  $scope.addRole = function() {
    var userRole, _i, _len, _ref;
    console.log($scope.userRole);
    _ref = $scope.userRoles;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      userRole = _ref[_i];
      if (userRole.role1 === $scope.userRole.role1) {
        return;
      }
    }
    return $scope.userRoles.unshift({
      role1: $scope.userRole.role1,
      active: $scope.userRole.active
    });
  };
  $scope.getRoleDescription = function(roleId) {
    var role, _i, _len, _ref;
    console.log(roleId);
    _ref = $scope.roles;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      role = _ref[_i];
      console.log(role.roleId);
      if (parseInt(role.roleId) === parseInt(roleId)) {
        return role.description;
      }
    }
  };
  $scope.removeRole = function(index) {
    return $scope.userRoles.splice(index, 1);
  };
  $scope.saveUser = function(postMethod) {
    return $http.post("/user/save", $scope.user).success(function(data) {
      console.log(data);
      $scope.user.userId = data;
      return postMethod();
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.saveContactDetails = function() {
    $scope.contactDetails.userId = $scope.user.userId;
    return $http.post("/contactdetails/save", $scope.contactDetails).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.saveEmployee = function() {
    var employeeModel, employeeRoles, role, _i, _len, _ref;
    $scope.employee.userId = $scope.user.userId;
    employeeRoles = [];
    _ref = $scope.userRoles;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      role = _ref[_i];
      employeeRoles.push({
        roleId: role.role1,
        description: $scope.getRoleDescription(role.role1)
      });
    }
    employeeModel = {
      employee: $scope.employee,
      roles: employeeRoles
    };
    console.log(employeeModel);
    return $http.post("/employee/save", employeeModel).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.saveCustomer = function() {
    var customer;
    customer = {
      userId: $scope.user.userId,
      name: $scope.contactDetails.name,
      remark: 0
    };
    return $http.post("/customer/save", customer).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.saveVendor = function() {
    var vendor;
    vendor = {
      userId: $scope.user.userId,
      name: $scope.contactDetails.name,
      remark: 0
    };
    return $http.post("/vendor/save", vendor).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log(data);
    });
  };
  $scope.saveEmployeeRoles = function() {
    return console.log("save employee roles");
  };
  $scope.save = function() {
    console.log("save()");
    return $scope.saveUser(function() {
      $scope.saveContactDetails();
      if ($scope.userType.type === "employee") {
        return $scope.saveEmployee();
      } else if ($scope.userType.type === "customer") {
        return $scope.saveCustomer();
      } else {
        return $scope.saveVendor();
      }
    });
  };
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
      if (!$scope.isEmployee() && $scope.step === 3) {
        return $scope.step -= 2;
      } else {
        return $scope.step -= 1;
      }
    }
  };
  $scope.handleNext = function() {
    if ($scope.isLastStep()) {
      return $scope.save();
    } else {
      if (!$scope.isEmployee() && $scope.step === 1) {
        return $scope.step += 2;
      } else {
        return $scope.step += 1;
      }
    }
  };
  return $scope.isEmployee = function() {
    return $scope.userType.type === "employee";
  };
};
