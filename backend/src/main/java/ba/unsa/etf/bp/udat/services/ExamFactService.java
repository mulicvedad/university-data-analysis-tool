package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.ExamFact;
import ba.unsa.etf.bp.udat.repositories.ExamFactRepository;
import javafx.beans.binding.ObjectExpression;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ExamFactService extends BaseService<ExamFact, ExamFactRepository> {
    @Autowired
    private ExamFactRepository examFactRepository;

    public ExamFact save(ExamFact model) throws ServiceException {
        Collection<ExamFact> lista = repository.findAll();
        for(ExamFact ef : lista )
        {
            if(ef.getAcademicYearDim().equals(model.getAcademicYearDim()) &&
                    ef.getTimeDim().equals(model.getTimeDim()) &&
                    ef.getSemesterDim().equals(model.getSemesterDim()) &&
                    ef.getDepartmentDim().equals(model.getDepartmentDim()) &&
                    ef.getCourseDim().equals(model.getCourseDim()) &&
             //BUG       ef.getAveragePoints().equals(model.getAveragePoints())
                    ef.getTurnout().equals(model.getTurnout()))
                return null;
        }
        return super.save(model);
    }

    public List<Object[]> groupByAcademicYear() {
        return examFactRepository.filterByAllYears();
    }

    public List<Object[]> groupByDepartments() {
        return examFactRepository.filterByAllDepartments();
    }

    public List<Object[]> groupByCourses() {
        return examFactRepository.filterByAllCourses();
    }

}
