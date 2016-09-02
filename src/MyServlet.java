import general.TestChatBotMain;
import general.graph.theory.WikipediaInfoBoxModel2OldJune14_PERSONAL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by corpi on 2016-07-11.
 */
@WebServlet(name = "Servlet")
public class MyServlet extends HttpServlet {
    private static String question="hi question";
    private static String answer ="hi answer";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        answer = question;
        //.setAttribute("user", new User(userName, password)););
        try (PrintWriter writer = response.getWriter()) {

            writer.println(question);
        }
    }
/* JUst some old example ...
        String pass = req.getParameter("password");
        if ("edu4java".equals(user) && "eli4java".equals(pass)) {
            response(resp, "login ok");
        } else {
            response(resp, "invalid login");
        }
 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String query = req.getParameter("user");
        String filename = req.getParameter("password");

/*
            try {
                WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName =
                        "/WEB-INF/openNLP/";
                if (filename.equals("0"))
                    WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_july6.txt");
                else if (filename.equals("1"))
                    WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements");
                else if (filename.equals("2"))
                    WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_selection");
                System.out.println("I made it to the chatbot");
                String answer = TestChatBotMain.runChatbot(query);
                response(resp,"I made it to the chatbot: "+answer);
            } catch (Exception e) {
                e.printStackTrace();
                String st = "0____";
                st += e.getCause().getMessage() + "\n";
                for(StackTraceElement s :e.getStackTrace())
                    st += s.toString() + "\n";
                response(resp,st.toString()+"\n"+WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName);
            }
        */
        String rootPath=req.getServletContext().getRealPath("/WEB-INF/");
        File webInfFolder=new File(rootPath,"openNLP");
        WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName =
                webInfFolder.toPath().toAbsolutePath().toString() + "/";
        if (filename.equals("0"))
            WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_july6.txt");
        else if (filename.equals("1"))
            WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements");
        else if (filename.equals("2"))
            WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_selection");
/*
        response(resp,query+" yoyoyoy hello world "+ filename + WikipediaInfoBoxModel2OldJune14_PERSONAL.statementsDirectoryName
                + Client1.extractConcept("wolf"));

        File folder = new File(req.getRealPath("/WEB-INF/openNLP"));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        WikipediaInfoBoxModel2OldJune14_PERSONAL.changeStatementsFileName("statements_july6.txt");


*/try {
            String answer = TestChatBotMain.runChatbot(query);
            response(resp,answer);
        } catch (Exception e) {
            e.printStackTrace();
            response(resp,"EROR!!!");
        }

    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(msg);
    }
}

