var app = angular.module('app', ['ui.router']);

app.controller('orderCtrl', function($scope, $http, $state) {
	
	$scope.order = {
		customer : {
			customerId: parseInt($state.params.customerId),
		},
		orderDetails : []
	};
	
	$scope.selectedProducts = {};
	
	var url = "../rest/customer/" + $state.params.customerId;
	
	$http.get(url).then(function(response) {
		$scope.customer = response.data
	}, function error(response) {
		$scope.postResultMessage = "Error with status: " + response.statusText;
	});
	
	$scope.createOrder = function() {
		
		var url = "../rest/order/"

		var config = {
			headers : {
				'Content-Type' : 'application/json'
			}
		}
		
		var flag = true;
		
		for(productId in $scope.selectedProducts){
		    var selectedProduct = $scope.selectedProducts[productId];
		    
		    if(selectedProduct.selected){
		    	
		    	if(!selectedProduct.quantity){
		    		alert("You must select a quantity for product [" + productId + "]");
		    		flag = false;
		    		break;
		    	}
		    	
		    	var orderDetail = {
	    			product: {
	    					productId: parseInt(productId)
	    			},
	    			productQuantity: selectedProduct.quantity 
			    }
			    
			    $scope.order.orderDetails.push(orderDetail);
		    }
		}

		if(flag){
			$http.post(url, $scope.order, config).then(
				function(response) {
					var createdOrder = response.data;
					alert("The order [" + createdOrder.orderId + "] has been created");
					$state.go("customer");
				},
				function error(response) {
					$scope.postResultMessage = "Error with status: "
							+ response.statusText;
				});
		}
	}
});

app.controller('customerCtrl', function($scope, $http, $location, $filter) {
	
	var url = "../rest/customer";

	var myDate = new Date();
	previousMonth = new Date(myDate);
	previousMonth.setMonth(myDate.getMonth() - 1); // mes pasado
	
	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1); // +1 dia para que funcione el between hasta el dia actual a las 24 hrs
	
	var startDate = $filter('date')(previousMonth, "yyyy-MM-dd");
	var endDate = $filter('date')(tomorrow, "yyyy-MM-dd");
	
	$scope.hasChanged = function() {
		var url2 = "../rest/order/customer/"
				+ $scope.selectedCustomer.customerId + "?initialDate="
				+ startDate + "&finalDate=" + endDate;
		
		$http.get(url2).then(function(response) {
			$scope.orders = response.data
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " + response.statusText;
		});
	}
	
	$http.get(url).then(function(response) {
		$scope.customers = response.data
		$scope.selectedCustomer =  "";
	}, function error(response) {
		$scope.postResultMessage = "Error with status: " + response.statusText;
	});
});

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/customer');

    $stateProvider

        .state('customer', {
        	url: '/customer',
    		templateUrl: '/views/customer.html',
            controller: 'customerCtrl'
        })
        .state('order', {
        	url: '/order/:customerId',
    		templateUrl: '/views/order.html',
            controller: 'orderCtrl'
        });

});