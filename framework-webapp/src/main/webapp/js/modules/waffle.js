angular.module('waffle', [ 'waffle.controller',
                           'systemParameter.controller', 
                           'login.controller' ]);

angular.module('waffle.controller', []).config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'pages/dashboard.html'
    }).otherwise({
        templateUrl: 'pages/404.html'
    });
});