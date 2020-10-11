package controller.loanController;

import commons.ExceptionWrapper;
import model.entity.LoanType;
import model.entity.RealPerson;
import model.service.LoanTypeService;
import model.service.RealPersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/loanFile/FindLoanFile.do")
public class FindLoanFile extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String customerNumber = req.getParameter("customerNumber");
            List<RealPerson> realPersonList = RealPersonService.getInstance().findById(Long.valueOf(customerNumber));
            List<LoanType> loanTypeList = LoanTypeService.getInstance().findAll();
            String customerExists;
            if (realPersonList.size() > 0) {
                customerExists = "true";
                RealPerson realPerson = realPersonList.get(0);
                LoanType loanType = loanTypeList.get(0);
                req.setAttribute("customerExists", customerExists);
                req.setAttribute("realCustomer", realPerson);
                req.setAttribute("loanTypes", loanType);
                req.setAttribute("customerExists", customerExists);
                req.setAttribute("realCustomer", realPerson);
                req.setAttribute("loanTypes", loanTypeList);
                req.getRequestDispatcher("/loan/LoanFile.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}
