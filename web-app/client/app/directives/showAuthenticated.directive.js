export default function ShowAuthenticated(sessionService) {
    'ngInject';

    return {
        restrict: 'A',
        link: (scope, element, attrs) => {
            scope.sessionService = sessionService;

            scope.$watch('sessionService.user.jwt', (val) => {
                if (val) {
                    if (attrs.showAuthenticated === 'true') {
                        element.css({ display: 'inherit'});
                    } 
                    else {
                        element.css({ display: 'none'});
                    }

                } else {
                    if (attrs.showAuthenticated === 'true') {
                        element.css({ display: 'none'});
                    } 
                    else {
                        element.css({ display: 'inherit'});
                    }
                }
            });

        }
    };
};