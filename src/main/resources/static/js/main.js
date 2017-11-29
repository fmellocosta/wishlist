$(document).ready(function () {

	var root = 'http://jsonplaceholder.typicode.com/posts';
	
	refreshItems();
	
	$("#refresh").click(function(){
		refreshItems($("#keyword").val());
	});
	
	function refreshItems(userId) {
		
		var suffix = (userId === undefined || userId === "") ? "" : "?userId=" + userId;
		var url = root + suffix;
		
		$.ajax({
		  	url: url,
			method: 'GET',
			data: {
		    	format: 'json'
		 	},	  
		}).then(function(data) {
			$("#items").empty();
			$.each(data, function(index, item) {
				var rowClass = (index%2 == 0) ? "" : "alt";
				var link = "/api/wishlist/populate/" + item.id;
				$("#items").append("" +
						"<tr class='" + rowClass + "'>" +
								"<td>" + item.userId + "</td>" +
								"<td>" + item.title + "</td>" +
								"<td><a href='" + link + "' target='_blank'>ADD</a></td>" +
						"</tr>");
			});
		});
		
	}
	
});