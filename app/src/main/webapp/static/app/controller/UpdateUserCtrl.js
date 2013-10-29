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
  $scope.userPassword = {};
  $scope.userRoles = [];
  $scope.userRole = {};
  $scope.roles = [];
  $scope.detailsUpdated = {};
  $scope.detailsUpdated.userPassword = false;
  $scope.detailsUpdated.userAccountState = false;
  $scope.detailsUpdated.contactDetails = false;
  $scope.detailsUpdated.employeeNames = false;
  $scope.detailsUpdated.employeeRoles = false;
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
  $scope.employeeRoleChanged = function() {
    return $scope.detailsUpdated.employeeRoles = true;
  };
  $http.get("/user?userId=" + $routeParams.userId).success(function(data) {
    delete data.id;
    return $scope.user = data;
  }).error(function(data) {
    return console.log("error while retriving data '/user?userId='");
  });
  $http.get("/contactdetails?userId=" + $routeParams.userId).success(function(data) {
    delete data.user;
    return $scope.contactDetails = data;
  }).error(function(data) {
    return console.log("error while retriving data from '/contactdetails?userId='");
  });
  $http.get("/employee?userId=" + $routeParams.userId).success(function(data) {
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
    var uRolePk, userRole, _i, _len, _ref;
    _ref = $scope.userRoles;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      userRole = _ref[_i];
      if (Number(userRole.role1.roleId) === Number($scope.userRole.role1.roleId) && userRole.resignDate === null) {
        return;
      }
    }
    $scope.userRole.role1.description = $scope.getRoleDescription($scope.userRole.role1.roleId);
    $scope.userRole.active = true;
    uRolePk = {};
    uRolePk = {
      assignDate: new Date().getTime(),
      role: $scope.userRole.role1.roleId,
      user: $routeParams.userId
    };
    $scope.userRole["resignDate"] = null;
    $scope.userRole["userRolePK"] = uRolePk;
    $scope.userRoles.unshift(jQuery.extend(true, {}, $scope.userRole));
    return $scope.employeeRoleChanged();
  };
  $scope.getRoleDescription = function(roleId) {
    var role, _i, _len, _ref;
    _ref = $scope.roles;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      role = _ref[_i];
      if (parseInt(role.roleId) === parseInt(roleId)) {
        return role.description;
      }
    }
  };
  $scope.activateRole = function(index) {
    if ($scope.userRoles[index].active) {
      $scope.userRoles[index].resignDate = new Date().getTime();
      $scope.userRoles[index].active = false;
    } else {
      $scope.userRoles[index].resignDate = null;
      $scope.userRoles[index].active = true;
    }
    return $scope.employeeRoleChanged();
  };
  $http.get("/userrole?userId=" + $routeParams.userId).success(function(data) {
    var d, userType, _i, _len;
    userType = "";
    for (_i = 0, _len = data.length; _i < _len; _i++) {
      d = data[_i];
      $scope.userRoles.unshift(jQuery.extend(true, {}, d));
      if (d.role1.description === "Customer") {
        userType = "customer";
      } else if (d.role1.description === "Vendor") {
        userType = "vendor";
      } else {
        userType = "employee";
      }
    }
    $scope.userType.type = userType;
    return $scope.detailsUpdated.employeeRoles = false;
  }).error(function(data) {
    return console.log("error in /userrole/userId=");
  });
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
  $scope.updateEmployeeRoles = function() {
    var employeeModel;
    if ($scope.detailsUpdated.employeeRoles === false) {
      return;
    }
    employeeModel = {
      employee: $scope.employee,
      userRoles: $scope.userRoles
    };
    console.log(employeeModel);
    return $http.post("/employee/updateroles", $scope.userRoles).success(function(data) {
      return console.log(data);
    }).error(function(data) {
      return console.log("error in /employee/updateroles");
    });
  };
  return $scope.update = function() {
    $scope.updateUserPassword();
    $scope.updateUserAccountState();
    $scope.updateContactDetails();
    $scope.updateEmployeeNames();
    return $scope.updateEmployeeRoles();
  };
};
