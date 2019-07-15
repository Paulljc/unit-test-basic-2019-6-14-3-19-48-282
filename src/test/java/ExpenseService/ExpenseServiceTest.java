package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static ExpenseService.Expense.ExpenseType.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.isEquals;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.INTERNAL, "project1");

        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        assertThat(expenseType).isEqualTo(INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL, "Project A");

        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        assertThat(expenseType).isEqualTo(EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL, "Project B");

        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        assertThat(expenseType).isEqualTo(EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        Project project = new Project(ProjectType.EXTERNAL, "OTHER_EXPENSE");

        ExpenseType expenseType = ExpenseService.getExpenseCodeByProjectTypeAndName(project);

        assertThat(expenseType).isEqualTo(OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "OTHER_EXPENSE");

        assertThat(catchThrowable(() -> ExpenseService.getExpenseCodeByProjectTypeAndName(project))).isInstanceOf(UnexpectedProjectTypeException.class);
    }
}