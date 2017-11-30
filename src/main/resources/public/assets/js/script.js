var scotchApp = angular.module('scotchApp', [ 'ngRoute' ]);

var apiSearchData = "https://www.adidas.co.uk/api/suggestions/";
var apiWishlist = "http://localhost:8080/api/wishlist/";


scotchApp.config(function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})	
	.when('/wishlist', {
		templateUrl : 'pages/wishlist.html',
		controller : 'wishlistController'
	})

});

scotchApp.directive('preventDefault', function() {
    return function(scope, element, attrs) {
        angular.element(element).bind('click', function(event) {
            event.preventDefault();
            event.stopPropagation();
        });
    }
});

scotchApp.controller('mainController', function($route, $scope, $http) {
	
    $http.get(apiSearchData)
    .then(function(response) {
        $scope.items = response.data.products;
    });
   
    $scope.retrieveItemInList = function(term) {
        $http.get(apiSearchData + term)
        .then(function(response) {
            $scope.items = response.data.products;
        });
    };    
    
    $scope.addItem = function(image, url) {
        $http({
            url: apiWishlist,
            method: "POST",
            data: { 'image' : image, 'url' : url }
        })
        .then(function(response) {
            alert("Item added to your wishlit");
        }, 
        function(response) {
        	alert("Error while adding item...");
        });
    };    
    
    $scope.removeItem = function(item) {
        $http({
            url: apiWishlist + item,
            method: "DELETE"
        })
        .then(function(response) {
            alert("Item removed from your wishlit");
            $route.reload();
        }, 
        function(response) {
        	alert("Error while removing item...");
        });
    };    
    
});

scotchApp.controller('wishlistController', function($scope, $http, $location) {
	
	$scope.retrieveAllItemsInWishlist = function() {
	    $http.get(apiWishlist)
	    .then(function(response) {
	        $scope.items = response.data;
	    });
	}
    
    $scope.retrieveItemInWishlist = function(id) {
    	
    	if (id == "") {
    		$scope.retrieveAllItemsInWishlist();
    		return;
    	}
    	
    	var items = [];
    	$http.get(apiWishlist + id)
        .then(function(response) {
        	items.push(response.data);
        	if (response.data != "") {
        		$scope.items = items;
        	} else {
        		alert("No item found with the specified id");
        		$scope.retrieveAllItemsInWishlist();
        	}
        });
    	
    }
    
	$scope.retrieveAllItemsInWishlist();    
    	
});