var autobuska = angular.module("autobuskaApp", ['ngRoute']);

autobuska.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/linije.html'
    }).when('/linije/edit/:id',{
        templateUrl: '/app/html/partial/edit-linija.html'
    }).when('/prevoznici/add/',{
        templateUrl: '/app/html/partial/Prevoznik.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

autobuska.controller("linijeCtrl", function($scope, $http, $location){

	var baseUrlPrevoznici = "/api/prevoznici";
    var baseUrlLinije = "/api/linije";
    

    $scope.promeniRezim = function(){
        $scope.rezimDodavanja = !$scope.rezimDodavanja;
    };
    $scope.rezimDodavanja = true;

    $scope.pageNum = 0;
    $scope.totalPages = 0;
    
    $scope.kolicinaKarti= "";
    
    $scope.prevoznici = [];
    $scope.linije = [];

    $scope.novaLinija = {};
    $scope.novaLinija.brojMesta = "";
    $scope.novaLinija.cena = "";
    $scope.novaLinija.destinacija = "";
    $scope.novaLinija.vremePolaska = "";
    $scope.novaLinija.prevoznikId = "";
    $scope.novaLinija.prevoznikNaziv = "";


    $scope.trazenaLinija = {};
    $scope.trazenaLinija.destinacija = "";
    $scope.trazenaLinija.prevoznikId = "";
    $scope.trazenaLinija.maxCena = "";

    var getLinije = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaLinija.destinacija != ""){
            config.params.destinacija = $scope.trazenaLinija.destinacija;
        }

        if($scope.trazenaLinija.prevoznikId != ""){
            config.params.prevoznikId = $scope.trazenaLinija.prevoznikId;
        }

        if($scope.trazenaLinija.maxCena != ""){
            config.params.maxCena = $scope.trazenaLinija.maxCena;
        }


        $http.get(baseUrlLinije, config)
            .then(function success(data){
                $scope.linije = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };

    var getPrevoznici = function(){

        $http.get(baseUrlPrevoznici)
            .then(function success(data){
                $scope.prevoznici = data.data;
            });

    };

    getPrevoznici();
    getLinije();
   

    $scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getLinije();
        }
    };

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getLinije();
        }
    };

    $scope.dodaj = function(){
        $http.post(baseUrlLinije, $scope.novaLinija)
            .then(function success(data){
            	getLinije();
                $scope.novaLinija = {};
                $scope.novaLinija.brojMesta = "";
                $scope.novaLinija.cena = "";
                $scope.novaLinija.destinacija = "";
                $scope.novaLinija.vremePolaska = "";
                $scope.novaLinija.prevoznikId = "";
            });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getLinije();
    }

    $scope.editLinija = function(id){
        $location.path('/linije/edit/' + id);
    }
    $scope.dodajPrevoznika = function(id){
        $location.path('/prevoznici/add/');
    }
    
    $scope.edit = function(id){
    	$http.put(baseUrlLinije + "/" + id, $scope.novaLinija).
    		then(
    			function uspeh(data){
       				$location.path("/linije");
    				$scope.novaLinija = {};
    				getLinije();
    			},
    			function error(data){
    				alert("Something went wrong!");
    			}
    		);
    }


    $scope.obrisi = function(id){
        $http.delete(baseUrlLinije + "/" + id).then(
            function success(data){
            	getLinije();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupovina = function(id){
    	$http.post(baseUrlLinije + "/" + id + "/kupovina").then(
    		function success(data){
    			
    			alert("Karta/e su uspesno kupljene.");
    			getLinije();
    		},
    		function error(data){
    			alert("Nije uspela kupovina karata.")
    		}
    	)
    }
});



autobuska.controller("editLinijaCtrl", function($scope, $http, $routeParams, $location){
	
	
    var baseUrlLinije = "/api/linije/";
    var baseUrlPrevoznici = "/api/prevoznici";
	var id = $routeParams.id;

	 $scope.prevoznici = [];


	    $scope.novaLinija = {};
	    $scope.novaLinija.brojMesta = "";
	    $scope.novaLinija.cena = "";
	    $scope.novaLinija.destinacija = "";
	    $scope.novaLinija.vremePolaska = "";
	    $scope.novaLinija.prevoznikId = "";
	    $scope.novaLinija.prevoznikNaziv = "";
	  
	
	var 	getLinija = function(){
		$http.get(baseUrlLinije + id).
			then(
				function success(data){
					$scope.novaLinija = data.data;
				},
				function error(data){
					
				}
			);	
	}
	
	getLinija();
		
	$scope.edit = function(){
		$http.put(baseUrlLinije + id, $scope.novaLinija).
			then(
				function uspeh(data){
					$location.path("/linije");
				},
				function error(data){
					alert("Something went wrong!");
				}
			);
	}

	
	var getPrevoznici = function(){
		
		$http.get(baseUrlPrevoznici).then(
			function success(data){
				$scope.prevoznici = data.data;
			},
			function error(data){
				alert("Neuspesno dobavljanje prevoznika.")
			}	
		);
	}
	
	getPrevoznici();
		

});



autobuska.controller("editPrevoznikCtrl", function($scope, $http, $location){
	
	  var baseUrlPrevoznici = "/api/prevoznici";
	   $scope.noviPrevoznik = {};
	    $scope.noviPrevoznik.naziv = "";
	    $scope.noviPrevoznik.adresa = "";
	    $scope.noviPrevoznik.pib = "";
	    
	    $scope.dodajPr = function(){
	        $http.post(baseUrlPrevoznici, $scope.noviPrevoznik)
	            .then(function success(data){
	            	$location.path("/");

	            
	            });
	    };
	    

});



