'use strict'

angular.module('myApp').controller('StaffController', ['$scope', 'StaffService', function($scope, StaffService) {
    var self = this;

    self.searchById = getStaffById;
    self.searchByName = getStaffByName;

    // массив сотрудников
    self.staffs = [];

    // массив страниц
    self.pages = [];

    self.page = 0;
    self.count = 2;
    self.maxpage = 1;
    self.method = getAllStaff;
    self.param = '';

    self.flipped = flipped;

    self.criteria = [{
        name: 'Всех',
        method: getAllStaff
    }, {
        name: 'По Id',
        method: getStaffById
    },{
        name: 'По имени',
        method: getStaffByName
    }];

    getAllStaff();

    function getAllStaff() {
        self.method = getAllStaff;
        self.param = '';
        StaffService.fetchAllStaff(self.page, self.count)
            .then(success, error);
    }

    function getStaffById(id) {
        self.method = getStaffById;
        self.param = id;
        StaffService.fetchStaffById(id)
            .then(success, error);
    }

    function getStaffByName(name) {
        self.method = getStaffByName;
        self.param = name;
        StaffService.fetchStaffByName(name, self.page, self.count)
            .then(success, error);
    }

    function search() {

    }

    function flipped(page) {
        console.info(page);
        if (page < 0) {
            page = 0;
        } else if (page >= self.maxpage) {
            page = self.maxpage - 1;
        }
        self.page = page;
        self.method(self.param);
    }

    function success(value) {
        console.error(value);
        self.maxpage = value.totalPages;
        for (var i = 0; i < value.totalPages; i++) {
            var p = {
                number: i + 1,
                select: (i == self.page)
            };

            self.pages[i] = p;
        }
        self.staffs = value.content;
    }

    function error(err) {
        console.error('Error response from service: ' + err);
    }
}]);