package reportEmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.testng.annotations.Test;

public class HTMLEmail {
		
	@Test
	public void EmailReport() throws EmailException, InterruptedException, IOException {
		
		 DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		 LocalDateTime now = LocalDateTime.now(); 
		   
	HtmlEmail email = new HtmlEmail();
	email.setDebug(true);
	email.setSmtpPort(587);
	email.setAuthenticator(new DefaultAuthenticator("support@personalbrandingcouncil.com", "Rockon$459"));
	email.setStartTLSRequired(true);
	//email.setSSLOnConnect(true);
	email.setHostName("mail.personalbrandingcouncil.com");
		
	
	/* Get the Path of the Overview.html from the test.reports Folders */
		Path dir = Paths.get("C:\\Users\\dev\\eclipse-workspace\\PersonalBranding\\test.reports\\");
		Path lastModified = dir;
    	if (Files.isDirectory(dir)) {
        Optional<Path> opPath = Files.list(dir)
          .filter(p -> Files.isDirectory(p))
          .sorted((p1, p2)-> Long.valueOf(p2.toFile().lastModified())
            .compareTo(p1.toFile().lastModified()))
          .findFirst();

        if (opPath.isPresent()){
             lastModified =  opPath.get();
        }
    	}
        Path indexHtmlPath = Paths.get(lastModified.toString(), "html", "overview.html");
        String Folder = lastModified.getFileName().toString();        
        
	/* Paste it on the Email Body */
        StringBuilder contentBuilder = new StringBuilder();
        try {
        BufferedReader in = new BufferedReader(new FileReader(indexHtmlPath.toString()));
        String str;
        while ((str = in.readLine()) != null) {
            contentBuilder.append(str);
        }
        in.close();
        } catch (IOException e) {
        } 
        String content = contentBuilder.toString();    
        if(content == null || content.isEmpty()){	
    	content = lastModified.toAbsolutePath().toString();
    	}
    
    /* CSS Design (Green and Red Color) for the HTML Report */
    		String html = content;
    		Document doc = Jsoup.parse(html);
    		Elements contents = doc.getElementsByClass("passRate");
    		for (Element content1 : contents) 
    		{
    		  String percentage = content1.text();
    		  if(percentage.contains("100"))
    		  {
    			content1.attr("style","background-color:#009900; color: #ffffff;");
    		  }
    		  else
    		  {
    			content1.attr("style","background-color:#FF0000; color: #ffffff;");
    		  }
    		}
    		
    /* Links to the Test Suite names */	
    		Elements contentLink = doc.getElementsByTag("a");
    		int count=0;
    		for (Element elem : contentLink)
    		{
    		count++;
    		if(count<=2)
    		continue;
    		elem.attr("href", "http://192.168.6.152/"+Folder+"/html/"+elem.attr("href"));
    		}   		  		
    		String htmlview = doc.toString();
    
    /* Email Subject and Body */
    		String emailContent = "Hello All, The below HTML is the Regression test suite results. For today's detailed result please do <a href=\"http://192.168.6.152/"+Folder+"/html/\">click here</a> and for the Previous day's Results <a href=\"http://192.168.6.152/\">click here</a> Thank you" +htmlview;           
    		email.setFrom("support@personalbrandingcouncil.com");
    		email.setSubject("Today's Automation Test Report "+date.format(now));
    		email.addTo("neeraj.chaudhary@tescra.com");
    		String[] emails = {"iftikhar@tescra.com", "vaibhav.jindal@tescra.com","shivji.bhagat@tescra.com", "anand.tiwari@tescra.com", "chirayu.anant@tescra.com", "anuj.gupta@tescra.com","apoorv.k@tescra.com", "Hemanthkumar.k@tescra.com", "Shashidhar.yadala@tescra.com"}; 	
    		email.addCc(emails );	
    		email.setHtmlMsg(emailContent);	
    		email.send();

}
}
