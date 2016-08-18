'use strict'

angular.module('myApp').factory('StaffService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/api/find/staff/';

    var factory = {
        fetchStaff: fetchStaff
    };

    return factory;
    
    function fetchStaff(user, page, count) {
        var param = 'id=' + user.id + '&name=' + user.name + '&surname=' + user.surname +
                    '&patronymic=' + user.patronymic + '&post=' + user.post +
                    '&birthday=' + user.birthday + '&page=' + page + '&count=' + count;
        return request(param);
    }
    
    function request(param) {
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: REST_SERVICE_URI,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: param
        }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (err) {
                console.error('Error response [' + REST_SERVICE_URI + ']: ' + err);
            }
        );
        return deferred.promise;
    }
}]);