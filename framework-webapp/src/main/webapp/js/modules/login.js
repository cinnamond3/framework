angular.module('login.service', []).factory('LoginResponse', ['$http', function($http){
    return{
        load: function(callback){
            $http.post('services/login/').success(function(data) {
                callback(data);
            });
        }
    };
}]);

angular.module('login.controller', [ 'login.service' ]).config(function($routeProvider) {
    $routeProvider.when('/login', {
        templateUrl: 'pages/login.html', 
    });
});