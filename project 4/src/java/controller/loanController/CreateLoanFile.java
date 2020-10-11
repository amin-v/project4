package controller.loanController;

import commons.ExceptionWrapper;
import commons.exceptions.BusinessException;
import model.entity.LoanFile;
import model.entity.LoanType;
import model.entity.RealPerson;
import model.service.LoanFileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loanFile/CreatLoanFile.do")
public class CreateLoanFile extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long customerId = Long.parseLong(req.getParameter("customerId"));
        String amount = req.getParameter("amount");
        String duration = req.getParameter("duration");
        long loanTypeId= Long.parseLong(req.getParameter("chosenLoanType"));
try {
    //long retrieveLonaTypeId= LoanFileService.validate(amount,duration,loanTypeId);
  //  RealPerson realPerson=new RealPerson(customerId);
    LoanFile loanFile=new LoanFile(amount,duration);
    LoanType loanType=new LoanType().setLoanTypeId(loanTypeId);
    RealPerson realPerson=new RealPerson().setRealPersonId(customerId);
    LoanFileService.getInstance().persist(loanType,realPerson,loanFile);
} catch (NumberFormatException | BusinessException e) {
    req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
    resp.sendError(700);
}

    }
}
