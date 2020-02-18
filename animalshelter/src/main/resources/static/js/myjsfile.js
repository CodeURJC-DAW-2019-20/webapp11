jQuery(function($){
    var links = [
      "img/dg1.jpg",
      "img/ct6.jpg",
      "img/dg3.jpg",
      "img/ct5.jpg",
      "img/dg5.jpg",
      "img/ct7.jpg",
      "img/dg7.jpg",
      "img/dg1.jpg",
      "img/dg1.jpg",

    ];
    
    var $gallery  = $(".lightgallery1").first();
    var $loadMore = $("#button");

    
    for(var i = 0; i < 3; i++) {
     
        if(links.length) {
    
          var src   = links.pop();
          var $link = $("<a>");
          var $img  = $("<img>");
    
          $img.attr("src", src);
          $link.attr("href", src).addClass("item");
          
          $link.append($img);
          $gallery.append($link);
        }
    }
	
   
     function loadMore(e) {
       
        for(var i = 0; i < 3; i++) {
     
            if(links.length) {
        
              var src   = links.pop();
              var $link = $("<a>");
              var $img  = $("<img>");
        
              $img.attr("src", src);
              $link.attr("href", src).addClass("item");
              
              $link.append($img);
              $gallery.append($link);
            }
            else{
                $loadMore.hide();

            }
        }
        
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
    	
}
    $loadMore.on("click", loadMore);
});