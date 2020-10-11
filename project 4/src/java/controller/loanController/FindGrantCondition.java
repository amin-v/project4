package controller.loanController;

import commons.ExceptionWrapper;
import model.entity.GrantCondition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/grantCondition/find.do")
public class FindGrantCondition extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            long minDuration = Long.parseLong(req.getParameter("minDuration"));
            long maxDuration = Long.parseLong(req.getParameter("maxDuration"));
            BigDecimal maxAmount = BigDecimal.valueOf(Long.parseLong(req.getParameter("maxAmount")));
            BigDecimal minAmount = BigDecimal.valueOf(Long.parseLong(req.getParameter("minAmount")));
          //  GrantCondition grantCondition = new GrantCondition().setName(name).setGrantConditionId(id).setMinDuration(minDuration).setMaxDuration(maxDuration).setMinAmount(minAmount).setMaxAmount(maxAmount);
         GrantCondition grantCondition=new GrantCondition(id,name,minDuration,maxDuration,maxAmount,minAmount);
         req.setAttribute("grantCondition",grantCondition);
            req.getRequestDispatcher("/loan/GrantCondition.jsp").forward(req, resp);
          //  GrantConditionalService.getInstance().update(grantCondition);
         //   resp.sendRedirect("/grantCondition/find.do?id=" + grantCondition.getGrantConditionId() + "&name=" + grantCondition.getName() + "&minDuration=" + grantCondition.getMinDuration() + "&maxDuration=" + grantCondition.getMaxDuration() + "&maxAmount=" + grantCondition.getMaxAmount() + "&minAmount=" + grantCondition.getMinAmount());
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}

