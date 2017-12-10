import controller from './students.controller';
import template from './students.html';
import style from './students.css';

let studentsComponent = {
    restrict: 'E',
    bindings: {},
    template,
    style,
    controller
};
  
  export default studentsComponent;