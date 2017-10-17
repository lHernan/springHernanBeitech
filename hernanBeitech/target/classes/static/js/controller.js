var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {
	$scope.submitForm = function() {
		var url = $location.absUrl() + "postcustomer";

		var config = {
			headers : {
				'Accept' : 'text/plain'
			}
		}
		var data = {
			firstname : $scope.firstname,
			lastname : $scope.lastname
		};

		$http.post(url, data, config).then(
				function(response) {
					$scope.postResultMessage = response.data;
				},
				function error(response) {
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});

		$scope.firstname = "";
		$scope.lastname = "";
	}
});

app.controller('getcontroller', function($scope, $http, $location) {
	$scope.getfunction = function() {
		var url = $location.absUrl() + "rest/customer";

		$http.get(url).then(
				function(response) {
					$scope.response = response.data
					alert(response.data);
				},
				function error(response) {
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
	}
});