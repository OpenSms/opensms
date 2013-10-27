var UpdateUserCtrl;

UpdateUserCtrl = function($scope, $http, $location, $routeParams) {
  $scope.steps = ["one", "two", "three"];
  $scope.step = 0;
  $scope.wizard = {
    tacos: 2
  };
  $scope.user = {};
  $scope.userType = {};
  $scope.contactDetails = {};
  $scope.employee = {};
  $scope.userRole = {};
  $scope.userPassword = {};
  $scope.userRoles = [];
  $scope.roles = [];
  $scope.detailsUpdated = {};
  $scope.detailsUpdated.userPassword = false;
  $scope.detailsUpdated.userAccountState = false;
  $scope.detailsUpdated.contactDetails = false;
  $scope.detailsUpdated.employeeNames = false;
  $http.get("/user?userId=" + $routeParams.userId).success(function(data) {
    delete data.id;
    return $scope.user = data;
  }).error(function(data) {
    return console.log("error while retriving data '/user?userId='");
  });
  $http.get("/contactdetails?userId=" + $routeParams.userId).success(function(data) {
    delete data.user;
    delete data.id;
    return $scope.contactDetails = data;
  }).error(function(data) {
    return console.log("error while retriving data from '/contactdetails?userId='");
  });
  $http.get("/employee?userId=" + $routeParams.userId).success(function(data) {
    delete data.id;
    return $scope.employee = data;
  }).error(function(data) {
    return console.log("error while retriving data from '/employee?userId='");
  });
  $scope.isEmployee = function() {
    return $scope.userType.type === "employee";
  };
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
  $scope.handlePrevious = function() {
    if ($scope.isFirstStep()) {
      return $scope.step -= 0;
    } else {
      if (!$scope.isEmployee() && $scope.step === 2) {
        return $scope.step -= 2;
      } else {
        return $scope.step -= 1;
      }
    }
  };
  $scope.handleNext = function() {
    if ($scope.isLastStep()) {
      return $scope.step += 0;
    } else {
      if (!$scope.isEmployee() && $scope.step === 0) {
        return $scope.step += 2;
      } else {
        return $scope.step += 1;
      }
    }
  };
  $scope.userPasswordChanged = function() {
    if (typeof $scope.userPassword.oldPass !== "undefined" && typeof $scope.userPassword.newPass !== "undefined" && typeof $scope.userPassword.confirmPass !== "undefined") {
      return $scope.detailsUpdated.userPassword = true;
    } else {
      return $scope.detailsUpdated.userPassword = false;
    }
  };
  $scope.userAccountStateChanged = function() {
    return $scope.detailsUpdated.userAccountState = true;
  };
  $scope.contactDetailsChanged = function() {
    return $scope.detailsUpdated.contactDetails = true;
  };
  $scope.employeeNameChanged = function() {
    return $scope.detailsUpdated.employeeNames = true;
  };
  $scope.updateUserPassword = function() {
    if ($scope.detailsUpdated.userPassword === true) {
      $scope.user.password = $scope.userPassword.oldPass;
      return $http.post("/user/validatepassword", $scope.user).success(function(data) {
        if (data === "true") {
          $scope.user.password = $scope.userPassword.newPass;
          $http.post("/user/changepassword", $scope.user).success(function(data) {
            return console.log(data);
          }).error(function(data) {
            return console.log("error in /user/changepassword");
          });
          return console.log("password changed");
        } else {
          return console.log("invalid password");
        }
      }).error(function(data) {
        return console.log("error in /user/validatepassword");
      });
    }
  };
  $scope.updateUserAccountState = function() {
    if ($scope.detailsUpdated.userAccountState === true) {
      return $http.post("/user/updatestate", $scope.user).success(function(data) {
        return console.log(data);
      }).error(function(data) {
        return console.log("error in /user/updatestate");
      });
    }
  };
  $scope.updateContactDetails = function() {
    if ($scope.detailsUpdated.contactDetails === true) {
      $scope.contactDetails.userId = $scope.user.userId;
      console.log($scope.contactDetails);
      return $http.post("/contactdetails/update", $scope.contactDetails).success(function(data) {
        return console.log(data);
      }).error(function(data) {
        return console.log("error in /contactdetails/update");
      });
    }
  };
  $scope.updateEmployeeNames = function() {
    if ($scope.detailsUpdated.employeeNames === true) {
      return $http.post("/employee/updatenames", $scope.employee).success(function(data) {
        return console.log(data);
      }).error(function(data) {
        return console.log("error in /employee/updatenames");
      });
    }
  };
  return $scope.update = function() {
    $scope.updateUserPassword();
    $scope.updateUserAccountState();
    $scope.updateContactDetails();
    return $scope.updateEmployeeNames();
  };
};
