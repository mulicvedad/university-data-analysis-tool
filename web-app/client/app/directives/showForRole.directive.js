export default function ShowForRole(sessionService) {
    'ngInject';

    return {
        restrict: 'A',
        link: (scope, element, attrs) => {
            scope.sessionService = sessionService;

            scope.$watch('sessionService.currentUser.role', (val) => {
                if (attrs.showForRole.search(val) != -1) {
                    element.css({ display: 'inherit'});
                } 
                else {
                    element.css({ display: 'none'});
                }
            });

        }
    };
};