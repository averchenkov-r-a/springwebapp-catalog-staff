'use strict'

angular.module('myApp').controller('StaffController', ['$scope', 'StaffService', function($scope, StaffService) {
    var self = this;

    // массив сотрудников
    self.staffs = [];

    self.user = {
        id: '',
        name: '',
        surname: '',
        patronymic: '',
        post: '',
        birthday: ''
    };

    // массив страниц
    self.pages = [];

    self.page = 0;
    self.count = 5;
    
    self.maxpage = 1;
    self.param = '';

    self.navigation = navigation;
    self.search = search;

    search();
    function search() {
        StaffService.fetchStaff(self.user, self.page, self.count)
            .then(success, error);
    }

    function navigation(page) {
        if (page < 0) {
            page = 0;
        } else if (page >= self.maxpage) {
            page = self.maxpage - 1;
        }
        self.page = page;
        search(self.param);
    }

    function success(value) {
        console.info(value);
        self.pages = [];
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