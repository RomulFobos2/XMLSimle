package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Worker {

    public static void createXML() throws Exception{
        Connection connection = ManageDB.getCurrentConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID_ART,NAME,CODE,USERNAME,GUID FROM WHS.ARTICLE");
        Articles articles = new Articles();
        while (resultSet.next()){
            articles.getArticleList().add(new Article(
                    resultSet.getString("ID_ART"),
                    resultSet.getString("NAME"),
                    resultSet.getString("CODE"),
                    resultSet.getString("USERNAME"),
                    resultSet.getString("GUID")
            ));
        }

        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Articles.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(articles, writer);

        FileWriter fileWriter = new FileWriter(new File("1.xml"));
        fileWriter.write(writer.getBuffer().toString());

        resultSet.close();
        statement.close();
        fileWriter.close();

        transform(new File("1.xml"), new File("src\\main\\resources\\template.xsl"), new File("2.xml"));
        transform(new File("2.xml"), new File("src\\main\\resources\\templateForCSV.xsl"), new File("3.txt"));
    }

    public static void transform(File in, File template, File out) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(template);
        Transformer transformer = factory.newTransformer(xslt);
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        Source xml = new StreamSource(in);
        transformer.transform(xml, new StreamResult(out));
    }
}
