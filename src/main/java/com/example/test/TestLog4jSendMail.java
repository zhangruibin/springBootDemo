/*
package com.example.test;


import com.example.entity.Book;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

*/
/**
 * Created by zhangrui on 2018/7/13.
 *//*

*/
/*
* @ClassName TestLog4jSendMail
*@Description TODO
*@Author zhangrui
*@Date 16:05 16:05
*@Version 
* *//*

public class TestLog4jSendMail {
    static Logger logger = Logger.getLogger(TestLog4jSendMail.class);
*/
/*    SMTPAppender appender = new SMTPAppender();

    public TestLog4jSendMail() {
        try {
            appender.setSMTPUsername("yin_jw123@126.com");
            appender.setSMTPPassword("yinjianwei");
            appender.setTo("yin_jw456@126.com");
            appender.setFrom("yin_jw123@126.com");
            // SMTP服务器 smtp.163.com
            appender.setSMTPHost("smtp.126.com");
            appender.setLocationInfo(true);
            appender.setSubject("Test Mail From Log4J");
            appender.setLayout(new DateLayout() {
            });
            appender.activateOptions();
            logger.addAppender(new Appender() {
                @Override
                public void addFilter(Filter filter) {

                }

                @Override
                public Filter getFilter() {
                    return null;
                }

                @Override
                public void clearFilters() {

                }

                @Override
                public void close() {

                }

                @Override
                public void doAppend(LoggingEvent loggingEvent) {

                }

                @Override
                public String getName() {
                    return null;
                }

                @Override
                public void setErrorHandler(ErrorHandler errorHandler) {

                }

                @Override
                public ErrorHandler getErrorHandler() {
                    return null;
                }

                @Override
                public void setLayout(Layout layout) {

                }

                @Override
                public Layout getLayout() {
                    return null;
                }

                @Override
                public void setName(String s) {

                }

                @Override
                public boolean requiresLayout() {
                    return false;
                }
            });
            logger.error("测试");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Printing ERROR Statements", e);
        }
    }*//*


    public static void main(String args[]) {
        int a  = 10;
        try {
            Book book = new Book();
            String[] split = book.getName().split(".");
            logger.info("do you recieve this message");
        } catch (Exception e) {
            //logger.error("do you recieve this message");
            e.printStackTrace();
            logger.info("do you recieve this message");
        }
    }

}
*/
