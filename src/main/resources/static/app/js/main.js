var hotelApp = angular.module("hotelApp",["ngRoute"]);

hotelApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html'
		})
		.when('/gosti', {
			templateUrl : '/app/html/gost.html'
		})
		.when('/gosti/add', {
			templateUrl : '/app/html/gost-add.html'
		})
		.when('/gosti/edit/:id', {
			templateUrl : '/app/html/gost-edit.html'
		})
		.when('/cenovnik', {
			templateUrl : '/app/html/cenovnik.html'
		})
		.when('/sobe', {
			templateUrl : '/app/html/soba.html'
		})
		.when('/sobe/edit/:id', {
			templateUrl : '/app/html/soba-edit.html'
		})
		.when('/rezervacije', {
			templateUrl : '/app/html/rezervacije.html'
		})
		.when('/rezervacije/add', {
			templateUrl : '/app/html/rezervacija-add.html'
		})
		.when('/rezervacije/edit/:id', {
			templateUrl : '/app/html/rezervacija-edit.html'
		})
		
		.otherwise({
			redirectTo: '/'
		});
}]);



//  Gosti

hotelApp.controller("GostCtrl", function($scope, $http, $location, $window){
	
	var url = "/gosti";
	
	$scope.gosti = [];
	
	$scope.search = {};
	$scope.search.naziv = "";
	$scope.search.mesto = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getGosti = function(){
		
		var config = {params:{}};
		
		if($scope.search.naziv != ""){
			config.params.naziv = $scope.search.naziv;
		}
		if($scope.search.mesto != ""){
			config.params.mesto = $scope.search.mesto;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.gosti = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch gosti");
			}
		);
	}
	
	getGosti();
	
	
	$scope.goToAdd = function(){
		$location.path("/gosti/add" );
	}
	
		
	$scope.goToEdit = function(id){
		$location.path("/gosti/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getGosti(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the Gost")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getGosti();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getGosti();
	}
	
	
	$scope.podatak = {};
	$scope.podatak.gostId = "";
	$scope.podatak.gostNaziv = "";
	$scope.podatak.sobaId = "";
	$scope.podatak.sobaNaziv = "";
	$scope.podatak.ulaz = "";
	$scope.podatak.izlaz = "";
	$scope.podatak.brojDana = "";
	$scope.podatak.cena = "";

	 $scope.GetDetails = function (index) { 	
	    	var idG = $scope.gosti[index].id;
	    	$http.get(url+ "/" + idG + "/podatakGosta").then(
		 			function success(response){
		 				$scope.podatak = response.data;
		 				$scope.podatak.gostId = response.data[0];
		 				$scope.podatak.gostNaziv = response.data[1];
		 				$scope.podatak.sobaId = response.data[2];
		 				$scope.podatak.sobaNaziv = response.data[3];
		 				$scope.podatak.ulaz = response.data[4];
		 				$scope.podatak.izlaz = response.data[5];
		 				$scope.podatak.brojDana = response.data[6];
		 				$scope.podatak.cena = response.data[7];
		 			},
		 			function error(){
		 				alert("Couldn't fetch gost podatak.");
		 			}
		 		);
	     };
	
	
	
});


hotelApp.controller("EditGostCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var gostiUrl = "/gosti/" + $routeParams.id;
		
	$scope.gost = {};
	$scope.gost.naziv = "";
	$scope.gost.mesto = "";
	$scope.gost.jmbg = "";
	$scope.gost.telefon = "";
	
	var getGosti = function(){
		$http.get(gostiUrl).then(
			function success(res){
				$scope.gost = res.data;
			},
			function error(){
				alert("Couldn't fetch gost.");
			}
		);
	}
	
	getGosti();
	
	
	$scope.doEdit = function(){
		$http.put(gostiUrl, $scope.gost).then(
			function success(){
				$location.path("/gosti");
			},
			function error(response){
			   alert("Niste dobro popunili podatke!");  
			}
		);
	}
	
	
	
});



hotelApp.controller("AddGostCtrl", function($scope, $http, $routeParams, $location){
	
	var gostiUrl = "/gosti";
		
	$scope.newGost = {};
	$scope.newGost.naziv = "";
	$scope.newGost.mesto = "";
	$scope.newGost.jmbg = "";	
	$scope.newGost.telefon = "";
	

	var getGosti = function(){
		$http.get(gostiUrl).then(
			function success(res){
				$scope.gost = res.data;
			},
			function error(){
				alert("Couldn't fetch gost.");
			}
		);
	}
	
	getGosti();
	
	
	$scope.doAdd = function(){
		$http.post(gostiUrl, $scope.newGost).then(
			function success(res){
				$location.path("/gosti");
			},
			function error(){
				alert("Couldn't fetch gost.");
			}
		);
	}
	
	
});



//  Cenovnik

hotelApp.controller("CenovnikCtrl", function($scope, $http, $location, $window){
	
	var url = "/cenovniksobe";
	
	$scope.listasoba = [];
		
	var getCene = function(){
				
		var promise = $http.get(url);
	    promise.then(
	 		function success(res){ 
				$scope.listasoba = res.data;
			},
			function error(){
				alert("Couldn't fetch cenovnik");
			}
		);
	}
	
	getCene();
	
		
	
});



// Sobe

hotelApp.controller("SobaCtrl", function($scope, $http, $location, $window){
	
	var url = "/sobe";
	
	$scope.sobe = [];
	
	$scope.search = {};
	$scope.search.naziv = "";
	$scope.search.brojKreveta = "";
	$scope.search.slobodnoTekst = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	
	var getSobe = function(){
		
		var config = {params:{}};
		
		if($scope.search.naziv != ""){
			config.params.naziv = $scope.search.naziv;
		}
		if($scope.search.brojKreveta != ""){
			config.params.brojKreveta = $scope.search.brojKreveta;
		}
		if($scope.search.slobodnoTekst != ""){
			config.params.slobodnoTekst = $scope.search.slobodnoTekst;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.sobe = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch sobe");
			}
		);
	}
	
	getSobe();
	
	
	$scope.goToEdit = function(id){
		$location.path("/sobe/edit/" + id);
	}
	
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getSobe();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getSobe();
	}
	
	
	
	
	// REZERVACIJE
	
	var rezervacijeUrl = "/rezervacije";
	
	$scope.rezervacije = [];
	
	$scope.pageNumR=0;
	$scope.totalPagesR = 1;
	
	var getRezervacije = function(){
		
		var configR = {paramsR:{}};
		configR.paramsR.pageNumR = $scope.pageNumR;
		
		var promise = $http.get(rezervacijeUrl, configR);
		promise.then(
			function success(res){
				$scope.rezervacije = res.data;
				$scope.totalPagesR = res.headers("totalPagesR");
			},
			function error(){
				alert("Couldn't fetch rezervacije");
			}
		);
	}
	
	getRezervacije();
	
		
	$scope.changePageR = function(direction){
		$scope.pageNumR += direction;
		getRezervacije();
	}	
	
	
});


hotelApp.controller("EditSobaCtrl", function($scope, $http, $routeParams, $location , $log , $window ){
	
	var sobeUrl = "/sobe/" + $routeParams.id;
		
	$scope.soba = {};
	$scope.soba.naziv = "";
	$scope.soba.brojKreveta = "";
	$scope.soba.slobodnoTekst = "";
	
	
	var getSobe = function(){
		$http.get(sobeUrl).then(
			function success(res){
				$scope.soba = res.data;
			},
			function error(){
				alert("Couldn't fetch gost.");
			}
		);
	}
	
	getSobe();
	
	
	$scope.doEdit = function(){
		$http.put(sobeUrl, $scope.soba).then(
			function success(){
				$location.path("/sobe");
			},
			function error(response){
			   alert("Niste dobro popunili podatke!");  
			}
		);
	}
	
	
	
});






//  Rezervacije

hotelApp.controller("RezervacijeCtrl", function($scope, $http, $location){
	
	var url = "/rezervacije";
	
	$scope.rezervacije = [];
	
	$scope.search = {};
	$scope.search.nazivSobe = "";
	$scope.search.datumvremePocetak = "";
	$scope.search.datumvremeKraj = "";
		
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
	var getRezervacije = function(){
		
		var config = {params:{}};
		
		if($scope.search.nazivSobe != ""){
			config.params.nazivSobe = $scope.search.nazivSobe;
		}
		if($scope.search.datumvremePocetak != ""){
			config.params.datumvremePocetak = $scope.search.datumvremePocetak; 
		}
		if($scope.search.datumvremeKraj != ""){
			config.params.datumvremeKraj = $scope.search.datumvremeKraj; 
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.rezervacije = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Couldn't fetch rezervacije");
			}
		);
	}
	
	getRezervacije();
	
	
	$scope.goToAdd = function(){
		$location.path("/rezervacije/add" );
	}
	
	
	$scope.goToEdit = function(id){
		$location.path("/rezervacije/edit/" + id);
	}
	
	
	$scope.doDelete = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getRezervacije(); 
				alert("Succes!");	
			},
			function error(){
				alert("Couldn't delete the gost")
			}
		);
		
	}
		
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getRezervacije();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getRezervacije();
	}
	
	
	
	
});


hotelApp.controller("EditRezervacijaCtrl", function($scope, $http, $routeParams, $location){
	
	var rezervacijeUrl = "/rezervacije/" + $routeParams.id;
	var sobeUrl = "/sobe"
	var gostiUrl = "/gosti";

	
	$scope.rezervacija = {};
	$scope.rezervacija.sobaId = "";
	$scope.rezervacija.datetimeUlaz = "";
	$scope.rezervacija.datetimeIzlaz = "";
	$scope.rezervacija.gostId = "";
	
	$scope.sobe = [];
	$scope.gosti = [];
	
	var getSobe = function(){
		$http.get(sobeUrl + "/sve").then(
			function success(res){
				$scope.sobe = res.data;
				getGosti();
			},
			function error(){
				alert("Couldn't fetch gosti");
			}
	 	);
	}
	
	var getGosti = function(){
		$http.get(gostiUrl+ "/sve").then(
			function success(res){
				$scope.gosti = res.data;
				getRezervacije();
			},
			function error(){
				alert("Couldn't fetch gosti");
			}
	 	);
	}
	
	var getRezervacije = function(){
		$http.get(rezervacijeUrl).then(
			function success(res){
				$scope.rezervacija = res.data;
			},
			function error(){
				alert("Couldn't fetch Rezervacije.");
			}
		);
	}
	
	getSobe();
	
	
	$scope.doEdit = function(){
		$http.put(rezervacijeUrl, $scope.rezervacija).then(
			function success(){
				$location.path("/rezervacije");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});



hotelApp.controller("AddRezervacijaCtrl", function($scope, $http, $routeParams, $location){
	
	var rezervacijeUrl = "/rezervacije";
	var sobeUrl = "/sobe"
	var gostiUrl = "/gosti";
		
	$scope.newRezervacija = {};
	$scope.newRezervacija.sobaId = "";
	$scope.newRezervacija.datetimeUlaz = "";
	$scope.newRezervacija.datetimeIzlaz = "";	
	$scope.newRezervacija.gostId = "";
	
	$scope.sobe = [];
	$scope.gosti = [];

	var getSobe = function(){
		$http.get(sobeUrl + "/sve").then(
			function success(res){
				$scope.sobe = res.data;
				getGosti();
			},
			function error(){
				alert("Couldn't fetch gosti");
			}
	 	);
	}
	
	var getGosti = function(){
		$http.get(gostiUrl+ "/sve").then(
			function success(res){
				$scope.gosti = res.data;
				getRezervacije();
			},
			function error(){
				alert("Couldn't fetch gosti");
			}
	 	);
	}
	
	getSobe();
	
	
	
	$scope.doAdd = function(){
		$http.post(rezervacijeUrl, $scope.newRezervacija).then(
			function success(res){
				$location.path("/rezervacije");
			},
			function error(){
				alert("Couldn't fetch rezervacije.");
			}
		);
	}
	
	
});

