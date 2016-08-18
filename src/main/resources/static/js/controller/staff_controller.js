'use strict'

angular.module('myApp').controller('StaffController', ['$scope', 'StaffService', function($scope, StaffService) {
    var self = this;

    // массив сотрудников
    self.staffs = [];

    // объект критериев поиска
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

    // номер текущей страницы
    self.page = 0;
    // количесво элементов на станице
    self.count = 5;
    
    // общее количесво страниц
    self.maxpage = 1;

    self.navigation = navigation;
    self.search = search;

    search();
    
    function search() {
        self.page = 0;
        getStaff();
    }
    
    function getStaff() {
        StaffService
            .fetchStaffs(self.user, self.page, self.count)
            .then(success, error);
    }

    function navigation(page) {
        if (page < 0) {
            page = 0;
        } else if (page >= self.maxpage) {
            page = self.maxpage - 1;
        }
        if (self.page != page){
            self.page = page;
            getStaff();
        }
    }

    function success(value) {
        console.info(value);
        self.pages = [];
        self.maxpage = value.totalPages;
        for (var i = 0; i < self.maxpage; i++) {
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