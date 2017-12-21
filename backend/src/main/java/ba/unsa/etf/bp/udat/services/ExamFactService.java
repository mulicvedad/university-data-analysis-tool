package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.ExamFact;
import ba.unsa.etf.bp.udat.repositories.ExamFactRepository;
import javafx.beans.binding.ObjectExpression;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
public class ExamFactService extends BaseService<ExamFact, ExamFactRepository> {

    public ExamFact save(ExamFact model, boolean firstImport) throws ServiceException {
        if(firstImport == true)
            return super.save(model);
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
    public Integer turnoutByAcademicYearDepartmentCourse(Long ay, Long dep, Long course) throws ServiceException
    {
        return repository.turnoutByAcademicYearDepartmentCourse(ay, dep, course);
    }

    public BigDecimal averagePointsByAcademicYearDepartmentCourse(Long ay, Long dep, Long course) throws ServiceException
    {
        return repository.averagePointsByAcademicYearDepartmentCourse(ay, dep, course);
    }

    public List<Object[]> groupByAcademicYear() {
        return repository.filterByAllYears();
    }

    public List<Object[]> groupByDepartments() {
        return repository.filterByAllDepartments();
    }

    public List<Object[]> groupByCourses() {
        return repository.filterByAllCourses();
    }

}
