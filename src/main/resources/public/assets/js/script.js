var scotchApp = angular.module('scotchApp', [ 'ngRoute' ]);

var apiFakeData = "https://jsonplaceholder.typicode.com/posts/";
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

scotchApp.controller('mainController', function($scope, $http) {
	
    $http.get(apiFakeData)
    .then(function(response) {
        $scope.items = response.data;
    });
   
    $scope.getItem = function(itemId) {
        $http.get(apiFakeData + itemId)
        .then(function(response) {
            $scope.addItem(response.data);
        });
    };    

    $scope.addItem = function(item) {
        $http({
            url: apiWishlist,
            method: "POST",
            data: { 'userId' : item.userId, 'title' : item.title, 'body' : item.body }
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