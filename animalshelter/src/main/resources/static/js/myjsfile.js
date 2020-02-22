jQuery(function($){
		    size_li = $("#myList img").length;
		    x=3;
		    $('#myList img:lt('+x+')').show();
		    $('#loadMore').click(function () {
		        x= (x+3 <= size_li) ? x+3 : size_li;
		        $('#myList img:lt('+x+')').show();
		        $.ajax({
					type: 'GET',
					success: function(data){
						console.log(data);
					}
				}).done(function() {
		            $("#overlay").fadeIn(300);　

					setTimeout(function(){
						$("#overlay").fadeOut(300);
					},500);
				});
		    });
		  
});