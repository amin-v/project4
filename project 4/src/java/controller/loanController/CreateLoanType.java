package controller.loanController;

import commons.ExceptionWrapper;
import model.entity.LoanType;
import model.service.LoanTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loanType/signUp.do")
public class CreateLoanType extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo : at least one of the condition should not be empty
        try {

            //   if (Objects.isNull(req.getParameter("loanName"))) {
            //     throw new BusinessException("your loan should have a name");
            // }

            //if (Objects.isNull(req.getParameter("interestRate"))) {
            //   throw new BusinessException("your loan should have interestedRate");
            //}

            String loanName = req.getParameter("loanName");
            String interestRate = req.getParameter("interestRate");
            LoanType loanType = new LoanType();

            loanType.setLoanName(loanName).setInterestRate(interestRate);
            LoanTypeService.validate(loanName.trim(), interestRate.trim());
            req.setAttribute("loanName", loanName);
            req.setAttribute("interestRate", interestRate);
            req.setAttribute("loanType", loanType);

            req.getRequestDispatcher("/loan/GrantCondition.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}