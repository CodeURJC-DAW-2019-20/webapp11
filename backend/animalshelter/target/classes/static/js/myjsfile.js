
let loaded = 0;
let loaded2 = 0;


const spinner = document.getElementById("spinner");//get spinner 

function showSpinner() {//method to show spinner
  spinner.className = "show";
  setTimeout(() => {
    spinner.className = spinner.className.replace("show", "");
  }, 2000);
}


function hideSpinner() {//method to hide spinner after receiving response
spinner.className = spinner.className.replace("show", "");
}


function applyFilter() {
	loaded = 0;
	// clears the div
	document.getElementById("imagebox").innerHTML = "";
	
	// calls the function with selected value
	let button = document.getElementById('add-button');
	let selected = document.getElementById('filter').value;
	loadFilter(selected);
	console.log(selected)
	
	// assign the function to button accordingly
	let cloneButton = button.cloneNode(true);
	button.parentNode.replaceChild(cloneButton, button);
	cloneButton.addEventListener("click", function() { loadFilter(selected) })
	
}

function loadFilter(type) {
	var selected = type;
	console.log(selected);
	showSpinner()
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	hideSpinner()
	    	loaded+=3;
		    var jsonResponse= JSON.parse(this.responseText);
		    console.log(jsonResponse)
		    jsonResponse.forEach(element => {
		    	 const image = element['animalPhoto'];
		    	 const links = element['idAnimal'];
			     let img = document.createElement("img")
			     let link = document.createElement("a")
			   	 link.href = "/animal/"+links
			   	 img.src = "/images/animal/"+image
			     img.className = "allimages"
		   	     link.appendChild(img)
			   	 document.getElementById("imagebox").appendChild(link)
		     })
	    }
	    
    };
    
    xhttp.open("POST",+loaded+"/animals/", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhttp.send("type="+selected);
  
}

function applySuit() {
	// clears the div
	loaded = 0;
	document.getElementById("imagebox").innerHTML = "";
	
	// calls the function with selected value
	let button = document.getElementById('add-button');
	loadSuit();
	
	// assign the function to button accordingly
	let cloneButton = button.cloneNode(true);
	button.parentNode.replaceChild(cloneButton, button);
	cloneButton.addEventListener("click", loadSuit)
}

//Done; applies the suit
function loadSuit() {// for suit you
	showSpinner()
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	 hideSpinner()
	     loaded+=3;
	     var jsonResponse= JSON.parse(this.responseText);
	     console.log(jsonResponse)
	     jsonResponse.forEach(element => {
	    	 const image = element['animalPhoto'];
	    	 const links = element['idAnimal'];

	    	  let img = document.createElement("img")
	    	  
	   	      let link = document.createElement("a")
	   	      
	   	      link.href = "/animal/"+links
	   	      img.src = "/images/animal/"+image
			  img.className = "allimages"
			  link.appendChild(img)
	   	      document.getElementById("imagebox").appendChild(link)


	     })
	     
	    }
	  };
	  
	  xhttp.open("GET", "/suitedAnimal/"+loaded , true);
	  xhttp.send();
	  
}




function applySearch() {
	// clears the div
	loaded = 0;
	document.getElementById("imagebox").innerHTML = "";
	
	// calls the function with search value
	let button = document.getElementById('add-button');
	let search = document.getElementById('names').value;
	loadSearch();
	
	// assign the function to button accordingly
	let cloneButton = button.cloneNode(true);
	button.parentNode.replaceChild(cloneButton, button);
	cloneButton.addEventListener("click", loadSearch);
	
}

//Done; applies the search
function loadSearch() {
	  showSpinner()
	  var selected = document.getElementById('names').value
	  console.log(selected);
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	       hideSpinner()
	       loaded+=3;
	       var jsonResponse= JSON.parse(this.responseText);
	       console.log(jsonResponse)
	       jsonResponse.forEach(element => {
	          const image = element['animalPhoto'];
	          const links = element['idAnimal'];
	          let img = document.createElement("img")	  
	   	      let link = document.createElement("a")   	      
	   	      link.href = "/animal/"+links
	   	      img.src = "/images/animal/"+image
		      img.className = "allimages"
		      link.appendChild(img)
	   	      document.getElementById("imagebox").appendChild(link)
           })
	    }
	  };
	  
	  xhttp.open("POST",+loaded+"/animal/", true);
	  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhttp.send("name="+selected);


	  
}
function Gallery() {//on window load this method will function ..this will show three first images of users gallery and after by clicking loadmore it will show next three images
	showSpinner()

	loaded2 = 0;
	// clears the div
	document.getElementById("imbox").innerHTML = "";
	
	// calls the function with selected value
	let button = document.getElementById('add-loadbutton');
	loadGallery();
	
	// assign the function to button accordingly
	let cloneButton = button.cloneNode(true);
	button.parentNode.replaceChild(cloneButton, button);
	cloneButton.addEventListener("click", loadGallery)
	
}
function loadGallery() {
	showSpinner()
	  
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	hideSpinner()
	       loaded2+=3;

	        jsonResponse= JSON.parse(this.responseText);
	        console.log(jsonResponse)
	        jsonResponse.forEach((element,index) => { 
		      console.log(jsonResponse);
		      let link = document.createElement("a")
	              let img = document.createElement("img")	  
	   	      img.src = jsonResponse[index];
		      link.href=jsonResponse[index];
		      img.className = "allimages"
		      link.appendChild(img)
	   	      document.getElementById("imbox").appendChild(link)
	   	     
         })
	    }
	  };
	  
	  xhttp.open("GET", "/usergallerys/"+loaded2, true);
	  xhttp.send();
	  
}
window.onload=Gallery();



















