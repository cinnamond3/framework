angular.module('login.service', []).factory('ServiceResponse', ['$http', function($http){
    return{
        load: function(callback){
            $http.get('../services/login/').success(function(data) {
                callback(data);
            });
        }
    };
}]);

angular.module('login.controller', [ 'login.service' ]).config(function($routeProvider) {
    $routeProvider.when('/login', {
        templateUrl: '../pages/login.html', 
    });
});