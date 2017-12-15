package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.AttendanceFact;
import ba.unsa.etf.bp.udat.repositories.AttendanceFactRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collection;

@Service
public class AttendanceFactService extends BaseService<AttendanceFact, AttendanceFactRepository> {
    public AttendanceFact save(AttendanceFact model) throws ServiceException {
        Collection<AttendanceFact> lista = repository.findAll();
        for(AttendanceFact af : lista )
        {
            if(af.getCourseDim().equals(model.getCourseDim()) &&
                    af.getDepartmentDim().equals(model.getDepartmentDim()) &&
                    af.getTimeDim().equals(model.getTimeDim()) &&
                    af.getLecturerDim().equals(model.getLecturerDim()) &&
                    af.getAttendance().equals(model.getAttendance())) //&&
               //BUG     af.getAttendancePercentage().equals(model.getAttendancePercentage())
                return null;
        }
        return super.save(model);
    }
    public BigDecimal attendanceByDepartmentCourseLecturer(Long dep, Long course, Long lecturer) throws ServiceException
    {
        return repository.attendanceByDepartmentCourseLecturer(dep, course, lecturer);
    }

}
