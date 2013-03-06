angular.module('systemparameter.data', [ 'ngResource' ]).factory('ServiceResponse', function($resource) {
    return $resource('../../services/systemParameters/retrieveAllActive');
});

angular.module('systemparameter', ['systemparameter.data']).config(function($routeProvider) {
    $routeProvider.when('/', {
        controller : ListCtrl,
        templateUrl : 'list.html'
    }).when('/edit/:id', {
        controller : EditCtrl,
        templateUrl : 'detail.html'
    }).otherwise({
        redirectTo : '/'
    });
});

function ListCtrl($scope, ServiceResponse) {
	$scope.systemParameters = ServiceResponse.get();
}

function EditCtrl($scope, $location, $routeParams, ServiceResponse) {
    var self = this;

    ServiceResponse.get({
        id : $routeParams.id
    }, function(project) {
        self.original = project;
        $scope.project = new ServiceResponse(self.original);
    });

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.project);
    }

    $scope.destroy = function() {
        self.original.destroy(function() {
            $location.path('/list');
        });
    };

    $scope.save = function() {
        $scope.project.update(function() {
            $location.path('/');
        });
    };
}