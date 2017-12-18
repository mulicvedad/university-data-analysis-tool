import BaseService from './baseService';

export default class AttendanceService extends BaseService {
    attendance(department, course, lecturer) {
        return super.get("attendance?dep=" + department + "&course=" + course + "&lecturer=" + lecturer);
    }
    attendancePercentage(department, course, lecturer) {
        return super.get("attendancePercentage?dep=" + department + "&course=" + course + "&lecturer=" + lecturer);
    }
}