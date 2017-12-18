import BaseService from './baseService';

export default class ExamService extends BaseService {
    turnout(ay,dep) {
        return super.get("exams/turnout?ay=" + ay + "&dep=" + dep + "&course=-1");
    }

    averagePoints(ay,dep) {
        return super.get("exams/averagePoints?ay=" + ay + "&dep=" + dep + "&course=-1");
    }
}