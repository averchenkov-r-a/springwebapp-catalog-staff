'use strict'

angular.module('myApp').factory('StaffService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/api/find/staff/';

    var factory = {
        fetchAllStaff: fetchAllStaff,
        fetchStaffById: fetchStaffById,
        fetchStaffByName: fetchStaffByName
    };

    return factory;

    function fetchAllStaff(page, count) {
        var param = 'page=' + page + '&count=' + count;
        return request('', param);
    }

    function fetchStaffById(id) {
        var param = 'id=' + id;
        return request('id/', param);
    }

    function fetchStaffByName(name, page, count) {
        var param = 'name=' + name + '&page=' + page + '&count=' + count;
        return request('name/', param);
    }
    
    function request(url, param) {
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: REST_SERVICE_URI + url,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: param
        }).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function (err) {
                console.error('Error response [' + REST_SERVICE_URI + url + ']: ' + err);
            }
        );
        return deferred.promise;
    }
}]);