package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.StudentFact;
import ba.unsa.etf.bp.udat.repositories.StudentFactRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Edin on 12.11.2017..
 */
@Service
public class StudentFactService extends BaseService<StudentFact, StudentFactRepository>
{
    public Collection<StudentFact> filterByFreshmen() {
        return repository.filterByFreshmen();
    }
    public int filterByAcademicYear(int year_value) {
        return repository.filterByAcademicYear(year_value);
    }
    public int filterByAcademicYearAllStudents(int year_value) {
        return repository.filterByAcademicYearAllStudents(year_value);
    }
}
