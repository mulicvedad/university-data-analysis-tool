package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.AttendanceFact;
import ba.unsa.etf.bp.udat.repositories.AttendanceFactRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AttendanceFactService extends BaseService<AttendanceFact, AttendanceFactRepository> {
    public AttendanceFact save(AttendanceFact model) throws ServiceException {
        Collection<AttendanceFact> lista = repository.findAll();
        for(AttendanceFact af : lista )
        {
            if(af.getCourse_dim().equals(model.getCourse_dim()) &&
                    af.getDepartment_dim().equals(model.getDepartment_dim()) &&
                    af.getTime_dim().equals(model.getTime_dim()) &&
                    af.getLecturer_dim().equals(model.getLecturer_dim()) &&
                    af.getAttendance().equals(model.getAttendance())) //&&
               //BUG     af.getAttendance_percentage().equals(model.getAttendance_percentage())
                return null;
        }
        return super.save(model);
    }

}
