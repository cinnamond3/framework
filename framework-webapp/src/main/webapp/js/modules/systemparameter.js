angular.module('systemParameter.service', []).factory('ServiceResponse', ['$http', function($http){
    return{
        load: function(callback){
            $http.post('../services/systemParameter/','{"request":null,"requestHeader":{"userid":null,"sessionid":null}}').success(function(data) {
                callback(data);
            });
        },
        loadByCode: function(callback, code){
            $http.post('../services/systemParameter/','{"request":{"code":"'+ code +'"},"requestHeader":{"userid":null,"sessionid":null}}').success(function(data) {
                callback(data);
            });
        }
    };
}]);

angular.module('systemParameter.controller', [ 'systemParameter.service' ]).config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: '../pages/dashboard.html', 
    }).when('/systemParameter', {
        templateUrl: '../pages/systemParameter/list.html', 
        controller:  'ListCtrl'
    }).when('/systemParameter/:code', {
        templateUrl: '../pages/systemParameter/detail.html', 
        controller:  'EditCtrl'
    }).otherwise({
        redirectTo : '/'
    });
});

function ListCtrl($scope, ServiceResponse) {
    ServiceResponse.load(function(data){
        $scope.serviceResponse = data;
    });
}

function EditCtrl($scope, $location, $routeParams, ServiceResponse) {
    ServiceResponse.loadByCode(function(data){
        $scope.systemParameter = data.results[0];
    },$routeParams.code);
}