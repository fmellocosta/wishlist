var apiData = 'http://jsonplaceholder.typicode.com/posts';
var apiWishlist = 'http://localhost:8080/api';

function getItem(itemId) {
	
	$.ajax({
		url: apiData + "/" + itemId,
		method: "GET",
		success: function(data){
			if ($.trim(data)) {
				addItem(data);
			}
		},
		error(jqXHR, textStatus, errorThrown){
			 console.log(textStatus);
			 console.log(errorThrown);
		}
	});
	
}

function deleteItem(itemId) {
	
	$.ajax({
		url: apiWishlist + "/wishlist/" + itemId,
		type: "DELETE",
		success: function(result) {
			  console.log(result);
		},
		error(jqXHR, textStatus, errorThrown) {
			 console.log(textStatus);
			 console.log(errorThrown);
		}
	});
	
}

function addItem(item) {
	
	$.ajax({
		url: apiWishlist + "/wishlist",
		method: "POST",
		data: JSON.stringify({userId: item.id, title: item.title, body: item.body}),
		dataType: 'json',
		contentType: "application/json",
		success: function(result,status,jqXHR) {
			  console.log(result);
		},
		error(jqXHR, textStatus, errorThrown) {
			 console.log(textStatus);
			 console.log(errorThrown);
		}
	});

}

$(document).ready(function () {

	refreshItems();
	
	$("#refresh").click(function(){
		refreshItems($("#keyword").val());
	});
		
	function refreshItems(userId) {
		
		var suffix = (userId === undefined || userId === "") ? "" : "?userId=" + userId;
		
		$.ajax({
		  	url: apiData + suffix,
			method: 'GET',
			data: {
		    	format: 'json'
		 	},	  
		}).then(function(data) {
			$("#items").empty();
			$.each(data, function(index, item) {
				var rowClass = (index%2 == 0) ? "" : "alt";
				$("#items").append("" +
						"<tr class='" + rowClass + "'>" +
								"<td>" + item.userId + "</td>" +
								"<td>" + item.title + "</td>" +
								"<td><a href='#' onclick='getItem("+item.id+")'>ADD</a> | <a href='#' onclick='deleteItem("+item.id+")'>DEL</a></td>" +
						"</tr>");
			});
		});
		
	}
	
});