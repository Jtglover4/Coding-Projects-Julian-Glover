

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SchoolFormServlet
 */
@WebServlet("/SchoolFormServlet")
public class SchoolFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String favcolor = request.getParameter("favcolor");
		String month = request.getParameter("month");
		String url = request.getParameter("url");
		String range = request.getParameter("range");
		String search = request.getParameter("search");
		String theme = request.getParameter("theme");
		String onion = request.getParameter("onion");
		String termsAndConditions = request.getParameter("agree");
		String brand = request.getParameter("brand");
		String reset = request.getParameter("reset");
		//printData(email, password, birthday, newStudent, college, major, color, termsAndConditions);
		
		response.setContentType("text/html");
		out.println("<body>\r\n"
				+ "	<form name=\"schoolform\" method=\"GET\" action=\"SchoolFormServlet\">\r\n"
				+ "		<!-- form name=\"schoolform\" method=\"GET\" action=\"SchoolForm.jsp\" -->\r\n"
				+ "		<table>\r\n"
				+ "      <h3>Customer Information</h3>\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Name</td>\r\n"
				+ "				<td><input type=\"text\" name=\"name\" placeholder=\"1234568\"\r\n"
				+ "					title=\"Name\" /></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Email</td>\r\n"
				+ "				<td><input type=\"email\" name=\"email\" required /></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><label for=\"favcolor\">Select your favorite color:</label></td>\r\n"
				+ "				<td><input type=\"color\" id=\"favcolor\" name=\"favcolor\"\r\n"
				+ "					value=\"#ff0000\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Favorite Month</td>\r\n"
				+ "				<td><input type=\"month\" id=\"start\" name=\"month\" min=\"2018-03\"\r\n"
				+ "					value=\"2018-05\"></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Favorite Website URL</td>\r\n"
				+ "				<td><input type=\"text\" name=\"url\" placeholder=\"1234568\"\r\n"
				+ "					title=\"Name\" /></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Things you want to search for the most:</td>\r\n"
				+ "				<td><input type=\"text\" name=\"search\" placeholder=\"1234568\"\r\n"
				+ "					title=\"Search\" /></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><h3>Please choose a theme for your burger</h3></td>\r\n"
				+ "				<td>\r\n"
				+ "				<fieldset>\r\n"
				+ "					<input type=\"radio\" name=\"theme\" value=\"beef\" checked />Beef\r\n"
				+ "					<input type=\"radio\" name=\"theme\" value=\"pork\" />Pork\r\n"
				+ "					<input type=\"radio\" name=\"theme\" value=\"chicken\" />Chicken\r\n"
				+ "					<input type=\"radio\" name=\"theme\" value=\"veggie\" />Veggie\r\n"
				+ "				</fieldset>\r\n"
				+ "				</td>\r\n"
				+ "				<td>\r\n"
				+ "			</tr>\r\n"
				+ "			\r\n"
				+ "			<tr>\r\n"
				+ "				<td>Onion Preference:</td>\r\n"
				+ "				<td><input type = \"checkbox\" name=\"onion\" checked> Onion?</td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><h3>How you would you like your burger cooked?</h3></td>\r\n"
				+ "				<td>\r\n"
				+ "				<input type=\"range\" name=\"range\" value=\"yes\" />Range\r\n"
				+ "				</td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><h3>Please enter BRAND</h3></td>\r\n"
				+ "				<td><select name=\"brand\">\r\n"
				+ "						<option value=\"MCD\">MCD Double Cheese</option>\r\n"
				+ "						<option value=\"INNOUT\">IN N OUT</option>\r\n"
				+ "						<option value=\"BC\">BURGER CRUSH</option>\r\n"
				+ "\r\n"
				+ "				</select></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><h3>Please enter other special requests</h3></td>\r\n"
				+ "				<td><textarea name=\"ta\"></textarea></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td colspan=\"2\"><input type=\"reset\" name=\"reset\"\r\n"
				+ "					value=\"Reset Form\" /> <input type=\"submit\" name=\"submit\"\r\n"
				+ "					value=\"Submit Form\" /></td>\r\n"
				+ "			</tr>\r\n"
				+ "		</table>\r\n"
				+ "	</form>\r\n"
				+ "</body>");
		out.println("<html>");
		out.println("<head><title>School Form Submission</title></head>");
		out.println("<h1>Submitted Data</h1>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Field</th><th>Value</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Name</td><td>" + name + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Email</td><td>" + email + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Color</td><td>" + favcolor + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Month</td><td>" + month + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>URL</td><td>" + url + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Search</td><td>" + search + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Takeout Box</td><td>" + brand + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<tr>");
		out.println("<td>Burger Style</td><td>" + theme + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Onion</td><td>" + onion + "</td>");
		out.println("</tr>");
		out.println("<td>Cooking Style</td><td>" + range + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Special Request</td><td>" + theme + "</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

//	private void printData(String email, String password, String birthday, String newStudent, 
//						   String college, String major, String color, String termsAndConditions) {
//		System.out.println("Email = " + email);
//		System.out.println("Password = " + password);
//		System.out.println("Birthday = " + birthday);
//		System.out.println("New Student = " + newStudent);
//		System.out.println("College = " + college);
//		System.out.println("Major = " + major);
//		System.out.println("Color = " + color);
//		System.out.println("Terms and Conditions = " + termsAndConditions);
//	}

}
