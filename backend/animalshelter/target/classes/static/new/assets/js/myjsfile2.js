jQuery(function($){
		    size_lenth = $("#myRequestList div").length;
		    x=11;
		    $('#myRequestList div:lt('+x+')').show();
		    $('#load').click(function () {
		        x= (x+11 <= size_li) ? x+11: size_lenth;
		        $('#myRequestList div:lt('+x+')').show();
		        $.ajax({
					type: 'GET',
					success: function(data){
						console.log(data);
					}
				}).done(function() {
		            $("#over").fadeIn(300);　

					setTimeout(function(){
						$("#over").fadeOut(300);
					},500);
				});
		    });
		  
});